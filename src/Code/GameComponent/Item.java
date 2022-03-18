package Code.GameComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Creates an item
 * @since 1.5
 * @version 1.2
 * @author Khamilah Nixon
 */
public class Item extends GameComponent {

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

    /**
     * Creates a GameComponent object using a file and text color
     *
     * @param file      - The GameComponent's associated file
     * @throws IOException - If the file does not exist
     */
    public Item(BufferedReader file) throws IOException {
        super(file, "yellow");
        info = readDescription().replaceAll("\n","").split("",2);
        roomID = readRoomID();
        tradeOutputID = readTradeOutput();
    }

    public int getRoomID() {
        return roomID;
    }

    public int getTradeOutputID() {
        return tradeOutputID;
    }

    /**
     * This method will read the roomID from a text file and return the value
     * @return - Room ID
     */
    private int readRoomID() {
        return Integer.parseInt(info[0]);
    }

    /**
     * This method will read the trade output from a text file and return the value
     * @return - Trade Output ID
     */
    private int readTradeOutput() {
        return Integer.parseInt(info[1]);
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
