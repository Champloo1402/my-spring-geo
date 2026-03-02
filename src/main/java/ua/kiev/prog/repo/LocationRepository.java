package ua.kiev.prog.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dto.LocationDTO;
import ua.kiev.prog.model.Location;

import java.util.ArrayList;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    public Page<Location> findByCity(String city, Pageable pageable);

    public Long countByCity (String city);
}
