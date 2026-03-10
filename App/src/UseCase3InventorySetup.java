import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    // Centralized inventory using HashMap
    private Map<String, Integer> roomAvailability;

    // Constructor to initialize inventory
    public RoomInventory() {
        roomAvailability = new HashMap<>();

        // Register room types with available counts
        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Deluxe Room", 2);
    }

    // Retrieve availability of a room type
    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    // Update availability in a controlled way
    public void updateAvailability(String roomType, int newCount) {
        roomAvailability.put(roomType, newCount);
    }

    // Display current inventory state
    public void displayInventory() {
        System.out.println("Current Room Inventory:");

        for (Map.Entry<String, Integer> entry : roomAvailability.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=== BookMyStayApp - Room Inventory ===");

        // Initialize inventory component
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Example controlled update
        System.out.println("\nUpdating availability for Deluxe Room...\n");
        inventory.updateAvailability("Deluxe Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        // Retrieve availability
        System.out.println("\nAvailable Single Rooms: "
                + inventory.getAvailability("Single Room"));
    }
}