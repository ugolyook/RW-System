package com.sveta.serialization.impl.dto;

import com.sveta.serialization.SuperSerializationDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainRunSerializationDto implements SuperSerializationDto {
    private TrainSerializationDto train;
    private RouteSerializationDto route;
    private LocalDateTime departureTime;
    private List<TicketSerializationDto> tickets;
    private boolean isExpress;
}