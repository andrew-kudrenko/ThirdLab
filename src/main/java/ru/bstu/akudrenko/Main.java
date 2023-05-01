package ru.bstu.akudrenko;

import ru.bstu.akudrenko.vehicles.base.Vehicle;
import ru.bstu.akudrenko.vehicles.factory.VehicleFactory;
import ru.bstu.akudrenko.vehicles.scanner.ConsoleVehicleScanner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var factory = new VehicleFactory();
        var vehicleScanner = new ConsoleVehicleScanner();

        var optionsCount = getOptionsCount();
        List<Vehicle<?>> vehicles = new ArrayList<>(optionsCount);

        for (int idx = 0; idx < optionsCount; idx++) {
            vehicles.add(factory.create(vehicleScanner.scanOptions()));
        }

        vehicles.stream()
                .max(Comparator.comparingInt(Vehicle::getLoadCapacity))
                .ifPresent(v -> System.out.println("Vehicle with max load capacity.\n" + v));
    }

    private static int getOptionsCount() {
        System.out.println("Please enter count of options.");
        try {
            var count = scanner.nextInt();

            return Math.max(count, 0);
        } catch (Exception e) {
            return 0;
        }
    }
}