package GameClasses;

import java.io.Serializable;

public class Console implements Serializable
{

    private String consoleName;
    private String consoleReleaseDate;
    private String consoleManufacturer;


    public Console(String consoleName, String consoleReleaseDate, String consoleManufacturer) {
        this.consoleName = consoleName;
        this.consoleReleaseDate = consoleReleaseDate;
        this.consoleManufacturer = consoleManufacturer;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public String getConsoleReleaseDate() {
        return consoleReleaseDate;
    }

    public void setConsoleReleaseDate(String consoleReleaseDate) {
        this.consoleReleaseDate = consoleReleaseDate;
    }

    public String getConsoleManufacturer() {
        return consoleManufacturer;
    }

    public void setConsoleManufacturer(String consoleManufacturer) {
        this.consoleManufacturer = consoleManufacturer;
    }

    @Override
    public String toString() {
        return consoleName;
    }
}
