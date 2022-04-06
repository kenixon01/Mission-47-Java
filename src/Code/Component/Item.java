package Code.Component;

import Code.Component.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Creates an item
 * @since 1.5
 * @version 1.2
 * @author Khamilah Nixon
 */
public class Item extends Component {

    /**
     * Specific information about an item
     */
    private String[] info;

    /**
     * ID of the room that the item is in
     */
    private int roomID;

    /**
     * Item ID of the output during a trade
     */
    private int tradeOutputID;

    private int health;

    private double damage;

    private double resistance;

    /**
     * Creates a Component object using a file and text color
     *
     * @param file      - The Component's associated file
     * @throws IOException - If the file does not exist
     */
    public Item(BufferedReader file) throws IOException {
        super(file, "yellow");
        roomID = Integer.parseInt(getFile().readLine());
        tradeOutputID = Integer.parseInt(getFile().readLine());
        health = Integer.parseInt(getFile().readLine());
        damage = Double.parseDouble(getFile().readLine()) / 100;
        resistance = Double.parseDouble(getFile().readLine()) / 100;
        getFile().readLine();
    }

    public int getRoomID() {
        return roomID;
    }

    public int getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public double getResistance() {
        return resistance;
    }

    public int getTradeOutputID() {
        return tradeOutputID;
    }


    @Override
    public String toString() {
        return "Item{" +
                ", info=" + Arrays.toString(info) +
                ", roomID=" + roomID +
                ", tradeOutputID=" + tradeOutputID +
                '}';
    }
}
