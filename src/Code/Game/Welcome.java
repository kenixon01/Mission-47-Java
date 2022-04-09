package Code.Game;

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
 * @version 1.3
 * @author Khamilah Nixon
 */
public final class Welcome {

    /**
     * The file used to access the welcome screen elements
     */
    private BufferedReader file;

    /**
     * The game's prologue.
     */
    private String prologue;

    /**
     * The game's instructions.
     */
    private String instructions;

    /**
     * The game's splash screen.
     */
    private String splash;

    /**
     * The game's character selection screen.
     */
    private String characterSelect;

    /**
     * Creates a welcome object and reads all the welcome screen's information
     */
    public Welcome() {
        try {
            file = new BufferedReader(new FileReader("src/TextFiles/GlobalMessages.txt"));
            instructions = readWelcomeInfo();
            prologue = readWelcomeInfo();
            splash = readWelcomeInfo();
            characterSelect = readWelcomeInfo();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the game's splash screen
     * @return the splash screen, delimited by a String on a
     * separate line before and after the splash screen
     */
    public String getSplash() {
        return splash;
    }

    /**
     * Returns the game's character selection screen
     * @return the character selection screen, delimited by a String on a
     * separate line before and after the character selection screen
     */
    public String getCharacterSelect() {
        return characterSelect;
    }

    /**
     * Returns the game's prologue
     * @return the prologue, delimited by a String on a separate line before and after
     * the prologue
     */
    public String getPrologue() {
        return prologue;
    }

    /**
     * Returns the game's instructions
     * @return the instructions, delimited by a String on a separate line before and
     * after the prologue
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Reads a file and stores each section of {@link #file} for storage in appropriate variables
     * @throws IOException - If the file does not exist
     * @return a section of text between a given string delimiter in the file
     */
    private String readWelcomeInfo() throws IOException {
        String next = file.readLine();
        StringBuilder section = new StringBuilder();
        while(file.ready()) {
            if(next.equals("----")) break;
                section.append(next).append("\n");
            next = file.readLine();
        }
        return section.toString();
    }
}
