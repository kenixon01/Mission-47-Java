package Code.Component.Room;

import Code.Component.Component;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The Room class defines the structure and functionality of each room in a game.  In each room, a player can
 * move north, south, east, and west if those rooms exists adjacently to the current room.  All rooms must have a
 * defined connection and list of all exists as well as have the ability to be active when the player is in the room.
 * @author Khamilah Nixon
 * @since 1.0
 * @version 2.0
 */
public class Room extends Component {

    /**
     * List of possible exits in the room
     */
    private String exits;

    /**
     * The value to determine if the player is inside the room
     */
    private boolean active;

    /**
     * The value to determine if the player previously visited the room
     */
    private boolean visited;

    /**
     * A reference to a room that lies north of this room
     */
    private Room north;

    /**
     * A reference to a room that lies south of this room
     */
    private Room south;

    /**
     * A reference to a room that lies east of this room
     */
    private Room east;

    /**
     * A reference to a room that lies west of this room
     */
    private Room west;

    /**
     * Creates a Room object
     * @param file - The file that holds all the information about each room
     * @throws IOException - If the file does not exist
     */
    public Room(BufferedReader file) throws IOException {
        super(file,"purple");
        exits = readExits();
        active = false;
        visited = false;
    }

    /**
     * Returns all the possible exists to the room as well as the ID of those rooms
     * @return a String of the possible exists
     */
    public String getExits() {
        return exits;
    }

    /**
     * Returns a String holding the room's description and whether the player previously visited it.
     * This method allows applies the modified text color if applicable
     * @return a String of the room's description
     */
    public String roomData() {
        String text = getDescription() + "\n" + (visited ? "VISITED" : "NOT VISITED");
        return getConsoleColors().colorString(text);
    }

    /**
     * Sets the initial value of each adjacent room
     * @param north - The room north of this room
     * @param south - The room south of this room
     * @param east - The room east of this room
     * @param west - The room west of this room
     */
    public void setAdjacentRooms(Room north, Room south, Room east, Room west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    /**
     * Returns the room that is north of this room
     * @return a Room reference to the room north of this room
     */
    public Room getNorth() {
        return north;
    }

    /**
     * Returns the room that is south of this room
     * @return a Room reference to the room south of this room
     */
    public Room getSouth() {
        return south;
    }

    /**
     * Returns the room that is east of this room
     * @return a Room reference to the room east this room
     */
    public Room getEast() {
        return east;
    }

    /**
     * Returns the room that is west of this room
     * @return a Room reference to the room west of this room
     */
    public Room getWest() {
        return west;
    }

    /**
     * Returns whether the player is inside this room
     * @return a boolean to determine if the room is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Determines the room's activation status
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns whether the player previously visit this room
     * @return a boolean determining if the player visited the room
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Determines the room's visitation status
     * @param visited - The room's new visitation status
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Reads the list of exits from a file
     * @return a String holding the possible exits
     * @see #readDescription()
     */
    private String readExits() {
        String description = "";
        try {
            description = readDescription();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return description;
    }

    @Override
    public String toString() {
        return "Room{" +
                "exits='" + exits + '\'' +
                ", active=" + active +
                ", visited=" + visited +
                ", north=" + north +
                ", south=" + south +
                ", east=" + east +
                ", west=" + west +
                '}';
    }
}
