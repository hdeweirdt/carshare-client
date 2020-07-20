package be.harm.carshare.client.reservations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Component
@ConfigurationProperties(value = "be.harm.carshare.reservations", ignoreUnknownFields = false)
public class ReservationClient {
    private static final String RESERVATIONS_PATH = "/reservations/";

    private String hostName;
    private String port;

    private final RestTemplate restTemplate;

    public ReservationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Reservation saveNewReservation(@Valid Reservation reservation) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(hostName + ":" + port + RESERVATIONS_PATH);
        return restTemplate.postForObject(uriBuilder.build().toUriString(), reservation, Reservation.class);
    }

    public List<Reservation> getReservationsForUser(long userId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(hostName + ":" + port + RESERVATIONS_PATH);
        uriBuilder.queryParam("userId", userId);

        Reservation[] reservations =  restTemplate.getForObject(uriBuilder.toUriString(), Reservation[].class);

        return Arrays.asList(reservations);
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
