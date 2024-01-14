package be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import com.google.maps.model.LatLng;
import org.springframework.boot.origin.TextResourceOrigin;

import java.util.Map;
import java.util.UUID;

public record LocationDto(
        UUID uuid,
        LatLng topLeft,
        LatLng bottomRight,
        Map<String, Double> measurements,
        boolean subbed
) {
    public LocationDto(Location location) {
        this (
                location.getUuid().uuid(),
                location.getTopLeft(),
                location.getBottomRight(),
                null,
                false
        );
    }

    public LocationDto(Location location, Map<String, Double> latestMeasurements, boolean subbed) {
        this (
                location.getUuid().uuid(),
                location.getTopLeft(),
                location.getBottomRight(),
                latestMeasurements,
                subbed
        );
    }
}
