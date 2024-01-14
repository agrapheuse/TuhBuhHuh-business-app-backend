package be.kdg.integration5.business_app.business_app_backend.ports.out.location;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.AnomalyJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LocationAnomaliesLoadPort {
    Map<String, String> findLatestByLocation(Location.LocationUUID locationUUID);

    List<AnomalyJpaEntity> findByLocationAndType(Location.LocationUUID locationUUID, ValueType type);

    Optional<AnomalyJpaEntity> findOneByLocationAndTypeAndDateTimeAfter(Location.LocationUUID locationUUID, ValueType valueType, LocalDateTime localDateTime);
}
