package com.sveta.serialization.impl.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.SerializerType;
import com.sveta.serialization.SuperSerializationDto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class KryoSerializer extends AbstractFileSerializer {
    private static final ThreadLocal<Kryo> KRYO = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setClassLoader(Thread.currentThread().getContextClassLoader());
        return kryo;
    });

    @Override
    public SerializerType getSerializerName() {
        return SerializerType.KRYO_SERIALIZER;
    }

    @Override
    protected void write(SuperSerializationDto object, Path path) throws Exception {
        try (Output output = new Output(Files.newOutputStream(path))) {
            KRYO.get().writeClassAndObject(output, object);
        }
    }

    @Override
    protected Object read(Path path, Class<? extends SuperSerializationDto> clazz) throws Exception {
        try (Input input = new Input(Files.newInputStream(path))) {
            return KRYO.get().readClassAndObject(input);
        }
    }
}