import java.io.*;
import java.util.*;

class Passenger implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Train implements Serializable {
    private static final long serialVersionUID = 1L;
    private String trainNumber;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;

    public Train(String trainNumber, String source, String destination, int totalSeats) {
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeats(int seats) {
        if (availableSeats >= seats) {
            availableSeats -= seats;
            System.out.println(seats + " seats booked successfully.");
        } else {
            System.out.println("Insufficient seats available.");
        }
    }

    public void cancelSeats(int seats) {
        availableSeats += seats;
        System.out.println(seats + " seats canceled successfully.");
    }
}

public class RailwayManagementSystem {
    private static final String TRAINS_FILE = "trains.ser";
    private static final String PASSENGERS_FILE = "passengers.ser";

    private List<Train> trains;
    private Map<Integer, Passenger> passengers;
    private Scanner scanner;

    public RailwayManagementSystem() {
        trains = new ArrayList<>();
        passengers = new HashMap<>();
        scanner = new Scanner(System.in);
        loadTrains();
        loadPassengers();
    }

    public void addTrain() {
        System.out.println("Enter train number:");
        String trainNumber = scanner.nextLine();
        System.out.println("Enter source:");
        String source = scanner.nextLine();
        System.out.println("Enter destination:");
        String destination = scanner.nextLine();
        System.out.println("Enter total seats:");
        int totalSeats = scanner.nextInt();
        Train train = new Train(trainNumber, source, destination, totalSeats);
        trains.add(train);
        saveTrains();
    }

    public void bookTicket() {
        displayTrains();
        System.out.println("Enter train index to book ticket:");
        int trainIndex = scanner.nextInt();
        if (trainIndex >= 1 && trainIndex <= trains.size()) {
            Train train = trains.get(trainIndex - 1);
            System.out.println("Enter passenger name:");
            scanner.nextLine(); // consume newline
            String name = scanner.nextLine();
            System.out.println("Enter passenger age:");
            int age = scanner.nextInt();
            System.out.println("Enter number of seats to book:");
            int seats = scanner.nextInt();
            if (seats > 0 && seats <= train.getAvailableSeats()) {
                train.bookSeats(seats);
                passengers.put(passengers.size() + 1, new Passenger(name, age));
                savePassengers();
            } else {
                System.out.println("Invalid number of seats or insufficient seats available.");
            }
        } else {
            System.out.println("Invalid train index.");
        }
    }

    public void cancelTicket() {
        if (passengers.isEmpty()) {
            System.out.println("No bookings available to cancel.");
            return;
        }
        displayPassengers();
        System.out.println("Enter passenger ID to cancel ticket:");
        int passengerId = scanner.nextInt();
        if (passengerId >= 1 && passengerId <= passengers.size()) {
            Passenger passenger = passengers.remove(passengerId);
            Train train = trains.get(0); // Assuming passenger is always booked for the first train
            System.out.println("Enter number of seats to cancel:");
            int seats = scanner.nextInt();
            train.cancelSeats(seats);
            savePassengers();
        } else {
            System.out.println("Invalid passenger ID.");
        }
    }

    public void displayTrains() {
        System.out.println("Available Trains:");
        for (int i = 0; i < trains.size(); i++) {
            Train train = trains.get(i);
            System.out.println((i + 1) + ". " + train.getTrainNumber() + " - From " + train.getSource() + " to "
                    + train.getDestination() + ", Available Seats: " + train.getAvailableSeats());
        }
    }

    public void displayPassengers() {
        System.out.println("Passenger List:");
        for (Map.Entry<Integer, Passenger> entry : passengers.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue().getName() + ", Age: "
                    + entry.getValue().getAge());
        }
    }

    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    private void loadTrains() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRAINS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                trains = (List<Train>) obj;
            } else {
                System.out.println("Error loading trains data: Invalid data type in file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading trains data: " + e.getMessage());
        }
    }

    private void saveTrains() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRAINS_FILE))) {
            oos.writeObject(trains);
        } catch (IOException e) {
            System.out.println("Error saving trains data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    private void loadPassengers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PASSENGERS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof Map<?, ?>) {
                passengers = (Map<Integer, Passenger>) obj;
            } else {
                System.out.println("Error loading passengers data: Invalid data type in file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading passengers data: " + e.getMessage());
        }
    }

    private void savePassengers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PASSENGERS_FILE))) {
            oos.writeObject(passengers);
        } catch (IOException e) {
            System.out.println("Error saving passengers data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        RailwayManagementSystem system = new RailwayManagementSystem();

        while (true) {
            System.out.println("\n1. Add a train");
            System.out.println("2. Display available trains");
            System.out.println("3. Book a ticket");
            System.out.println("4. Cancel a ticket");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = system.scanner.nextInt();

            switch (choice) {
                case 1:
                    system.addTrain();
                    break;
                case 2:
                    system.displayTrains();
                    break;
                case 3:
                    system.bookTicket();
                    break;
                case 4:
                    system.cancelTicket();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
