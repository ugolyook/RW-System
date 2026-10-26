package com.sveta.serialization.impl.config;

import com.sveta.route.TrainRun;

public interface TrainRunStorageService {
    void save(TrainRun trainRun, String filePath) throws Exception;
    TrainRun load(String filePath) throws Exception;
}