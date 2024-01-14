package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.LocationJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationLoadPort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationMeasurementsLoadPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class LocationDBAdapter implements LocationLoadPort {
    private final static Logger log = LoggerFactory.getLogger(LocationDBAdapter.class);
    private final LocationJpaEntityRepository locationRepository;


    @Override
    public List<Location> findAll() {
        return locationRepository.findAll().stream().map(Location::new).toList();
    }

    @Override
    public Optional<Location> findById(Location.LocationUUID locationUUID) {
        return locationRepository.findById(locationUUID.uuid()).map(Location::new);
    }

}
