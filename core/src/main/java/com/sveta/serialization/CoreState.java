package com.sveta.serialization;

import com.sveta.route.TrainRun;
import com.sveta.train.Train;

import java.util.List;

public record CoreState(
        List<TrainRun> trainRuns,
        List<Train> trains
){}