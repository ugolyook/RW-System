package com.sveta.init;

import com.sveta.route.TrainRun;
import com.sveta.serialization.CoreSerializer;
import com.sveta.serialization.CoreState;
import com.sveta.serialization.impl.config.SerializationConfig;
import com.sveta.serialization.impl.serializer.core.BaseCoreSerializer;
import com.sveta.serialization.impl.serializer.javaIo.SerializerFactory;
import com.sveta.train.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InitScript {
    static void main(String[] args) {
        CoreSerializer serializer = new BaseCoreSerializer(
                SerializerFactory.createAll(),
                SerializationConfig.defaultConfig());

        CoreState existing = serializer.load();
        List<Train> trains = new ArrayList<>(existing.trains());
        List<TrainRun> runs = new ArrayList<>(existing.trainRuns());

        List<Train> requiredTrains = InitData.createTrains();
        List<TrainRun> requiredRuns = InitData.createTrainRuns(requiredTrains);

        int addedTrains = addMissing(trains, requiredTrains, Train::getTrainNumber);
        int addedRuns = addMissing(runs, requiredRuns,
                r -> r.getTrain().getTrainNumber() + "@" + r.getDepartureTime());

        serializer.save(new CoreState(runs, trains));

        System.out.printf("Init done. Added trains: %d, runs: %d. Total: %d trains, %d runs%n",
                addedTrains, addedRuns, trains.size(), runs.size());
    }

    private static <T, K> int addMissing(List<T> target, List<T> required, Function<T, K> key) {
        List<K> existingKeys = target.stream().map(key).toList();
        int added = 0;
        for (T item : required) {
            if (!existingKeys.contains(key.apply(item))) {
                target.add(item);
                added++;
            }
        }
        return added;
    }
}