package Code.Component;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The Character class represents all the functionality of any Character, in which their
 * attributes are read from a file.  Any character, playable or non-playable, are implemented
 * as subclass of this class.
 *
 * This class holds methods that examine various character attributes.  If character
 * functions are read through a file, this class will throw an IOException.
 * @since 1.5
 * @author Khamilah Nixon
 */

public class Character extends Component {

    private final int INITIAL_HEALTH;

    /**
     * This value is used to storage the character's health value.
     */
    private int health;

    /**
     * Initializes a Character object, whereas, its attributes are read through a file
     * @param file - The file containing the Character's data
     * @param textColor - The character's desired text color
     * @throws IOException - Occurs if the file does not exist
     * @see Code.Utilities.ConsoleColors
     */
    public Character(BufferedReader file, String textColor) throws IOException {
        super(file, textColor);
        INITIAL_HEALTH = 0;
    }

    /**
     * Initializes a Character object
     * @param name - The name of the character
     * @param textColor - The character's desired text color
     * @param health - The character's initial health
     * @see Code.Utilities.ConsoleColors
     */
    public Character(String name, String textColor, int health) {
        super(name, textColor);
        this.health = health;
        INITIAL_HEALTH = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Allows the character to lose health by a given amount
     * @param val - Amount of health the character will lose
     */
    public void loseHealth(int val) {

        if(health - val < 0) {
            health = 0;
        }
        else {
            health -= val;
        }
    }

    /**
     * Allows the character to gain health by a given amount
     * @param val - Amount of health the character will gain
     */
    public void gainHealth(int val) {
        if(health + val > INITIAL_HEALTH) {
            health += INITIAL_HEALTH - health;
        }
        else {
            health += val;
        }
    }
}
