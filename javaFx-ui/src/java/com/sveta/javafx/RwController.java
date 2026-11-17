package com.sveta.javafx;

import com.sveta.TestDataFactory;
import com.sveta.factory.train.PassengerTrainFactory;
import com.sveta.route.Directions;
import com.sveta.route.TrainRun;
import com.sveta.train.carriage.passenger.PassengerCarriage;
import com.sveta.train.carriage.passenger.models.SeatType;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RwController {
    @FXML
    private BorderPane root;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ComboBox<String> directionComboBox;
    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private CheckBox specialSeatsCheckBox;
    @FXML
    private Stage infoStage;
    private TrainTableAdapter tableAdapter;

    @FXML
    private TableView<TrainRun> trainTable;
    @FXML
    private TableColumn<TrainRun, String> numberColumn;
    @FXML
    private TableColumn<TrainRun, String> routeColumn;
    @FXML
    private TableColumn<TrainRun, String> typeColumn;
    @FXML
    private TableColumn<TrainRun, String> departureColumn;
    @FXML
    private TableColumn<TrainRun, String> freeSeatsColumn;

    private final ObservableList<TrainRun> allRuns =
            FXCollections.observableArrayList();

    private final FilteredList<TrainRun> filteredRuns =
            new FilteredList<>(allRuns, run -> true);

    @FXML
    private void initialize() {
        configureCombos();
        configureTable();
        loadDemoData();
    }

    private void openInfoWindow() {
        if (infoStage == null) {
            getInfoStage();
        }
        infoStage.show();
        infoStage.toFront();
    }

    private void getInfoStage() {
        infoStage = InfoController.open(
                root.getScene().getWindow(),
                filteredRuns,
                tableAdapter.selectedProperty());
    }

    private void configureCombos() {
        typeComboBox.getItems().setAll(
                TrainRunFilter.ALL_TYPES,
                TrainRunView.PASSENGER,
                TrainRunView.EXPRESS);
        directionComboBox.getItems().setAll(
                TrainRunFilter.ALL_DIRECTIONS,
                Directions.FORWARD.name(),
                Directions.BACKWARD.name()
        );

        typeComboBox.getSelectionModel().selectFirst();
        directionComboBox.getSelectionModel().selectFirst();
    }

    private void configureTable() {
        addAdapter();
        tableAdapter.setItems(filteredRuns);
        tableAdapter.onSelected(run -> openInfoWindow());
    }

    private void addAdapter() {
        tableAdapter = new TrainTableAdapter(
                trainTable, numberColumn, routeColumn,
                typeColumn, departureColumn, freeSeatsColumn);
    }

    private void loadDemoData() {
        var trainFactory = TestDataFactory.createTrainFactory();
        addInfoToTrainRunArray(trainFactory);
    }

    private void addInfoToTrainRunArray(PassengerTrainFactory trainFactory) {
        for (int i = 0; i < 3; i++) {
            var carriages = TestDataFactory.createCarriages(
                    TestDataFactory.createBaseCarriageFactory());

            var train = trainFactory.createTrain(carriages);
            var baseRun = TestDataFactory.createTrainRun(train);

            allRuns.add(new TrainRun(
                    baseRun.getTrain(),
                    baseRun.getRoute(),
                    baseRun.getDepartureTime().plusHours(i * 2L),
                    i % 2 == 0
            ));
        }
    }

    @FXML
    private void onButtonUpdateAction() {
        filteredRuns.setPredicate(currentFilter());
    }

    @FXML
    private void onButtonResetAction() {
        searchField.clear();
        typeComboBox.getSelectionModel().selectFirst();
        directionComboBox.getSelectionModel().selectFirst();
        departureDatePicker.setValue(null);
        specialSeatsCheckBox.setSelected(false);

        filteredRuns.setPredicate(TrainRunFilter.NONE);
        tableAdapter.clearSelection();
    }

    private TrainRunFilter currentFilter() {
        return new TrainRunFilter(
                searchField.getText(),
                typeComboBox.getValue(),
                directionComboBox.getValue(),
                departureDatePicker.getValue(),
                specialSeatsCheckBox.isSelected());
    }
}