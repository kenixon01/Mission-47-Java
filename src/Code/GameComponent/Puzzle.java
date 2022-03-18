package Code.GameComponent;

import Code.CreateGame.GameMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Creates a puzzle
 * @since 1.5
 * @version 1.0
 * @author Khamilah Nixon
 */
public class Puzzle extends GameComponent {

    /**
     * A value to determine if the puzzle is solved
     */
    private boolean isSolved;

    /**
     * A value to store the puzzle hint
     */
    private String hint;

    /**
     * A value to store the puzzle answer
     */
    private String answer;

    /**
     * A value to store the room ID that the puzzle is in
     */
    private int roomID;

    /**
     * A value to store the maximum attempts for a puzzle
     */
    private int maxAttempts;

    /**
     * A value to store the item ID of the puzzle reward upon completion
      */
    private int itemID;

    /**
     * A value to store the player's current attempt at the puzzle
     */
    private int currentAttempt;

    /**
     * A value to store the rooms the puzzle will unlock upon completion
     */
    private int[] unlockRoomID;

    /**
     * A reference to store the puzzle's inventory
     */
    private Inventory inventory = new Inventory();

    /**
     * Creates a GameComponent object using a file and text color
     *
     * @param file      - The GameComponent's associated file
     * @throws IOException - If the file does not exist
     */
    public Puzzle(BufferedReader file) throws IOException {
        super(file, "cyan");
        hint = readHint();
        answer = readAnswer();
        roomID = readRoomID();
        maxAttempts = readMaxAttempts();
        itemID = readItemID();
        unlockRoomID = readUnlockedRoomID();
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
        return isSolved;
    }

    public String getHint() {
        return hint;
    }

    public int getItemID() {
        return itemID;
    }

    public int getRoomID() {
        return roomID;
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
     * the current room and returns.
     * @param answer - Player's answer
     * @param map - Game map
     */
    public void solve(String answer, GameMap map) {
        if(!this.answer.isBlank() && !isSolved) {
            if(currentAttempt < maxAttempts) {
                if (answer.equalsIgnoreCase(this.answer)) {
                    Item item = map.getItemList().get(itemID);
                    Room room = map.getRoomList().get(item.getRoomID());
                    inventory.remove(item);
                    room.getInventory().add(item);
                    System.out.println(getConsoleColors().colorString("You solved the puzzle correctly!"));
                    System.out.println(item.getName() + getConsoleColors().colorString(" available in ") + room.getName());
                    isSolved = true;
                    if(unlockRoomID != null) {
                        for(int id : unlockRoomID) {
                            map.getRoomList().get(id).setLocked(false);
                            System.out.println("You gained authorization to enter an area.");
                        }
                    }
                }
                else {
                    currentAttempt++;
                    System.out.println("The answer you provided is wrong, you still have " +
                            (maxAttempts - currentAttempt) + " attempt(s)." + ((currentAttempt == maxAttempts) ?
                            "\nFailed to solve." : " Try one more time."));
                }
            }
            else {
                System.out.println("Puzzle " + getName() + " on cooldown.\nFailed to solve.");
            }
        }
        else {
            System.out.println("Nothing to solve.");
        }
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "isSolved=" + isSolved +
                ", hint='" + hint + '\'' +
                ", answer='" + answer + '\'' +
                ", roomID=" + roomID +
                ", maxAttempts=" + maxAttempts +
                ", itemID=" + itemID +
                ", currentAttempt=" + currentAttempt +
                ", unlockRoomID=" + Arrays.toString(unlockRoomID) +
                ", inventory=" + inventory +
                '}';
    }
}
