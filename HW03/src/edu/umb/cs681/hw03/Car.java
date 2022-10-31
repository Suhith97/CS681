package edu.umb.cs681.hw03;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class Car {
  private String carMake;
  private int carMileage;
  private int carYear;
  private int carPrice;
  private int dominationCount;
  public Car(String make, int year, int mileage, int price, int dominationCount) {
    this.carMake = make;
    this.carMileage = year;
    this.carYear = mileage;
    this.carPrice = price;
    this.dominationCount = dominationCount;
  }
  public int getDominationCount() {
    return this.dominationCount;
  }
  public String getMake() {
    return this.carMake;
  }
  public int getYear() {
    return this.carYear;
  }
  public int getPrice() {
    return this.carPrice;
  }
  public int getMileage() {
    return this.carMileage;
  }

  public static void main(String[] args) {
    List<Car> cars = new ArrayList<>();
    cars.add(new Car("Hyundai", 2022, 11, 200000, 1));
    cars.add(new Car("Honda", 2021, 15, 158900, 4));
    cars.add(new Car("Ferrari", 2022, 5, 29000000, 2));
    cars.add(new Car("Mazda", 2019, 12, 4008666, 3));

    System.out.println("Order by Mileage:");
    PrintStream ps = System.out;
    Objects.requireNonNull(ps);
    List<Car> orderByMileage = (List) cars.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(Collectors.toList());
    orderByMileage.forEach(ps::println);
    System.out.println("Order by Make:");
    List<Car> orderByMake = (List) cars.stream().sorted(Comparator.comparing(Car::getMake)).collect(Collectors.toList());
    orderByMake.forEach(ps::println);
    System.out.println("Order by Domination Count:");
    List<Car> orderByDomCount = (List) cars.stream().sorted(Comparator.comparingInt(Car::getDominationCount)).collect(Collectors.toList());
    orderByDomCount.forEach(ps::println);
    System.out.println("Order by Model:");
    List<Car> orderByPrice = (List) cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
    orderByPrice.forEach(ps::println);

    // Median price
    double medianPrice =  cars.stream().sorted( Comparator.comparing((Car car) -> car.getPrice()) )
            .map((Car car) -> car.getPrice()).skip(Math.max(0, ((cars.size() + 1) / 2) - 1))
            .limit(1 + (1 + cars.size()) % 2).mapToInt(Integer::intValue).average().getAsDouble();

    System.out.println("Median price: $"+ medianPrice);

    // Median year
    double medianYear =  cars.stream().sorted( Comparator.comparing((Car car) -> car.getYear()) )
            .map((Car car) -> car.getYear()).skip(Math.max(0, ((cars.size() + 1) / 2) - 1))
            .limit(1 + (1 + cars.size()) % 2).mapToInt(Integer::intValue).average().getAsDouble();

    System.out.println("Median year: "+ medianYear);

    // Median mileage
    double mileagePrice =  cars.stream().sorted( Comparator.comparing((Car car) -> car.getMileage()) )
            .map((Car car) -> car.getMileage()).skip(Math.max(0, ((cars.size() + 1) / 2) - 1))
            .limit(1 + (1 + cars.size()) % 2).mapToInt(Integer::intValue).average().getAsDouble();

    System.out.println("Median mileage: "+ mileagePrice+" miles");

    // Median domination counter
    double dominationMeidan =  cars.stream().sorted( Comparator.comparing((Car car) -> car.getDominationCount()) )
            .map((Car car) -> car.getDominationCount()).skip(Math.max(0, ((cars.size() + 1) / 2) - 1))
            .limit(1 + (1 + cars.size()) % 2).mapToInt(Integer::intValue).average().getAsDouble();

    System.out.println("Median domination: "+ dominationMeidan);

  }
}
