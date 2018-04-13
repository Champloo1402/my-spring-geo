package ua.kiev.prog.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.prog.dto.LocationDTO;
import ua.kiev.prog.model.Location;
import ua.kiev.prog.model.Rate;
import ua.kiev.prog.repo.LocationRepository;
import ua.kiev.prog.retrievers.GeoRetriever;
import ua.kiev.prog.retrievers.RateRetriever;
import ua.kiev.prog.services.LocationService;

@RestController
public class RateController {
    private final RateRetriever rateRetriever;
    private final GeoRetriever geoRetriever;
    private final LocationService locationService;

    public RateController(RateRetriever rateRetriever, GeoRetriever geoRetriever, LocationService locationService) {
        this.rateRetriever = rateRetriever;
        this.geoRetriever = geoRetriever;
        this.locationService = locationService;
    }

    @GetMapping("/rate")
    public Rate rate(HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        LocationDTO location = geoRetriever.getLocation(ip);
        locationService.save(location);

        return rateRetriever.getRate();
    }
}
