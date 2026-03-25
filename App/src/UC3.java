import java.io.*;
import java.util.*;

// Reservation (Serializable)
class Reservation implements Serializable {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType;
    }
}

// Inventory (Serializable)
class RoomInventory implements Serializable {
    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
        availability.put("Deluxe Room", 1);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String type : availability.keySet()) {
            System.out.println(type + ": " + availability.get(type));
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // Save data
    public void save(List<Reservation> bookings, RoomInventory inventory) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(bookings);
            out.writeObject(inventory);

            System.out.println("\nData saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data
    public Object[] load() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            List<Reservation> bookings = (List<Reservation>) in.readObject();
            RoomInventory inventory = (RoomInventory) in.readObject();

            System.out.println("\nData loaded successfully.");

            return new Object[]{bookings, inventory};

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=== UC12: Data Persistence & Recovery ===");

        PersistenceService service = new PersistenceService();

        // Try loading previous state
        Object[] data = service.load();

        List<Reservation> bookings;
        RoomInventory inventory;

        if (data != null) {
            bookings = (List<Reservation>) data[0];
            inventory = (RoomInventory) data[1];
        } else {
            bookings = new ArrayList<>();
            inventory = new RoomInventory();

            // Simulate new bookings
            bookings.add(new Reservation("RES-101", "Alice", "Single Room"));
            bookings.add(new Reservation("RES-102", "Bob", "Deluxe Room"));
        }

        // Display current state
        System.out.println("\nBooking History:");
        for (Reservation r : bookings) {
            System.out.println(r);
        }

        inventory.display();

        // Save state before exit
        service.save(bookings, inventory);
    }
}