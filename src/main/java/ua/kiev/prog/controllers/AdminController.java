package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kiev.prog.model.Location;
import ua.kiev.prog.model.PageCount;
import ua.kiev.prog.repo.LocationRepository;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("pages")
    public PageCount pageCount() {
        return new PageCount(locationRepository.count());
    }

    @GetMapping("geo")
    public List<Location> locations(@RequestParam(required = false, defaultValue = "0") int page) {
        return locationRepository
                .findAll(PageRequest.of(page, 10, Sort.Direction.DESC))
                .getContent();
    }
}
