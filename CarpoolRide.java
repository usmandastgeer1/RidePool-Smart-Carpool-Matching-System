import java.util.ArrayList;

public class CarpoolRide {

    int rideId;
    String driverName;
    String pickupArea;
    String destination;
    int totalSeats;
    int availableSeats;

    ArrayList<String> passengers;

    public CarpoolRide(int rideId, String driverName,
                       String pickupArea,
                       String destination,
                       int totalSeats) {

        this.rideId = rideId;
        this.driverName = driverName;
        this.pickupArea = pickupArea;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;

        passengers = new ArrayList<>();
    }

    public void addPassenger(String passengerName, int seatsBooked) {
        passengers.add(passengerName + " - Seats: " + seatsBooked);
        availableSeats -= seatsBooked;
    }

    public void display() {

        System.out.println("--------------------------------------");
        System.out.println("Ride ID: " + rideId);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Pickup Area: " + pickupArea);
        System.out.println("Destination: " + destination);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Available Seats: " + availableSeats);

        if (passengers.isEmpty()) {
            System.out.println("Passengers: No passengers assigned yet");
        } else {
            System.out.println("Passengers:");

            for (String passenger : passengers) {
                System.out.println(" - " + passenger);
            }
        }

        System.out.println("--------------------------------------");
    }
}
