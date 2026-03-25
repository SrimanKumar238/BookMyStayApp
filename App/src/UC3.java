import java.util.*;

// Reservation (reused concept)
class Reservation {
    private String guestName;
    private String roomType;
    private String reservationId;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Booking History (List maintains order)
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed booking
    public void addBooking(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getAllBookings() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    private BookingHistory bookingHistory;

    public BookingReportService(BookingHistory bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    // Display all bookings
    public void showAllBookings() {

        System.out.println("\n=== Booking History ===");

        for (Reservation r : bookingHistory.getAllBookings()) {
            System.out.println("Reservation ID: " + r.getReservationId());
            System.out.println("Guest: " + r.getGuestName());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println("-------------------------");
        }
    }

    // Summary report
    public void generateSummary() {

        System.out.println("\n=== Booking Summary ===");

        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : bookingHistory.getAllBookings()) {
            String type = r.getRoomType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        for (String type : countMap.keySet()) {
            System.out.println(type + " booked: " + countMap.get(type));
        }
    }
}

// Main Class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=== UC8: Booking History & Reporting ===");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addBooking(new Reservation("RES-101", "Alice", "Single Room"));
        history.addBooking(new Reservation("RES-102", "Bob", "Deluxe Room"));
        history.addBooking(new Reservation("RES-103", "Charlie", "Single Room"));

        // Reporting
        BookingReportService reportService = new BookingReportService(history);

        reportService.showAllBookings();
        reportService.generateSummary();
    }
}