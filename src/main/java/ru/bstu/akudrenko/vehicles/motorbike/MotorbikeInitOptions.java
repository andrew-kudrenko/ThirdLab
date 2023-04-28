package ru.bstu.akudrenko.vehicles.motorbike;

import ru.bstu.akudrenko.vehicles.base.VehicleInitOptions;
import ru.bstu.akudrenko.vehicles.scanner.Scannable;

public class MotorbikeInitOptions extends VehicleInitOptions {
    @Scannable(label = "Имеется ли коляска?")
    public boolean hasCarriage = false;
}
