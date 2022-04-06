package Code.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Trader extends Character {

    private Inventory inventory = new Inventory();
    private int roomID;
    private ArrayList<Integer> itemID;
    /**
     * Creates a Component object using a file and text color
     *
     * @param file      - The Component's associated file
     * @throws IOException - If the file does not exist
     */
    public Trader(BufferedReader file) throws IOException {
        super(file, "red");
        roomID = readRoomID();
        itemID = readItemID();
    }

    public ArrayList<Integer> getItemID() {
        return itemID;
    }

    public int getRoomID() {
        return roomID;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private int readRoomID() throws IOException {
        return Integer.parseInt(getFile().readLine());
    }

    private ArrayList<Integer> readItemID() throws IOException {
        String line = getFile().readLine();
        if(!line.isBlank()) {
            String[] str = line.split(" ");
            ArrayList<Integer> intArray = new ArrayList<>();
            for (String s : str) {
                intArray.add(Integer.parseInt(s));
            }
            return intArray;
        }
        return null;
    }

    public Item trade(Item item) {
        if(item != null) {
            Item output = inventory.findItem(item.getTradeOutputID());
            System.out.println(getConsoleColors().textColor("You got yourself a deal."));
            System.out.println(output.getName() + " has been dropped.");
            return output;
        }
        System.out.println(getConsoleColors().textColor("Are you trying to cheat me?"));
        return null;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "inventory=" + inventory +
                ", roomID=" + roomID +
                '}';
    }
}
