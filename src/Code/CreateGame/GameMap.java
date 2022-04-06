package Code.CreateGame;

<<<<<<< HEAD:src/Code/Game/GameMap.java
import Code.Component.*;
=======
import Code.GameComponent.Characters.Trader;
import Code.GameComponent.Item;
import Code.GameComponent.Puzzle;
import Code.GameComponent.Room;
>>>>>>> main:src/Code/CreateGame/GameMap.java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Creates a map of all the rooms.  This class will map the room connections as well as
 * puzzle connections, item connections, and trader connections with rooms.  The item
 * connections with the puzzles, rooms, and traders are also mapped in this class.
 * @since 1.4
 * @version 1.5
 * @author Khamilah Nixon
 */
public class GameMap {

    /**
     * A reference to room IDs and associated room
     */
    private TreeMap<Integer, Room> roomList = new TreeMap<>();

    /**
     * A reference to item IDs and associated items
     */
    private HashMap<Integer, Item> itemList = new HashMap<>();

    /**
     * A reference to trader IDs and associated trader
     */
    private HashMap<Integer, Trader> traderList = new HashMap<>();

    /**
     * A reference to puzzle IDs and associated puzzle
     */
    private HashMap<Integer, Puzzle> puzzleList = new HashMap<>();

    private HashMap<Integer, Monster> monsterList = new HashMap<>();

    /**
     * A reference to rooms and their connections
     */
    private TreeMap<Room, ArrayList<Room>> map = new TreeMap<>();

    /**
     * A reference to the file containing room information
     */
    private BufferedReader roomsFile;

    /**
     * A reference to the file containing item information
     */
    private BufferedReader itemsFile;

    /**
     * A reference to the file containing puzzle information
     */
    private BufferedReader puzzleFile;

    /**
     * A reference to the file containing trader information
     */
    private BufferedReader traderFile;

    private BufferedReader monsterFile;

    /**
     * Creates a game map object
     */
    public GameMap() {
        try {
            roomsFile = new BufferedReader(new FileReader("src/TextFiles/Rooms.txt"));
            itemsFile = new BufferedReader(new FileReader("src/TextFiles/Items.txt"));
            puzzleFile = new BufferedReader(new FileReader("src/TextFiles/Puzzles.txt"));
            traderFile = new BufferedReader(new FileReader("src/TextFiles/Trader.txt"));
            monsterFile = new BufferedReader(new FileReader("src/TextFiles/Monsters.txt"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public TreeMap<Integer, Room> getRoomList() {
        return roomList;
    }

    public HashMap<Integer, Item> getItemList() {
        return itemList;
    }

    public TreeMap<Room, ArrayList<Room>> getMap() {
        return map;
    }

    /**
     * Stores rooms and their IDs in the roomList
     * @throws IOException If the file does not exist
     */
    private void identifyRooms() throws IOException {
        while(roomsFile.ready()) {
            Room room = new Room(roomsFile);
            roomList.put(room.getId(), room);
        }
    }

    /**
     * Creates connections between items and rooms, puzzles, and traders
     * @throws IOException If the file does not exist
     */
    private void identifyItems() throws IOException {
        while(itemsFile.ready()) {
            Item item = new Item(itemsFile);
            itemList.put(item.getId(), item);
        }
        for(Room room: roomList.values()) {
            if(room.getItemID() != null) {
                ArrayList<Integer> roomItems = room.getItemID();
                if (roomItems != null) {
                    for (Integer x : roomItems) {
                        for (int i = 1; i <= itemList.values().size(); i++) {
                            Item item = itemList.get(i);
                            if (x == item.getId()) {
                                room.getInventory().add(item);
                                break;
                            }
                        }
                    }
                }
            }

            if(room.getTrader() != null) {
                ArrayList<Integer>traderItems = room.getTrader().getItemID();
                for (Integer x : traderItems) {
                    for (int i = 1; i <= itemList.values().size(); i++) {
                        Item item = itemList.get(i);
                        if (x == item.getId()) {
                            room.getTrader().getInventory().add(item);
                            break;
                        }
                    }
                }
            }
        }
        for(Item item : itemList.values()) {
            Room room = roomList.get(item.getRoomID());
            for(Puzzle puzzle : puzzleList.values()) {
                if (puzzle.getItemID() == item.getId()) {
                    room.getPuzzle().getInventory().add(item);
                }
            }
        }
    }

    /**
     * Creates connection between rooms and puzzle
     * @throws IOException If the file does not exist
     */
    private void identifyPuzzles() throws IOException {
        while(puzzleFile.ready()) {
            Puzzle puzzle = new Puzzle(puzzleFile);
            int id = puzzle.getRoomID();
            if(roomList.get(id) != null) {
                roomList.get(id).setPuzzle(puzzle);
                puzzleList.put(puzzle.getId(), puzzle);
            }
        }
    }

    /**
     * Creates connection between room and traders
     * @throws IOException If the file does not exist
     */
    private void identifyTrader() throws IOException {
        while(traderFile.ready()){
            Trader trader = new Trader(traderFile);
            int id = trader.getRoomID();
            if(roomList.get(id) != null) {
                roomList.get(id).setTrader(trader);
                traderList.put(trader.getId(), trader);
            }
        }
    }

    private void identifyMonster() throws IOException {
        while (monsterFile.ready()) {
            Monster monster = new Monster(monsterFile);
            int id = monster.getRoomID();
            if(roomList.get(id) != null) {
                roomList.get(id).setMonster(monster);
                monsterList.put(monster.getId(), monster);
            }
        }
    }

    /**
     * Creates connection between all rooms
     */
    private void identifyRoomConnections() {
        for (Map.Entry<Integer, Room> item : roomList.entrySet()) { //loop through the entire list of rooms and their IDs
            ArrayList<Room> rooms = new ArrayList<>();
            for (String connection : item.getValue().getConnection()) { //loop through each room's connections
                if (connection.isEmpty()) {
                    rooms.add(null);
                }
                else {
                    rooms.add(roomList.get(Integer.parseInt(connection)));
                }
                map.put(item.getValue(),rooms);
            }
        }
    }

    /**
     * Defines the game's map and the connections between room in the map.  All the {@link Room} are added to the
     * game's map
     * @throws IOException - If the {@link #roomsFile} does not exist.
     */
    public void createMap() throws IOException {
        identifyRooms();
        identifyRoomConnections();
        identifyPuzzles();
        identifyTrader();
        identifyItems();
        identifyMonster();

        map.firstKey().setActive(true);
    }

    @Override
    public String toString() {
        return "GameMap{" +
                "roomList=" + roomList +
                ", itemList=" + itemList +
                ", traderList=" + traderList +
                ", puzzleList=" + puzzleList +
                ", map=" + map +
                ", roomsFile=" + roomsFile +
                ", itemsFile=" + itemsFile +
                ", puzzleFile=" + puzzleFile +
                ", traderFile=" + traderFile +
                '}';
    }
}
