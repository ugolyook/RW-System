package com.sveta.serialization.impl.test;

public enum SerializerType {
    JAVA_IO_SERIALIZER("java_io"),
    JSON_JACKSON_SERIALIZER("json_jackson"),
    KryoSerializer("kryo"),
    XmlSerializer("xml");

    private String folderName;

    SerializerType(String folder) {
        folderName = folder;
    }

    public String getFolderName() {
        return folderName;
    }
}
