package be.harm.carshare.client;

import be.harm.carshare.client.cars.CarClient;
import be.harm.carshare.client.commands.NewReservationCommand;
import be.harm.carshare.client.dtos.FullReservationDTO;
import be.harm.carshare.client.queries.GetReservationsFromUserQuery;
import be.harm.carshare.client.reservations.Reservation;
import be.harm.carshare.client.reservations.ReservationClient;
import be.harm.carshare.client.users.UserClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final UserClient userClient;
    private final ReservationClient reservationClient;
    private final CarClient carClient;

    public MainController(UserClient userClient, ReservationClient reservationClient, CarClient carClient) {
        this.userClient = userClient;
        this.reservationClient = reservationClient;
        this.carClient = carClient;
    }

    @PostMapping("/users/{userId}/reservations/")
    public Reservation createNewReservation(@PathVariable long userId, @RequestBody NewReservationCommand reservationCommand) {
        var user = userClient.getUserById(userId);
        var car = carClient.getCarById(reservationCommand.carId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }

        var createdReservation = reservationClient.saveNewReservation(Reservation.builder()
                .carId(reservationCommand.carId)
                .userId(reservationCommand.userId)
                .start(reservationCommand.start)
                .end(reservationCommand.end)
                .build());

        System.out.println("RESERVATION CREATED: " + createdReservation.toString());
        return createdReservation;
    }

    @GetMapping("/users/{userId}/reservations")
    public GetReservationsFromUserQuery getAllReservationsFromUser(@PathVariable long userId) {
        var user = userClient.getUserById(userId);
        var result = new GetReservationsFromUserQuery();

        List<Reservation> reservations = reservationClient.getReservationsForUser(userId);
        for (Reservation reservation : reservations) {
            var car = carClient.getCarById(reservation.getCarId());
            result.getReservations().add(new FullReservationDTO(car, user, reservation.getStart(), reservation.getEnd()));
        }
        return result;
    }
}
