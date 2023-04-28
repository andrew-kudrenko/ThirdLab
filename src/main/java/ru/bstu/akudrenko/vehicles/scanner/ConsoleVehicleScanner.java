package ru.bstu.akudrenko.vehicles.scanner;

import ru.bstu.akudrenko.vehicles.base.VehicleInitOptions;
import ru.bstu.akudrenko.vehicles.car.CarInitOptions;
import ru.bstu.akudrenko.vehicles.motorbike.MotorbikeInitOptions;
import ru.bstu.akudrenko.vehicles.truck.TruckInitOptions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class ConsoleVehicleScanner {
    private final List<String> availableOptionKinds = Arrays.asList(
            "Car",
            "Motorbike",
            "Truck"
    );
    private final Scanner scanner = new Scanner(System.in);

    public <Options extends VehicleInitOptions> Options scanOptions() {
        printAvailableOptionKinds();

        var options = (Options) createOptions(getOptionsKind());
        getScannableFields(options).forEach((f) -> assignOptionsField(f, options));

        return options;
    }

    private String getOptionsKind() {
        System.out.println("What kind of a vehicle?\n> ");
        return scanner.next().trim();
    }

    private void printAvailableOptionKinds() {
        System.out.println("It's possible to create one of these vehicles:");
        availableOptionKinds.forEach((k) -> System.out.println("\t~ " + k));
    }

    private void assignOptionsField(Field field, VehicleInitOptions options) {
        try {
            var typeName = field.getType().getName();
            var convert = getFromStringFunction(typeName);

            field.set(options, convert.apply(getRawFieldValue(field, typeName)));
        } catch (Exception e) {
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }

    private String getRawFieldValue(Field field, String typeName) {
        var label = field.getAnnotation(Scannable.class).label();
        var prelude = label + " `" + field.getName() + "` [" + typeName + "]> ";

        System.out.print(prelude);
        return scanner.next().trim();
    }

    private List<Field> getScannableFields(VehicleInitOptions options) {
        var fields = options.getClass().getFields();

        return Stream.of(fields).filter((f) -> f.isAnnotationPresent(Scannable.class)).toList();
    }

    private <T> Function<String, ?> getFromStringFunction(String typeName) {
        return switch (typeName) {
            case "java.lang.String" -> Function.identity();
            case "boolean" -> Boolean::parseBoolean;
            case "int" -> Integer::parseInt;
            case "char" -> (v) -> v.charAt(0);
            case "double" -> Double::parseDouble;
            default -> throw new IllegalArgumentException("Type `" + typeName + "` is not supported yet");
        };
    }

    private VehicleInitOptions createOptions(String kind) {
        return switch (kind) {
            case "Car" -> new CarInitOptions();
            case "Motorbike" -> new MotorbikeInitOptions();
            case "Truck" -> new TruckInitOptions();
            default -> throw new IllegalArgumentException("Creating options instance failed");
        };
    }
}
