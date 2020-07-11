package model;

/**
 *
 * @author student
 */
public class Film {
    private int idFilm;
    private String title;
    private String genre;
    private int duration;

    public Film() {
    }

    public Film(int idFilm, String title, String genre, int duration) {
        this.idFilm = idFilm;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return title + " / " + genre + " / " + duration + " min";
    } 
}
