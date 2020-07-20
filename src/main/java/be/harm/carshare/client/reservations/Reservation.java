package be.harm.carshare.client.reservations;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@Builder
public class Reservation {

    @Null
    private Long id;

    @NotNull
    private Long userId;

    @Getter
    @NotNull
    private Long carId;

    @Getter
    @NotNull
    private LocalDateTime start;

    @Getter
    @NotNull
    // TODO: write custom matcher to check if end is after start
    private LocalDateTime end;

}
