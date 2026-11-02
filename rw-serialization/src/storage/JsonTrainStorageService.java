package storage;

import com.sveta.serialization.impl.config.StorageConfigManager;
import com.sveta.serialization.impl.dto.TrainSerializationDto;
import com.sveta.serialization.impl.mapper.TrainMapper;
import com.sveta.serialization.impl.serializer.JsonJacksonSerializer;
import com.sveta.train.Train;

public class JsonTrainStorageService {
    private final JsonJacksonSerializer serializer;
    private StorageConfigManager configManager;

    public JsonTrainStorageService(JsonJacksonSerializer serializer) {
        this.serializer = serializer;
    }

    public void save(Train train) throws Exception {
        if (train == null) {
            throw new IllegalArgumentException("Train cannot be null");
        }

        TrainSerializationDto dto = TrainMapper.toDto(train);
        String fileName = "train_" + train.getTrainNumber() + ".json";

        String filePath = configManager.resolveFilePath(
                serializer.getSerializerName(),
                "trains",
                fileName
        );

        serializer.save(dto, filePath);
    }

    public Train load(int trainNumber) throws Exception {
        String fileName = "train_" + trainNumber + ".json";

        String filePath = configManager.resolveFilePath(
                serializer.getSerializerName(),
                "trains",
                fileName
        );

        TrainSerializationDto dto = serializer.load(filePath, TrainSerializationDto.class);
        return TrainMapper.toDomain(dto);
    }
}