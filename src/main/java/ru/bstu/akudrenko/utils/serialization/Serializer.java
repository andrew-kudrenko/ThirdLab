package ru.bstu.akudrenko.utils.serialization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    public static String toString(Object object) {
        var accumulator = "`" + object.getClass().getSimpleName() + "`:\n";

        for (var field : filterSerializableFields(object.getClass())) {
            var pair = toStringKeyValuePair(object, field);

            if (!pair.isEmpty()) {
                accumulator += pair + "\n";
            }
        }

        return accumulator;
    }

    private static List<Field> filterSerializableFields(Class<?> cls) {
        List<Field> serializableFields = new ArrayList<>();

        for (var field : cls.getDeclaredFields()) {
            field.trySetAccessible();

            if (hasAnnotation(field)) {
                serializableFields.add(field);
            }
        }

        if (cls.getSuperclass() != null) {
            serializableFields.addAll(filterSerializableFields(cls.getSuperclass()));
        }

        return serializableFields;
    }

    private static String toStringKeyValuePair(Object object, Field field) {
        try {
            return "\t- " + field.getName() + ": " + field.get(object).toString();
        } catch (IllegalAccessException e) {
            return "";
        }
    }

    private static boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(SerializeField.class);
    }
}
