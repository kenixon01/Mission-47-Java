package Code.Component;

import java.io.BufferedReader;
import java.io.IOException;

public class Monster extends Character {
    private int roomID;

    private int attackDmg;

    private int initialDamage;


    private int threshold;

    private String deathMsg;

    /**
     * Creates a Component object using a file and text color
     *
     * @param file      - The Component's associated file
     * @throws IOException - If the file does not exist
     */
    public Monster(BufferedReader file) throws IOException {
        super(file, "red");
        roomID = readRoomID();
        setHealth(readHP());
        threshold = readThreshold();
        attackDmg = readAttackDamage();
        deathMsg = readDeathMsg();
        initialDamage = attackDmg;
    }

    public int getInitialDamage() {
        return initialDamage;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getDeathMsg() {
        return deathMsg;
    }

    public void setDeathMsg(String deathMsg) {
        this.deathMsg = deathMsg;
    }

    public void attack(Player player) {
        if(player != null) {
            int dmg = attackDmg;
            if(player.getResistance() > 0) {
                dmg = (int) (attackDmg * (1 - player.getResistance()));
            }
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

    @Override
    public String toString() {
        return "Monster{" +
                "roomID=" + roomID +
                ", attackDmg=" + attackDmg +
                ", threshold=" + threshold +
                ", deathMsg='" + deathMsg + '\'' +
                '}';
    }
}
