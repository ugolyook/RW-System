package com.sveta.javafx;

import com.sveta.route.TrainRun;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.function.Consumer;
import java.util.function.Function;

public final class TrainTableAdapter {
    private final TableView<TrainRun> table;

    public TrainTableAdapter(
            TableView<TrainRun> table,
            TableColumn<TrainRun, String> numberColumn,
            TableColumn<TrainRun, String> routeColumn,
            TableColumn<TrainRun, String> typeColumn,
            TableColumn<TrainRun, String> departureColumn,
            TableColumn<TrainRun, String> freeSeatsColumn
    ) {
        this.table = table;

        bindColumn(numberColumn, TrainRunView::number);
        bindColumn(routeColumn, TrainRunView::route);
        bindColumn(typeColumn, TrainRunView::typeLabel);
        bindColumn(departureColumn, TrainRunView::departure);
        bindColumn(freeSeatsColumn, run -> String.valueOf(TrainRunView.freeSeats(run)));
    }

    public void setItems(ObservableList<TrainRun> items) {
        table.setItems(items);
    }

    public ObservableValue<TrainRun> selectedProperty() {
        return table.getSelectionModel().selectedItemProperty();
    }

    public void onSelected(Consumer<TrainRun> action) {
        selectedProperty().addListener((obs, oldRun, newRun) -> {
            if (newRun != null) {
                action.accept(newRun);
            }
        });
    }

    public void clearSelection() {
        table.getSelectionModel().clearSelection();
    }

    private static void bindColumn(TableColumn<TrainRun, String> column,
                                   Function<TrainRun, String> mapper) {
        column.setCellValueFactory(cd -> new ReadOnlyStringWrapper(mapper.apply(cd.getValue())));
    }
}