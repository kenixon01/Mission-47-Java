package Code.Game;

import Code.Component.*;

import java.io.IOException;
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
    private Welcome welcome = new Welcome();

    /**
     * Creates a Scanner object
     */
    private Scanner input = new Scanner(System.in);


    /**
     * Creates a single player
     * @see Player
     */
    private Player player;

    /**
     * Creates a game map
     */
    private GameMap gameMap = new GameMap();

    /**
     * Allows player to start the game if they confirm that they would like to begin the game
     * @see #loadInitialEnvironment()
     */
    public void startGame() {
        System.out.println(welcome.getSplash().toString());
        String response = input.nextLine();
            if (response.equalsIgnoreCase("yes") || response.contains("yes".toLowerCase())) {
                try {
                    loadInitialEnvironment();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * Creates the initial game environment.  This method creates the entire map and all its connections, the
     * player, and loads all starting information on the welcome screen
     * @throws IOException - If the any game files do not exist
     * @see Welcome
     * @see Player
     * @see #constructPlayer()
     */
    private void loadInitialEnvironment() throws IOException {
        System.out.println(welcome.getInstructions());
        System.out.println(welcome.getPrologue());
        if(input.nextLine() != null){
            gameMap.createMap();
            player = constructPlayer();
            displayRoomData();
        }
    }

    /**
     * Creates a player object and initializes it to {@link #player}.  This method allows the user to select their
     * character's name.
     * @return - a player object
     */
    private Player constructPlayer() {
        System.out.println(welcome.getCharacterSelect().toString());
        String name = input.nextLine();
        int health = 100;
        return new Player(name,health, gameMap.getMap().firstKey(), gameMap);
    }

    /**
     * Displays current information about a room
     * @throws IOException - If the any game files ({@link Room},{@link Welcome}) do not exist
     * @see Welcome
     * @see Player
     * @see Room
     * @see #verifyCommands()
     */
    private void displayRoomData() throws IOException {
        while (true) {
            boolean validCommand = false;
            if(player.getCurrentRoom().isActive()) {
                System.out.println(player.getCurrentRoom().roomData());
                setPuzzleConditions();
                setTraderConditions();
                setMonsterConditions();
                player.getCurrentRoom().setActive(false);
            }
            while (!validCommand) {
                validCommand = verifyCommands();
            }
        }
    }

    /**
     * Prints the puzzle description if an existing puzzle (not null) is not solved.  If solved, the puzzle is set
     * to null
     */
    private void setPuzzleConditions() {
        Puzzle puzzle = player.getCurrentRoom().getPuzzle();
        if(puzzle != null) {
            if(!puzzle.isSolved()) {
                puzzle.setCurrentAttempt(0);
                System.out.println(puzzle.getDescription());
            }
            else {
                player.getCurrentRoom().setPuzzle(null);
            }
        }
    }

    private void setMonsterConditions() {
        Monster monster = player.getCurrentRoom().getMonster();
        if(monster != null) {
            if(monster.getHealth() > 0) {
                System.out.println(monster.getDescription());
            }
            else {
                player.getCurrentRoom().setMonster(null);
                System.out.println(monster.getDeathMsg());
            }
        }
    }

    /**
     * Prints the trader description and inventory if a trader exists
     */
    private void setTraderConditions() {
        Trader trader = player.getCurrentRoom().getTrader();
        if(trader != null) {
            System.out.println(trader.getDescription());
            player.getCurrentRoom().getTrader().getInventory().showInventory();
        }
    }

    /**
     * Verifies if the player enters valid game commands.
     * A list of these commands are defined in <a href="src/TextFiles/CommandsList.txt">CommandsList</a>
     * @return - a boolean that verifies if the player entered a valid command
     * @throws IOException - If any of the game files do not exist
     * @see Room
     * @see Player
     */
    private boolean verifyCommands() throws IOException {
        String[] response = (input.nextLine() + " ").split(" ",2);
        String info = response[1].trim();
        String command = response[0];
        boolean validCommand = true;
        switch (command) {
            case "location" -> System.out.println(player.getName() + " you are in sector " + player.getCurrentRoom().getId() + ", " + player.getCurrentRoom().getName());
            case "exits" -> System.out.println(player.getCurrentRoom().getExits());
            case "help" -> player.help();
            case "east", "north", "west", "south" -> player.move(command);
            case "inventory" -> player.inventory();
            case "explore" -> player.explore();
            case "drop" -> player.drop(player.getInventory().findItem(info));
            case "pickup" -> player.pickup(player.getCurrentRoom().getInventory().findItem(info));
            case "inspect" -> player.inspect(player.getInventory().findItem(info));
            case "drill" -> player.drill();
            case "hint" -> player.hint();
            case "solve" -> {
                if(player.getCurrentRoom().getPuzzle() != null) {
                    player.getCurrentRoom().getPuzzle().solve(info, gameMap);
                }
                else {
                    System.out.println("Nothing to solve.");
                }
            }
            case "trade" -> player.trade(info);
            default -> validCommand = false;
        }
        if(!validCommand) {
            System.out.println("Invalid command");
        }
        System.out.println();
        return validCommand;
    }

    @Override
    public String toString() {
        return "CreateGame{" +
                "welcome=" + welcome +
                ", INPUT=" + input +
                ", player=" + player +
                ", GAME_MAP=" + gameMap +
                '}';
    }
}
