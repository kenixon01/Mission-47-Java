package Code.GameComponent.Characters;

import Code.GameComponent.GameComponent;

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

public class Character extends GameComponent {

    /**
     * This value is used to storage the character's health value.
     */
    private int currentHealth;

    private final int MAX_HEALTH;
    /**
     * Initializes a Character object, whereas, its attributes are read through a file
     * @param file - The file containing the Character's data
     * @param name - The name of the character
     * @param textColor - The character's desired text color
     * @param maxHealth - THe character's initial currentHealth
     * @throws IOException - Occurs if the file does not exist
     * @see Code.Utilities.ConsoleColors
     */
    public Character(BufferedReader file, String name, String textColor, int maxHealth) throws IOException {
        super(file, name, textColor);
        this.MAX_HEALTH = maxHealth;
        currentHealth = MAX_HEALTH;
    }

    /**
     * Initializes a Character object
     * @param name - The name of the character
     * @param textColor - The character's desired text color
     * @param maxHealth - The character's initial currentHealth
     * @see Code.Utilities.ConsoleColors
     */
    public Character(String name, String textColor, int maxHealth) {
        super(name, textColor);
        this.MAX_HEALTH = maxHealth;
    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    /**
     * Allows other classes to change the character's health
     * @param currentHealth - Character's current health
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Allows other classes to access the character's health value
     * @return The character's current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Allows the character to lose health by a given amount
     * @param val - Amount of health the character will lose
     */
    public void loseHealth(int val) {
        currentHealth -= val;
    }

    /**
     * Allows the character to gain health by a given amount
     * @param val - Amount of health the character will gain
     */
    public void gainHealth(int val) {
        currentHealth += val;
    }

    @Override
    public String toString() {
        return "Character{" +
                "health=" + currentHealth +
                '}';
    }
}
