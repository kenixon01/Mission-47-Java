package Code.Component.Character;

import Code.Component.Component;

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

public class Character extends Component implements CharacterFunctions {

    /**
     * This value is used to storage the character's health value.
     */
    private int health;

    /**
     * Initializes a Character object, whereas, its attributes are read through a file
     * @param file - The file containing the Character's data
     * @param name - The name of the character
     * @param textColor - The character's desired text color
     * @param health - THe character's initial health
     * @throws IOException - Occurs if the file does not exist
     * @see Code.Utilities.ConsoleColors
     */
    public Character(BufferedReader file, String name, String textColor, int health) throws IOException {
        super(file, name, textColor);
        this.health = health;
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
    }

    /**
     * Allows other classes to change the character's health
     * @param health - Character's current health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Allows other classes to access the character's health value
     * @return The character's current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Allows the character to lose health by a given amount
     * @param val - Amount of health the character will lose
     */
    @Override
    public void loseHealth(int val) {
        health -= val;
    }

    /**
     * Allows the character to gain health by a given amount
     * @param val - Amount of health the character will gain
     */
    @Override
    public void gainHealth(int val) {
        health += val;
    }

    @Override
    public String toString() {
        return "Character{" +
                "health=" + health +
                '}';
    }
}
