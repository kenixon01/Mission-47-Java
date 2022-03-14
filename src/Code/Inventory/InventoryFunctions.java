package Code.Inventory;

import Code.Component.Item.Item;

public interface InventoryFunctions {

    /**
     * Allows objects to add an item to the inventory, {@link #items}, if the
     * given item is not null.  This method will not add any null value to the
     * inventory.  If the desired item exists in the inventory, the size of the
     * ArrayList holding the number of a particular item will increase by 1.
     * @param item - Desired item to add to the inventory
     */
    void add(Item item);

    /**
     * Allows objects to remove an item from the inventory, {@link #items}, if the
     * given item is not null.  This method will not remove any null value from the
     * inventory.
     * @param item - Desired item to remove from the inventory
     */
    void remove(Item item);

    /**
     * Prints the description of an item from the inventory, {@link #items}, if the
     * given item is not null.  This method will not print information for
     * any null value in the inventory.
     * @param item - Desired item to inspect
     */
    void inspect(Item item);

    /**
     * This method will print an ordered list of each item in the inventory with the count of
     * every item.  If the inventory is empty, the method will print, "No available items."
     */
    void showInventory();

    /**
     * Allows classes to find a particular item in the inventory based on a int
     * id of that item, given the item is not null and that item exists in the inventory.  Otherwise, this method returns
     * null.
     * @param id - Desired item id to find
     * @return - Located item
     */
    Item findItem(int id);

    /**
     * Allows classes to find a particular item in the inventory based on a String
     * description of that item, given the item is not null and that item exists in the inventory.  Otherwise, this method returns
     * null.
     * @param item - Desired item to find
     * @return - Located item
     */
    Item findItem(String item);
}
