package Code.GameComponent;

import Code.Utilities.ConsoleColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * The GameComponent class defines the attributes and functions of any object in the game.  All objects in the game
 * must inherit from GameComponent.  Objects must have the following components (Players will only hold the first
 * two bullet points:
 * <blockquote>
 *     <ul>
 *         <li>Name - The object's name (REQUIRED)</li>
 *         <li>Text Color - The color of any information printed about the respective
 *         object in the console (REQUIRED)</li>
 *         <li>Description - Information about that object (OPTIONAL)</li>
 *         <li>ID - Serial number to identify that object (OPTIONAL)</li>
 *         <li>File - A file to define all the object's qualities (OPTIONAL)</li>
 *     </ul>
 * </blockquote>
 * @since 1.4
 * @version 1.2
 * @author Khamilah Nixon
 */
<<<<<<< HEAD:src/Code/Component/Component.java
public class Component {
=======
public class GameComponent {
>>>>>>> main:src/Code/GameComponent/GameComponent.java

    /**
     * The component's name
     */
    private String name;

    /**
     * The component's description
     */
    private String description;

    /**
     * A ConsoleColors object to define text colors in the console
     * @see ConsoleColors
     */
    private ConsoleColors consoleColors;

    /**
     * The component's ID
     */
    private int id;
    private int[] itemID;

    /**
     * The component's respective file
     */
    private BufferedReader file;

    /**
     * Creates a GameComponent object using a file, name, and text color
     * @param file - The file containing all the component's information
     * @param name - The component's name
     * @param textColor - The component's text color when information from that component is printed on the console
     * @throws IOException - If {@link #file} does not exist
     */
    public GameComponent(BufferedReader file, String name, String textColor) throws IOException {
        consoleColors = new ConsoleColors();
        consoleColors.setTextColor(textColor);
        this.file = file;
        this.name = name;
        this.id = readID();
        this.description = readDescription();
    }

    /**
     * Creates a GameComponent object using a name and text color
     * @param name - The component's name
     * @param textColor - The component's text color when information from that component is printed on the console
     */
    public GameComponent(String name, String textColor) {
        consoleColors = new ConsoleColors();
        consoleColors.setTextColor(textColor);
        this.name = name;
    }

    /**
     * Creates a GameComponent object using a file and text color
     * @param file - The GameComponent's associated file
     * @param textColor - The component's text color when information from that component is printed on the console
     * @throws IOException - If the file does not exist
     */
    public GameComponent(BufferedReader file, String textColor) throws IOException {
        consoleColors = new ConsoleColors();
        consoleColors.setTextColor(textColor);
        this.file = file;
        this.id = readID();
        this.name = readName();
        this.description = readDescription();
    }
    /**
     * Returns the GameComponent's description
     * @return a String of the GameComponent's description
     */
    public String getDescription() {
        return consoleColors.colorString(description);
    }

    /**
     * Returns the GameComponent's name
     * @return a String of the GameComponent's name
     */
    public String getName() {
        return consoleColors.colorString(name);
    }

    /**
     * Returns the GameComponent's ID
     * @return a String of the GameComponent's ID
     */
    public int getId() {
        return id;
    }

    public BufferedReader getFile() {
        return file;
    }

    public ConsoleColors getConsoleColors() {
        return consoleColors;
    }

    /**
     * Returns the GameComponent's ID once read from a text file
     * @return a String of the GameComponent's ID
     * @throws IOException - If the file does not exist
     */
    public int readID() throws IOException {
        return Integer.parseInt(file.readLine());
    }

    /**
     * Returns the GameComponent's name once read from a text file
     * @return a String of the GameComponent's name
     * @throws IOException - If the file does not exist
     */
    public String readName() throws IOException {
        return file.readLine();
    }

    /**
     * Returns the GameComponent's description once read from a text file
     * @return a String of the GameComponent's description
     * @throws IOException - If the file does not exist
     */
    public String readDescription() throws IOException {
        String next = file.readLine();
        StringBuilder description = new StringBuilder();
        while(file.ready() && !next.equals("----")){
            description.append(next).append("\n");
            next = file.readLine();
        }
        return description.toString();
    }

    @Override
    public String toString() {
        return "GameComponent{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", consoleColors=" + consoleColors +
                ", id=" + id +
                ", itemID=" + Arrays.toString(itemID) +
                ", file=" + file +
                '}';
    }
}
