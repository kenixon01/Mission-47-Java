<<<<<<< HEAD:src/Code/GameComponent/Item.java
package Code.Component;

import Code.Component.Component;
=======
package Code.GameComponent;
>>>>>>> main:src/Code/Component/Item/Item.java

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Creates an item
 * @since 1.5
 * @version 1.3
 * @author Khamilah Nixon
 */
public class Item extends GameComponent {
    private int hp;

    private int damage;

    private int shieldHP;

    private boolean isEquippable;
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
     * Creates a GameComponent object using a file and text color
     *
     * @param file      - The GameComponent's associated file
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

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getShieldHP() {
        return shieldHP;
    }

    public boolean isEquippable() {
        return isEquippable;
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

<<<<<<< HEAD:src/Code/GameComponent/Item.java
    public double getResistance() {
        return resistance;
=======
    /**
     * This method will read the trade output from a text file and return the value
     * @return - Trade Output ID
     */
    private int readTradeOutput() throws IOException {
        return Integer.parseInt(info[0]);
>>>>>>> main:src/Code/Component/Item/Item.java
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
