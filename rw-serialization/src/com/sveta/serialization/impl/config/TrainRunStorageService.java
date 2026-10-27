package com.sveta.serialization.impl.config;

import com.sveta.route.TrainRun;

public interface TrainRunStorageService {
    void save(TrainRun trainRun) throws Exception;
    TrainRun load(int id) throws Exception;
}