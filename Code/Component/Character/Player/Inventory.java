//package Code.Component.CharacterFunctions.Player;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//
//public class Inventory {
//    private ArrayList<Item>inventory;
//
//    public Inventory() {
//        inventory = new ArrayList<>();
//    }
//
//    public ArrayList<Item> getItems() {
//        return inventory;
//    }
//
//    public void setItems(ArrayList<Item> items) {
//        this.inventory = items;
//    }
//
//    private void grab(Item item) {
//        inventory.add(item);
//    }
//
//    public void use(@NotNull Item item) {
//        String itemName = item.getType();
//        int numUses = item.getNumUses();
//        if(inventory.contains(item)) {
//            System.out.println("You used " + itemName);
//            if(numUses == 0) {
//                inventory.remove(item);
//                System.out.println(itemName + " depleted. Removed from inventory.");
//            }
//            item.use();
//        }
//        else {
//            System.out.println(itemName + " not found");
//        }
//    }
//    private void remove(Item item) {
//        inventory.remove(item);
//    }
//}
