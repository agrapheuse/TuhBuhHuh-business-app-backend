package be.kdg.integration5.business_app.business_app_backend.ports.out.location;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.User;

import java.util.List;
import java.util.Optional;

public interface LocationLoadPort {
    List<Location> findAll();
    Optional<Location> findById(Location.LocationUUID locationUUID);
}
