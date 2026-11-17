package com.sveta.javafx;

import com.sveta.route.TrainRun;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.io.UncheckedIOException;

public class InfoController {

    @FXML private Label selectedTrainLabel;
    @FXML private Label trainCountLabel;
    @FXML private Label totalSeatsLabel;
    @FXML private Label nextDepartureLabel;

    private ObservableList<TrainRun> runs;
    private ObservableValue<TrainRun> selected;

    public static Stage open(Window owner,
                             ObservableList<TrainRun> runs,
                             ObservableValue<TrainRun> selected) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    InfoController.class.getResource("/com.sveta.javafx/info.fxml"));
            Parent content = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Train info");
            stage.initOwner(owner);
            stage.setScene(new Scene(content, 340, 520));

            loader.<InfoController>getController().bind(runs, selected);
            return stage;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void bind(ObservableList<TrainRun> runs,
                      ObservableValue<TrainRun> selected) {
        this.runs = runs;
        this.selected = selected;

        selected.addListener((obs, oldRun, newRun) -> refresh());
        runs.addListener((ListChangeListener<TrainRun>) change -> refresh());

        refresh();
    }

    private void refresh() {
        selectedTrainLabel.setText(TrainRunView.selectedText(selected.getValue()));
        trainCountLabel.setText(String.valueOf(runs.size()));
        totalSeatsLabel.setText(String.valueOf(TrainRunView.totalFreeSeats(runs)));
        nextDepartureLabel.setText(TrainRunView.nextDeparture(runs));
    }
}