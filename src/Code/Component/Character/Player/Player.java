package Code.Component.Character.Player;

import Code.Component.Character.Character;
import Code.Component.Item.Item;
import Code.Component.Puzzle.Puzzle;
import Code.Component.Room.Room;
import Code.Game.GameMap;
import Code.Inventory.Inventory;

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
 * @version 2.0
 * @since 1.0
 */

public final class Player extends Character implements PlayerFunctions {

    /**
     * A reference storing the player's current location
     */
    private Room currentRoom;

    /**
     * The player's inventory
     */
    private Inventory inventory = new Inventory();

    /**
     * The game map
     */
    private GameMap map;

    /**
     * Creates a player object
     */
    public Player(String name, int health, Room currentRoom, GameMap map) {
        super("Captain " + name, "green", health);
        this.currentRoom = currentRoom;
        this.map = map;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * If applicable, moves the player to the room north of the current room
     * @param direction - The direction in which the player would like to move
     */
    @Override
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
                System.out.println("Unsatisfied requirements to proceed here.");
            }
        } else {
            System.out.println("The Alliance has not given you authorization to proceed here.");
        }
    }

    /**
     * Allows the player to add an item from the current room's inventory into their inventory if that item
     * is not null.
     * @param item - Desired item to add to inventory
     */
    @Override
    public void pickup(Item item) {
        if(item != null) {
            currentRoom.getInventory().remove(item);
            inventory.add(item);
            System.out.println(item.getName() + inventory.getConsoleColors().textColor(" has been picked up from the room " +
                    "and " + "successfully " + "added to the player inventory"));
        }
        else {
            System.out.println("Item not found");
        }
    }

    /**
     * Allows the player to remove an item from their inventory into the current room's inventory, if that item is
     * not null
     * @param item - Desired item to remove from inventory
     */
    @Override
    public void drop(Item item) {
        if(item != null) {
            currentRoom.getInventory().add(item);
            inventory.remove(item);
            System.out.println(item.getName() + inventory.getConsoleColors().colorString(" has been dropped successfully from the player inventory and placed in ") + currentRoom.getName());
        }
        else {
            System.out.println("Item not found");
        }
    }

    /**
     * Allows player to view an item's description if that item is not null and is in their inventory
     * @param item - Desired item to inspect
     */
    @Override
    public void inspect(Item item) {
        if(item != null) {
            inventory.inspect(item);
        }
        else {
            System.out.println("Nothing to inspect");
        }
    }

    @Override
    public void equip(Item item) {

    }

    @Override
    public void unequip(Item item) {

    }

    @Override
    public void heal(Item item) {

    }

    @Override
    public void repair(Item item) {

    }

    @Override
    public void attack() {

    }

    @Override
    public void ignore() {

    }

    /**
     * Allows a player to destroy asteroids for a puzzle if that puzzle is not null.
     */
    @Override
    public void drill() {
        Puzzle puzzle = currentRoom.getPuzzle();
        int random = Math.abs(new Random().nextInt());
        if(puzzle != null) {
            System.out.println("Asteroid drilled.");
            Item item = puzzle.getInventory().findItem(puzzle.getItemID());
            if (random % 2 == 0) {
                currentRoom.getInventory().add(item);
                System.out.println("You found " + item.getName());
            } else {
                System.out.println("You did not collect anything of value.");
            }
        }
        else {
            System.out.println("Nothing to drill");
        }
    }

    /**
     * Allows the player to access their inventory.
     * @see Inventory#showInventory()
     */
    @Override
    public void inventory() {
        if(inventory.getItems().isEmpty()) {
            System.out.println(inventory.getConsoleColors().colorString("You didn't pickup any items yet"));
        }
        else {
            System.out.println(getName() + "'s Storage:");
            inventory.showInventory();
        }
    }

    /**
     * Allows the player to explore the current room.  This method will print information about the current room
     * including: the room name and ID, if a trader or puzzle exists in the room, and the items in the room's
     * inventory.  If a trader is in the room, the trade name will display.  If a puzzle exists, the puzzle name
     * will display.
     */
    @Override
    public void explore() {
        System.out.println("Region: " + currentRoom.getName() + " (" + currentRoom.getId() + ") " +
                (currentRoom.getTrader() != null ? "\nTrader: " + currentRoom.getTrader().getName() : "") +
                (currentRoom.getPuzzle() != null && !currentRoom.getPuzzle().isSolved() ?
                        "\nActive Puzzle: " + currentRoom.getPuzzle().getName() :
                        "") +
                "\nList of Available Items");
        Inventory roomInventory = currentRoom.getInventory();
        if(roomInventory.getItems().isEmpty()) {
            System.out.println(roomInventory.getConsoleColors().colorString("No items available..."));
        }
        else {
            roomInventory.showInventory();
        }
    }

    /**
     * Allows a player to trade items with a trader as long as that item is not null and the trader contains
     * the respective output item for the trade
     * @param item - Desired item to trade
     */
    @Override
    public void trade(String item) {
        if(currentRoom.getTrader() != null) {
            Item input = inventory.findItem(item);
            Item output = currentRoom.getTrader().trade(input);
            inventory.remove(input);
            currentRoom.getInventory().add(output);
        }
    }

    /**
     * Allows a player to view the current puzzle's hint as long as that puzzle is not null and exists in the
     * current room
     */
    @Override
    public void hint() {
        if(currentRoom.getPuzzle() != null) {
            System.out.println(currentRoom.getPuzzle().getHint());
        }
        else {
            System.out.println("Cannot locate puzzle in " + currentRoom.getName());
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "currentRoom=" + currentRoom +
                '}';
    }
}
