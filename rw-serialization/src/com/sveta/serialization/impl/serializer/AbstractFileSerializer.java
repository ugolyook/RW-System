package com.sveta.serialization.impl.serializer;

import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.SuperSerializationDto;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractFileSerializer implements ObjectSerializer {
    @Override
    public final <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception {
        if (object == null) throw new IllegalArgumentException("Object to save cannot be null");
        Path path = Path.of(filePath);
        if (path.getParent() != null) Files.createDirectories(path.getParent());
        write(object, path);
    }

    @Override
    public final <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception {
        Path path = Path.of(filePath);
        if (!Files.isRegularFile(path)) throw new FileNotFoundException(filePath);
        return clazz.cast(read(path, clazz));
    }

    protected abstract void write(SuperSerializationDto object, Path path) throws Exception;
    protected abstract Object read(Path path, Class<? extends SuperSerializationDto> clazz) throws Exception;
}