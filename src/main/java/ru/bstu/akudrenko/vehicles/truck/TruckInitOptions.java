package ru.bstu.akudrenko.vehicles.truck;

import ru.bstu.akudrenko.vehicles.base.VehicleInitOptions;
import ru.bstu.akudrenko.vehicles.scanner.Scannable;

public class TruckInitOptions extends VehicleInitOptions {
    @Scannable(label = "Имеется ли прицеп?")
    public boolean hasTrailer = false;
}
