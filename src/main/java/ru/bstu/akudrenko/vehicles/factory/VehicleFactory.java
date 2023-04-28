package ru.bstu.akudrenko.vehicles.factory;

import ru.bstu.akudrenko.vehicles.base.Vehicle;
import ru.bstu.akudrenko.vehicles.base.VehicleInitOptions;
import ru.bstu.akudrenko.vehicles.car.Car;
import ru.bstu.akudrenko.vehicles.car.CarInitOptions;
import ru.bstu.akudrenko.vehicles.motorbike.Motorbike;
import ru.bstu.akudrenko.vehicles.motorbike.MotorbikeInitOptions;
import ru.bstu.akudrenko.vehicles.truck.Truck;
import ru.bstu.akudrenko.vehicles.truck.TruckInitOptions;

import java.util.AbstractMap;
import java.util.Map;

public class VehicleFactory {
    private final Map<String, Class<? extends Vehicle>> constructors = Map.ofEntries(
        new AbstractMap.SimpleImmutableEntry<>(CarInitOptions.class.getName(), Car.class),
        new AbstractMap.SimpleImmutableEntry<>(MotorbikeInitOptions.class.getName(), Motorbike.class),
        new AbstractMap.SimpleImmutableEntry<>(TruckInitOptions.class.getName(), Truck.class)
    );

    public <Instance extends Vehicle<Options>, Options extends VehicleInitOptions> Instance create(Options options) {
        var constructor = constructors.get(options.getClass().getName());

        try {
            Instance instance = (Instance) constructor.getDeclaredConstructor().newInstance();
            instance.init(options);

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Creating with factory failed");
        }
    }
}
