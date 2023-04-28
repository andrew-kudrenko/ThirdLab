package ru.bstu.akudrenko;

import ru.bstu.akudrenko.vehicles.base.Vehicle;
import ru.bstu.akudrenko.vehicles.factory.VehicleFactory;
import ru.bstu.akudrenko.vehicles.scanner.ConsoleVehicleScanner;
import ru.bstu.akudrenko.vehicles.truck.Truck;
import ru.bstu.akudrenko.vehicles.truck.TruckInitOptions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var factory = new VehicleFactory();

        var options = new TruckInitOptions();

        options.brand = "Chevy Cobalt";
        options.loadCapacity = 1172;
        options.licensePlate = "M069AN31RUS";
        options.topSpeed = 175;

        Truck chevyCobalt = factory.create(options);

        List<Vehicle<?>> vehicles = new ArrayList<>();

        vehicles.stream()
                .max(Comparator.comparingInt(Vehicle::getLoadCapacity))
                .ifPresent((v) -> System.out.println(v.getLoadCapacity()));

        //System.out.println("Instance " + chevyCobalt.getLoadCapacity());

        var scanner = new ConsoleVehicleScanner();

        //var options = scanner.scanOptions();

        System.out.println(chevyCobalt.toString());
    }
}