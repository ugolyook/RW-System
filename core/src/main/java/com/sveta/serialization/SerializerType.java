package com.sveta.serialization;

import lombok.Getter;

@Getter
public enum SerializerType {
    JAVA_IO_SERIALIZER("java_io", "bin"),
    JSON_JACKSON_SERIALIZER("json_jackson", "json"),
    KRYO_SERIALIZER("kryo", "bin"),
    XML_SERIALIZER("xml", "xml");

    private final String folderName;
    private final String extension;

    SerializerType(String folderName, String extension) {
        this.folderName = folderName;
        this.extension = extension;
    }
}