package com.sveta.serialization.impl.serializer.javaIo;

import com.sveta.serialization.impl.serializer.files.AbstractFileSerializer;
import com.sveta.serialization.impl.serializer.files.SerializerType;
import com.sveta.serialization.impl.serializer.core.SuperSerializationDto;

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