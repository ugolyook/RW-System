package com.sveta.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RwApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                RwApplication.class.getResource("/com.sveta.javafx/ui.fxml"));

        Scene scene = new Scene(loader.load(), 1100, 700);

        stage.setTitle("RW System — Train Schedule");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}