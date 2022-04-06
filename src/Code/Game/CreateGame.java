package Code.Game;

import Code.Component.*;

import java.io.IOException;
import java.util.Random;
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
            if(!gameMap.getMap().isEmpty()) {
                gameMap = new GameMap();
            }
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
        int health = 300;
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
            System.out.println(monster.getName() + " is here...");
                monster.setAttackDmg(monster.getInitialDamage());
                if(new Random().nextInt() * 100 < monster.getThreshold()) {
                    monster.setAttackDmg(monster.getAttackDmg() * 2);
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

    private boolean isRoomCommand(String command, String info) throws IOException {
        boolean validCommand = true;
        switch (command) {
            case "location" -> System.out.println(player.getName() + " you are in sector " + player.getCurrentRoom().getId() + ", " + player.getCurrentRoom().getName());
            case "exits" -> System.out.println(player.getCurrentRoom().getExits());
            case "help" -> player.help();
            case "east", "north", "west", "south" -> player.move(command);
            case "explore" -> player.explore();
            case "stats" -> player.stats();
            default -> validCommand = false;
        }
        return validCommand;
    }

    private boolean isInventoryCommand(String command, String info) throws IOException {
        boolean validCommand = true;
        switch (command) {
            case "inventory" -> player.inventory();
            case "drop" -> player.drop(player.getStoredItems().findItem(info));
            case "pickup" -> player.pickup(player.getCurrentRoom().getInventory().findItem(info));
            case "inspect" -> player.inspect(player.getStoredItems().findItem(info));
            case "equip" -> player.equip(player.getStoredItems().findItem(info));
            case "unequip" -> player.unequip(player.getEquippedItems().findItem(info));
            case "heal" -> player.heal(player.getStoredItems().findItem(info));
            default -> validCommand = false;
        }
        return validCommand;
    }

    private boolean isTraderCommand(String command, String info) throws IOException {
        boolean validCommand = true;
        if ("trade".equals(command)) {
            player.trade(info);
        } else {
            validCommand = false;
        }
        return validCommand;
    }

    private boolean isPuzzleCommand(String command, String info) throws IOException {
        boolean validCommand = true;
        switch (command) {
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
            default -> validCommand = false;
        }
        return validCommand;
    }

    private boolean isMonsterCommand(String command, String info) throws IOException {
        boolean validCommand = true;
        switch (command) {
            case "examine" -> player.examine();
            case "attack" -> {
                Monster monster = player.getCurrentRoom().getMonster();
//                while (monster != null) {
//                    while ()
//                    player.attack();
//                    monster.attack(player);
//                }
                if(monster != null) {
                    player.attack();
                    monster.attack(player);

                    String[] response = (input.nextLine() + " ").split(" ", 2);
                    info = response[1].trim();
                    command = response[0];
                    while (true) {
                        boolean isMonsterCommand = false;
                        while (!isMonsterCommand) {
                            boolean isInventoryCommand = isInventoryCommand(command, info);
                            isMonsterCommand = command.equalsIgnoreCase("attack");
                            if (!(isInventoryCommand || isMonsterCommand)) {
                                System.out.println("Invalid command");
                                response = (input.nextLine() + " ").split(" ", 2);
                                info = response[1].trim();
                                command = response[0];
                            }
                        }
                        player.attack();
                        monster.attack(player);

                        if (monster.getHealth() <= 0) {
                            System.out.println("You defeated " + monster.getName());
                            System.out.println(monster.getDeathMsg());
                            player.getCurrentRoom().setMonster(null);
                            break;
                        }
                        if (player.getHealth() <= 0) {
                            System.out.println(monster.getName() + " killed you");
                            loadInitialEnvironment();
                            break;
                        }

                        response = (input.nextLine() + " ").split(" ", 2);
                        info = response[1].trim();
                        command = response[0];
                    }
                }
            }
            case "ignore" -> player.ignore();
            default -> validCommand = false;
        }
        return validCommand;
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
        boolean validCommand = isInventoryCommand(command,info) || isRoomCommand(command,info) ||
                isMonsterCommand(command,info) || isPuzzleCommand(command,info) ||
                isTraderCommand(command,info);
        if(!validCommand) {
            System.out.print("Invalid command");
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
