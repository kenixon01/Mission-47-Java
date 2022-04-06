<<<<<<< HEAD:src/Code/GameComponent/Character.java
package Code.Component;
=======
package Code.GameComponent.Characters;
>>>>>>> main:src/Code/GameComponent/Characters/Character.java

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

<<<<<<< HEAD:src/Code/GameComponent/Character.java
public class Character extends Component {
=======
public class Character extends GameComponent {
>>>>>>> main:src/Code/GameComponent/Characters/Character.java

    private final int INITIAL_HEALTH;
    /**
     * This value is used to storage the character's health value.
     */
    private int currentHealth;

    private final int MAX_HEALTH;
    /**
     * Initializes a Character object, whereas, its attributes are read through a file
     * @param file - The file containing the Character's data
     * @param textColor - The character's desired text color
<<<<<<< HEAD:src/Code/GameComponent/Character.java
     * @throws IOException - Occurs if the file does not exist
     * @see Code.Utilities.ConsoleColors
     */
    public Character(BufferedReader file, String textColor) throws IOException {
        super(file, textColor);
        INITIAL_HEALTH = 0;
=======
     * @param maxHealth - THe character's initial currentHealth
     * @throws IOException - Occurs if the file does not exist
     * @see Code.Utilities.ConsoleColors
     */
    public Character(BufferedReader file, String name, String textColor, int maxHealth) throws IOException {
        super(file, name, textColor);
        this.MAX_HEALTH = maxHealth;
        currentHealth = MAX_HEALTH;
>>>>>>> main:src/Code/GameComponent/Characters/Character.java
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
<<<<<<< HEAD:src/Code/GameComponent/Character.java
        this.health = health;
        INITIAL_HEALTH = health;
=======
        this.MAX_HEALTH = maxHealth;
    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
>>>>>>> main:src/Code/GameComponent/Characters/Character.java
    }


    public int getINITIAL_HEALTH() {
        return INITIAL_HEALTH;
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
<<<<<<< HEAD:src/Code/GameComponent/Character.java

        if(health - val < 0) {
            health = 0;
        }
        else {
            health -= val;
        }
=======
        currentHealth -= val;
>>>>>>> main:src/Code/GameComponent/Characters/Character.java
    }

    /**
     * Allows the character to gain health by a given amount
     * @param val - Amount of health the character will gain
     */
    public void gainHealth(int val) {
<<<<<<< HEAD:src/Code/GameComponent/Character.java
        if(health + val > INITIAL_HEALTH) {
            health += INITIAL_HEALTH - health;
        }
        else {
            health += val;
        }
=======
        currentHealth += val;
>>>>>>> main:src/Code/GameComponent/Characters/Character.java
    }

    @Override
    public String toString() {
        return "Character{" +
                "health=" + currentHealth +
                '}';
    }
}
