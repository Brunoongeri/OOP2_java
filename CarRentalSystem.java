
/*Bruno Ongeri
BSE-01-0034/2025
10/3/2025
java car rental system program
*/


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class CAR { 
    String brand; 
    String model;
    String carID;
    boolean availability;
    double rentalFee;

    
    public CAR(String brand, String model, String carID, double rentalFee) { 
        this.brand = brand;
        this.model = model;
        this.carID = carID;
        this.rentalFee = rentalFee;
        this.availability = true;
    }

    
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getCarID() { return carID; } 
    public double getRentalFee() { return rentalFee; } 
    public boolean isAvailable() { return availability; } 

    
    public void notAvailable() { this.availability = false; } 
    public void available() { this.availability = true; }

    public double totalPay(int rentDays) { 
        return rentalFee * rentDays;
    }

    @Override
    public String toString() {
       
        return "car[registration number: " + carID + ", car Brand: " + brand + ", Car Model: " + model + 
               ", Price Per Day: $" + String.format("%.2f", rentalFee) + 
               ", Availability: " + (availability ? "available" : "rented") + "]";
    }
}

class CUSTOMER { 
    String customerName; 
    String customerID;

    public CUSTOMER(String customerName, String customerID) { 
        this.customerName = customerName;
        this.customerID = customerID;
    }

    public String getCustomerName() { return customerName; }
    public String getCustomerID() { return customerID; }

    @Override
    public String toString() {
        return "CUSTOMER {ID No: " + customerID + ", Customers' name: " + customerName + "]"; 
    }
}


class RENTAL_AGENCY { 
    private final List<CAR> cars;
    
    public RENTAL_AGENCY() {
        this.cars = new ArrayList<>();
    }

    public void addCar(CAR car) {
        cars.add(car);
    }

    public CAR findAvailableCar(String carID) {
        for (CAR car : cars) {
            if (car.getCarID().equalsIgnoreCase(carID) && car.isAvailable()) {
                return car;
            }
        }
        return null;
    }

    public CAR findAvailableCarByDetails(String brand, String model) {
        for (CAR car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand) && 
                car.getModel().equalsIgnoreCase(model) && 
                car.isAvailable()) {
                return car; 
            }
        }
        return null;
    }

    public void rentCar(CUSTOMER customer, CAR car, int rentDays) {
        if (car.isAvailable()) {
            car.notAvailable();

            double totalCost = car.totalPay(rentDays); 

            System.out.println(customer.getCustomerName() + " has rented " + car.getBrand() + " " + car.getModel() + " (" + car.getCarID() + ")");
            System.out.println("Total Fee: $" + String.format("%.2f", totalCost));
        } else {
            System.out.println("\n !!The requested car is not available for rent!!.");
        }
    }
}

public class CarRentalSystem {

    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        RENTAL_AGENCY agency = new RENTAL_AGENCY();

        // Initialize cars
        agency.addCar(new CAR("Toyota", "Spade", "T133", 50.00));
        agency.addCar(new CAR("Dodge", "Hellcat", "H255", 45.00));
        agency.addCar(new CAR("Mercedes", "Benz", "B399", 120.00));
        agency.addCar(new CAR("Tesla", "Model 3", "M130", 90.00));
        agency.addCar(new CAR("Ford", "Mustang", "H121", 63.00));
        agency.addCar(new CAR("Chevrolet", "Camaro", "RS22", 59.00));
        
        
        System.out.print("Enter the brand you are looking for: ");
        String checkBrand = scanner.nextLine();
        System.out.print("Enter the model you are looking for: ");
        String checkModel = scanner.nextLine();
        
        CAR carToRent = agency.findAvailableCarByDetails(checkBrand, checkModel);

        if (carToRent != null) {
            System.out.println("\n The car " + carToRent.getBrand() + " " + carToRent.getModel() + 
                               " (" + carToRent.getCarID() + ") is available for rental.");

            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();
            System.out.print("Enter your Customer ID: ");
            String customerID = scanner.nextLine();

                System.out.print("Enter number of days for rent: ");
               
                    int rentDays = scanner.nextInt();
                    scanner.nextLine(); 
                   
            CUSTOMER newCustomer = new CUSTOMER(customerName, customerID);
            agency.rentCar(newCustomer, carToRent, rentDays);

        } else {
            System.out.println("\n The car " + checkBrand + " " + checkModel + " is not available now.");
        }
        
       
        scanner.close();
    }
}