package com.sveta.serialization.impl.serializer;

import com.sveta.serialization.ObjectSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaIoSerializer implements ObjectSerializer {
    @Override
    public void save(Object object, String filePath) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(object);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T load(String filePath, Class<T> clazz) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (T) ois.readObject();
        }
    }
}