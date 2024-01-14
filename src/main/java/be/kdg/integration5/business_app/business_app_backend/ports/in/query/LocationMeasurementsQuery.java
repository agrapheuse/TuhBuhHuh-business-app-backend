package be.kdg.integration5.business_app.business_app_backend.ports.in.query;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;

import java.time.LocalDateTime;
import java.util.Map;

public interface LocationMeasurementsQuery {
    Map<String, Double> findLatestByLocation(Location.LocationUUID locationUUID);

    Map<LocalDateTime, Map<String, Double>> findHistoricalByLocation(Location.LocationUUID uuid);
    Map<String, Map<String, Double>> findSnapshotByLocation(Location.LocationUUID uuid);
}
