import GameClasses.Console;
import GameClasses.Game;
import GameClasses.Genre;
import Helpers.FileUtil;

import java.util.*;


public class Program {

    HashMap<String, Game> games = new HashMap<>();
    HashMap<String, Genre> genres = new HashMap<>();
    HashMap<String, Console> consoles = new HashMap<>();
    FileUtil fileUtil = new FileUtil();


    public Program() {
        System.out.println("\nWelcome to your game collection.");
    }

    public void runProgram() {
        games = (HashMap<String, Game>) fileUtil.readObject("games");
        genres = (HashMap<String, Genre>) fileUtil.readObject("genres");
        consoles = (HashMap<String, Console>) fileUtil.readObject("consoles");
        initMainMenu(MainMenu.values());
    }

    public <T extends MainMenu> void initMainMenu(T[] menuItems) {
        Scanner scan = new Scanner(System.in);
        int menuInput = -1;
        System.out.println();
        int i = 1;
        for (T menuItem : menuItems) {
            System.out.println("[" + i + "] " + menuItem.getDescription());
            i++;
        }
        System.out.print("\nMake a choice: ");
        try {
            while (menuInput < 0 || menuInput > 8) {
                menuInput = scan.nextInt();
                mainMenuChoice(menuInput);
            }
        } catch (Exception e) {
            menuInput = -1;
        }
    }

    public void mainMenuChoice(int choice) {

        switch (choice) {
            case 1:
                printAllGames();
                returnToMainMenu();
                return;

            case 2:
                printGenres();
                setGenreChoice();
                returnToMainMenu();
                return;

            case 3:
                printConsoles();
                setConsoleChoice();
                returnToMainMenu();
                return;

            case 4:
                Game game = setGameToAdd();
                if (game != null) {
                    addNewGame(game, game.getConsole(), game.getGenre());
                } else {
                    initMainMenu(MainMenu.values());
                }
                return;

            case 5:
                printAllGames();
                setGameToRemove();
                return;

            case 6:
                printConsoles();
                returnToMainMenu();
                return;

            case 7:
                printGenres();
                returnToMainMenu();
                return;
            case 8:
                System.out.println("\nShine on you crazy diamond.");
        }
    }

    public void setGameToRemove() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nName of the game to remove: ");
        String removeChoice = scan.nextLine();

        if (games.get(removeChoice.toUpperCase()) != null) {
            Game game = games.get(removeChoice.toUpperCase());
            System.out.println("\n" + game.getGameName() + " [" + game.getGenre() + "] for " + game.getConsole() + " removed.\n");
            removeGame(game.getGameName());

        } else {
            System.out.println("No game named " + removeChoice.toUpperCase() + " found.");
        }
        initMainMenu(MainMenu.values());


    }

    public Game setGameToAdd() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nName of the game to add: ");
        try {
            String gameName = scan.nextLine();
            Game game = new Game(gameName);
            addConsoleToGame(game);
            addGenreToGame(game);
            return game;


        } catch (Exception e) {
            initMainMenu(MainMenu.values());
        }
        return null;


    }

    public void addGenreToGame(Game game) {
        Scanner scan = new Scanner(System.in);
        printGenres();
        System.out.print("\nThe genre of the game: ");
        try {
            String genreName = scan.nextLine();
            if (genres.get(genreName.toUpperCase()) != null) {
                game.setGenre(genres.get(genreName.toUpperCase()));
            } else {
                addGenreToGame(game);
            }
        } catch (Exception e) {
            initMainMenu(MainMenu.values());
        }
    }

    public void addConsoleToGame(Game game) {
        Scanner scan = new Scanner(System.in);
        printConsoles();
        System.out.print("\nConsole: ");
        try {
            String consoleName = scan.nextLine();
            if (consoles.get(consoleName.toUpperCase()) != null) {
                game.setConsole(consoles.get(consoleName.toUpperCase()));
            } else {
                System.out.println("No such console.\n");
                addConsoleToGame(game);
            }
        } catch (Exception e) {
            initMainMenu(MainMenu.values());
        }
    }

    public void setConsoleChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nConsole: ");
        String consoleChoice = scan.nextLine();
        printGamesByConsole(consoleChoice);
    }

    public void setGenreChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nGenre: ");
        String genreChoice = scan.nextLine();
        printGamesByGenre(genreChoice);
    }

    public void returnToMainMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n[0] to return: ");
        try {
            int menuChoice = scan.nextInt();
            if (menuChoice == 0) {
                initMainMenu(MainMenu.values());
            } else {
                returnToMainMenu();
            }
        } catch (Exception e) {
            returnToMainMenu();
        }
    }


    public void addConsole(Console console) {
        consoles.put(console.getConsoleName().toUpperCase(), console);
        fileUtil.writeObject(consoles, "consoles");
    }

    public void addGenre(String genre) {
        genres.put(genre.toUpperCase(), new Genre(genre));
        fileUtil.writeObject(genres, "genres");

    }

    public void addNewGame(Game game, Console console, Genre genre) {
        game.setConsole(console);
        game.setGenre(genre);

        games.put(game.getGameName().toUpperCase(), game);
        fileUtil.writeObject(games, "games");
        System.out.println("\n" + game.getGameName() + " for " + game.getConsole().getConsoleName() + " added.\n");
        initMainMenu(MainMenu.values());
    }

    public void printConsoles() {
        System.out.println("\n  ------- YOUR CONSOLES -------");
        Collection<Console> values = consoles.values();
        values.stream()
                .sorted(Comparator.comparing(Console::getConsoleReleaseDate))
                .forEach(c -> System.out.println("* " + c.getConsoleName().toUpperCase() + ", Released: " + c.getConsoleReleaseDate() + " by " + c.getConsoleManufacturer()));

    }

    public void printGenres() {
        System.out.println("\n  ------- GENRES -------");
        Collection<Genre> values = genres.values();
        values.stream()
                .sorted(Comparator.comparing(Genre::getGenreName))
                .forEach(g -> System.out.println("* " + g.getGenreName()));

    }

    public void printAllGames() {
        System.out.println("\n  ------- ALL GAMES -------");
        Collection<Game> values = games.values();
        values.stream()
                .sorted(Comparator.comparing(Game::getGameName))
                .forEach(g -> System.out.println("* " + g.getGameName() + " [" + g.getGenre().getGenreName() + "]"));
    }

    public void printGamesByGenre(String genre) {
        System.out.printf("\n  ------- [Genre] %s -------\n", genre.toUpperCase());
        Collection<Game> values = games.values();
        values.stream()
                .filter(g -> g.getGenre().getGenreName().equalsIgnoreCase(genre))
                .sorted(Comparator.comparing(Game::getGameName))
                .forEach(g -> System.out.println("* " + g.getGameName() + " [" + g.getConsole().getConsoleName() + "]"));
    }

    public void printGamesByConsole(String console) {
        System.out.printf("\n  ------- %s -------\n", console.toUpperCase());
        Collection<Game> values = games.values();
        values.stream()
                .filter(g -> g.getConsole().getConsoleName().equalsIgnoreCase(console))
                .sorted(Comparator.comparing(Game::getGameName))
                .forEach(g -> System.out.println("* " + g.getGameName() + " [" + g.getGenre().getGenreName() + "]"));
    }

    public void removeGame(String game) {
        games.remove(game.toUpperCase());
        fileUtil.writeObject(games, "games");
    }
}