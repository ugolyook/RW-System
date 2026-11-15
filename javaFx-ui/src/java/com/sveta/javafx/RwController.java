package com.sveta.javafx;

import com.sveta.TestDataFactory;
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
import javafx.scene.control.Button;
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

    @FXML private BorderPane root;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private ComboBox<String> directionComboBox;
    @FXML private DatePicker departureDatePicker;
    @FXML private CheckBox specialSeatsCheckBox;
    @FXML private Button updateButton;
    @FXML private Button resetButton;
    @FXML private VBox infoPanel;
    private Stage infoStage;

    @FXML private TableView<TrainRun> trainTable;
    @FXML private TableColumn<TrainRun, String> numberColumn;
    @FXML private TableColumn<TrainRun, String> routeColumn;
    @FXML private TableColumn<TrainRun, String> typeColumn;
    @FXML private TableColumn<TrainRun, String> departureColumn;
    @FXML private TableColumn<TrainRun, String> freeSeatsColumn;

    @FXML private Label selectedTrainLabel;
    @FXML private Label trainCountLabel;
    @FXML private Label totalSeatsLabel;
    @FXML private Label nextDepartureLabel;

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

        if (infoPanel.getParent() instanceof Pane parent) {
            parent.getChildren().remove(infoPanel);
        }
    }

    @FXML
    private void onButtonInfoAction(){
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

    private void configureCombos() {
        typeComboBox.getItems().setAll("All types", "Passenger", "Express");
        directionComboBox.getItems().setAll(
                "All directions",
                Directions.FORWARD.name(),
                Directions.BACKWARD.name()
        );

        typeComboBox.getSelectionModel().selectFirst();
        directionComboBox.getSelectionModel().selectFirst();
    }

    private void configureTable() {
        numberColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        String.valueOf(cd.getValue().getTrain().getTrainNumber())));

        routeColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(formatRoute(cd.getValue())));

        typeColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        cd.getValue().isExpress() ? "Express" : "Passenger"));

        departureColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        cd.getValue().getDepartureTime().format(TIME)));

        freeSeatsColumn.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(
                        String.valueOf(availableSpots(cd.getValue()))));

        trainTable.setItems(filteredRuns);

        trainTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldRun, newRun) -> showDetails(newRun));
    }

    private void loadDemoData() {
        var trainFactory = TestDataFactory.createTrainFactory();

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
        if (query != null && !query.isBlank()) {
            String q = query.trim().toLowerCase();

            boolean byNumber = String.valueOf(run.getTrain().getTrainNumber())
                    .contains(q);
            boolean byRoute = formatRoute(run).toLowerCase().contains(q);

            if (!byNumber && !byRoute) {
                return false;
            }
        }

        String type = typeComboBox.getValue();
        if ("Express".equals(type) && !run.isExpress()) {
            return false;
        }
        if ("Passenger".equals(type) && run.isExpress()) {
            return false;
        }

        String direction = directionComboBox.getValue();
        if (direction != null
                && !"All directions".equals(direction)
                && !run.getRoute().direction().name().equals(direction)) {
            return false;
        }

        if (departureDatePicker.getValue() != null
                && !run.getDepartureTime().toLocalDate()
                .equals(departureDatePicker.getValue())) {
            return false;
        }

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
        return Math.max(0,
                run.getTrain().getTotalPassengerCap()
                        - run.getTickets().size());
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

        selectedTrainLabel.setText(
                "№ " + run.getTrain().getTrainNumber() + "\n"
                        + formatRoute(run) + "\n"
                        + run.getDepartureTime().format(TIME) + " · "
                        + (run.isExpress() ? "Express" : "Passenger")
        );
    }

    private void updateSummary() {
        trainCountLabel.setText(String.valueOf(filteredRuns.size()));

        int seats = filteredRuns.stream()
                .mapToInt(this::availableSpots)
                .sum();
        totalSeatsLabel.setText(String.valueOf(seats));

        nextDepartureLabel.setText(
                filteredRuns.stream()
                        .map(TrainRun::getDepartureTime)
                        .min(LocalDateTime::compareTo)
                        .map(time -> time.format(TIME))
                        .orElse("—")
        );
    }
}