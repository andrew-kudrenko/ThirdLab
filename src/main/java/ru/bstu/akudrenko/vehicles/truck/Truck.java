package ru.bstu.akudrenko.vehicles.truck;

import ru.bstu.akudrenko.utils.serialization.Serialize;
import ru.bstu.akudrenko.vehicles.base.Vehicle;

public class Truck extends Vehicle<TruckInitOptions> {
    @Serialize
    private boolean hasTrailer = false;

    @Override
    public void init(TruckInitOptions options) {
        super.init(options);

        hasTrailer = options.hasTrailer;
    }

    @Override
    public int getLoadCapacity() {
        return hasTrailer ? loadCapacity * 2 : loadCapacity;
    }
}
