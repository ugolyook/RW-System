package main.java.com.sveta.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapListUtils {
    public static <S, T> List<T> mapList(List<S> source, Function<? super S, ? extends T> mapper) {
        if (source == null) return null;
        return source.stream()
                .map(mapper)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}