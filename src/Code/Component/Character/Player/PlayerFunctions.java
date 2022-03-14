package Code.Component.Character.Player;

import Code.Component.Item.Item;
import Code.Inventory.Inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The PlayerFunctions interface represents all the user's functionality.  All users in java
 * programs have this implementation.
 * @author Khamilah Nixon
 * @version 1.5
 * @since 1.0
 */

public interface PlayerFunctions {

    /**
     * If applicable, moves the player to the room north of the current room
     * @param direction - The direction in which the player would like to move
     */
    void move(String direction);


    /**
     * Allows the player to add an item from the current room's inventory into their inventory if that item
     * is not null.
     * @param item - Desired item to add to inventory
     */
    void pickup(Item item);

    /**
     * Allows the player to remove an item from their inventory into the current room's inventory, if that item is
     * not null
     * @param item - Desired item to remove from inventory
     */
    void drop(Item item);

    /**
     * Allows player to view an item's description if that item is not null and is in their inventory
     * @param item - Desired item to inspect
     */
    void inspect(Item item);

    /**
     * Allows the player to explore the current room.  This method will print information about the current room
     * including: the room name and ID, if a trader or puzzle exists in the room, and the items in the room's
     * inventory.  If a trader is in the room, the trade name will display.  If a puzzle exists, the puzzle name
     * will display.
     */
    void explore();

    /**
     * Allows a player to trade items with a trader as long as that item is not null and the trader contains
     * the respective output item for the trade
     * @param item - Desired item to trade
     */
    void trade(String item);

    /**
     * Allows a player to view the current puzzle's hint as long as that puzzle is not null and exists in the
     * current room
     */
    void hint();

    /**
     * Allows a player to destroy asteroids for a puzzle if that puzzle is not null.
     */
    void drill();

    /**
     * Allows the player to access their inventory.
     * @see Inventory#showInventory()
     */
    void inventory();
    void attack();
    void ignore();
    void equip(Item item);
    void unequip(Item item);
    void heal(Item item);
    void repair(Item item);

    /**
     * Displays a list of all player commands with their descriptions
     * @throws IOException If the file does not exist
     */
    default void help() throws IOException {
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
}
