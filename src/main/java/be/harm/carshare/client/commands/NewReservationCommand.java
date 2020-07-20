package be.harm.carshare.client.commands;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class NewReservationCommand {
    public final Long carId;
    public final Long userId;
    public final LocalDateTime start;
    public final LocalDateTime end;
}
