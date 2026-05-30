import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class RidePoolApp {

    private static final Scanner input = new Scanner(System.in);

    // Data Structure 1: ArrayList
    private static final ArrayList<CarpoolRide> availableRides = new ArrayList<>();
    private static final ArrayList<RiderRequest> allRequests = new ArrayList<>();

    // Data Structure 2: Queue
    private static final Queue<RiderRequest> requestQueue = new ArrayDeque<>();

    private static int nextRideId = 1;
    private static int nextRequestId = 1;

    public static void main(String[] args) {

        addSampleData();

        int choice;

        do {
            showMenu();
            choice = readPositiveOrZeroInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addAvailableRide();
                    break;

                case 2:
                    submitRiderRequest();
                    break;

                case 3:
                    processNextRequest();
                    break;

                case 4:
                    displayAvailableRides();
                    break;

                case 5:
                    displayWaitingRequests();
                    break;

                case 6:
                    searchRiderRequest();
                    break;

                case 7:
                    displayAllRequests();
                    break;

                case 0:
                    System.out.println("\nThank you for using RidePool. Goodbye!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    private static void showMenu() {

        System.out.println("\n======================================");
        System.out.println("        RidePool Carpool System       ");
        System.out.println("======================================");
        System.out.println("1. Add Available Carpool Ride");
        System.out.println("2. Submit Rider Request");
        System.out.println("3. Process Next Ride Request");
        System.out.println("4. Display Available Rides");
        System.out.println("5. Display Waiting Requests");
        System.out.println("6. Search Rider Request by Name");
        System.out.println("7. Display All Requests");
        System.out.println("0. Exit");
        System.out.println("======================================");
    }

    private static void addSampleData() {

        availableRides.add(
                new CarpoolRide(nextRideId++,
                        "Ali",
                        "UCP",
                        "Johar Town",
                        4));

        availableRides.add(
                new CarpoolRide(nextRideId++,
                        "Ahmed",
                        "Model Town",
                        "Liberty Market",
                        3));

        availableRides.add(
                new CarpoolRide(nextRideId++,
                        "Sara",
                        "Wapda Town",
                        "UCP",
                        2));
    }

    private static void addAvailableRide() {

        System.out.println("\n--- Add Available Carpool Ride ---");

        String driverName = readNonEmptyString("Enter driver name: ");
        String pickupArea = readNonEmptyString("Enter pickup area: ");
        String destination = readNonEmptyString("Enter destination: ");
        int seats = readPositiveInt("Enter available seats: ");

        CarpoolRide ride = new CarpoolRide(
                nextRideId++,
                driverName,
                pickupArea,
                destination,
                seats);

        availableRides.add(ride);

        System.out.println("\nCarpool ride added successfully.");
    }

    private static void submitRiderRequest() {

        System.out.println("\n--- Submit Rider Request ---");

        String riderName = readNonEmptyString("Enter rider name: ");
        String pickup = readNonEmptyString("Enter pickup location: ");
        String destination = readNonEmptyString("Enter destination: ");
        int seatsNeeded = readPositiveInt("Enter seats needed: ");

        RiderRequest request = new RiderRequest(
                nextRequestId++,
                riderName,
                pickup,
                destination,
                seatsNeeded);

        requestQueue.add(request);
        allRequests.add(request);

        System.out.println("\nRide request submitted successfully.");
        System.out.println("Your request ID is: " + request.requestId);
    }

    private static void processNextRequest() {

        System.out.println("\n--- Process Next Ride Request ---");

        if (requestQueue.isEmpty()) {
            System.out.println("No pending ride requests.");
            return;
        }

        RiderRequest request = requestQueue.poll();

        System.out.println("\nProcessing request:");
        request.display();

        CarpoolRide matchedRide =
                findMatchingRideRecursive(
                        availableRides,
                        request,
                        0);

        if (matchedRide != null) {

            matchedRide.addPassenger(
                    request.riderName,
                    request.seatsNeeded);

            request.status =
                    "Matched with Ride ID " +
                            matchedRide.rideId;

            System.out.println("\nMatch found!");
            System.out.println("Rider assigned to Ride ID: "
                    + matchedRide.rideId);

            System.out.println("Driver Name: "
                    + matchedRide.driverName);

        } else {

            request.status =
                    "Waiting - No matching ride found";

            requestQueue.add(request);

            System.out.println("\nNo matching ride found.");
            System.out.println(
                    "Request has been placed back in the waiting queue.");
        }
    }

    private static void displayAvailableRides() {

        System.out.println("\n--- Available Carpool Rides ---");

        if (availableRides.isEmpty()) {
            System.out.println("No available rides.");
            return;
        }

        for (CarpoolRide ride : availableRides) {
            ride.display();
        }
    }

    private static void displayWaitingRequests() {

        System.out.println("\n--- Waiting Ride Requests ---");

        if (requestQueue.isEmpty()) {
            System.out.println("No waiting requests.");
            return;
        }

        for (RiderRequest request : requestQueue) {
            request.display();
        }
    }

    private static void searchRiderRequest() {

        System.out.println("\n--- Search Rider Request by Name ---");

        if (allRequests.isEmpty()) {
            System.out.println("No rider requests available.");
            return;
        }

        String name =
                readNonEmptyString(
                        "Enter rider name to search: ");

        boolean found =
                searchRiderRecursive(
                        allRequests,
                        name,
                        0);

        if (!found) {
            System.out.println(
                    "\nNo request found for rider name: "
                            + name);
        }
    }

    private static void displayAllRequests() {

        System.out.println("\n--- All Rider Requests ---");

        if (allRequests.isEmpty()) {
            System.out.println("No requests found.");
            return;
        }

        displayRequestsRecursive(allRequests, 0);
    }

    private static CarpoolRide findMatchingRideRecursive(
            ArrayList<CarpoolRide> rides,
            RiderRequest request,
            int index) {

        if (index >= rides.size()) {
            return null;
        }

        CarpoolRide currentRide = rides.get(index);

        boolean pickupMatches =
                isSimilarLocation(
                        currentRide.pickupArea,
                        request.pickup);

        boolean destinationMatches =
                isSimilarLocation(
                        currentRide.destination,
                        request.destination);

        boolean seatsAvailable =
                currentRide.availableSeats
                        >= request.seatsNeeded;

        if (pickupMatches &&
                destinationMatches &&
                seatsAvailable) {

            return currentRide;
        }

        return findMatchingRideRecursive(
                rides,
                request,
                index + 1);
    }

    private static boolean searchRiderRecursive(
            ArrayList<RiderRequest> requests,
            String name,
            int index) {

        if (index >= requests.size()) {
            return false;
        }

        RiderRequest currentRequest =
                requests.get(index);

        boolean found =
                searchRiderRecursive(
                        requests,
                        name,
                        index + 1);

        if (currentRequest.riderName
                .equalsIgnoreCase(name)) {

            System.out.println("\nRequest found:");
            currentRequest.display();
            return true;
        }

        return found;
    }

    private static void displayRequestsRecursive(
            ArrayList<RiderRequest> requests,
            int index) {

        if (index >= requests.size()) {
            return;
        }

        requests.get(index).display();

        displayRequestsRecursive(
                requests,
                index + 1);
    }

    private static boolean isSimilarLocation(
            String locationOne,
            String locationTwo) {

        String first =
                locationOne.trim().toLowerCase();

        String second =
                locationTwo.trim().toLowerCase();

        return first.equals(second)
                || first.contains(second)
                || second.contains(first);
    }

    private static String readNonEmptyString(
            String message) {

        String value;

        while (true) {

            System.out.print(message);

            value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "Input cannot be empty. Please try again.");
        }
    }

    private static int readPositiveInt(
            String message) {

        int number;

        while (true) {

            System.out.print(message);

            try {

                number = Integer.parseInt(
                        input.nextLine());

                if (number > 0) {
                    return number;
                }

                System.out.println(
                        "Please enter a positive number.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number.");
            }
        }
    }

    private static int readPositiveOrZeroInt(
            String message) {

        int number;

        while (true) {

            System.out.print(message);

            try {

                number = Integer.parseInt(
                        input.nextLine());

                if (number >= 0) {
                    return number;
                }

                System.out.println(
                        "Please enter zero or a positive number.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number.");
            }
        }
    }
}
