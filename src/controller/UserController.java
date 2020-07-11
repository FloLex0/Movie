package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author student
 */
public class UserController {
    private Connection con;
    
    private UserController() {
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
        private static final UserController INSTANCE = new UserController();
    }
    
    public static UserController getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public void addUser(User user) {
        String sql = "insert into user value (null, ?, ?)";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean login(User user) {
        String sql = "select idUser "
                   + "  from user "
                   + " where username = ? "
                   + "   and password = ? ";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            try(ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }            
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
