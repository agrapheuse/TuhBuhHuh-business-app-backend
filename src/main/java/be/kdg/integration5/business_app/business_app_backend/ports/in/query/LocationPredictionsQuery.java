package be.kdg.integration5.business_app.business_app_backend.ports.in.query;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;

import java.util.Map;

public interface LocationPredictionsQuery {
    Map<String, Map<String, Double>> findLatestByLocation(Location.LocationUUID locationUUID);

}
