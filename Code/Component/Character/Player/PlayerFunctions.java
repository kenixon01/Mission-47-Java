package Code.Component.Character.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The PlayerFunctions interface represents all the user's functionality.  All users in java
 * programs have this implementation.
 * @author Khamilah Nixon
 * @version 1.0
 * @since 1.0
 */

public interface PlayerFunctions {


    /**
     * method: north()
     * If applicable, moves the player to the room north of the current room
     */

    void north();

    /**
     * method: south()
     * If applicable, moves the player to the room south of the current room
     */

    void south();

    /**
     * method: east()
     * If applicable, moves the player to the room east of the current room
     */

    void east();

    /**
     * method: west()
     * If applicable, moves the player to the room west of the current room
     */

    void west();

    /**
     * method: help()
     * Displays list of valid commands that the player can utilize during gameplay
     * @throws IOException This method throws and IOException if the file is not found
     */

    default void help() throws IOException {
        String filePath = "UserManual/TextFiles/CommandsList.txt";
        String fileName = filePath.substring(filePath.lastIndexOf("/"));
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filePath));
            while(buffer.ready()) {
                System.out.println(buffer.readLine());
            }
            buffer.close();
        }
        catch (FileNotFoundException ex) {
            throw new FileNotFoundException(fileName + " not found at given file path: " + filePath);
        }

    }
}
