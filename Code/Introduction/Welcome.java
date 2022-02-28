package Code.Introduction;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Creates each section of the welcome screen, which includes:
 * <blockquote>
 *     <ul>
 *         <li>Instructions - How the player can play the game</li>
 *         <li>Prologue - The circumstances around the game's story</li>
 *         <li>Title - The name of the game</li>
 *     </ul>
 * </blockquote>
 * All of these elements are read from a file and stored into appropriate variables for later usage.
 * This class is immutable.
 * @since 1.2
 * @version 1.1
 * @author Khamilah Nixon
 */
public final class Welcome {

    /**
     * The file used to access the welcome screen elements
     */
    private final BufferedReader FILE;

    /**
     * The game's title.
     */
    private final StringBuilder TITLE;

    /**
     * The game's prologue.
     */
    private final StringBuilder PROLOGUE;

    /**
     * The game's instructions.
     */
    private final StringBuilder INSTRUCTIONS;

    /**
     * Creates a welcome object and reads all the welcome screen's information
     * @param delimiter - a String separating each section of the file
     * @throws IOException - If the file does not exist
     */
    public Welcome(String delimiter) throws IOException {
        FILE = new BufferedReader(new FileReader("UserManual/TextFiles/GlobalMessages.txt"));
        PROLOGUE = new StringBuilder();
        INSTRUCTIONS = new StringBuilder();
        TITLE = new StringBuilder();
        loadTitle(delimiter);
        loadInstructions(delimiter);
        loadPrologue(delimiter);
        FILE.close();
    }

    /**
     * Returns the game's title
     * @return a StringBuilder object storing the title, delimited by String on a separate line before and
     * after the title
     * @see #loadWelcomeInfo(StringBuilder,String)
     */
    public StringBuilder getTITLE() {
        return TITLE;
    }

    /**
     * Returns the game's prologue
     * @return a StringBuilder object storing the prologue, delimited by a String on a separate line before and after
     * the prologue
     */
    public StringBuilder getPROLOGUE() {
        return PROLOGUE;
    }

    /**
     * Returns the game's instructions
     * @return a StringBuilder object storing the instructions, delimited by a String on a separate line before and
     * after the prologue
     */
    public StringBuilder getINSTRUCTIONS() {
        return INSTRUCTIONS;
    }

    /**
     * Skips a line in the file
     */
    private void skipLine() throws IOException{
        FILE.readLine();
    }

    /**
     * Reads a file and stores each section of {@link #FILE} for storage in appropriate variables
     * @param str - Section title
     * @param delimiter - String separating each section
     * @throws IOException - If the file does not exist
     * @see #loadInstructions(String)
     * @see #loadPrologue(String)
     * @see #loadTitle(String)
     */
    private void loadWelcomeInfo(StringBuilder str,String delimiter) throws IOException {
        skipLine();
        String next = FILE.readLine();
        while(FILE.ready()) {
            if(next.equals(delimiter)) break;
                str.append(next).append("\n");
            next = FILE.readLine();
        }
    }

    /**
     * Reads the title in {@link #FILE}
     * @param delimiter - String separating each section
     * @throws IOException - If the file does not exist
     */
    private void loadTitle(String delimiter) throws IOException {
        skipLine();
        loadWelcomeInfo(TITLE, delimiter);
    }

    /**
     * Reads the prologue in {@link #FILE}
     * @param delimiter - String separating each section
     * @throws IOException - If the file does not exist
     */
    private void loadPrologue(String delimiter) throws IOException {
        loadWelcomeInfo(PROLOGUE, delimiter);
    }

    /**
     * Reads the instructions in {@link #FILE}
     * @param delimiter - String separating each section
     * @throws IOException - If the file does not exist
     */
    private void loadInstructions(String delimiter) throws IOException {
        loadWelcomeInfo(INSTRUCTIONS, delimiter);
    }

    public @NotNull String toString() {
        return "Welcome{" +
                "file=" + FILE +
                ", title=" + TITLE +
                ", prologue=" + PROLOGUE +
                ", instructions=" + INSTRUCTIONS +
                '}';
    }
}
