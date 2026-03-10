public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        Welcome to BookMyStayApp ");
        System.out.println("=================================");

        System.out.println("\nAvailable Room Types:\n");

        // Static room data
        String room1 = "Single Room";
        int price1 = 1500;
        boolean available1 = true;

        String room2 = "Double Room";
        int price2 = 2500;
        boolean available2 = true;

        String room3 = "Deluxe Room";
        int price3 = 4000;
        boolean available3 = false;

        // Display rooms
        System.out.println("1. " + room1 + " - ₹" + price1 + "/night - " +
                (available1 ? "Available" : "Not Available"));

        System.out.println("2. " + room2 + " - ₹" + price2 + "/night - " +
                (available2 ? "Available" : "Not Available"));

        System.out.println("3. " + room3 + " - ₹" + price3 + "/night - " +
                (available3 ? "Available" : "Not Available"));
    }
}