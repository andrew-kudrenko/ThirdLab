package ru.bstu.akudrenko.vehicles.base;

import ru.bstu.akudrenko.vehicles.scanner.Scannable;

public abstract class VehicleInitOptions {
    @Scannable(label = "Марка")
    public String brand;

    @Scannable(label = "Номер")
    public String licensePlate;

    @Scannable(label = "Максимальная скорость")
    public int topSpeed;

    @Scannable(label = "Грузоподъемность")
    public int loadCapacity;
}
