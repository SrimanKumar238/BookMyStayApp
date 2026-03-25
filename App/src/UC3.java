import java.util.HashMap;
import java.util.Map;

// Room class (Domain Model)
class Room {
    private String type;
    private int price;

    public Room(String type, int price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}

// Inventory (Centralized state - READ ONLY)
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();

        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 0);   // unavailable
        roomAvailability.put("Deluxe Room", 2);
    }

    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllRooms() {
        return roomAvailability;
    }
}

// Search Service (Read-only logic)
class SearchService {

    private RoomInventory inventory;
    private Map<String, Room> roomDetails;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;

        roomDetails = new HashMap<>();
        roomDetails.put("Single Room", new Room("Single Room", 2000));
        roomDetails.put("Double Room", new Room("Double Room", 3500));
        roomDetails.put("Deluxe Room", new Room("Deluxe Room", 5000));
    }

    public void searchAvailableRooms() {

        System.out.println("\nAvailable Rooms:\n");

        for (String type : inventory.getAllRooms().keySet()) {

            int available = inventory.getAvailability(type);

            // Show only rooms with availability > 0
            if (available > 0) {

                Room room = roomDetails.get(type);

                System.out.println("Room Type: " + room.getType());
                System.out.println("Price: ₹" + room.getPrice());
                System.out.println("Available: " + available);
                System.out.println("----------------------");
            }
        }
    }
}

// Main class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=== UC4: Room Search & Availability Check ===");

        RoomInventory inventory = new RoomInventory();

        SearchService searchService = new SearchService(inventory);

        // Perform search (no inventory modification)
        searchService.searchAvailableRooms();
    }
}