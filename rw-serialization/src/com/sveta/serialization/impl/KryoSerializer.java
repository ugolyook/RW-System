package com.sveta.serialization.impl;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sveta.serialization.ObjectSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class KryoSerializer implements ObjectSerializer {
    private final Kryo kryo = new Kryo();

    public KryoSerializer() {
        kryo.setRegistrationRequired(false);
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