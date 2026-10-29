package com.sveta.serialization;

public interface CoreSerializer {
    void save(CoreSerializationDto dto) throws CoreEntitySerializationException;
    CoreSerializationDto load() throws  CoreEntitySerializationException;
}
