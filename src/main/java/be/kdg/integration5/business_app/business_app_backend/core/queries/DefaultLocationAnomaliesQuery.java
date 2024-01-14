package be.kdg.integration5.business_app.business_app_backend.core.queries;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationAnomaliesQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.out.location.LocationAnomaliesLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class DefaultLocationAnomaliesQuery implements LocationAnomaliesQuery {


    private final LocationAnomaliesLoadPort locationAnomaliesLoadPort;
    @Override
    public Map<String, String> findLatestByLocation(Location.LocationUUID locationUUID) {
        return locationAnomaliesLoadPort.findLatestByLocation(locationUUID);
    }

    @Override
    public Map<String, Map<String, String>> findLatestByLocations(List<Location> locations) {
        Map<String, Map<String, String>> locationAnomalies = new HashMap<>();
        for(Location location: locations) {
            Map<String, String> locationAnomaly = findLatestByLocation(location.getUuid());
            if(!locationAnomaly.isEmpty()) {
                locationAnomalies.put(location.getUuid().uuid().toString(), locationAnomaly);
            }
        }
        return locationAnomalies;
    }

    @Override
    public Map<LocalDateTime, Map<String, Double>> findHistoricalByLocation(Location.LocationUUID uuid) {
        return null;
    }

    @Override
    public Map<String, Map<String, Double>> findSnapshotByLocation(Location.LocationUUID uuid) {
        return null;
    }
}
