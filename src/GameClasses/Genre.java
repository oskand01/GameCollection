package GameClasses;

import java.io.Serializable;
import java.util.List;

public class Genre implements Serializable {

    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return genreName;
    }
}
