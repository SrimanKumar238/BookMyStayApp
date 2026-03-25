import java.util.HashMap;
import java.util.Map;

// Inventory Class
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();

        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Deluxe Room", 2);
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

// Main Class
public class UC3 {

    public static void main(String[] args) {

        System.out.println("=== UC3: Centralized Room Inventory Management ===");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nChecking availability of Single Room:");
        System.out.println("Available: " + inventory.getAvailability("Single Room"));

        System.out.println("\nUpdating Deluxe Room availability...");
        inventory.updateAvailability("Deluxe Room", 4);

        inventory.displayInventory();
    }
}