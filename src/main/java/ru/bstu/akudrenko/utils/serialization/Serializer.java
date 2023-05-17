package ru.bstu.akudrenko.utils.serialization;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Serializer {
    public static String toString(Object object) {
        var fields = stringifyMembers(
            member -> toStringKeyValuePair(object, member),
            filterMembers(object.getClass(), Class::getDeclaredFields)
        );

        var methods = stringifyMembers(
            member -> toStringKeyValuePair(object, member),
            filterMembers(object.getClass(), Class::getDeclaredMethods)
        );

        return String.format("`%s`:%s%s", object.getClass().getSimpleName(), fields, methods);
    }

    private static <M extends AccessibleObject> String stringifyMembers(Function<M, String> with, List<M> members) {
        return members.stream().reduce(
            "",
            (acc, member) -> {
                var pair = with.apply(member);
                return acc + (pair.isEmpty() ? pair : "\n\t" + pair);
            },
            String::concat
        );
    }

    private static <M extends AccessibleObject> List<M> filterMembers(Class<?> cls, Function<Class<?>, M[]> with) {
        List<M> serializableFields = new ArrayList<>();

        for (var field : with.apply(cls)) {
            field.trySetAccessible();

            if (hasAnnotation(field)) {
                serializableFields.add(field);
            }
        }

        var superclass = cls.getSuperclass();

        if (superclass != null) {
            serializableFields.addAll(filterMembers(superclass, with));
        }

        return serializableFields;
    }

    private static String toStringKeyValuePair(Object object, Field member) {
        try {
            return String.format("- %s: %s", member.getName(), member.get(object).toString());
        } catch (Exception e) {
            return "";
        }
    }

    private static String toStringKeyValuePair(Object object, Method member) {
        try {
            return String.format("~ %s() -> %s", member.getName(), member.invoke(object).toString());
        } catch (Exception e) {
            return "";
        }
    }

    private static boolean hasAnnotation(AnnotatedElement member) {
        return member.isAnnotationPresent(Serialize.class);
    }
}
