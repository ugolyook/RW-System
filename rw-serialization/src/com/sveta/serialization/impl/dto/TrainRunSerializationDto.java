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
    private RouteSerializationDto route;
    private LocalDateTime departureTime;
    private List<TicketSerializationDto> tickets;
    private boolean isExpress;

    public TrainRunSerializationDto(TrainRun trainRun) {
        this.train = trainRun.getTrain() != null ? new TrainSerializationDto(trainRun.getTrain()) : null;
        this.route = new RouteSerializationDto(trainRun.getRoute());
        this.departureTime = trainRun.getDepartureTime();
        this.isExpress = trainRun.isExpress();
        this.tickets = trainRun.getTickets().stream()
                .map(TicketSerializationDto::new)
                .toList();
    }
}