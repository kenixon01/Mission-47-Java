package Component;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Defines the functionality of a basic item.  This class defines each item by
 * reading item data from a text file
 * @since 1.5
 * @version 1.3
 * @author Khamilah Nixon
 */
public class Item extends Component {
    /**
     * ID of the room that the item is in
     */
    private final int ROOM_ID;

    /**
     * Item ID of the output during a trade
     */
    private final int TRADE_OUTPUT_ID;

    /**
     * Amount of health given to player upon equipping
     */
    private final int HEALTH;

    /**
     * Amount player attack damage increase upon equipping
     */
    private final double DAMAGE;

    /**
     * Amount player damage resistance increase upon equipping
     */
    private final double RESISTANCE;

    /**
     * Creates a Component object using a file and text color
     *
     * @param file The Component's associated file
     * @throws IOException If the file does not exist
     */
    public Item(BufferedReader file) throws IOException {
        super(file, "yellow");
        ROOM_ID = Integer.parseInt(getFile().readLine());
        TRADE_OUTPUT_ID = Integer.parseInt(getFile().readLine());
        HEALTH = Integer.parseInt(getFile().readLine());
        DAMAGE = Double.parseDouble(getFile().readLine());
        RESISTANCE = Double.parseDouble(getFile().readLine());
        getFile().readLine();
    }

    public int getROOM_ID() {
        return ROOM_ID;
    }

    public int getHEALTH() {
        return HEALTH;
    }

    public double getDAMAGE() {
        return DAMAGE;
    }

    public double getRESISTANCE() {
        return RESISTANCE;
    }

    public int getTRADE_OUTPUT_ID() {
        return TRADE_OUTPUT_ID;
    }
}
