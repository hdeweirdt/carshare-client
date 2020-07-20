package be.harm.carshare.client.users;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(value = "be.harm.carshare.users", ignoreUnknownFields = false)
public class UserClient {
    private static final String USER_PATH = "/users/";
    private String hostName;
    private String port;

    private final RestTemplate restTemplate;

    public UserClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User getUserById(long id) {
        return restTemplate.getForObject(hostName +":" + port + USER_PATH + id, User.class);
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
