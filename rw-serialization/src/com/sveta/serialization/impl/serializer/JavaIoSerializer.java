package com.sveta.serialization.impl.serializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

class JavaIoSerializer extends AbstractFileSerializer {
    @Override
    public SerializerType getSerializerName() {
        return SerializerType.JAVA_IO_SERIALIZER;
    }

    @Override
    protected void write(SuperSerializationDto object, Path path) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(path)))) {
            oos.writeObject(object);
        }
    }

    @Override
    protected Object read(Path path, Class<? extends SuperSerializationDto> clazz) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(path)))) {
            return ois.readObject();
        }
    }
}