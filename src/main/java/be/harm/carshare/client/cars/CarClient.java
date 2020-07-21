package be.harm.carshare.client.cars;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Component
public class CarClient {
    private static final String CARS_PATH = "/cars/";

    private final EurekaClient eurekaClient;

    private String hostName;

    private int port;

    private final RestTemplate restTemplate;

    public CarClient(@Qualifier("eurekaClient") EurekaClient eurekaClient, RestTemplateBuilder restTemplateBuilder) {
        this.eurekaClient = eurekaClient;
        this.restTemplate = restTemplateBuilder.build();

        Application carsService = this.eurekaClient.getApplication("carshare-carsservice");
        InstanceInfo instanceInfo = carsService.getInstances().get(0);
        this.hostName = instanceInfo.getHostName();
        this.port = instanceInfo.getPort();
    }

    public Car getCarById(long carId) {
        return restTemplate.getForObject(baseUrl() + carId, Car.class);
    }
    public List<Car> getCars() {
        Car[] receivedCars = restTemplate.getForObject(hostName +":" + port + CARS_PATH, Car[].class);
        return Arrays.asList(receivedCars);
    }

    public Car saveNewCar(@Valid @RequestBody Car car) {
        return restTemplate.postForObject(hostName + ":" + port + CARS_PATH, car, Car.class);
    }

}
