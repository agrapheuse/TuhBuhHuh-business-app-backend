package be.kdg.integration5.business_app.business_app_backend.core.ETL;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewSensorDataEvent;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.ports.in.projection.NewSensorDataCreatedProjector;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationLoadPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultNewSensorDataCreatedProjector implements NewSensorDataCreatedProjector {
    private final static Logger log = LoggerFactory.getLogger(DefaultNewPredictionProjector.class);
    private final LocationLoadPort locationLoadPort;

    @Override
    public Measurement project(NewSensorDataEvent event) {
        Location.LocationUUID locationUUID = new Location.LocationUUID(event.squareUUID());
        Optional<Location> location = locationLoadPort.findById(locationUUID);
        if (location.isEmpty()) {
            log.error("Location with UUID {} not found", locationUUID);
            throw new IllegalArgumentException("Location not found");
        }
        return new Measurement(
                UUID.randomUUID(),
                location.get(),
                event.timestamp(),
                event.valueType(),
                event.value()
        );
    }
}
