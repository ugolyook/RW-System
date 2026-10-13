package com.sveta.serialization;

public interface ObjectSerializer {
    void save(Object object, String filePath) throws Exception;

    <T> T load(String filePath, Class<T> clazz) throws Exception;
}