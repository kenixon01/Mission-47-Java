package Code.Component;
import Code.Utilities.ConsoleColors;

import java.util.*;


/**
 * Creates a general inventory as well as the functionality for an inventory
 * @since 1.5
 * @version 1.2
 * @author Khamilah Nixon
 */
public class Inventory {

    /**
     * Stores items in inventory
     */
    private final HashMap<Integer, ArrayList<Item>> items = new HashMap<>(); //item id and item

    /**
     * Stores colors associated with text printed from this class
     */
    private ConsoleColors consoleColors = new ConsoleColors();

    /**
     * Creates constructor
     */
    public Inventory() {
        consoleColors.setTextColor("purple");
    }

    /**
     * Allows external classes to access the value of {@link #items}
     * @return - items in the inventory
     */
    public HashMap<Integer, ArrayList<Item>> getItems() {
        return items;
    }

    /**
     * Allows external classes to access the value of {@link #consoleColors}
     * @return - ConsoleColors object
     */
    public ConsoleColors getConsoleColors() {
        return consoleColors;
    }

    /**
     * Allows objects to add an item to the inventory, {@link #items}, if the
     * given item is not null.  This method will not add any null value to the
     * inventory.  If the desired item exists in the inventory, the size of the
     * ArrayList holding the number of a particular item will increase by 1.
     * @param item - Desired item to add to the inventory
     */
    public void add(Item item) {
        if(item != null) {
            if(!items.containsKey(item.getId())) {
                items.put(item.getId(), new ArrayList<>());
            }
            items.get(item.getId()).add(item);
        }
    }

    /**
     * Allows objects to remove an item from the inventory, {@link #items}, if the
     * given item is not null.  This method will not remove any null value from the
     * inventory.
     * @param item - Desired item to remove from the inventory
     */
    public void remove(Item item) {
        if(item != null) {
            if(items.containsKey(item.getId())) {
                items.get(item.getId()).remove(item);
            }
        }
    }

    /**
     * Prints the description of an item from the inventory, {@link #items}, if the
     * given item is not null.  This method will not print information for
     * any null value in the inventory.
     * @param item - Desired item to inspect
     */
    public void inspect(Item item) {
        if(item != null) {
            System.out.println(item.getDescription());
        }
        System.out.println("Description not available");
    }

    /**
     * Allows classes to find a particular item in the inventory based on a String
     * description of that item, given the item is not null and that item exists in the inventory.  Otherwise, this method returns
     * null.
     * @param item - Desired item to find
     * @return - Located item
     */
    public Item findItem(String item) {
        Iterator<Map.Entry<Integer, ArrayList<Item>>>iterator = iterator();
        while (iterator.hasNext()) {
            ArrayList<Item> itemEntry = iterator.next().getValue();
            if(itemEntry.size() > 0 && itemEntry.get(0).getName().toLowerCase().contains(item.toLowerCase())){
                return itemEntry.get(0);
            }
        }
        return null;
    }

    /**
     * Allows classes to find a particular item in the inventory based on a int
     * id of that item, given the item is not null and that item exists in the inventory.  Otherwise, this method returns
     * null.
     * @param id - Desired item id to find
     * @return - Located item
     */
    public Item findItem(int id) {
        return items.get(id).get(0);
    }

    /**
     * This method will print an ordered list of each item in the inventory with the count of
     * every item.  If the inventory is empty, the method will print, "No available items."
     */
    public void showInventory() {

        StringBuilder msg = new StringBuilder();
        if(items.isEmpty()) {
            msg = new StringBuilder("No available items.");
        }
        else {
            Iterator<Map.Entry<Integer, ArrayList<Item>>>iterator = iterator();
            int count = 1;
            while (iterator.hasNext()) {
                ArrayList<Item> itemEntry = iterator.next().getValue();
                if(itemEntry.size() > 0) {
                    msg.append(String.format("%d%-4s",count,"."));
                    msg.append(itemEntry.get(0).getName()).append(": ").append(itemEntry.size()).append("\n");
                    count++;
                }
            }
        }
        System.out.println(consoleColors.colorString(msg.isEmpty() ? "No visible items..." : String.valueOf(msg)));
    }

    /**
     * Returns a String of text with the appended ANSI color code to change the text color
     * @return a String with a varied color
     */
    public String textColor(String msg) {
        String txtColor = consoleColors.getTextColor();
        return txtColor + msg + txtColor;
    }

    /**
     * Creates an iterator to allow methods within the Inventory class to easily
     * iterate over the inventory items
     * @return - An iterator of the inventory items
     */
    private Iterator<Map.Entry<Integer, ArrayList<Item>>> iterator() {
        return items.entrySet().iterator();
    }
    @Override
    public String toString() {
        return "Inventory{" +
                "items=" + items +
                ", consoleColors=" + consoleColors +
                '}';
    }
}
