package com.sveta.serialization.impl.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.SerializerName;
import com.sveta.serialization.SuperSerializationDto;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class KryoSerializer implements ObjectSerializer {
    private final Kryo kryo;

    public KryoSerializer() {
        this.kryo = new Kryo();
        this.kryo.setRegistrationRequired(false);
        this.kryo.setClassLoader(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public SerializerName getSerializerName() {
        return SerializerName.KryoSerializer;
    }

    @Override
    public <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception {
        try (Output output = new Output(new FileOutputStream(filePath))) {
            kryo.writeClassAndObject(output, object);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception {
        try (Input input = new Input(new FileInputStream(filePath))) {
            return (T) kryo.readClassAndObject(input);
        }
    }
}