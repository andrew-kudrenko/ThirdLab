package ru.bstu.akudrenko.vehicles.base;

import ru.bstu.akudrenko.utils.serialization.Serialize;
import ru.bstu.akudrenko.utils.serialization.Serializer;

public abstract class Vehicle<Options extends VehicleInitOptions> {
    @Serialize
    protected String brand;

    @Serialize
    protected String licensePlate;

    @Serialize
    protected int topSpeed;

    protected int loadCapacity = 0;

    public void init(Options options) {
        brand = options.brand;
        licensePlate = options.licensePlate;
        topSpeed = options.topSpeed;
        loadCapacity = options.loadCapacity;
    }

    @Serialize
    public int getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public String toString() {
        return Serializer.toString(this);
    }
}
