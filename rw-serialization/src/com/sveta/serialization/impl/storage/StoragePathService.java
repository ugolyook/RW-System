package com.sveta.serialization.impl.storage;

import com.sveta.serialization.CoreEntitySerializationException;
import com.sveta.serialization.impl.serializer.SerializerType;
import com.sveta.serialization.impl.config.SerializationConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class StoragePathService {
    private final Path root;

    public StoragePathService(SerializationConfig config) {
        this.root = config.rootDir();
    }

    public Path resolveDir(SerializerType type, String entityFolder) {
        Path dir = root.resolve(type.getFolderName()).resolve(entityFolder);
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new CoreEntitySerializationException("Cannot create dir: " + dir, e);
        }
        return dir;
    }

    public Path newFilePath(SerializerType type, String entityFolder) {
        return resolveDir(type, entityFolder)
                .resolve(UUID.randomUUID() + "." + type.getExtension());
    }

    public List<Path> listFiles(SerializerType type, String entityFolder) {
        Path dir = resolveDir(type, entityFolder);
        try (Stream<Path> files = Files.list(dir)) {
            return files.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith("." + type.getExtension()))
                    .sorted()
                    .toList();
        } catch (IOException e) {
            throw new CoreEntitySerializationException("Cannot list dir: " + dir, e);
        }
    }

    public void clearDir(SerializerType type, String entityFolder) {
        for (Path file : listFiles(type, entityFolder)) {
            try {
                Files.delete(file);
            } catch (IOException e) {
                throw new CoreEntitySerializationException("Cannot delete: " + file, e);
            }
        }
    }
}
