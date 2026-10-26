package main.java.com.sveta.utils;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ObjectUtils {
    private ObjectUtils() {
    }

    public static <T, R> R applyIfNotNull(@Nullable final T object,
                                          @NonNull final Function<T, R> function) {
        return object == null ? null : function.apply(object);
    }
}