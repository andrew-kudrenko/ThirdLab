package ru.bstu.akudrenko;

import ru.bstu.akudrenko.vehicles.base.Vehicle;
import ru.bstu.akudrenko.vehicles.factory.VehicleFactory;
import ru.bstu.akudrenko.vehicles.scanner.ConsoleVehicleScanner;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var factory = new VehicleFactory();
        var vehicleScanner = new ConsoleVehicleScanner();

        var optionsCount = getOptionsCount();
        List<Vehicle<?>> vehicles = new ArrayList<>(optionsCount);

        IntStream.range(0, optionsCount).forEach(n -> vehicles.add(factory.create(vehicleScanner.scanOptions())));

        System.out.println("\n" + "*".repeat(20) + "\nAll of the vehicles.");
        vehicles.forEach(v -> System.out.println(v + "\n"));

        vehicles.stream()
                .max(Comparator.comparingInt(Vehicle::getLoadCapacity))
                .ifPresent(v -> System.out.println("Vehicle with max load capacity.\n" + v));
    }

    private static int getOptionsCount() {
        System.out.print("Please enter count of options.\n> ");

        try {
            return Math.max(scanner.nextInt(), 0);
        } catch (Exception e) {
            return 0;
        }
    }
}