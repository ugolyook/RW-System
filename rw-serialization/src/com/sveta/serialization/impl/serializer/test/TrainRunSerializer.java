package com.sveta.serialization.impl.serializer.test;

import com.sveta.route.TrainRun;
import com.sveta.serialization.ObjectSerializer;
import storage.StoragePathService;
import com.sveta.serialization.impl.dto.TrainRunSerializationDto;
import com.sveta.serialization.impl.mapper.TrainRunMapper;

import java.util.List;

public class TrainRunSerializer extends AbstractEntitySerializer<TrainRun, TrainRunSerializationDto> {
    public TrainRunSerializer(StoragePathService p, List<ObjectSerializer> s) {
        super(p, s);
    }

    @Override
    protected String folderName() {
        return "train-runs";
    }

    @Override
    protected Class<TrainRunSerializationDto> dtoClass() {
        return TrainRunSerializationDto.class;
    }

    @Override
    protected TrainRunSerializationDto toDto(TrainRun r) {
        return TrainRunMapper.toDto(r);
    }

    @Override
    protected TrainRun toDomain(TrainRunSerializationDto d) {
        return TrainRunMapper.toDomain(d);
    }
}