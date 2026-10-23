package com.sveta.serialization.impl.dto;

import com.sveta.route.TrainRun;
import com.sveta.serialization.SuperSerializationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TrainRunSerializationDto implements SuperSerializationDto {
    private TrainSerializationDto train;
    private RouteSerializationDto route;
    private LocalDateTime departureTime;
    private List<TicketSerializationDto> tickets;
    private boolean isExpress;
}