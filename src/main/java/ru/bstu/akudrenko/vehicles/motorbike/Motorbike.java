package ru.bstu.akudrenko.vehicles.motorbike;

import ru.bstu.akudrenko.utils.serialization.Serialize;
import ru.bstu.akudrenko.vehicles.base.Vehicle;

import static ru.bstu.akudrenko.vehicles.motorbike.MotorbikeCarriage.DEFAULT_LOAD_CAPACITY;

public class Motorbike extends Vehicle<MotorbikeInitOptions> {
    @Serialize
    private boolean hasCarriage = false;

    private MotorbikeCarriage carriage;

    @Override
    public void init(MotorbikeInitOptions options) {
        super.init(options);

        hasCarriage = options.hasCarriage;

        if (options.hasCarriage) {
            carriage = createDefaultCarriage();
        }
    }

    @Override
    public int getLoadCapacity() {
      return carriage == null ? loadCapacity : loadCapacity + carriage.getLoadCapacity();
    }

    private static MotorbikeCarriage createDefaultCarriage() {
        return new MotorbikeCarriage(DEFAULT_LOAD_CAPACITY);
    }
}
