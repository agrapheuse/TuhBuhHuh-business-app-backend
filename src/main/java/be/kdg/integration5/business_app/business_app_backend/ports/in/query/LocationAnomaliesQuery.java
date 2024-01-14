package be.kdg.integration5.business_app.business_app_backend.ports.in.query;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface LocationAnomaliesQuery {
    Map<String, String> findLatestByLocation(Location.LocationUUID locationUUID);
    Map<String, Map<String, String>> findLatestByLocations(List<Location> locations);

    Map<LocalDateTime, Map<String, Double>> findHistoricalByLocation(Location.LocationUUID uuid);
    Map<String, Map<String, Double>> findSnapshotByLocation(Location.LocationUUID uuid);
}
