package Code.Component;

import Code.Utilities.ConsoleColors;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The Component class defines the attributes and functions of any object in the game.  All objects in the game
 * must inherit from Component.  Objects must have the following components (Players will only hold the first
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
public class Component {

    /**
     * The component's name
     */
    private final String NAME;

    /**
     * The component's description
     */
    private String description;

    /**
     * A ConsoleColors object to define text colors in the console
     * @see ConsoleColors
     */
    private final ConsoleColors consoleColors;

    /**
     * The component's ID
     */
    private int id;

    /**
     * The component's respective file
     */
    private BufferedReader file;

    /**
     * Creates a Component object using a file, name, and text color
     * @param file - The file containing all the component's information
     * @param name - The component's name
     * @param textColor - The component's text color when information from that component is printed on the console
     * @throws IOException - If {@link #file} does not exist
     */
    public Component(BufferedReader file, String name, String textColor) throws IOException {
        consoleColors = new ConsoleColors();
        consoleColors.setTextColor(textColor);
        NAME = name;
        this.file = file;
        this.id = readID();
        this.description = readDescription();
    }

    /**
     * Creates a Component object using a name and text color
     * @param name - The component's name
     * @param textColor - The component's text color when information from that component is printed on the console
     */
    public Component(String name, String textColor) {
        consoleColors = new ConsoleColors();
        consoleColors.setTextColor(textColor);
        NAME = name;
    }

    /**
     * Creates a Component object using a file and text color
     * @param file - The Component's associated file
     * @param textColor - The component's text color when information from that component is printed on the console
     * @throws IOException - If the file does not exist
     */
    public Component(BufferedReader file, String textColor) throws IOException {
        consoleColors = new ConsoleColors();
        consoleColors.setTextColor(textColor);
        this.file = file;
        this.id = readID();
        NAME = readName();
        this.description = readDescription();
    }

    public String getDescription() {
        return consoleColors.textColor(description);
    }

    public String getNAME() {
        return consoleColors.textColor(NAME);
    }

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
     * Returns the Component's ID once read from a text file
     * @return a String of the Component's ID
     * @throws IOException - If the file does not exist
     */
    public int readID() throws IOException {
        return Integer.parseInt(file.readLine());
    }

    /**
     * Returns the Component's name once read from a text file
     * @return a String of the Component's name
     * @throws IOException - If the file does not exist
     */
    public String readName() throws IOException {
        return file.readLine();
    }

    /**
     * Returns the Component's description once read from a text file
     * @return a String of the Component's description
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
}
