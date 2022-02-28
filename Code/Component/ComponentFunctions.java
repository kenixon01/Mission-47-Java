package Code.Component;

import Code.Utilities.ConsoleColors;

import java.io.IOException;

/**
 * This interface provides the functionality of any Component
 * @since 1.0
 * @see Code.Utilities.ConsoleColors
 * @author Khamilah Nixon
 */
public interface ComponentFunctions {

    /**
     * Returns the String msg argument with the appropriate ANSI color code value
     * appended to the String.  This will allow the color of that String to alter
     * in the console, accordingly.
     * @param msg - A String of text
     * @param color - The desired color, based on the valid colors given in
     *              {@link ConsoleColors}
     * @return - a String of the original message with a varied color
     */
    String textColor(String msg, String color);

    /**
     * Returns a String describing an Object's description that is written in a file. This
     * description is delimited by a "----" on separate lines at the beginning and end
     * of the description
     * @return - a String containing an Object's description
     * @throws IOException - If the file does not exist
     */
    String readDescription() throws IOException;

    /**
     * Returns a String describing an Object's name that is written in a file. This
     * name is delimited by a "----" on separate lines at the beginning and end
     * of the name
     * @return - a String containing an Object's name
     * @throws IOException - If the file does not exist
     */
    String readName() throws IOException;

    /**
     * Returns a String describing an Object's ID that is written in a file. This
     * ID is delimited by a "----" on separate lines at the beginning and end
     * of the ID
     * @return - a String containing an Object's ID
     * @throws IOException - If the file does not exist
     */
    int readID() throws IOException;
}
