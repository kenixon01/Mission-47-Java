package Game;

import Component.*;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Creates a full game and connects all the game's functionality
 * @since 1.0
 * @version 1.6
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
        System.out.println(welcome.getSplash());
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
        System.out.print(welcome.getPrologue());
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
        System.out.print(welcome.getCharacterSelect());
        String name = input.nextLine();
        int health = 500;
        return new Player(name,health, gameMap.getMap().firstKey(), gameMap);
    }

    private void setPuzzleConditions() {
        Puzzle puzzle = player.getCurrentRoom().getPuzzle();
        if(puzzle != null) {
            if(puzzle.isSolved()) {
                puzzle.setCurrentAttempt(0);
                System.out.print(puzzle.getDescription());
            }
            else {
                player.getCurrentRoom().setPuzzle(null);
            }
        }
    }

    private void setMonsterConditions() {
        Monster monster = player.getCurrentRoom().getMonster();
        if(monster != null) {
            System.out.print(monster.getNAME() + " is here...");
                monster.setAttackDmg(monster.getINITIAL_DAMAGE());
                if(new Random().nextInt() * 100 < monster.getTHRESHOLD()) {
                    monster.setAttackDmg(monster.getAttackDmg() * 2);
                }
        }
    }

    private void setTraderConditions() {
        Trader trader = player.getCurrentRoom().getTrader();
        if(trader != null) {
            System.out.print(trader.getDescription());
            player.getCurrentRoom().getTrader().getInventory().showInventory();
        }
    }

    private boolean isCommand(String command, String info) throws IOException {
        boolean validCommand = true;
        switch (command) {
            case "location" -> System.out.print(player.getNAME() + " you are in sector " + player.getCurrentRoom().getId() + ", " + player.getCurrentRoom().getNAME());
            case "exits" -> System.out.print(player.getCurrentRoom().getEXITS());
            case "help" -> player.help();
            case "east", "north", "west", "south" -> player.move(command);
            case "explore" -> player.explore();
            case "stats" -> player.stats();
            case "inventory" -> player.inventory();
            case "drop" -> player.drop(player.getStoredItems().findItem(info));
            case "pickup" -> player.pickup(player.getCurrentRoom().getInventory().findItem(info));
            case "inspect" -> player.inspect(player.getStoredItems().findItem(info));
            case "equip" -> player.equip(player.getStoredItems().findItem(info));
            case "unequip" -> player.unequip(player.getEquippedItems().findItem(info));
            case "heal" -> player.heal(player.getStoredItems().findItem(info));
            case "trade" -> player.trade(info);
            case "drill" -> player.drill();
            case "hint" -> player.hint();
            case "solve" -> {
                if(player.getCurrentRoom().getPuzzle() != null) {
                    player.getCurrentRoom().getPuzzle().solve(info, gameMap);
                }
                else {
                    System.out.print("Nothing to solve.");
                }
            }
            case "examine" -> player.examine();
            case "attack" -> {
                Monster monster = player.getCurrentRoom().getMonster();
                if(monster != null) {
                    while (true) {
                        System.out.println(player.getNAME() + "'s Health: " + player.getHealth());
                        System.out.println(monster.getNAME() + "'s Health: " + monster.getHealth());
                        System.out.println("Attack or Heal\n");
                        while (true) {
                            String[] response = (input.nextLine() + " ").split(" ", 2);
                            info = response[1].trim();
                            command = response[0];
                            if (command.equalsIgnoreCase("attack")) {
                                player.attack();
                                break;
                            }
                            else if (command.equalsIgnoreCase("heal")){
                                player.heal(player.getStoredItems().findItem(info));
                                System.out.println();
                                break;
                            }
                            else {
                                System.out.print("Invalid command");
                            }
                        }
                        if (monster.getHealth() <= 0) {
                            System.out.println("You defeated " + monster.getNAME());
                            if(!monster.getDEATH_MESSAGE().isEmpty()){
                                System.out.println(monster.getDEATH_MESSAGE());
                            };
                            player.getCurrentRoom().setMonster(null);
                            break;
                        }
                        monster.attack(player);
                        if (player.getHealth() <= 0) {
                            System.out.println(monster.getNAME() + " killed you");
                            System.out.println("Exit or Restart");
                            String response = input.nextLine();
                            if(response.equalsIgnoreCase("restart")) {
                                loadInitialEnvironment();
                                break;
                            }
                            else if(response.equalsIgnoreCase("exit")) {
                                System.exit(0);
                            }
                        }


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
        boolean validCommand = isCommand(command,info);
        if(!validCommand) {
            System.out.print("Invalid command");
        }
        return validCommand;
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
                System.out.println();
                validCommand = verifyCommands();
            }
            System.out.println();
        }
    }
}
