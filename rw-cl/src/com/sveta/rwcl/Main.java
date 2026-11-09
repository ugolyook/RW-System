package com.sveta.rwcl;

import com.sveta.rwcl.controller.TrainController;
import com.sveta.rwcl.formatter.BaseTrainInfoFormatter;
import com.sveta.rwcl.viewer.ConsoleTrainView;
import com.sveta.serialization.CoreSerializer;
import com.sveta.serialization.impl.serializer.javaIo.SerializerFactory;
import com.sveta.serialization.impl.serializer.core.BaseCoreSerializer;
import com.sveta.serialization.impl.config.SerializationConfig;
import com.sveta.view.TrainView;

public class Main {
    static void main(String[] args) {
        CoreSerializer coreSerializer = new BaseCoreSerializer(
                SerializerFactory.createAll(),
                SerializationConfig.defaultConfig()
        );

        TrainView view = new ConsoleTrainView(new BaseTrainInfoFormatter());
        TrainController controller = new TrainController(view, coreSerializer);
        controller.run();
    }
}