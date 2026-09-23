package com.sveta.train.formatter;

import com.sveta.route.TrainRun;
import com.sveta.tickets.SearchResult;
import com.sveta.tickets.Ticket;
import com.sveta.train.Train;

import java.util.List;

public interface TrainView {
    void showTrainCreated(Train train);

    void showTrainRunInfo(TrainRun run);

    void showSearchResults(List<SearchResult> searchResults);

    void showIssuedTicket(Ticket ticket);

    void showRemainingSeats(int count);

    void showError(String message);
}
