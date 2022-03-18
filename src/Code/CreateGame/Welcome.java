package Code.CreateGame;

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
    private BufferedReader file;

    /**
     * The game's title.
     */
    private StringBuilder title = new StringBuilder();

    /**
     * The game's prologue.
     */
    private StringBuilder prologue = new StringBuilder();

    /**
     * The game's instructions.
     */
    private StringBuilder instructions = new StringBuilder();

    /**
     * The game's splash screen.
     */
    private StringBuilder splash = new StringBuilder();

    /**
     * The game's character selection screen.
     */
    private StringBuilder characterSelect = new StringBuilder();

    /**
     * Creates a welcome object and reads all the welcome screen's information
     */
    public Welcome() {
        try {
            file = new BufferedReader(new FileReader("src/UserManual/TextFiles/GlobalMessages.txt"));
            loadStartMessages();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the game's splash screen
     * @return a StringBuilder object storing the splash screen, delimited by a String on a
     * separate line before and after the splash screen
     */
    public StringBuilder getSplash() {
        return splash;
    }

    /**
     * Returns the game's character selection screen
     * @return a StringBuilder object storing the character selection screen, delimited by a String on a
     * separate line before and after the character selection screen
     */
    public StringBuilder getCharacterSelect() {
        return characterSelect;
    }
    /**
     * Returns the game's title
     * @return a StringBuilder object storing the title, delimited by String on a separate line before and
     * after the title
     * @see #loadWelcomeInfo(StringBuilder)
     */
    public StringBuilder getTitle() {
        return title;
    }

    /**
     * Returns the game's prologue
     * @return a StringBuilder object storing the prologue, delimited by a String on a separate line before and after
     * the prologue
     */
    public StringBuilder getPrologue() {
        return prologue;
    }

    /**
     * Returns the game's instructions
     * @return a StringBuilder object storing the instructions, delimited by a String on a separate line before and
     * after the prologue
     */
    public StringBuilder getInstructions() {
        return instructions;
    }

    /**
     * Skips a line in the file
     */
    private void skipLine() throws IOException{
        file.readLine();
    }

    /**
     * Reads the splash screen in {@link #file}
     * @throws IOException - If the file does not exist
     */
    private void loadSplash() throws IOException {
        loadWelcomeInfo(splash);
    }

    /**
     * Reads the character selection screen in {@link #file}
     * @throws IOException - If the file does not exist
     */
    private void loadCharSelect() throws IOException {
        loadWelcomeInfo(characterSelect);
    }
    /**
     * Reads a file and stores each section of {@link #file} for storage in appropriate variables
     * @param str - Section title
     * @throws IOException - If the file does not exist
     * @see #loadInstructions()
     * @see #loadPrologue()
     * @see #loadTitle()
     */
    private void loadWelcomeInfo(StringBuilder str) throws IOException {
        String next = file.readLine();
        while(file.ready()) {
            String DELIMITER = "----";
            if(next.equals(DELIMITER)) break;
                str.append(next).append("\n");
            next = file.readLine();
        }
    }

    /**
     * Reads the title in {@link #file}
     * @throws IOException - If the file does not exist
     */
    private void loadTitle() throws IOException {
        loadWelcomeInfo(title);
    }

    /**
     * Reads the prologue in {@link #file}
     * @throws IOException - If the file does not exist
     */
    private void loadPrologue() throws IOException {
        loadWelcomeInfo(prologue);
    }

    /**
     * Reads the instructions in {@link #file}
     * @throws IOException - If the file does not exist
     */
    private void loadInstructions() throws IOException {
        loadWelcomeInfo(instructions);
    }

    /**
     * Reads all starting screens in {@link #file}
     * @throws IOException - If the file does not exist
     */
    private void loadStartMessages() throws IOException {
        loadTitle();
        loadInstructions();
        loadPrologue();
        loadSplash();
        loadCharSelect();
    }
    public String toString() {
        return "Welcome{" +
                "file=" + file +
                ", title=" + title +
                ", prologue=" + prologue +
                ", instructions=" + instructions +
                '}';
    }
}
