package com.sveta.serialization.impl.test;

import com.sveta.serialization.CoreEntitySerializationException;
import com.sveta.serialization.impl.config.StorageConfigManager;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.train.Train;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TrainSerializer {

    private String TRAIN_SAVE_PATH = "train";

    private final ObjectSerializer objectSerializer;
    private final StorageConfigManager pathManager;

    public void save(List<Train> trains) throws CoreEntitySerializationException {
        String pathToSave = getPathToSave(objectSerializer);
        objectSerializer.save(trains, pathToSave);
    }

    private String getPathToSave(ObjectSerializer objectSerializer) {
        var serializerPath = pathManager.getSerializerPath(objectSerializer);
        return serializerPath + TRAIN_SAVE_PATH;
    }

    public List<Train> load() throws CoreEntitySerializationException {
        var path = getPathToSave(objectSerializer.getFirst());
        TrainSerializationDto serializationDto = objectSerializer.load(path, TrainSerializationDto.class);
        return mapper.toObject(serializationDto);
    }
}
