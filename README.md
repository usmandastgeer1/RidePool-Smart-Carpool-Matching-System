# RidePool-Smart-Carpool-Matching-System
RidePool is a Java-based DSA project that helps passengers find shared rides with other riders going toward the same destination. The system manages carpool rides, stores rider requests, and matches riders using ArrayList, Queue, and Recursion.

Project Description 🚖✨

RidePool is a smart ride-sharing system inspired by ride request applications. Unlike the sample project, which focuses on route optimization, this project focuses on carpool matching. The system allows drivers to add available carpool rides and passengers to submit ride requests. When a request is processed, the system searches for a suitable ride based on pickup location, destination, and available seats.

Main Functions of the System ⚙️

🚘 Add Available Carpool Ride:
A driver can add a ride by entering driver name, pickup area, destination, and available seats.
These rides are stored using an ArrayList.

🙋 Submit Rider Request:
A passenger can enter name, pickup location, destination, and required seats.
Requests are added into a Queue, so they are handled in first-come, first-served order.

🔄 Process Ride Requests:
The system takes the first request from the queue.
It searches available rides to find a matching carpool.
If a ride is found, the passenger is added to that ride.
If no ride is found, the request remains in the waiting queue.

🔍 Recursive Matching:
The system uses recursion to search through available rides.
It checks one ride at a time until a matching ride is found.

📋 Display Available Rides:
Shows all current carpool rides.
Displays driver name, pickup area, destination, available seats, and passenger list.

⏳ Display Waiting Requests:
Shows passengers still waiting for a matching ride.

🔎 Recursive Rider Search:
The system can search rider requests recursively by rider name.


Data Structures Used ✅

| Data Structure | Purpose                                                                     |
| -------------- | --------------------------------------------------------------------------- |
| **ArrayList**  | Stores available rides, all rider requests, and passengers inside each ride |
| **Queue**      | Stores pending ride requests in first-come, first-served order              |
| **Recursion**  | Used for searching rides and rider records                                  |


