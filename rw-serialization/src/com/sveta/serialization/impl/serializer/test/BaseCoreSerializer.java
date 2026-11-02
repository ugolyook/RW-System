package com.sveta.serialization.impl.serializer.test;

import com.sveta.serialization.CoreEntitySerializationException;
import com.sveta.serialization.CoreSerializationDto;
import com.sveta.serialization.CoreSerializer;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.impl.config.SerializationConfig;
import storage.StoragePathService;

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
    public void save(CoreSerializationDto dto) throws CoreEntitySerializationException {
        trainSerializer.save(dto.trains());
        trainRunSerializer.save(dto.trainRuns());
    }

    @Override
    public CoreSerializationDto load() throws CoreEntitySerializationException {
        return new CoreSerializationDto(trainRunSerializer.load(), trainSerializer.load());
    }
}