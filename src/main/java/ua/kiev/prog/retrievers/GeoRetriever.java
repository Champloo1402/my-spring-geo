package ua.kiev.prog.retrievers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.kiev.prog.model.Location;

@Component
public class GeoRetriever {
    private static final String URL = "http://ipinfo.io/";

    public Location getLocation(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Location> response = restTemplate.getForEntity(URL + ip, Location.class);
        return response.getBody();
    }
}
