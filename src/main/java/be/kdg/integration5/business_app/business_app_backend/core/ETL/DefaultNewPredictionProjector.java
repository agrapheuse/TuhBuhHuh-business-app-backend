package be.kdg.integration5.business_app.business_app_backend.core.ETL;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewPredictionEvent;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;
import be.kdg.integration5.business_app.business_app_backend.ports.in.projection.NewPredictionProjector;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationLoadPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultNewPredictionProjector implements NewPredictionProjector {
    private final static Logger log = LoggerFactory.getLogger(DefaultNewPredictionProjector.class);
    private final LocationLoadPort locationLoadPort;

    @Override
    public Prediction project(NewPredictionEvent event) {
        Location.LocationUUID locationUUID = new Location.LocationUUID(event.squareUUID());
        Optional<Location> location = locationLoadPort.findById(locationUUID);
        if (location.isEmpty()) {
            log.error("Location with UUID {} not found", locationUUID);
            throw new IllegalArgumentException("Location not found");
        }
        return new Prediction(
                UUID.randomUUID(),
                location.get(),
                event.timestamp(),
                event.timeMade(),
                event.valueType(),
                event.value()
        );
    }
}
