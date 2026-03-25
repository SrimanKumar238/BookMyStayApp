import java.util.*;

// Reservation
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

// Thread-safe Inventory
class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 2);
    }

    // Critical section (synchronized)
    public synchronized boolean allocateRoom(String type) {

        int available = availability.getOrDefault(type, 0);

        if (available > 0) {
            availability.put(type, available - 1);
            return true;
        }
        return false;
    }
}

// Booking Processor (Thread)
class BookingProcessor implements Runnable {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (true) {

            Reservation r;

            // Critical section for queue access
            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                r = queue.poll();
            }

            if (r != null) {

                boolean success = inventory.allocateRoom(r.getRoomType());

                if (success) {
                    System.out.println(Thread.currentThread().getName()
                            + " booked room for " + r.getGuestName());
                } else {
                    System.out.println(Thread.currentThread().getName()
                            + " failed booking for " + r.getGuestName());
                }
            }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=== UC11: Concurrent Booking Simulation ===");

        Queue<Reservation> queue = new LinkedList<>();

        // Simulate multiple booking requests
        queue.offer(new Reservation("Alice", "Single Room"));
        queue.offer(new Reservation("Bob", "Single Room"));
        queue.offer(new Reservation("Charlie", "Single Room"));
        queue.offer(new Reservation("David", "Single Room"));

        RoomInventory inventory = new RoomInventory();

        // Create multiple threads
        Thread t1 = new Thread(new BookingProcessor(queue, inventory), "Thread-1");
        Thread t2 = new Thread(new BookingProcessor(queue, inventory), "Thread-2");

        t1.start();
        t2.start();
    }
}