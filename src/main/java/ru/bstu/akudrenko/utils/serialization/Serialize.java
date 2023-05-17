package ru.bstu.akudrenko.utils.serialization;

import java.lang.annotation.*;

@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Serialize { }
