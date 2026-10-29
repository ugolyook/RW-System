package com.sveta.serialization.impl.dto;

import com.sveta.serialization.impl.test.SuperSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.CarriageSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.SeatSerializationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketSerializationDto implements SuperSerializationDto {
    private String passengerName;
    private TrainRunSerializationDto trainRun;
    private StationSerializationDto departureStation;
    private StationSerializationDto arrivalStation;
    private CarriageSerializationDto carriage;
    private SeatSerializationDto seat;
    private BigDecimal price;
}