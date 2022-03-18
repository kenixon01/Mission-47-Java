package Code.GameComponent.Characters;

import Code.GameComponent.GameComponent;

import java.io.BufferedReader;
import java.io.IOException;

public class Monster extends GameComponent {
    /**
     * Creates a GameComponent object using a file and text color
     *
     * @param file      - The GameComponent's associated file
     * @param textColor - The component's text color when information from that component is printed on the console
     * @throws IOException - If the file does not exist
     */
    public Monster(BufferedReader file, String textColor) throws IOException {
        super(file, "red");
    }
}
