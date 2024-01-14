package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.AnomalyJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.AnomalyJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationAnomaliesLoadPort;
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
public class AnomaliesDBAdapter implements LocationAnomaliesLoadPort {
    private final static Logger log = LoggerFactory.getLogger(AnomaliesDBAdapter.class);
    private final AnomalyJpaEntityRepository anomalyJpaEntityRepository;
    @Override
    public Map<String, String> findLatestByLocation(Location.LocationUUID locationUUID) {
        Map<String, String> locationAnomalies = new HashMap<>();
        AnomalyJpaEntity anomaly = anomalyJpaEntityRepository.findFirstByLocationUuidOrderByDateTimeDesc(locationUUID.uuid()).orElse(null);
        if(anomaly != null) {
            locationAnomalies.put("locationUUID", locationUUID.uuid().toString());
            locationAnomalies.put("type", anomaly.getType().toString());
            locationAnomalies.put("timestamp", anomaly.getDateTime().toString());
            locationAnomalies.put("value", String.valueOf(anomaly.getValue()));
        }

        return locationAnomalies;
    }

    @Override
    public List<AnomalyJpaEntity> findByLocationAndType(Location.LocationUUID locationUUID, ValueType valueType) {
        return null;
    }

    @Override
    public Optional<AnomalyJpaEntity> findOneByLocationAndTypeAndDateTimeAfter(Location.LocationUUID locationUUID, ValueType valueType, LocalDateTime localDateTime) {
        return null;
    }
}
