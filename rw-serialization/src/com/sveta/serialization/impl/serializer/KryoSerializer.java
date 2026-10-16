package com.sveta.serialization.impl.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sveta.serialization.ObjectSerializer;

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
    public void save(Object object, String filePath) throws Exception {
        try (Output output = new Output(new FileOutputStream(filePath))) {
            kryo.writeClassAndObject(output, object);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T load(String filePath, Class<T> clazz) throws Exception {
        try (Input input = new Input(new FileInputStream(filePath))) {
            return (T) kryo.readClassAndObject(input);
        }
    }
}