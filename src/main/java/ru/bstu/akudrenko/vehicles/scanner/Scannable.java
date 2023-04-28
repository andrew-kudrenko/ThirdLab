package ru.bstu.akudrenko.vehicles.scanner;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scannable {
    String label() default "";
}
