package ru.bstu.akudrenko.vehicles.motorbike;

public class MotorbikeCarriage {
    public static int DEFAULT_LOAD_CAPACITY = 100;

    private int loadCapacity;

    public MotorbikeCarriage(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }
}
