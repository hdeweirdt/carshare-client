package be.harm.carshare.client.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserClientTest {

    @Autowired
    UserClient client;

    @Test
    void getUserByIdShouldReturnUser() {
        User user = client.getUserById(1L);
        assertNotNull(user);
    }
}