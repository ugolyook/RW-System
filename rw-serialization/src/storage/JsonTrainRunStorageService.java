package storage;

import com.sveta.route.TrainRun;
import com.sveta.serialization.impl.test.ObjectSerializer;
import com.sveta.serialization.impl.config.StorageConfigManager;
import com.sveta.serialization.impl.config.TrainRunStorageService;
import com.sveta.serialization.impl.dto.TrainRunSerializationDto;
import com.sveta.serialization.impl.mapper.TrainRunMapper;

public class JsonTrainRunStorageService implements TrainRunStorageService {
    private final ObjectSerializer serializer;

    public JsonTrainRunStorageService(ObjectSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void save(TrainRun trainRun) throws Exception {
        TrainRunSerializationDto dto = TrainRunMapper.toDto(trainRun);
        String fileName = "train_run_" + trainRun.getTrain().getTrainNumber() + ".json";

        String filePath = StorageConfigManager.resolveFilePath(
                serializer.getSerializerType(),
                "train-runs",
                fileName
        );

        serializer.save(dto, filePath);
    }

    @Override
    public TrainRun load(int trainNumber) throws Exception {
        String fileName = "train_run_" + trainNumber + ".json";
        String filePath = StorageConfigManager.resolveFilePath(
                serializer.getSerializerType(),
                "train-runs",
                fileName
        );
        TrainRunSerializationDto dto = serializer.load(filePath, TrainRunSerializationDto.class);
        return TrainRunMapper.toDomain(dto);
    }
}