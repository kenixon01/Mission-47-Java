package Code.Component;
import java.io.IOException;

/**
 * This interface provides the functionality of any Component
 * @since 1.0
 * @author Khamilah Nixon
 */
public interface ComponentFunctions {

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
