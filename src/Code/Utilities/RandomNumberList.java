package Code.Utilities;
import java.util.Arrays;

/**
 * This class provides a function to generate any amount of random integers
 * @author Khamilah Nixon
 * @since 1.0
 * @version 1.0
 */
public class RandomNumberList {

    /**
     * The number of elements in the array
     */
    private final int NUM_ELEMENTS;

    /**
     * An array of random numbers
     */
    private final int[] NUMBER;

    /**
     * The minimum random value
     */
    private final int MIN;

    /**
     * The maximum random value
     */
    private final int MAX;

    /**
     * Creates a new RandomNumberList object
     * @param numElements Number of elements in number array
     * @param min Smallest possible random number
     * @param max Largest possible random number
     */
    public RandomNumberList(int numElements, int min, int max) {
        checkMinMax();
        NUM_ELEMENTS = numElements;
        this.NUMBER = new int[NUM_ELEMENTS];
        this.MAX = max;
        this.MIN = min;
    }

    /**
     * Checks if the minimum value is greater than the maximum value.
     * @throws IllegalArgumentException - If the max value is less than the min value
     */
    private void checkMinMax() throws IllegalArgumentException {
        try {
            if(MAX < MIN) {
                throw new IllegalArgumentException("The minimum value cannot be greater than the maximum value");
            }
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
        }
    }

    /**
     * Generates a list of random numbers
     * @return A list of random numbers
     */
    public int[] randomNumList() {
        for(int i = 0; i < NUM_ELEMENTS; i++) {
            NUMBER[i] = ((int)(Math.random() * MAX) + (MAX - MIN));
        }
        return NUMBER;
    }

    @Override
    public String toString() {
        return "RandomNumberList{" +
                "NUM_DIGITS=" + NUM_ELEMENTS +
                ", NUMBER=" + Arrays.toString(NUMBER) +
                ", MIN=" + MIN +
                ", MAX=" + MAX +
                '}';
    }
}
