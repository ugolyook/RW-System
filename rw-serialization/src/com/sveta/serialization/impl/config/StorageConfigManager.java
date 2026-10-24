package com.sveta.serialization.impl.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageConfigManager {
    private static final String ENV_VAR_NAME = "RW_STORAGE_DIR";
    private static final String DEFAULT_FOLDER_NAME = ".rw-system";

    public static Path resolveStorageDirectory() {
        String customDir = System.getenv(ENV_VAR_NAME);
        Path storagePath;

        if (customDir != null && !customDir.isBlank()) {
            storagePath = Paths.get(customDir);
        } else {
            String userHome = System.getProperty("user.home");
            storagePath = Paths.get(userHome, DEFAULT_FOLDER_NAME);
        }

        try {
            if (!Files.exists(storagePath)) {
                Files.createDirectories(storagePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create storage directory: " + storagePath, e);
        }

        return storagePath;
    }

    public static String resolveFilePath(String fileName) {
        return resolveStorageDirectory().resolve(fileName).toAbsolutePath().toString();
    }
}