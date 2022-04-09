package Code.Component;

import Code.Game.GameMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * The Player class represents the user's functionality.  All users in java
 * programs are implemented as an instance of this. Players are constant,
 * meaning the player's values cannot change:
 * <blockquote>
 *     <ul>
 *          <li>Name</li>
 *          <li>Text Color</li>
 *          <li>Highlight Color</li>
 *          <li>Health</li>
 *          <li>ID</li>
 *     </ul>
 *  </blockquote>
 *  The player class contains methods that pertain to user commands, such as moving
 *  north, south, east, and west, in relation to the current room.
 * @author Khamilah Nixon
 * @version 2.1
 * @since 1.0
 */

public final class Player extends Character {

    private int attackDamage;

    private double resistance;

    /**
     * A reference storing the player's current location
     */
    private Room currentRoom;

    /**
     * The player's inventory
     */
    private final Inventory storedItems = new Inventory();

    private final Inventory equippedItems = new Inventory();

    /**
     * The game map
     */
    private final GameMap map;

    /**
     * Creates a player object
     */
    public Player(String name, int health, Room currentRoom, GameMap map) {
        super("Captain " + name, "green", health);
        this.currentRoom = currentRoom;
        this.map = map;
        attackDamage = 50;
        resistance = 0;
    }

    public double getResistance() {
        return resistance;
    }

    public Inventory getEquippedItems() {
        return equippedItems;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Inventory getStoredItems() {
        return storedItems;
    }

    /**
     * If applicable, moves the player to the room north of the current room
     * @param direction - The direction in which the player would like to move
     */
    public void move(String direction) {
        Room room = null;
        switch (direction.toLowerCase()) {
            case "north" -> room = map.getMap().get(currentRoom).get(0);
            case "south" -> room = map.getMap().get(currentRoom).get(1);
            case "east" -> room = map.getMap().get(currentRoom).get(2);
            case "west" -> room = map.getMap().get(currentRoom).get(3);
        }
        if (room != null) {
            if (!room.isLocked()) {
                currentRoom.setVisited(true);
                currentRoom = room;
                currentRoom.setActive(true);
            } else {
                System.out.print("Unsatisfied requirements to proceed here.");
            }
        } else {
            System.out.print("The Alliance has not given you authorization to proceed here.");
        }
    }

    /**
     * Allows the player to add an item from the current room's inventory into their inventory if that item
     * is not null.
     * @param item - Desired item to add to inventory
     */
    public void pickup(Item item) {
        if(item != null) {
            Inventory.transferItem(currentRoom.getInventory(),storedItems,item);
            System.out.print(item.getNAME() + storedItems.getConsoleColors().textColor(
                    " has been picked up from the room and successfully added to the player inventory"
            ));
        }
        else {
            System.out.print("Item not found");
        }
    }

    /**
     * Allows the player to remove an item from their inventory into the current room's inventory, if that item is
     * not null
     * @param item - Desired item to remove from inventory
     */
    public void drop(Item item) {
        if(item != null) {
            Inventory.transferItem(storedItems,currentRoom.getInventory(),item);
            System.out.print(item.getNAME() + storedItems.getConsoleColors().colorString(
                    " has been dropped successfully from the player inventory and placed in ") +
                    currentRoom.getNAME()
            );
        }
        else {
            System.out.print("Item not found");
        }
    }

    /**
     * Allows player to view an item's description if that item is not null and is in their inventory
     * @param item - Desired item to inspect
     */
    public void inspect(Item item) {
        storedItems.inspect(item);
    }

    public void equip(Item item) {
        if(item != null && storedItems.findItem(item.getId()) != null) {
            Inventory.transferItem(storedItems,equippedItems,item);
                if(item.getDAMAGE() > 0) {
                    attackDamage /= item.getDAMAGE();
                }
                if (item.getRESISTANCE() > 0) {
                    resistance += item.getRESISTANCE();
                }
            System.out.print("You equipped " + item.getNAME());
        }
        else {
            System.out.print("Nothing to equip");
        }
    }

    public void unequip(Item item) {
        if(item != null && equippedItems.findItem(item.getId()) != null) {
            Inventory.transferItem(equippedItems,storedItems,item);
            if (item.getDAMAGE() > 0) {
                attackDamage *= item.getDAMAGE();
            }
            if (item.getRESISTANCE() > 0) {
                resistance -= (resistance - item.getRESISTANCE() < 0 ? 0 : item.getRESISTANCE());
            }
            System.out.print("You unequipped " + item.getNAME());
        }
        else {
            System.out.println("Nothing to unequip");
        }
    }

    public void stats() {
        System.out.println(getNAME() + "'s Statistics:");
        System.out.printf("%-14s%d\n%-14s%d\n%-14s%1.0f%s","Health:",getHealth(),
                "Attack Damage:",attackDamage,
                "Resistance:",resistance * 100,"%");
    }

    public void heal(Item item) {
        if(item != null && storedItems.findItem(item.getId()) != null && item.getHEALTH() > 0) {
            Inventory.transferItem(storedItems,equippedItems,item);
            gainHealth(item.getHEALTH());
            System.out.print("You used " + item.getNAME() + " to heal");
        }
        else {
            System.out.print("Item does not exist");
        }
    }

    public void examine() {
        Monster monster = currentRoom.getMonster();
        if(monster != null) {
            System.out.println(monster.getDescription());
            System.out.println("Health: " + monster.getHealth());
            System.out.println("Attack Damage: " + monster.getAttackDmg());
            System.out.print("Attack or Ignore");
        }
        else {
            System.out.print("There isn't a monster in " + currentRoom.getNAME());
        }
    }

    public void attack() {
        Monster monster = currentRoom.getMonster();
        if(monster != null) {
            monster.loseHealth(attackDamage);
        }
    }

    public void ignore() {
        Monster monster = currentRoom.getMonster();
        if(monster != null) {
            System.out.print("You ignored " + monster.getNAME());
            currentRoom.setMonster(null);
        }
        else {
            System.out.print("There isn't a monster to ignore in " + currentRoom.getNAME());
        }
    }

    /**
     * Allows a player to destroy asteroids for a puzzle if that puzzle is not null.
     */
    public void drill() {
        Puzzle puzzle = currentRoom.getPuzzle();
        int random = Math.abs(new Random().nextInt());
        if(puzzle != null) {
            System.out.println("Asteroid drilled.");
            Item item = puzzle.getInventory().findItem(puzzle.getITEM_ID());
            if (random % 2 == 0) {
                currentRoom.getInventory().add(item);
                System.out.print("You found " + item.getNAME());
            } else {
                System.out.print("You did not collect anything of value.");
            }
        }
        else {
            System.out.print("Nothing to drill");
        }
    }

    /**
     * Allows the player to access their inventory.
     * @see Inventory#showInventory()
     */
    public void inventory() {
            System.out.println(getNAME() + "'s Storage:");
            storedItems.showInventory();
    }

    /**
     * Allows the player to explore the current room.  This method will print information about the current room
     * including: the room name and ID, if a trader or puzzle exists in the room, and the items in the room's
     * inventory.  If a trader is in the room, the trade name will display.  If a puzzle exists, the puzzle name
     * will display.
     */
    public void explore() {
        System.out.println("Region: " + currentRoom.getNAME() + " (" + currentRoom.getId() + ") " +
                (currentRoom.getTrader() != null ? "\nTrader: " + currentRoom.getTrader().getNAME() : "") +
                (currentRoom.getPuzzle() != null && currentRoom.getPuzzle().isSolved() ?
                        "\nActive Puzzle: " + currentRoom.getPuzzle().getNAME() :
                        "") +
                (currentRoom.getMonster() != null ? "\nMonster: " + currentRoom.getMonster().getNAME() : "") +
                "\nList of Available Items");
        currentRoom.getInventory().showInventory();
    }

    /**
     * Allows a player to trade items with a trader as long as that item is not null and the trader contains
     * the respective output item for the trade
     * @param item - Desired item to trade
     */
    public void trade(String item) {
        if(currentRoom.getTrader() != null) {
            Item input = storedItems.findItem(item);
            Item output = currentRoom.getTrader().trade(input);
            storedItems.remove(storedItems.findItem(item));
            Inventory.transferItem(storedItems,currentRoom.getInventory(),output);
        }
    }

    /**
     * Allows a player to view the current puzzle's hint as long as that puzzle is not null and exists in the
     * current room
     */
    public void hint() {
        if(currentRoom.getPuzzle() != null) {
            System.out.print(currentRoom.getPuzzle().getHINT());
        }
        else {
            System.out.print("Cannot locate puzzle in " + currentRoom.getNAME());
        }
    }
    /**
     * Displays a list of all player commands with their descriptions
     * @throws IOException If the file does not exist
     */
    public void help() throws IOException {
        String filePath = "src/TextFiles/CommandsList.txt";
        String fileName = filePath.substring(filePath.lastIndexOf("/"));
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filePath));
            while(buffer.ready()) {
                System.out.println(buffer.readLine());
            }
            buffer.close();
        }
        catch (FileNotFoundException ex) {
            throw new FileNotFoundException(fileName + " not found at given file path: " + filePath);
        }
    }
}
