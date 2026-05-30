public class RiderRequest {

    int requestId;
    String riderName;
    String pickup;
    String destination;
    int seatsNeeded;
    String status;

    public RiderRequest(int requestId,
                        String riderName,
                        String pickup,
                        String destination,
                        int seatsNeeded) {

        this.requestId = requestId;
        this.riderName = riderName;
        this.pickup = pickup;
        this.destination = destination;
        this.seatsNeeded = seatsNeeded;

        this.status = "Waiting";
    }

    public void display() {

        System.out.println("--------------------------------------");
        System.out.println("Request ID: " + requestId);
        System.out.println("Rider Name: " + riderName);
        System.out.println("Pickup: " + pickup);
        System.out.println("Destination: " + destination);
        System.out.println("Seats Needed: " + seatsNeeded);
        System.out.println("Status: " + status);
        System.out.println("--------------------------------------");
    }
}
