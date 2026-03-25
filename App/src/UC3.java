import java.util.*;

// Service class (Add-On Service)
class Service {
    private String name;
    private int cost;

    public Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<Service>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());

        serviceMap.get(reservationId).add(service);

        System.out.println("Added service: " + service.getName()
                + " to Reservation: " + reservationId);
    }

    // Calculate total cost
    public int calculateTotalCost(String reservationId) {

        List<Service> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());

        int total = 0;

        for (Service s : services) {
            total += s.getCost();
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {

        List<Service> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());

        System.out.println("\nServices for Reservation: " + reservationId);

        for (Service s : services) {
            System.out.println(s.getName() + " - ₹" + s.getCost());
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=== UC7: Add-On Service Selection ===");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Example reservation IDs (from UC6 concept)
        String res1 = "RES-101";
        String res2 = "RES-102";

        // Add services
        manager.addService(res1, new Service("Breakfast", 500));
        manager.addService(res1, new Service("Airport Pickup", 1000));
        manager.addService(res2, new Service("Spa", 1500));

        // Display services and cost
        manager.displayServices(res1);
        manager.displayServices(res2);
    }
}