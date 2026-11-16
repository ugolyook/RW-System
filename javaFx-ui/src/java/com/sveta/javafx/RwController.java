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
    private VBox infoPanel;
    private Stage infoStage;
    private static final String PASSENGER = "Passenger";
    private static final String EXPRESS = "Express";
    private static final String ALL_TYPES = "All types";
    private static final String ALL_DIRECTIONS = "All directions";

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

    @FXML
    private Label selectedTrainLabel;
    @FXML
    private Label trainCountLabel;
    @FXML
    private Label totalSeatsLabel;
    @FXML
    private Label nextDepartureLabel;

    private static final DateTimeFormatter TIME =
            DateTimeFormatter.ofPattern("HH:mm");

    private final ObservableList<TrainRun> allRuns =
            FXCollections.observableArrayList();

    private final FilteredList<TrainRun> filteredRuns =
            new FilteredList<>(allRuns, run -> true);

    @FXML
    private void initialize() {
        configureCombos();
        configureTable();
        loadDemoData();
        updateSummary();
        toHideInfo();
    }

    @FXML
    private void onButtonInfoAction() {
        if (infoStage == null) {
            infoPanel.relocate(0, 0);
            infoPanel.setPrefHeight(Region.USE_COMPUTED_SIZE);

            infoStage = new Stage();
            infoStage.setTitle("Train info");
            infoStage.initOwner(root.getScene().getWindow());
            infoStage.setScene(new Scene(infoPanel, 340, 520));
        }
        infoStage.show();
        infoStage.toFront();
    }

    private void toHideInfo() {
        if (infoPanel.getParent() instanceof Pane parent) {
            parent.getChildren().remove(infoPanel);
        }
    }

    private void configureCombos() {
        typeComboBox.getItems().setAll(ALL_TYPES, PASSENGER, EXPRESS);
        directionComboBox.getItems().setAll(
                ALL_DIRECTIONS,
                Directions.FORWARD.name(),
                Directions.BACKWARD.name()
        );

        typeComboBox.getSelectionModel().selectFirst();
        directionComboBox.getSelectionModel().selectFirst();
    }

    private void configureTable() {
        setTrainNumberToColumn();
        setRouteToColumn();
        setTypeToColumn();
        setDepartureTimeToColumn();
        setAvailableSpotsToColumn();

        trainTable.setItems(filteredRuns);
        trainTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldRun, newRun) -> showDetails(newRun));
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
        filteredRuns.setPredicate(this::matchesFilter);
        updateSummary();
    }

    @FXML
    private void onButtonResetAction() {
        searchField.clear();
        typeComboBox.getSelectionModel().selectFirst();
        directionComboBox.getSelectionModel().selectFirst();
        departureDatePicker.setValue(null);
        specialSeatsCheckBox.setSelected(false);

        filteredRuns.setPredicate(run -> true);
        trainTable.getSelectionModel().clearSelection();
        showDetails(null);
        updateSummary();
    }

    private boolean matchesFilter(TrainRun run) {
        String query = searchField.getText();

        if (matchByNumber(run, query)) return false;
        if (matchTypes(run)) return false;
        if (matchDirections(run)) return false;
        if (matchDate(run)) return false;

        return !specialSeatsCheckBox.isSelected() || hasSpecialSeats(run);
    }

    private boolean hasSpecialSeats(TrainRun run) {
        return run.getTrain().getCarriages().stream()
                .filter(PassengerCarriage.class::isInstance)
                .map(PassengerCarriage.class::cast)
                .flatMap(carriage -> carriage.getAllSeats().stream())
                .anyMatch(seat -> seat.type() == SeatType.BICYCLE
                        || seat.type() == SeatType.PET);
    }

    private int availableSpots(TrainRun run) {
        return getNumberOfAvailableSpots(run);
    }

    private String formatRoute(TrainRun run) {
        var stops = run.getRoute().stops();
        if (stops.isEmpty()) {
            return "";
        }
        return stops.getFirst().stationName() + " → "
                + stops.getLast().stationName();
    }

    private void showDetails(TrainRun run) {
        if (run == null) {
            selectedTrainLabel.setText("—");
            return;
        }

        setTableInfo(run);
    }

    private void updateSummary() {
        trainCountLabel.setText(String.valueOf(filteredRuns.size()));
        updateSeatsLabel();
        updateNextDepartureLabel();
    }

    private void setAvailableSpotsToColumn() {
        freeSeatsColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        String.valueOf(availableSpots(cd.getValue()))));
    }

    private void setDepartureTimeToColumn() {
        departureColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        cd.getValue().getDepartureTime().format(TIME)));
    }

    private void setTypeToColumn() {
        typeColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        cd.getValue().isExpress() ? EXPRESS : PASSENGER));
    }

    private void setRouteToColumn() {
        routeColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(formatRoute(cd.getValue())));
    }

    private void setTrainNumberToColumn() {
        numberColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cd.getValue().getTrain().getTrainNumber())));
    }

    private boolean matchByNumber(TrainRun run, String query) {
        if (query != null && !query.isBlank()) {
            String q = query.trim().toLowerCase();

            boolean byNumber = String.valueOf(run.getTrain().getTrainNumber())
                    .contains(q);
            boolean byRoute = formatRoute(run).toLowerCase().contains(q);

            return !byNumber && !byRoute;
        }
        return false;
    }

    private boolean matchDate(TrainRun run) {
        return departureDatePicker.getValue() != null
                && !run.getDepartureTime().toLocalDate()
                .equals(departureDatePicker.getValue());
    }

    private boolean matchDirections(TrainRun run) {
        String direction = directionComboBox.getValue();
        return direction != null
                && !ALL_DIRECTIONS.equals(direction)
                && !run.getRoute().direction().name().equals(direction);
    }

    private boolean matchTypes(TrainRun run) {
        String type = typeComboBox.getValue();
        if (EXPRESS.equals(type) && !run.isExpress()) {
            return true;
        }
        return PASSENGER.equals(type) && run.isExpress();
    }

    private static int getNumberOfAvailableSpots(TrainRun run) {
        return Math.max(0,
                run.getTrain().getTotalPassengerCap()
                        - run.getTickets().size());
    }

    private void setTableInfo(TrainRun run) {
        selectedTrainLabel.setText(
                "№ " + run.getTrain().getTrainNumber() + "\n"
                        + formatRoute(run) + "\n"
                        + run.getDepartureTime().format(TIME) + " · "
                        + (run.isExpress() ? EXPRESS : PASSENGER)
        );
    }

    private void updateNextDepartureLabel() {
        nextDepartureLabel.setText(
                filteredRuns.stream()
                        .map(TrainRun::getDepartureTime)
                        .min(LocalDateTime::compareTo)
                        .map(time -> time.format(TIME))
                        .orElse("—")
        );
    }

    private void updateSeatsLabel() {
        int seats = filteredRuns.stream()
                .mapToInt(this::availableSpots)
                .sum();
        totalSeatsLabel.setText(String.valueOf(seats));
    }
}