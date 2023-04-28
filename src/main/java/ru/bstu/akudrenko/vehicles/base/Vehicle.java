package ru.bstu.akudrenko.vehicles.base;

import ru.bstu.akudrenko.utils.serialization.SerializeField;
import ru.bstu.akudrenko.utils.serialization.Serializer;

public abstract class Vehicle<Options extends VehicleInitOptions> {
    @SerializeField
    protected String brand;

    @SerializeField
    protected String licensePlate;

    @SerializeField
    protected int topSpeed;

    @SerializeField
    protected int loadCapacity = 0;

    public void init(Options options) {
        brand = options.brand;
        licensePlate = options.licensePlate;
        topSpeed = options.topSpeed;
        loadCapacity = options.loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public String toString() {
        return Serializer.toString(this);
    }
}
