package be.kdg.integration5.business_app.business_app_backend.core.queries;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationMeasurementsQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationPredictionsQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationMeasurementsLoadPort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationPredictionsLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class DefaultLocationPredictionsQuery implements LocationPredictionsQuery {
    private final LocationPredictionsLoadPort locationPredictionsLoadPort;
    @Override
    public Map<String, Map<String, Double>> findLatestByLocation(Location.LocationUUID locationUUID) {
        Map<String, Map<String, Double>> locationPredictions = new HashMap<>();
        locationPredictions.put("1hour", new HashMap<>());
        locationPredictions.put("1day", new HashMap<>());
        locationPredictions.put("3days", new HashMap<>());
        for (ValueType valueType : ValueType.values()) {
            locationPredictionsLoadPort.findOneByLocationAndTypeAndDateTimeAfter(
                    locationUUID,
                    valueType,
                    LocalDateTime.now().minusHours(1)
            ).ifPresent(
                     predictionJpaEntity -> locationPredictions.get("1hour").put(
                            valueType.name(),
                            (double) predictionJpaEntity.getValue()
                    )
            );
            locationPredictionsLoadPort.findOneByLocationAndTypeAndDateTimeAfter(
                    locationUUID,
                    valueType,
                    LocalDateTime.now().minusDays(1)
            ).ifPresent(
                    measurementJpaEntity -> locationPredictions.get("1day").put(
                            valueType.name(),
                            (double) measurementJpaEntity.getValue()
                    )
            );
            locationPredictionsLoadPort.findOneByLocationAndTypeAndDateTimeAfter(
                    locationUUID,
                    valueType,
                    LocalDateTime.now().minusDays(3)
            ).ifPresent(
                    measurementJpaEntity -> locationPredictions.get("3days").put(
                            valueType.name(),
                            (double) measurementJpaEntity.getValue()
                    )
            );
        }
        return locationPredictions;
    }

}
