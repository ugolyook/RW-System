package com.sveta.serialization.impl.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public record SerializationConfig(Path rootDir){
    private static final String ENV_VAR_NAME = "RW_STORAGE_DIR";
    private static final String DEFAULT_FOLDER_NAME = ".rw-system";

    public static SerializationConfig defaultConfig() {
        String custom = System.getenv(ENV_VAR_NAME);
        Path root = (custom != null && !custom.isBlank())
                ? Paths.get(custom)
                : Paths.get(System.getProperty("user.home"), DEFAULT_FOLDER_NAME);
        return new SerializationConfig(root);
    }
}