import java.util.*;

// Reservation (from UC5)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory Service (from UC3)
class RoomInventory {
    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
        availability.put("Deluxe Room", 1);
        availability.put("Double Room", 1);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

// Booking Service (UC6 Core Logic)
class BookingService {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    // Track allocated room IDs
    private Map<String, Set<String>> allocatedRooms;

    public BookingService(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
    }

    // Generate unique room ID
    private String generateRoomId(String type, int count) {
        return type.replace(" ", "") + "-" + count;
    }

    // Process queue
    public void processBookings() {

        System.out.println("\nProcessing Bookings...\n");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();
            String type = r.getRoomType();

            int available = inventory.getAvailability(type);

            if (available > 0) {

                // Get allocated set for this type
                allocatedRooms.putIfAbsent(type, new HashSet<>());

                Set<String> roomSet = allocatedRooms.get(type);

                // Generate unique ID
                String roomId = generateRoomId(type, roomSet.size() + 1);

                // Ensure uniqueness (Set prevents duplicates)
                roomSet.add(roomId);

                // Update inventory
                inventory.reduceRoom(type);

                System.out.println("Booking Confirmed for " + r.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Assigned Room ID: " + roomId);
                System.out.println("----------------------------");

            } else {
                System.out.println("Booking Failed for " + r.getGuestName()
                        + " (No rooms available for " + type + ")");
                System.out.println("----------------------------");
            }
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=== UC6: Reservation Confirmation & Room Allocation ===");

        // Step 1: Create queue (UC5)
        Queue<Reservation> queue = new LinkedList<>();

        queue.offer(new Reservation("Alice", "Single Room"));
        queue.offer(new Reservation("Bob", "Deluxe Room"));
        queue.offer(new Reservation("Charlie", "Single Room"));
        queue.offer(new Reservation("David", "Single Room")); // should fail

        // Step 2: Inventory (UC3)
        RoomInventory inventory = new RoomInventory();

        // Step 3: Booking Service
        BookingService bookingService = new BookingService(queue, inventory);

        // Step 4: Process bookings
        bookingService.processBookings();
    }
}