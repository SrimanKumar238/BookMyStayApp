import java.util.*;

// Room Domain Model
class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}

// Inventory (State Holder - READ ONLY ACCESS)
class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    public void addRoom(String type, int count) {
        availability.put(type, count);
    }

    public int getAvailableCount(String type) {
        return availability.getOrDefault(type, 0);
    }
}

// Search Service (NO MODIFICATION)
class SearchService {

    public void searchRooms(Inventory inventory, List<Room> rooms) {

        System.out.println("\n--- Available Rooms ---");

        for (Room room : rooms) {

            int count = inventory.getAvailableCount(room.getType());

            // Validation (Defensive Programming)
            if (count > 0) {
                System.out.println("Type      : " + room.getType());
                System.out.println("Price     : " + room.getPrice());
                System.out.println("Amenities : " + room.getAmenities());
                System.out.println("Available : " + count);
                System.out.println("----------------------");
            }
        }
    }
}

// Main Class
public class UseCase3RoomSearch {

    public static void main(String[] args) {

        // Inventory Setup
        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Double", 0); // should NOT display
        inventory.addRoom("Suite", 3);

        // Room Details (Domain Objects)
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Single", 2000, "AC, WiFi"));
        rooms.add(new Room("Double", 3500, "AC, TV"));
        rooms.add(new Room("Suite", 5000, "AC, WiFi, TV, Mini Bar"));

        // Search (Read-only)
        SearchService service = new SearchService();
        service.searchRooms(inventory, rooms);
    }
}