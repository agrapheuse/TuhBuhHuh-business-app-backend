package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.LocationJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.MeasurementJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationLoadPort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationMeasurementsLoadPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class LocationMeasurementsDBAdapter implements LocationMeasurementsLoadPort {
    private final static Logger log = LoggerFactory.getLogger(LocationMeasurementsDBAdapter.class);
    private final MeasurementJpaEntityRepository measurementJpaEntityRepository;
    @Override
    public Map<String, Double> findLatestByLocation(Location.LocationUUID locationUUID) {
        Map<String, Double> locationMeasurements = new HashMap<>();
        for (ValueType valueType : ValueType.values()) {
            MeasurementJpaEntity measurement = measurementJpaEntityRepository.findFirstByLocationUuidAndTypeOrderByDateTimeDesc(locationUUID.uuid(), valueType).orElse(null);
            if(measurement != null) {
                locationMeasurements.put(valueType.toString(), (double) measurement.getValue());
            }
        }
        return locationMeasurements;
    }

    @Override
    public List<MeasurementJpaEntity> findByLocationAndType(Location.LocationUUID locationUUID, ValueType valueType) {
        return measurementJpaEntityRepository.findByLocationUuidAndTypeOrderByDateTimeDesc(locationUUID.uuid(), valueType);
    }

    @Override
    public Optional<MeasurementJpaEntity> findOneByLocationAndTypeAndDateTimeAfter(Location.LocationUUID locationUUID, ValueType valueType, LocalDateTime localDateTime) {
        return measurementJpaEntityRepository.findFirstByLocationUuidAndTypeAndDateTimeAfter(locationUUID.uuid(), valueType, localDateTime);
    }
}
