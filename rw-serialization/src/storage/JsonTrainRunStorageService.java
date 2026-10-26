package storage;

import com.sveta.route.TrainRun;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.TrainRunStorageService;
import com.sveta.serialization.impl.dto.TrainRunSerializationDto;
import com.sveta.serialization.impl.mapper.TrainRunMapper;

public class JsonTrainRunStorageService implements TrainRunStorageService {
    private final ObjectSerializer serializer;

    public JsonTrainRunStorageService(ObjectSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void save(TrainRun trainRun, String filePath) throws Exception {
        TrainRunSerializationDto dto = TrainRunMapper.toDto(trainRun);
        serializer.save(dto, filePath);
    }

    @Override
    public TrainRun load(String filePath) throws Exception {
        TrainRunSerializationDto dto = serializer.load(filePath, TrainRunSerializationDto.class);
        return TrainRunMapper.toDomain(dto);
    }
}