package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.PredictionJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.MeasurementJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.PredictionJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationMeasurementsLoadPort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationPredictionsLoadPort;
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
public class LocationPredictionsDBAdapter implements LocationPredictionsLoadPort {
    private final static Logger log = LoggerFactory.getLogger(LocationPredictionsDBAdapter.class);
    private final PredictionJpaEntityRepository predictionJpaEntityRepository;

    @Override
    public Optional<PredictionJpaEntity> findOneByLocationAndTypeAndDateTimeAfter(Location.LocationUUID locationUUID, ValueType valueType, LocalDateTime localDateTime) {
        return predictionJpaEntityRepository.findFirstByLocationUuidAndTypeAndTimestampAfter(locationUUID.uuid(), valueType, localDateTime);
    }
}
