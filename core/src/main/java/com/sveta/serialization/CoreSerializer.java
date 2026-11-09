package com.sveta.serialization;

public interface CoreSerializer {
    void save(CoreState dto) throws CoreEntitySerializationException;
    CoreState load() throws  CoreEntitySerializationException;
}