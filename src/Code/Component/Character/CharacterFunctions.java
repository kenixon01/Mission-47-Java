package Code.Component.Character;

/**
 * This interface defines all the character's functionalities
 * @since 1.5
 * @version 1.3
 */
public interface CharacterFunctions {

    /**
     * Determines how each character can lose health
     * @param val - Amount character loses health
     */
    void loseHealth(int val);

    /**
     * Determines how each character can gain health
     * @param val - Amount character gains health
     */
    void gainHealth(int val);
}
