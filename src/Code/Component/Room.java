package Code.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Room class defines the structure and functionality of each room in a game.  In each room, a player can
 * move north, south, east, and west if those rooms exists adjacently to the current room.  All rooms must have a
 * defined connection and list of all exists as well as have the ability to be active when the player is in the room.
 * @author Khamilah Nixon
 * @since 1.0
 * @version 2.1
 */
public class Room extends Component implements Comparable<Room> {

    /**
     * Room inventory
     */
    private final Inventory inventory = new Inventory();

    /**
     * A list that contains all rooms connected to this room
     */
    private final ArrayList<String> CONNECTION = new ArrayList<>();

    /**
     * A list that contains the ID's of all the items in the room
     */
    private final ArrayList<Integer> ITEM_ID;

    /**
     * List of possible exits in the room
     */
    private final String EXITS;

    /**
     * Puzzle in room
     */
    private Puzzle puzzle;

    /**
     * Trader in room
     */
    private Trader trader;

    /**
     * Monster in room
     */
    private Monster monster;

    /**
     * The value to determine if the player is inside the room
     */
    private boolean active;

    /**
     * The value to determine if the player previously visited the room
     */
    private boolean visited;

    /**
     * The value to determine if the room is locked
     */
    private boolean isLocked;

    /**
     * Creates a Room object
     * @param file - The file that holds all the information about each room
     * @throws IOException - If the file does not exist
     */
    public Room(BufferedReader file) throws IOException {
        super(file,"blue");
        isLocked = readLocked();
        EXITS = readExits();
        ITEM_ID = readItemID();
        getFile().readLine();
        active = true;
        visited = false;
    }

    public ArrayList<Integer> getITEM_ID() {
        return ITEM_ID;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getEXITS() {
        return getConsoleColors().textColor(EXITS);
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public ArrayList<String> getCONNECTION() {
        return CONNECTION;
    }

    public boolean isActive() {
        return active;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns a String holding the room's description and whether the player previously visited it.
     * This method allows applies the modified text color if applicable
     * @return a String of the room's description
     */
    public String roomData() {
        String text = getDescription() + (visited ? "\nYOU ALREADY VISITED THIS SECTOR\n" : "");
        return getConsoleColors().textColor(text);
    }

    /**
     * Reads a text file to determine if a room is locked or unlocked
     * @return - A boolean to determine if a room is locked
     * @throws IOException If the file does not exist
     */
    private boolean readLocked() throws IOException {
        String next = getFile().readLine();
        return next.equalsIgnoreCase("locked");
    }
    /**
     * Reads the list of exits from a file
     * @return a String holding the possible exits
     * @see #readDescription()
     */
//    private String readExits() throws IOException {
//        String next = getFile().readLine();
//        StringBuilder exits = new StringBuilder();
//        while(getFile().ready() && !next.equals("----")){
//            exits.append(next).append("\n");
//            if(next.contains(" ")) {
//                String[] description = next.split(" ",2);
//                System.out.println(description[1]);
//                connection.add(description[1]);
//            }
//            else {
//                connection.add("");
//            }
//            next = getFile().readLine();
//        }
//        return exits.toString();
//    }
//
    private String readExits() throws IOException {
        String next = getFile().readLine();
        StringBuilder exits = new StringBuilder();
        while (getFile().ready() && !next.equals("----")) {
            String[] description = next.split(" ", 2);
            if (description.length > 1) {
                exits.append(description[0]).append("\n");
                CONNECTION.add(description[1]);
            }
            else {
                CONNECTION.add("");
            }
            next = getFile().readLine();
        }
        return exits.toString();
    }

    /**
     * Reads the list of items ID's in a text file and stores it in an ArrayList
     * @return - ArrayList of items ID's in room
     * @throws IOException If the file does not exist or is empty
     */
    private ArrayList<Integer> readItemID() throws IOException {
        String line = getFile().readLine();
        if(!line.isBlank()) {
            String[] str = line.split(" ");
            ArrayList<Integer> intArray= new ArrayList<>();
            for (String s : str) {
                intArray.add(Integer.parseInt(s));
            }
            return intArray;
        }
        return null;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param room the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Room room) {
        return Integer.compare(this.getId(),room.getId());
    }
}
