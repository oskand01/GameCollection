package GameClasses;

import java.io.Serializable;

public class Game implements Serializable {

    private String gameName;
    private Genre genre;
    private Console console;

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    @Override
    public String toString() {
        return "{Game:" + gameName +
                ", genre: " + genre +
                ", console: " + console + "}";
    }
}
