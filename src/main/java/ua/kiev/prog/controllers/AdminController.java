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
    private static final int PAGE_SIZE = 6;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("count")
    public PageCount count() {
        return new PageCount(locationRepository.count(), PAGE_SIZE);
    }

    @GetMapping("geo")
    public List<Location> locations(@RequestParam(required = false, defaultValue = "0") int page) {
        return locationRepository
                .findAll(PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "id"))
                .getContent();
    }
}
