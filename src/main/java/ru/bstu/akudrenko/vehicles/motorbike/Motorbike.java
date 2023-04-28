package ru.bstu.akudrenko.vehicles.motorbike;

import ru.bstu.akudrenko.utils.serialization.SerializeField;
import ru.bstu.akudrenko.vehicles.base.Vehicle;

import java.util.Optional;

import static ru.bstu.akudrenko.vehicles.motorbike.MotorbikeCarriage.DEFAULT_LOAD_CAPACITY;

public class Motorbike extends Vehicle<MotorbikeInitOptions> {
    @SerializeField
    private boolean hasCarriage = false;

    private Optional<MotorbikeCarriage> carriage = Optional.empty();

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
      return carriage.isEmpty() ? loadCapacity : loadCapacity + carriage.get().getLoadCapacity();
    }

    private Optional<MotorbikeCarriage> createDefaultCarriage() {
        return Optional.of(new MotorbikeCarriage(DEFAULT_LOAD_CAPACITY));
    }
}
