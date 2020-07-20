package be.harm.carshare.client.dtos;

import be.harm.carshare.client.cars.Car;
import be.harm.carshare.client.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class FullReservationDTO {
    private Car car;
    private User user;
    private LocalDateTime start;
    private LocalDateTime end;
}
