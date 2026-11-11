package com.sveta.init;

import com.sveta.init.data.InitRoutes;
import com.sveta.init.data.InitTrainRuns;
import com.sveta.init.data.InitTrains;
import com.sveta.route.TrainRun;
import com.sveta.serialization.CoreSerializer;
import com.sveta.serialization.CoreState;
import com.sveta.serialization.impl.config.SerializationConfig;
import com.sveta.serialization.impl.serializer.core.BaseCoreSerializer;
import com.sveta.serialization.impl.serializer.javaIo.SerializerFactory;
import com.sveta.train.Train;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class InitScript {
    private final CoreSerializer serializer;

    public InitScript() {
        this(createSerializer());
    }

    InitScript(CoreSerializer serializer) {
        this.serializer = serializer;
    }

    static void main() {
        new InitScript().run();
    }

    void run() {
        CoreState storedState = serializer.load();

        List<Train> initialTrains = new InitTrains().create();
        List<TrainRun> initialRuns = new InitTrainRuns(new InitRoutes()).create(initialTrains);


        List<Train> mergedTrains = withMissing(storedState.trains(), initialTrains, Train::getTrainNumber);
        List<TrainRun> mergedRuns = withMissing(storedState.trainRuns(), initialRuns, InitScript::runKey);

        serializer.save(new CoreState(mergedRuns, mergedTrains));

        serializer.save(new CoreState(mergedRuns, mergedTrains));
        log.info("Init done. Added trains: {}, runs: {}. Total: {} trains, {} runs",
                mergedTrains.size() - storedState.trains().size(),
                mergedRuns.size() - storedState.trainRuns().size(),
                mergedTrains.size(),
                mergedRuns.size());

    }

    private static CoreSerializer createSerializer() {
        return new BaseCoreSerializer(
                SerializerFactory.createAll(),
                SerializationConfig.defaultConfig());
    }

    private static String runKey(TrainRun run) {
        return run.getTrain().getTrainNumber() + "@" + run.getDepartureTime();
    }

    private static <T, K> List<T> withMissing(List<T> stored, List<T> initial, Function<T, K> key) {
        Set<K> storedKeys = stored.stream()
                .map(key)
                .collect(Collectors.toSet());

        List<T> missing = initial.stream()
                .filter(item -> !storedKeys.contains(key.apply(item)))
                .toList();

        return Stream.concat(stored.stream(), missing.stream()).toList();
    }
}