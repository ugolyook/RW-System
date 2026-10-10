package com.sveta.train.carriage.passenger;

import com.sveta.train.carriage.ElectricCarriage;
import com.sveta.train.carriage.passenger.models.Seat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static com.sveta.train.carriage.passenger.models.SeatType.BICYCLE;

@Getter
@ToString(callSuper = true)
public class SeatedCarriage extends PassengerCarriage implements ElectricCarriage {
    private int seatsLimit;
    private final List<Seat> seats = new ArrayList<>(seatsLimit);

    public SeatedCarriage(
            int seatsLimit,
            int baseCarriageWeightKg
    ) {
        super(baseCarriageWeightKg);
        this.seatsLimit = seatsLimit;
    }

    public boolean isBicycleSpots() {
        return seats.stream().anyMatch(seat -> seat.getType() == BICYCLE);
    }

    @Override
    public int getPassengerCapacity() {
        return seatsLimit;
    }

    @Override
    public int getNumberOfPlaces() {
        return seats.size();
    }

    @Override
    public List<Seat> getAllSeats() {
        return seats;
    }
}