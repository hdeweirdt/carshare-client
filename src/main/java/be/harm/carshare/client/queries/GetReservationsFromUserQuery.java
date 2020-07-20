package be.harm.carshare.client.queries;

import be.harm.carshare.client.dtos.FullReservationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetReservationsFromUserQuery {
    private List<FullReservationDTO> reservations = new ArrayList<>();
}
