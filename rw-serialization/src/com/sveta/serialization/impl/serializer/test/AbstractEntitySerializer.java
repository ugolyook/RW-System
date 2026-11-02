package com.sveta.serialization.impl.serializer.test;

import com.sveta.serialization.CoreEntitySerializationException;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.SerializerType;
import com.sveta.serialization.SuperSerializationDto;
import storage.StoragePathService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntitySerializer<E, D extends SuperSerializationDto> {
    private final StoragePathService pathService;
    private final List<ObjectSerializer> serializers;

    protected AbstractEntitySerializer(StoragePathService pathService,
                                       List<ObjectSerializer> serializers) {
        if (serializers == null || serializers.isEmpty()) {
            throw new IllegalArgumentException("At least one ObjectSerializer is required");
        }
        this.pathService = pathService;
        this.serializers = serializers;
    }

    protected abstract String folderName();
    protected abstract Class<D> dtoClass();
    protected abstract D toDto(E entity);
    protected abstract E toDomain(D dto);

    public void save(List<E> entities) throws CoreEntitySerializationException {
        List<D> dtos = entities == null ? List.of() : entities.stream().map(this::toDto).toList();

        for (ObjectSerializer serializer : serializers) {
            SerializerType type = serializer.getSerializerName();
            pathService.clearDir(type, folderName());
            try {
                for (D dto : dtos) {
                    serializer.save(dto, pathService.newFilePath(type, folderName()).toString());
                }
            } catch (Exception e) {
                throw new CoreEntitySerializationException(
                        "Save failed [" + type + "/" + folderName() + "]", e);
            }
        }
    }

    public List<E> load() throws CoreEntitySerializationException {
        ObjectSerializer serializer = serializers.getFirst();
        SerializerType type = serializer.getSerializerName();

        List<E> result = new ArrayList<>();
        for (Path file : pathService.listFiles(type, folderName())) {
            try {
                D dto = serializer.load(file.toString(), dtoClass());
                result.add(toDomain(dto));
            } catch (Exception e) {
                throw new CoreEntitySerializationException("Load failed: " + file, e);
            }
        }
        return result;
    }
}