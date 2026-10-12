package com.sveta.rwcl;

import com.sveta.rwcl.controller.TrainController;
import com.sveta.rwcl.formatter.BaseTrainInfoFormatter;
import com.sveta.rwcl.formatter.ConsoleTrainView;
import com.sveta.rwcl.view.TrainView;


public class Main {
    public static void main(String[] args) {
        TrainView view = new ConsoleTrainView(new BaseTrainInfoFormatter());
        TrainController controller = new TrainController(view);
        controller.run();
    }
}