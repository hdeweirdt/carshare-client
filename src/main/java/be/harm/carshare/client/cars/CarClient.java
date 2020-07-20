package be.harm.carshare.client.cars;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Component
@ConfigurationProperties(value = "be.harm.carshare.cars", ignoreUnknownFields = false)
public class CarClient {
    private static final String CARS_PATH = "/cars/";

    @Setter
    private String hostName;

    @Setter
    private String port;

    private final RestTemplate restTemplate;

    public CarClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Car getCarById(long carId) {
        Car receivedCar = restTemplate.getForObject(hostName +":" + port + CARS_PATH + "/" + carId, Car.class);
        return receivedCar;
    }
    public List<Car> getCars() {
        Car[] receivedCars = restTemplate.getForObject(hostName +":" + port + CARS_PATH, Car[].class);
        return Arrays.asList(receivedCars);
    }

    public Car saveNewCar(@Valid @RequestBody Car car) {
        return restTemplate.postForObject(hostName + ":" + port + CARS_PATH, car, Car.class);
    }

}
