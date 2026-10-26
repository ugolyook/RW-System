package com.sveta.rwcl;

import com.sveta.rwcl.controller.TrainController;
import com.sveta.rwcl.formatter.BaseTrainInfoFormatter;
import com.sveta.rwcl.viewer.ConsoleTrainView;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.impl.config.TrainRunStorageService;
import com.sveta.serialization.impl.serializer.JsonJacksonSerializer;
import com.sveta.view.TrainView;
import storage.JsonTrainRunStorageService;


public class Main {
    static void main(String[] args) {
        ObjectSerializer serializer = new JsonJacksonSerializer();
        TrainRunStorageService storageService = new JsonTrainRunStorageService(serializer);

        TrainView view = new ConsoleTrainView(new BaseTrainInfoFormatter());
        TrainController controller = new TrainController(view, storageService);
        controller.run();
    }
}