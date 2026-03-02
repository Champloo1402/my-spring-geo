package ua.kiev.prog.services;

import org.springframework.data.domain.Pageable;
import ua.kiev.prog.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    void save(LocationDTO dto);
    long count(String city);
    List<LocationDTO> getLocations(String city, Pageable pageable);
}
