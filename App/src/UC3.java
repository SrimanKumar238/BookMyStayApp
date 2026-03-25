import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}

// Inventory Service
class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 1);
        availability.put("Deluxe Room", 0);
    }

    public void increaseRoom(String type) {
        availability.put(type, availability.getOrDefault(type, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : availability.keySet()) {
            System.out.println(type + ": " + availability.get(type));
        }
    }
}

// Cancellation Service (UC10 Core)
class CancellationService {

    private Map<String, Reservation> confirmedBookings;
    private Stack<String> rollbackStack;
    private RoomInventory inventory;

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.confirmedBookings = new HashMap<>();
        this.rollbackStack = new Stack<>();
    }

    // Add confirmed booking (simulate UC6 result)
    public void addBooking(Reservation r) {
        confirmedBookings.put(r.getReservationId(), r);
    }

    // Cancel booking
    public void cancelBooking(String reservationId) {

        System.out.println("\nProcessing cancellation for: " + reservationId);

        // Validate existence
        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }

        Reservation r = confirmedBookings.get(reservationId);

        // Push to rollback stack
        rollbackStack.push(r.getRoomId());

        // Restore inventory
        inventory.increaseRoom(r.getRoomType());

        // Remove booking
        confirmedBookings.remove(reservationId);

        System.out.println("Cancellation successful.");
        System.out.println("Released Room ID: " + r.getRoomId());
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=== UC10: Booking Cancellation & Inventory Rollback ===");

        RoomInventory inventory = new RoomInventory();

        CancellationService service = new CancellationService(inventory);

        // Simulate confirmed bookings
        service.addBooking(new Reservation("RES-101", "Single Room", "SingleRoom-1"));
        service.addBooking(new Reservation("RES-102", "Deluxe Room", "DeluxeRoom-1"));

        // Cancel booking
        service.cancelBooking("RES-101");

        // Invalid cancellation
        service.cancelBooking("RES-999");

        // Display inventory after rollback
        inventory.displayInventory();
    }
}