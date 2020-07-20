package be.harm.carshare.client.cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarClientTest {

    @Autowired
    CarClient client;

    @Test
    void getCarsShouldReturnNonEmptyList() {
        List<Car> cars = client.getCars();
        assertNotNull(cars);
        assertFalse(cars.isEmpty());
    }

    @Test
    void saveNewCarShouldReturnSavedCar() {
        Car car = Car.builder()
                .brand("TestBrand")
                .fuelType(FuelType.DIESEL)
                .name("TestCar")
                .build();

        var returnedCar = client.saveNewCar(car);

        assertNotNull(returnedCar);
        assertTrue(returnedCar.getId()>0);
    }
}
