package ua.kiev.prog.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.prog.model.Location;
import ua.kiev.prog.model.Rate;
import ua.kiev.prog.repo.LocationRepository;
import ua.kiev.prog.retrievers.GeoRetriever;
import ua.kiev.prog.retrievers.RateRetriever;

@RestController
public class RateController {
    @Autowired
    private RateRetriever rateRepository;
    @Autowired
    private GeoRetriever geoRetriever;
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/rate")
    public Rate rate(HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        Location location = geoRetriever.getLocation(ip);
        locationRepository.save(location);

        return rateRepository.getRate();
    }
}
