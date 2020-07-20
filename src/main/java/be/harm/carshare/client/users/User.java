package be.harm.carshare.client.users;

import be.harm.carshare.client.users.security.ApplicationRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private Long id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String address;

    private String drivingLicenseNumber;

    private String telephoneNumber;

    private Set<ApplicationRole> roles = new HashSet<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
