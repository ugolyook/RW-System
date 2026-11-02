package com.sveta.serialization.impl.serializer.test;

import com.sveta.serialization.ObjectSerializer;
import storage.StoragePathService;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.serialization.impl.mapper.TrainMapper;
import com.sveta.train.Train;

import java.util.List;

public class TrainSerializer extends AbstractEntitySerializer<Train, TrainSerializationDto> {
    public TrainSerializer(StoragePathService p, List<ObjectSerializer> s) {
        super(p, s);
    }

    @Override
    protected String folderName() {
        return "trains";
    }

    @Override
    protected Class<TrainSerializationDto> dtoClass() {
        return TrainSerializationDto.class;
    }

    @Override
    protected TrainSerializationDto toDto(Train t) {
        return TrainMapper.toDto(t);
    }

    @Override
    protected Train toDomain(TrainSerializationDto d) {
        return TrainMapper.toDomain(d);
    }
}