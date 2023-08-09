package sg.edu.rp.c346.id22016635.insertmoviesl11;

import java.io.Serializable;
import java.util.Calendar;

public class Movies implements Serializable {
    private int id;
    private String title;
    private String genre;
    private int year;
    private String rating;

    public Movies(int id, String title, String genre, int year, String rating){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }
    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
