package Code.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Trader extends Character {
    private final Inventory inventory = new Inventory();
    private final int ROOM_ID;
    private final ArrayList<Integer> ITEM_ID;

    /**
     * Creates a Component object using a file and text color
     *
     * @param file      - The Component's associated file
     * @throws IOException - If the file does not exist
     */
    public Trader(BufferedReader file) throws IOException {
        super(file, "red");
        ROOM_ID = readRoomID();
        ITEM_ID = readItemID();
    }

    public ArrayList<Integer> getITEM_ID() {
        return ITEM_ID;
    }

    public int getROOM_ID() {
        return ROOM_ID;
    }

    public Inventory getInventory() {
        return inventory;
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
            Item output = inventory.findItem(item.getTRADE_OUTPUT_ID());
            System.out.println(getConsoleColors().textColor("You got yourself a deal."));
            System.out.print(output.getNAME() + " has been dropped.");
            return output;
        }
        System.out.print(getConsoleColors().textColor("Are you trying to cheat me?"));
        return null;
    }
}
