package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Film;

/**
 *
 * @author student
 */
public class FilmController {
    private Connection con;
    
    private FilmController() {
        String url = "jdbc:mysql://localhost/java1curs8";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
    
    private static class SingletonHolder {
        private static final FilmController INSTANCE = new FilmController();
    }
    
    public static FilmController getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public void addFilm(Film film) {
        String sql = "insert into film value (null, ?, ?, ?)";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getGenre());
            stmt.setInt(3, film.getDuration());
            stmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Film> getFilms() {
        List<Film> films = new ArrayList<>();
        String sql = "select * from film";
        try(PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                Film film = new Film();
                film.setTitle(rs.getString("title"));
                film.setGenre(rs.getString("genre"));
                film.setDuration(rs.getInt("duration"));
                films.add(film);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
}
