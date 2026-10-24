package com.sveta.rwcl;

import com.sveta.rwcl.controller.TrainController;
import com.sveta.rwcl.formatter.BaseTrainInfoFormatter;
import com.sveta.rwcl.viewer.ConsoleTrainView;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.impl.serializer.JsonSerializer;
import com.sveta.view.TrainView;


public class Main {
    static void main(String[] args) {
        ObjectSerializer serializer = new JsonSerializer();

        TrainView view = new ConsoleTrainView(new BaseTrainInfoFormatter());
        TrainController controller = new TrainController(view, serializer);
        controller.run();
    }
}