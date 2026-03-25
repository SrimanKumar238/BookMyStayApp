import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory (with validation)
class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
        availability.put("Deluxe Room", 1);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, -1);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {

        // Validate room type
        if (!availability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = availability.get(roomType);

        // Validate availability
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        // Update inventory
        availability.put(roomType, available - 1);
    }
}

// Validator / Booking Handler
class BookingHandler {

    private RoomInventory inventory;

    public BookingHandler(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBooking(String guestName, String roomType) {

        try {
            System.out.println("\nProcessing booking for " + guestName);

            // Attempt booking
            inventory.bookRoom(roomType);

            System.out.println("Booking successful for " + guestName
                    + " (" + roomType + ")");

        } catch (InvalidBookingException e) {

            // Graceful error handling
            System.out.println("Booking failed for " + guestName);
            System.out.println("Reason: " + e.getMessage());
        }
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("=== UC9: Error Handling & Validation ===");

        RoomInventory inventory = new RoomInventory();
        BookingHandler handler = new BookingHandler(inventory);

        // Valid booking
        handler.processBooking("Alice", "Single Room");

        // Invalid room type
        handler.processBooking("Bob", "Suite Room");

        // No availability case
        handler.processBooking("Charlie", "Deluxe Room");
        handler.processBooking("David", "Deluxe Room"); // should fail
    }
}