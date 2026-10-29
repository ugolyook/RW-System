package com.sveta.serialization.impl.test;

import com.esotericsoftware.kryo.Serializer;
import com.sveta.route.TrainRun;
import com.sveta.serialization.CoreEntitySerializationException;
import com.sveta.serialization.CoreSerializationDto;
import com.sveta.serialization.CoreSerializer;
import com.sveta.serialization.impl.config.StorageConfigManager;
import com.sveta.train.Train;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

public class BaseCoreSerializer implements CoreSerializer {

    private final List<ObjectSerializer> objectSerializers;

    private final StorageConfigManager pathManager;

    private final TrainSerializer trainSerializer;
    private final TrainRunSerializer trainRunSerializer;


    public BaseCoreSerializer(List<ObjectSerializer> objectSerializers,
                              SerializationConfig config) {
        this.objectSerializers = objectSerializers;
        this.pathManager = new StorageConfigManager(config);
        this.objectSerializers = new TrainSerializer(pathManager, objectSerializers);
    }

    @Override
    public void save(CoreSerializationDto dto) throws CoreEntitySerializationException {
        trainSerializer.save(dto.trains());
        trainRunSerializer.save(dto.trainRuns());
    }

    @Override
    public CoreSerializationDto load() throws CoreEntitySerializationException {
        List<Train> trains = trainSerializer.load();
        List<TrainRun> run = trainRunSerializer.load();
        return new CoreSerializationDto(run, trains);
    }
}
