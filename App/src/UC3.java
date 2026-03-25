import java.util.LinkedList;
import java.util.Queue;

// Reservation class (represents a booking request)
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

// Booking Request Queue (FIFO)
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added: "
                + reservation.getGuestName()
                + " -> " + reservation.getRoomType());
    }

    // Display all requests in FIFO order
    public void displayRequests() {
        System.out.println("\nBooking Requests in Queue:");

        for (Reservation r : queue) {
            System.out.println(r.getGuestName()
                    + " requested " + r.getRoomType());
        }
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=== UC5: Booking Request Queue (FIFO) ===");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Deluxe Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Double Room"));

        // Display queue (FIFO order)
        bookingQueue.displayRequests();
    }
}