package edu.umb.cs681.hw04;


import java.util.ArrayList; 
import java.util.List;

public class Car {
	
	private String carManufacturer;
	
	private String model;
    
	private int mileage;
    
	private int year;
    
	private int price;
    
	private int dominationCount;
    
    public Car(String make, String model, int year, int mileage, int price, int dominationCount) {
		this.carManufacturer = make;
		this.model = model;
		this.mileage = year;
		this.year = mileage;
		this.price = price;
		this.dominationCount = dominationCount;
    }
    
    public int getDomCount() {
    	return this.dominationCount; 
    }
    
    public String getMake() {
    	return this.carManufacturer; 
    }
    
    public String getModel() {
    	return this.model; 
    
    }
    public int getYear() {
    	return this.year; 
    }
    public int getPrice() {
    	return this.price; 
    }
    public int getMileage() {
    	return this.mileage; 
    }
    
    private static int getExpensive(List<Car> listOfCars) {
    	int expensive = listOfCars.stream()
				.map((Car car) ->car.getPrice())
				.reduce(0, (result,price)->result > price ? result : price); 
    	return expensive;
    }
    
    private static int getCheapest(List<Car> listOfCars) {
    	int expensive = listOfCars.stream()
    			.map((Car car) ->car.getPrice())
    			.reduce(1000000000, (result, price)->price > result ? result :price);
    	return expensive;
    }
    
    private static int getAverageCost(List<Car> listOfCars) {
    	return listOfCars.stream()
    			.map((Car car) ->car.getPrice())
    			.reduce(0, (result,price) -> result+price, (finalResult,intermediateResult) -> finalResult)/listOfCars.size();
    }
    
    
	public static void main(String[] args) {
    	List<Car> listOfCars = createCars();
    	
		int expensive = getExpensive(listOfCars);
		
		System.out.println("Price of most expensive car is: $"+expensive);
		
		int cheapest = getCheapest(listOfCars);
		System.out.println("Price of cheapest car is: $"+cheapest);
		
		int average_cost_of_all_cars = getAverageCost(listOfCars);
		System.out.println("Avg price for the available cars is: $" + average_cost_of_all_cars);
	}
    
    private static List<Car> createCars() {
    	List<Car> cars = new ArrayList<Car>();
    	cars.add(new Car("Nissan", "Sentra", 2016, 9, 12000000, 3)); 
    	cars.add(new Car("Volkswagen", "Polo", 2017, 7, 14000000, 2));
    	cars.add(new Car("Buggati", "Vyron", 2022, 6, 15500000, 1));
    	cars.add(new Car("Mercedes", "S-Class", 2019, 12, 13300000, 4));
    	return cars;
    }
}