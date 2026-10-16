package com.sveta.serialization.impl.dto;

import com.sveta.route.TrainRun;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TrainRunSerializationDto {
    private TrainSerializationDto train;
    private RouteDto route;
    private LocalDateTime departureTime;
    private List<TicketDto> tickets;

    public TrainRunSerializationDto(TrainRun trainRun) {
        this.train = trainRun.getTrain() != null ? new TrainSerializationDto(trainRun.getTrain()) : null;
        this.route = new RouteDto(trainRun.getRoute());
        this.departureTime = trainRun.getDepartureTime();
        this.tickets = trainRun.getTickets().stream()
                .map(TicketDto::new)
                .toList();
    }
}
