<<<<<<< HEAD:src/Code/GameComponent/Player.java
package Code.Component;

import Code.Game.GameMap;
=======
package Code.GameComponent.Characters;

import Code.GameComponent.Item;
import Code.GameComponent.Puzzle;
import Code.GameComponent.Room;
import Code.CreateGame.GameMap;
import Code.GameComponent.Inventory;
>>>>>>> main:src/Code/GameComponent/Characters/Player.java

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
<<<<<<< HEAD:src/Code/GameComponent/Player.java

    private int attackDamage;

    private double resistance;
=======
>>>>>>> main:src/Code/GameComponent/Characters/Player.java

    /**
     * A reference storing the player's current location
     */
    private Room currentRoom;

    /**
     * The player's inventory
     */
    private Inventory storedItems = new Inventory();

    private Inventory equippedItems = new Inventory();

    private Inventory equippedItems = new Inventory();

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
        attackDamage = 60;
        resistance = 0;
    }

    public double getResistance() {
        return resistance;
    }

    public Inventory getEquippedItems() {
        return equippedItems;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public GameMap getMap() {
        return map;
    }

    public int getAttackDamage() {
        return attackDamage;
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
<<<<<<< HEAD:src/Code/GameComponent/Player.java
    public void move(String direction) {
=======
   public void move(String direction) {
>>>>>>> main:src/Code/GameComponent/Characters/Player.java
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
    public void pickup(Item item) {
        if(item != null) {
<<<<<<< HEAD:src/Code/GameComponent/Player.java
            Inventory.transferItem(currentRoom.getInventory(),storedItems,item);
            System.out.println(item.getName() + storedItems.getConsoleColors().textColor(" has been picked up from the room " +
=======
            currentRoom.getInventory().remove(item);
            inventory.add(item);
            System.out.println(item.getName() + inventory.getConsoleColors().colorString(" has been picked up from the room " +
>>>>>>> main:src/Code/GameComponent/Characters/Player.java
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
    public void drop(Item item) {
        if(item != null) {
            Inventory.transferItem(storedItems,currentRoom.getInventory(),item);
            System.out.println(item.getName() + storedItems.getConsoleColors().colorString(" has been dropped successfully from the player inventory and placed in ") + currentRoom.getName());
        }
        else {
            System.out.println("Item not found");
        }
    }

    /**
     * Allows player to view an item's description if that item is not null and is in their inventory
     * @param item - Desired item to inspect
     */
    public void inspect(Item item) {
        if(item != null) {
            storedItems.inspect(item);
        }
        else {
            System.out.println("Nothing to inspect");
        }
    }

    public void equip(Item item) {
<<<<<<< HEAD:src/Code/GameComponent/Player.java
        if(item != null && storedItems.findItem(item.getId()) != null) {
            Inventory.transferItem(storedItems,equippedItems,item);
                if(item.getDamage() > 0) {
                    attackDamage /= item.getDamage();
                }
                if (item.getResistance() > 0) {
                    if(resistance < 1) resistance = 1;
                    resistance /= item.getResistance();
                }
            System.out.println("You equipped " + item.getName());
=======
        if(item != null) {
            if(item.isEquippable()) {
                equippedItems.add(item);
                System.out.println("You equipped " + item.getName());
            }
            else {
                System.out.println("You cannot equip " + item.getName());
            }
        }
        else {
            System.out.println("Item not found");
>>>>>>> main:src/Code/GameComponent/Characters/Player.java
        }
    }

    public void unequip(Item item) {
        if(item != null && equippedItems.findItem(item.getId()) != null) {
<<<<<<< HEAD:src/Code/GameComponent/Player.java
            Inventory.transferItem(equippedItems,storedItems,item);
            if (item.getDamage() > 0) {
                attackDamage *= item.getDamage();
            }
            if (item.getResistance() > 0) {
                resistance *= item.getResistance();
                if(resistance <= 1) resistance = 0;
            }
            System.out.println("You unequipped " + item.getName());
        }
    }

    public void stats() {
        System.out.println(getName() + "'s Statistics:");
        System.out.printf("%-14s%d\n%-14s%d\n%-14s%1.0f%s","Health:",getHealth(),
                "Attack Damage:",attackDamage,
                "Resistance:",resistance,"%");
    }

    public void heal(Item item) {
        if(item != null && storedItems.findItem(item.getId()) != null && item.getHealth() > 0) {
            Inventory.transferItem(storedItems,equippedItems,item);
            gainHealth(item.getHealth());
            System.out.println("You used " + item.getName() + " to heal");
        }
    }

    public void examine() {
        Monster monster = currentRoom.getMonster();
        if(monster != null) {
                System.out.println(monster.getDescription());
                System.out.println("Health: " + monster.getHealth());
                System.out.println("Attack Damage: " + monster.getAttackDmg());
            System.out.println("Attack or Ignore");
        }
        else {
            System.out.println("There isn't a monster in " + currentRoom.getName());
=======
                equippedItems.remove(item);
                System.out.println("You unequipped " + item.getName());
        }
        else {
            System.out.println("Item not found");
        }
    }

    public void heal(Item item) {
        if(item != null && item.getHp() > 0) {
            if(getCurrentHealth() < getMAX_HEALTH()) {
                gainHealth(Math.min(getMAX_HEALTH() - getCurrentHealth(), item.getHp()));
                inventory.remove(item);
                System.out.println(item.getName() + " gave you " + item.getHp() + " HP");
            }
            else {
                System.out.println("Your HP is full");
            }
>>>>>>> main:src/Code/GameComponent/Characters/Player.java
        }
    }

    public void attack() {
        Monster monster = currentRoom.getMonster();
        if(monster != null) {
            monster.loseHealth(attackDamage);
        }
    }

<<<<<<< HEAD:src/Code/GameComponent/Player.java
    public void ignore() {
        Monster monster = currentRoom.getMonster();
        if(monster != null) {
            currentRoom.setMonster(null);
        }
        else {
            System.out.println("There isn't a monster to ignore in " + currentRoom.getName());
        }
=======
     public void ignore() {

>>>>>>> main:src/Code/GameComponent/Characters/Player.java
    }

    /**
     * Allows a player to destroy asteroids for a puzzle if that puzzle is not null.
     */
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
    public void inventory() {
        if(storedItems.getItems().isEmpty()) {
            System.out.println(storedItems.getConsoleColors().colorString("You didn't pickup any items yet"));
        }
        else {
            System.out.println(getName() + "'s Storage:");
            storedItems.showInventory();
        }
    }

    /**
     * Allows the player to explore the current room.  This method will print information about the current room
     * including: the room name and ID, if a trader or puzzle exists in the room, and the items in the room's
     * inventory.  If a trader is in the room, the trade name will display.  If a puzzle exists, the puzzle name
     * will display.
     */
    public void explore() {
        System.out.println("Region: " + currentRoom.getName() + " (" + currentRoom.getId() + ") " +
                (currentRoom.getTrader() != null ? "\nTrader: " + currentRoom.getTrader().getName() : "") +
                (currentRoom.getPuzzle() != null && !currentRoom.getPuzzle().isSolved() ?
                        "\nActive Puzzle: " + currentRoom.getPuzzle().getName() :
                        "") +
                (currentRoom.getMonster() != null ? "\nMonster: " + currentRoom.getMonster().getName() : "") +
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
    public void trade(String item) {
        if(currentRoom.getTrader() != null) {
            Item input = storedItems.findItem(item);
            Item output = currentRoom.getTrader().trade(input);
            Inventory.transferItem(storedItems,currentRoom.getInventory(),output);
        }
    }

    /**
     * Allows a player to view the current puzzle's hint as long as that puzzle is not null and exists in the
     * current room
     */
    public void hint() {
        if(currentRoom.getPuzzle() != null) {
            System.out.println(currentRoom.getPuzzle().getHint());
        }
        else {
            System.out.println("Cannot locate puzzle in " + currentRoom.getName());
        }
    }
    /**
     * Displays a list of all player commands with their descriptions
     * @throws IOException If the file does not exist
     */
    public void help() throws IOException {
        String filePath = "src/UserManual/TextFiles/CommandsList.txt";
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

    /**
     * Displays a list of all player commands with their descriptions
     * @throws IOException If the file does not exist
     */
    public void help() throws IOException {
        String filePath = "src/UserManual/TextFiles/CommandsList.txt";
        String fileName = filePath.substring(filePath.lastIndexOf("/"));
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filePath));
            while (buffer.ready()) {
                System.out.println(buffer.readLine());
            }
            buffer.close();
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException(fileName + " not found at given file path: " + filePath);
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "currentRoom=" + currentRoom +
                '}';
    }
}
