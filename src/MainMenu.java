public enum MainMenu {
    ALL_GAMES("All games"),
    GAMES_BY_GENRE("Games by Genre"),
    GAMES_BY_CONSOLE("Games by Console"),
    ADD_GAME("Add Game"),
    REMOVE_GAME("Remove Game"),
    CONSOLES("Consoles"),
    GENRES("Genres"),
    QUIT("Quit")
    ;

    private String description;

    MainMenu(String description){
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}

