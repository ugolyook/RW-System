package com.sveta.serialization.impl.serializer.test;

import com.sveta.serialization.CoreEntitySerializationException;
import com.sveta.serialization.CoreState;
import com.sveta.serialization.CoreSerializer;
import com.sveta.serialization.impl.serializer.ObjectSerializer;
import com.sveta.serialization.impl.config.SerializationConfig;
import com.sveta.serialization.impl.storage.StoragePathService;

import java.util.List;

public class BaseCoreSerializer implements CoreSerializer {
    private final TrainSerializer trainSerializer;
    private final TrainRunSerializer trainRunSerializer;


    public BaseCoreSerializer(List<ObjectSerializer> objectSerializers,
                              SerializationConfig config) {
        StoragePathService pathService = new StoragePathService(config);
        this.trainSerializer = new TrainSerializer(pathService, objectSerializers);
        this.trainRunSerializer = new TrainRunSerializer(pathService, objectSerializers);
    }

    @Override
    public void save(CoreState state) throws CoreEntitySerializationException {
        trainSerializer.save(state.trains());
        trainRunSerializer.save(state.trainRuns());
    }

    @Override
    public CoreState load() throws CoreEntitySerializationException {
        return new CoreState(trainRunSerializer.load(), trainSerializer.load());
    }
}