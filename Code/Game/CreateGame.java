package Code.Game;

import Code.Component.Character.Player.Player;
import Code.Component.Character.Player.PlayerFunctions;
import Code.Introduction.Welcome;
import Code.Component.Room.Room;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a full game and connects all the game's functionality
 * @since 1.4
 * @version 1.2
 * @author Khamilah Nixon
 */
public class CreateGame {

    /**
     * Creates Welcome object
     * @see Welcome
     */
    private Welcome welcome;

    /**
     * Creates BufferedReader object to store information about each room in the game
     */
    private BufferedReader roomsFile;

    /**
     * Creates a Scanner object to allow for user input
     */
    private final Scanner INPUT = new Scanner(System.in);

    /**
     * Creates an ArrayList of all the rooms in the game
     * @see Room
     */
    private ArrayList<Room>sector;

    /**
     * Creates a single player
     * @see Player
     */
    private Player player;

    /**
     * Creates a CreateGame object
     */
    public CreateGame() {
        try {
            welcome = new Welcome("----");
            roomsFile = new BufferedReader(new FileReader("UserManual/TextFiles/Rooms.txt"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Allows player to start the game if they confirm that they would like to begin the game
     * @see #playGame()
     * @see #loadGameEnvironment()
     * @see #loadStartingEnvironment()
     */
    public void startGame() {
        try {
            if (playGame()) {
                loadStartingEnvironment();
                roomsFile.close();
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Allows player confirm that they would like to begin the game
     * @see #playGame()
     */
    private boolean playGame() {
        System.out.println("Start " + welcome.getTITLE() + "\n" +
                ("-".repeat(welcome.getTITLE().length() + 5) + "\nYes or No"));
        String response = INPUT.nextLine();
        return response.equalsIgnoreCase("yes") || response.contains("yes".toLowerCase());
    }

    /**
     * Creates the initial game environment.  This method creates the entire map and all its connections, the
     * player, and loads all starting information on the welcome screen
     * @throws IOException - If the any game files do not exist
     * @see Welcome
     * @see Player
     * @see #printStartInfo()
     * @see #constructPlayer()
     * @see #createMap()
     */
    private void loadStartingEnvironment() throws IOException {
        printStartInfo();
        sector = new ArrayList<>();
        createMap();
        player = constructPlayer();
        loadGameEnvironment();
    }

    /**
     * Sets the game environment by initializing the first room and last room in the game.  These two rooms sets
     * the entire functionality of the game.  The player will begin in the first room, and the game will end once
     * the player completes all tasks in the last room.
     * @throws IOException - If the any game files do not exist
     * @see Welcome
     * @see Player
     * @see Room
     * @see #traverseGame(Room)
     */
    private void loadGameEnvironment() throws IOException {
        Room last = sector.get(sector.size() - 1);
        Room first = sector.get(0);
        first.setActive(true);
        traverseGame(last);
    }

    /**
     * Allows the player to traverse the map by verifying the player's commands that they type in the console
     * @throws IOException - If the any game files ({@link Room},{@link Welcome}) do not exist
     * @see Welcome
     * @see Player
     * @see Room
     * @see #printRoomData(Room)
     * @see #verifyCommands()
     */
    private void traverseGame(@NotNull Room last) throws IOException {
        while (!last.isVisited()) {
            boolean validCommand = false;
            for (Room m : sector) {
                if (m.isActive()) printRoomData(m);
            }
            while (!validCommand) {
                validCommand = verifyCommands();
            }
        }
    }

    /**
     * Creates a player object and initializes it to {@link #player}.  This method allows the user to select their
     * character's name.
     * @return - a player object
     */
    @Contract(" -> new")
    private @NotNull Player constructPlayer() {
        System.out.println("Enter your character's name: ");
        String name = INPUT.nextLine();
        int health = 100;
        return new Player(name,health,sector.get(0));
    }

    /**
     * Defines the game's map and the connections between room in the map.  All the {@link Room} are added to the
     * game's map of {@link #sector}
     * @throws IOException - If the {@link #roomsFile} does not exist.
     */
    private void createMap() throws IOException {

        //Creates all the rooms in the game
        Room lunarBase = new Room(roomsFile);
        Room asteroidBelt = new Room(roomsFile);
        Room solarStorm = new Room(roomsFile);
        Room caldoniI = new Room(roomsFile);
        Room caldoniVII = new Room(roomsFile);
        Room tilaniPatrol = new Room(roomsFile);
        Room tilaniPrime = new Room(roomsFile);

        //SET ALL ADJACENT SECTORS:     NORTH   ,   SOUTH   ,   EAST    ,   WEST
        tilaniPrime.setAdjacentRooms(null,null,null,null); //Player cannot go back
        tilaniPatrol.setAdjacentRooms(lunarBase,tilaniPrime,null,null);
        lunarBase.setAdjacentRooms(null,tilaniPatrol,solarStorm,asteroidBelt);
        asteroidBelt.setAdjacentRooms(caldoniI,null,lunarBase,null);
        caldoniI.setAdjacentRooms(null,asteroidBelt,caldoniVII,null);
        caldoniVII.setAdjacentRooms(null,solarStorm,null,caldoniI);
        solarStorm.setAdjacentRooms(caldoniVII,null,null,lunarBase);

        //adds the rooms to the map
        sector.add(lunarBase);
        sector.add(asteroidBelt);
        sector.add(solarStorm);
        sector.add(caldoniI);
        sector.add(caldoniVII);
        sector.add(tilaniPatrol);
        sector.add(tilaniPrime);
    }

    /**
     * Verifies if the player enters valid game commands.
     * A list of these commands are defined in <a href="../UserManual/TextFiles/CommandsList.txt">CommandsList</a>
     * @return - a boolean that verifies if the player entered a valid command
     * @throws IOException - If any of the game files do not exist
     * @see PlayerFunctions#help()
     * @see Room
     * @see Player
     */
    private boolean verifyCommands() throws IOException {
        String response = INPUT.nextLine();
        boolean help = verifyHelp(response);
        boolean info = verifyInformation(response);
        boolean movement = verifyMovement(response);
        boolean verify = movement || help || info;
        if(!verify) {
            System.out.println("Invalid command");
        }
        return verify;
    }

    /**
     * Verifies if the player entered the valid help command
     * @param response - The player's input
     * @return a boolean that defines if the input was a valid command
     * @throws IOException - If the {@link PlayerFunctions#help()} file exists
     */
    private boolean verifyHelp(@NotNull String response) throws IOException {
        boolean validCommand = false;
        if(response.equalsIgnoreCase("help")) {
            player.help();
            validCommand = true;
        }
        return validCommand;
    }

    /**
     * Verifies if the player entered a valid information command
     * @param response - The player's input
     * @return a boolean that defines if the input was a valid command
     * @see Room
     * @see Player
     */
    private boolean verifyInformation(@NotNull String response) {
        boolean validCommand = false;
        if(response.equalsIgnoreCase("location")) {
            System.out.println(player.getName() + " you are in sector " +
                    player.getCurrentRoom().getId() + ", " + player.getCurrentRoom().getName());
            validCommand = true;
        }
        if(response.equalsIgnoreCase("exits")) {
            System.out.println(player.getCurrentRoom().getExits());
            validCommand = true;
        }
        return validCommand;
    }

    /**
     * Verifies if the player entered a valid movement command
     * @param response - The player's input
     * @return a boolean that defines if the input was a valid command
     * @see Room
     * @see Player
     */
    private boolean verifyMovement(@NotNull String response) {
        boolean validCommand = false;
        if (response.equalsIgnoreCase("east")) {
            player.east();
            validCommand = true;
        }
        else if (response.equalsIgnoreCase("west")) {
            player.west();
            validCommand = true;
        }
        else if (response.equalsIgnoreCase("north")) {
            player.north();
            validCommand = true;
        }
        else if (response.equalsIgnoreCase("south")) {
            player.south();
            validCommand = true;
        }
        return validCommand;
    }

    /**
     * Prints the current room's description if it is currently active
     * @param room - The current Room
     * @see Room
     */
    private void printRoomData(@NotNull Room room) {
        System.out.println(room.roomData());
        room.setActive(true);
    }

    /**
     * Prints the current welcome screen information
     */
    private void printStartInfo() {
        System.out.println(welcome.getTITLE());
        System.out.println(welcome.getINSTRUCTIONS());
        System.out.println(welcome.getPROLOGUE());
    }

    @Override
    public String toString() {
        return "CreateGame{" +
                "welcome=" + welcome +
                ", roomsFile=" + roomsFile +
                ", in=" + INPUT +
                ", sector=" + sector +
                ", player=" + player +
                '}';
    }
}
