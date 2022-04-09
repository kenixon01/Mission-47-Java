package Code.Component;

import Code.Game.GameMap;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Creates a puzzle
 * @since 1.5
 * @version 1.0
 * @author Khamilah Nixon
 */
public class Puzzle extends Component {

    /**
     * A value to store the puzzle hint
     */
    private final String HINT;

    /**
     * A value to store the puzzle answer
     */
    private final String ANSWER;

    /**
     * A value to store the room ID that the puzzle is in
     */
    private final int ROOM_ID;

    /**
     * A value to store the maximum attempts for a puzzle
     */
    private final int MAX_ATTEMPTS;

    /**
     * A value to store the item ID of the puzzle reward upon completion
      */
    private final int ITEM_ID;

    /**
     * A value to store the rooms the puzzle will unlock upon completion
     */
    private final int[] UNLOCK_ROOM_ID;

    /**
     * A reference to store the puzzle's inventory
     */
    private final Inventory inventory = new Inventory();

    /**
     * A value to store the player's current attempt at the puzzle
     */
    private int currentAttempt;

    /**
     * A value to determine if the puzzle is solved
     */
    private boolean isSolved;

    /**
     * Creates a Component object using a file and text color
     *
     * @param file      - The Component's associated file
     * @throws IOException - If the file does not exist
     */
    public Puzzle(BufferedReader file) throws IOException {
        super(file, "cyan");
        HINT = readHint();
        ANSWER = readAnswer();
        ROOM_ID = readRoomID();
        MAX_ATTEMPTS = readMaxAttempts();
        ITEM_ID = readItemID();
        UNLOCK_ROOM_ID = readUnlockedRoomID();
        getFile().readLine();
        currentAttempt = 1;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setCurrentAttempt(int currentAttempt) {
        this.currentAttempt = currentAttempt;
    }

    public boolean isSolved() {
        return !isSolved;
    }

    public String getHINT() {
        return HINT;
    }

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public int getROOM_ID() {
        return ROOM_ID;
    }

    /**
     * Reads the puzzle hint from a text file
     * @return - Puzzle hint
     * @throws IOException If the file does not exist or is empty
     */
    private String readHint() throws IOException{
        return getFile().readLine();
    }

    /**
     * Reads the puzzle answer from a text file
     * @return - Puzzle answer
     * @throws IOException If the file does not exist or is empty
     */
    private String readAnswer() throws IOException{
        return getFile().readLine();
    }

    /**
     * Reads the puzzle attempts from a text file
     * @return - Puzzle attempts
     * @throws IOException If the file does not exist or is empty
     */
    private int readMaxAttempts() throws IOException{
        return Integer.parseInt(getFile().readLine());
    }

    /**
     * Reads the puzzle reward from a text file
     * @return - Puzzle reward
     * @throws IOException If the file does not exist or is empty
     */
    private int readItemID() throws IOException {
        return Integer.parseInt(getFile().readLine());
    }

    /**
     * Reads the puzzle room from a text file
     * @return - Puzzle room
     * @throws IOException If the file does not exist or is empty
     */
    private int readRoomID() throws IOException {
        return Integer.parseInt(getFile().readLine());
    }

    /**
     * Reads the rooms the puzzle will unlock from a text file
     * @return - Unlocked Rooms
     * @throws IOException If the file does not exist or is empty
     */
    private int[] readUnlockedRoomID() throws IOException {
        String line = getFile().readLine();
        if(!line.isBlank()) {
            String[] str = line.split(" ");
            int[] intArray = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                intArray[i] = Integer.parseInt(str[i]);
            }
            return intArray;
        }
        return null;
    }

    /**
     * Allows the player to solve a puzzle.  If the player provides an incorrect answer, the current attempts will
     * increase until the player reaches the max attempts. Then, the puzzle will not reset until the player leaves
     * the current room and returns.  If the player answers in the puzzle correctly, then the following events
     * will occur:
     * <blockquote>
     *     <ol>
     *         <li>{@link #isSolved} is set to true</li>
     *         <li>An item will appear in a room</li>
     *         <li>The player receives a success message</li>
     *         <li>If the puzzle unlocks a room, then that room unlocks</li>
     *     </ol>
     * </blockquote>
     * @param answer - Player's answer
     * @param map - Game map
     */
    public void solve(String answer, GameMap map) {
        if(!this.ANSWER.isBlank() && !isSolved) {
            if(currentAttempt < MAX_ATTEMPTS) {
                if (answer.equalsIgnoreCase(this.ANSWER)) {
                    isSolved = true;
                    Item item = map.getItemList().get(ITEM_ID);
                    Room room = map.getRoomList().get(item.getROOM_ID());
                    Inventory.transferItem(inventory,room.getInventory(),item);
                    System.out.println(getConsoleColors().textColor("You solved the puzzle correctly!"));
                    System.out.print(item.getNAME() + getConsoleColors().textColor(" available in ") + room.getNAME());
                    if(UNLOCK_ROOM_ID != null) {
                        for(int id : UNLOCK_ROOM_ID) {
                            map.getRoomList().get(id).setLocked(false);
                            System.out.print("\nYou gained authorization to enter an area.");
                        }
                    }
                }
                else {
                    currentAttempt++;
                    System.out.print("The answer you provided is wrong, you still have " +
                            (MAX_ATTEMPTS - currentAttempt) + " attempt(s)." + ((currentAttempt == MAX_ATTEMPTS) ?
                            "\nFailed to solve." : " Try one more time."));
                }
            }
            else {
                System.out.print("Puzzle " + getNAME() + " on cooldown.\nFailed to solve.");
            }
        }
        else {
            System.out.print("Nothing to solve.");
        }
    }
}
