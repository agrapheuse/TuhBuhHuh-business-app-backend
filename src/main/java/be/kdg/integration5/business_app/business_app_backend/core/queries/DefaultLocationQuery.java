package be.kdg.integration5.business_app.business_app_backend.core.queries;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultLocationQuery implements LocationQuery {
    private final LocationLoadPort locationLoadPort;
    @Override
    public List<Location> findAll() {
        return locationLoadPort.findAll();
    }

    @Override
    public Optional<Location> findByID(Location.LocationUUID locationUUID) {
        return locationLoadPort.findById(locationUUID);
    }
}
