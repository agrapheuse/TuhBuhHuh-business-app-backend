package be.kdg.integration5.business_app.business_app_backend.core.queries;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationMeasurementsQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationMeasurementsLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultLocationMeasurementsQuery implements LocationMeasurementsQuery {
    private final LocationMeasurementsLoadPort locationMeasurementsLoadPort;
    @Override
    public Map<String, Double> findLatestByLocation(Location.LocationUUID locationUUID) {
        return locationMeasurementsLoadPort.findLatestByLocation(locationUUID);
    }

    @Override
    public Map<LocalDateTime, Map<String, Double>> findHistoricalByLocation(Location.LocationUUID locationUUID) {
        Map<LocalDateTime, Map<String, Double>> locationMeasurements = new HashMap<>();
        for (ValueType valueType : ValueType.values()) {
            locationMeasurementsLoadPort.findByLocationAndType(locationUUID, valueType).stream().forEach(m -> {
                if(!locationMeasurements.containsKey(m.getDateTime())) {
                    locationMeasurements.put(m.getDateTime(), new HashMap<>());
                }
                locationMeasurements.get(m.getDateTime()).put(m.getType().toString(), (double) m.getValue());
            });
        }
        return locationMeasurements;
    }
    @Override
    public Map<String, Map<String, Double>> findSnapshotByLocation(Location.LocationUUID locationUUID) {
        Map<String, Map<String, Double>> locationMeasurements = new HashMap<>();
        locationMeasurements.put("1hour", new HashMap<>());
        locationMeasurements.put("1day", new HashMap<>());
        locationMeasurements.put("3days", new HashMap<>());
        for (ValueType valueType : ValueType.values()) {
            locationMeasurementsLoadPort.findOneByLocationAndTypeAndDateTimeAfter(
                    locationUUID,
                    valueType,
                    LocalDateTime.now().minusHours(1)
            ).ifPresent(
                    measurementJpaEntity -> locationMeasurements.get("1hour").put(
                        valueType.name(),
                        (double) measurementJpaEntity.getValue()
                    )
            );
            locationMeasurementsLoadPort.findOneByLocationAndTypeAndDateTimeAfter(
                    locationUUID,
                    valueType,
                    LocalDateTime.now().minusDays(1)
            ).ifPresent(
                    measurementJpaEntity -> locationMeasurements.get("1day").put(
                            valueType.name(),
                            (double) measurementJpaEntity.getValue()
                    )
            );
            locationMeasurementsLoadPort.findOneByLocationAndTypeAndDateTimeAfter(
                    locationUUID,
                    valueType,
                    LocalDateTime.now().minusDays(3)
            ).ifPresent(
                    measurementJpaEntity -> locationMeasurements.get("3days").put(
                            valueType.name(),
                            (double) measurementJpaEntity.getValue()
                    )
            );
        }
        return locationMeasurements;
    }
}
