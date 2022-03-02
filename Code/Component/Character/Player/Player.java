package Code.Component.Character.Player;

import Code.Component.Character.Character;
import Code.Component.Room.Room;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * The Player class represents the user's functionality.  All users in java
 * programs are implemented as an instance of this. Players are constant,
 * meaning the player's values cannot change:
 * <blockquote>
 *     <ul>
 *          <li>Name</li>
 *          <li>Text Color</li>
 *          <li>Highlight Color</li>
 *          <li>Health</li>
 *          <li>ID</li>
 *     </ul>
 *  </blockquote>
 *  The player class contains methods that pertain to user commands, such as moving
 *  north, south, east, and west, in relation to the current room.
 * @author Khamilah Nixon
 * @version 1.5
 * @since 1.0
 */

public final class Player extends Character implements PlayerFunctions {
    private Room currentRoom;

    public Player(String name, int health, Room currentRoom) {
        super(name,"green", health);
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * method: north()
     * If applicable, moves the player to the room north of the current room
     */
    @Override
    public void north() {
        Room north = currentRoom.getNorth();
        if(north != null) {
            currentRoom.setActive(false);
            currentRoom = north;
            System.out.println(north.roomData());
            north.setVisited(true);

        }
        else {
            System.out.println("The Alliance has not given you authorization to proceed here.");
        }
    }

    /**
     * method: south()
     * If applicable, moves the player to the room south of the current room
     */
    @Override
    public void south() {
        Room south = currentRoom.getSouth();
        if(south != null) {
            currentRoom.setActive(false);
            currentRoom = south;
            System.out.println(south.roomData());
            south.setVisited(true);
        }
        else {
            System.out.println("The Alliance has not given you authorization to proceed here.");
        }
    }

    /**
     * method: east()
     * If applicable, moves the player to the room east of the current room
     */
    @Override
    public void east() {
        Room east = currentRoom.getEast();
        if(east != null) {
            currentRoom.setActive(false);
            currentRoom = east;
            System.out.println(east.roomData());
            east.setVisited(true);
        }
        else {
            System.out.println("The Alliance has not given you authorization to proceed here.");
        }
    }

    /**
     * method: west()
     * If applicable, moves the player to the room west of the current room
     */
    @Override
    public void west() {
        Room west = currentRoom.getWest();
        if(west != null) {
            currentRoom.setActive(false);
            currentRoom = west;
            System.out.println(west.roomData());
            west.setVisited(true);
        }
        else {
            System.out.println("The Alliance has not given you authorization to proceed here.");
        }
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "Player{" +
                "currentRoom=" + currentRoom +
                '}';
    }
}
