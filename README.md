# RailwayManagementSystem
![Screenshot 2024-05-20 002438](https://github.com/SofiaAkhtar/RailwayManagementSystem/assets/122672892/bb7eaee2-0f86-4bda-b8d4-50d4aa9e1821)
![Screenshot 2024-05-20 002448](https://github.com/SofiaAkhtar/RailwayManagementSystem/assets/122672892/5da75e2f-67d1-48e0-b716-dd984edba79b)
![Screenshot 2024-05-20 002459](https://github.com/SofiaAkhtar/RailwayManagementSystem/assets/122672892/9f8644a2-4750-4746-860c-4903e4b0b239)
![Screenshot 2024-05-20 002508](https://github.com/SofiaAkhtar/RailwayManagementSystem/assets/122672892/2ee8af3b-4dfd-4817-a23f-1a4d7acad153)
![Screenshot 2024-05-20 002514](https://github.com/SofiaAkhtar/RailwayManagementSystem/assets/122672892/584ed852-7949-432b-9f88-2c5c7974fcd5)

# Railway Management System

This is a simple Railway Management System implemented in Java. The system allows for adding new trains, booking and canceling tickets, and displaying available trains and passengers.

## Features

- **Add New Trains**: Input train details such as train number, source, destination, and total seats to add a new train to the system.
- **Display Available Trains**: View a list of all trains along with their details including available seats.
- **Book Tickets**: Book tickets for a train by specifying the passenger's name, age, and number of seats to book.
- **Cancel Tickets**: Cancel tickets by specifying the passenger ID and number of seats to cancel.
- **Persist Data**: The system saves train and passenger data to files (`trains.ser` and `passengers.ser`) to maintain state between program runs.

## Classes and Methods

### Passenger Class

**Attributes**:
- `name`: Name of the passenger.
- `age`: Age of the passenger.

**Constructor**:
- `Passenger(String name, int age)`: Initializes a new passenger with the specified name and age.

**Methods**:
- `getName()`: Returns the name of the passenger.
- `getAge()`: Returns the age of the passenger.

### Train Class

**Attributes**:
- `trainNumber`: Train number.
- `source`: Source station.
- `destination`: Destination station.
- `totalSeats`: Total number of seats in the train.
- `availableSeats`: Number of available seats.

**Constructor**:
- `Train(String trainNumber, String source, String destination, int totalSeats)`: Initializes a new train with the specified details.

**Methods**:
- `getTrainNumber()`: Returns the train number.
- `getSource()`: Returns the source station.
- `getDestination()`: Returns the destination station.
- `getTotalSeats()`: Returns the total number of seats.
- `getAvailableSeats()`: Returns the number of available seats.
- `bookSeats(int seats)`: Books the specified number of seats if available.
- `cancelSeats(int seats)`: Cancels the specified number of seats.

### RailwayManagementSystem Class

**Attributes**:
- `trains`: List of trains.
- `passengers`: Map of passengers.
- `scanner`: Scanner object for user input.

**Constructor**:
- `RailwayManagementSystem()`: Initializes the system, loads train and passenger data.

**Methods**:
- `addTrain()`: Adds a new train to the system.
- `bookTicket()`: Books a ticket for a passenger.
- `cancelTicket()`: Cancels a passenger's ticket.
- `displayTrains()`: Displays all available trains.
- `displayPassengers()`: Displays all passengers.
- `loadTrains()`: Loads train data from file.
- `saveTrains()`: Saves train data to file.
- `loadPassengers()`: Loads passenger data from file.
- `savePassengers()`: Saves passenger data to file.

### Contributing
Feel free to fork this repository and contribute by submitting pull requests. For major changes, please open an issue first to discuss what you would like to change.
