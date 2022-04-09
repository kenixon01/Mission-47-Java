package Component;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @since 1.6
 * @version 1.0
 * @author Khamilah Nixon
 */
public class Monster extends Character {
    private final int ROOM_ID;

    private final int INITIAL_DAMAGE;

    private final int THRESHOLD;

    private final String DEATH_MESSAGE;

    private int attackDmg;

    /**
     * Creates a Component object using a file and text color
     *
     * @param file The Component's associated file
     * @throws IOException If the file does not exist
     */
    public Monster(BufferedReader file) throws IOException {
        super(file, "red");
        ROOM_ID = readRoomID();
        setHealth(readHP());
        THRESHOLD = readThreshold();
        attackDmg = readAttackDamage();
        DEATH_MESSAGE = readDeathMsg();
        INITIAL_DAMAGE = attackDmg;
    }

    public int getINITIAL_DAMAGE() {
        return INITIAL_DAMAGE;
    }

    public int getROOM_ID() {
        return ROOM_ID;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }

    public int getTHRESHOLD() {
        return THRESHOLD;
    }

    public String getDEATH_MESSAGE() {
        return DEATH_MESSAGE;
    }

    public void attack(Player player) {
        if(player != null) {
            int dmg = (player.getResistance() > 0 ? (int)(attackDmg * (1 - player.getResistance())) : attackDmg);
            player.loseHealth(dmg);
        }
    }

    private int readRoomID() throws IOException {
        return Integer.parseInt(getFile().readLine());
    }

    private int readHP() throws IOException {
        return Integer.parseInt(getFile().readLine());
    }

    private int readAttackDamage() throws IOException {
        return Integer.parseInt(getFile().readLine());
    }

    private int readThreshold() throws IOException {
        return Integer.parseInt(getFile().readLine());
    }

    private String readDeathMsg() throws IOException {
        return readDescription();
    }
}
