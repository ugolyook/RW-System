package com.sveta.serialization;

public enum SerializerType {
    JAVA_IO_SERIALIZER("java_io"),
    JSON_JACKSON_SERIALIZER("json_jackson"),
    KRYO_SERIALIZER("kryo"),
    XML_SERIALIZER("xml");

    private String folderName;

    SerializerType(String folder) {
        folderName = folder;
    }

    public String getFolderName() {
        return folderName;
    }
}