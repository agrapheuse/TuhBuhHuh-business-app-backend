package be.kdg.integration5.business_app.business_app_backend.ports.in.query;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationQuery {
    List<Location> findAll();
    Optional<Location> findByID(Location.LocationUUID locationUUID);
}
