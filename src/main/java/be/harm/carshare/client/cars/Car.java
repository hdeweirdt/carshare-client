package be.harm.carshare.client.cars;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class Car {

    @Null
    public Long id;

    @NotNull
    private Long ownerId;

    @NotBlank
    public String name;

    @NotBlank
    public String brand;

    @NotBlank
    public String model;

    public int numberOfSeats;

    public FuelType fuelType;

    public boolean hasGPS;
}
