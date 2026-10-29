package com.sveta.serialization.impl.test;

import com.sveta.route.TrainRun;
import com.sveta.serialization.CoreEntitySerializationException;
import com.sveta.train.Train;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TrainRunSerializer {

    private final ObjectSerializer objectSerializer;

    public void save(List<TrainRun> trains) throws CoreEntitySerializationException {

    }

    public List<TrainRun> load() throws CoreEntitySerializationException {
        return null;
    }
}
