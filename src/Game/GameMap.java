package Game;

import Component.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Creates a map of all the rooms.  This class will map the room connections as well as
 * puzzle connections, item connections, and trader connections with rooms.  The item
 * connections with the puzzles, rooms, and traders are also mapped in this class.
 * @since 1.4
 * @version 1.7
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
     * This method will read {@link #roomsFile} and {@link #itemsFile} and will sort
     * each room and item in {@link #roomList} and {@link #itemList}, respectively.
     * The method will also read {@link #puzzleFile}, {@link #traderFile},
     * {@link #monsterFile} and sort each puzzle, trader, and monster in their
     * respective rooms.
     * @throws IOException If the file does not exist
     */
    private void identifyGameComponents() throws IOException {
        while(roomsFile.ready()) {
            Room room = new Room(roomsFile);
            roomList.put(room.getId(), room);
        }

        while (itemsFile.ready()) {
            Item item = new Item(itemsFile);
            itemList.put(item.getId(), item);
        }

        while(puzzleFile.ready()) {
            Puzzle puzzle = new Puzzle(puzzleFile);
            int id = puzzle.getROOM_ID();
            if(roomList.get(id) != null) {
                roomList.get(id).setPuzzle(puzzle);
            }
        }

        while(traderFile.ready()){
            Trader trader = new Trader(traderFile);
            int id = trader.getROOM_ID();
            if(roomList.get(id) != null) {
                roomList.get(id).setTrader(trader);
            }
        }

        while (monsterFile.ready()) {
            Monster monster = new Monster(monsterFile);
            int id = monster.getROOM_ID();
            if(roomList.get(id) != null) {
                roomList.get(id).setMonster(monster);
            }
        }
    }

    /**
     * This method will determine if each room in {@link #roomList} contains any items,
     * puzzles, or traders.  It will, then, identify if the listed item ID's correspond
     * with an ID in {@link #itemList}.  If so, then that item will be added to that
     * component's (room, trader, or puzzle) inventory.
     */
    private void sortItems() {
        for(Room room: roomList.values()) {
            if (room.getITEM_ID() != null) {
                for (Integer id : room.getITEM_ID()) {
                    if (itemList.containsKey(id)) {
                        room.getInventory().add(itemList.get(id));
                    }
                }
            }
            if (room.getTrader() != null) {
                for (Integer id : room.getTrader().getITEM_ID()) {
                    if (itemList.containsKey(id)) {
                        room.getTrader().getInventory().add(itemList.get(id));
                    }
                }
            }
            if(room.getPuzzle() != null) {
                int id = room.getPuzzle().getITEM_ID();
                if(itemList.containsKey(id)) {
                    room.getPuzzle().getInventory().add(itemList.get(id));
                }
            }
        }
    }

    /**
     * This method will identify the connections between each room.
     */
    private void identifyRoomConnections() {
        for (Map.Entry<Integer, Room> room : roomList.entrySet()) {
            ArrayList<Room> roomArrayList = new ArrayList<>();
            for (String connection : room.getValue().getCONNECTION()) {
                roomArrayList.add(connection.isEmpty() ? null : roomList.get(Integer.parseInt(connection)));
                map.put(room.getValue(),roomArrayList);
            }
        }
    }

    /**
     * Defines the game's map and the connections between room in the map.  All the {@link Room} are added to the
     * game's map
     * @throws IOException - If the {@link #roomsFile} does not exist.
     */
    public void createMap() throws IOException {
        identifyGameComponents();
        identifyRoomConnections();
        sortItems();
        map.firstKey().setActive(true);
    }
}
