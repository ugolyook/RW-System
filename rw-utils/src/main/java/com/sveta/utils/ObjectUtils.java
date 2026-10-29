package main.java.com.sveta.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ObjectUtils {

    public static <T, R> R applyIfNotNull(@Nullable final T object,
                                          @NonNull final Function<T, R> function) {
        return object == null ? null : function.apply(object);
    }
}