package com.sveta.serialization.impl.serializer;

import com.sveta.serialization.impl.test.ObjectSerializer;
import com.sveta.serialization.impl.test.SerializerType;
import com.sveta.serialization.impl.test.SuperSerializationDto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class JavaIoSerializer implements ObjectSerializer {
    @Override
    public SerializerType getSerializerType() {
        return SerializerType.JavaIoSerializer;
    }

    @Override
    public <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(object);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (T) ois.readObject();
        }
    }
}