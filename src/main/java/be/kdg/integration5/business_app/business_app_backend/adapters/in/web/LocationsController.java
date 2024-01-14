package be.kdg.integration5.business_app.business_app_backend.adapters.in.web;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses.HistoricalDataDto;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses.LocationDto;
import be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses.SnapshotDataDto;
import be.kdg.integration5.business_app.business_app_backend.auth.UserFactory;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationAnomaliesQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationMeasurementsQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationPredictionsQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationQuery;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/locations")
public class LocationsController {

    private final static Logger log = LoggerFactory.getLogger(LocationsController.class);
    private final UserFactory userFactory;
    private final LocationQuery locationQuery;
    private final LocationMeasurementsQuery locationMeasurementsQuery;
    private final LocationPredictionsQuery locationPredictionsQuery;
    private final LocationAnomaliesQuery locationAnomaliesQuery;

    @GetMapping
    ResponseEntity<List<LocationDto>> getLocations(@AuthenticationPrincipal Jwt token) {
        User user = userFactory.getUserFromToken(token);
        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return new ResponseEntity<>(
                locationQuery.findAll()
                        .stream()
                        .map(location -> new LocationDto(location, locationMeasurementsQuery.findLatestByLocation(location.getUuid()), user.isSubbed(location)))
                        .toList(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    ResponseEntity<LocationDto> getLocation(@AuthenticationPrincipal Jwt token, @PathVariable Location.LocationUUID uuid) {
        User user = userFactory.getUserFromToken(token);
        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Location location = locationQuery.findByID(uuid).orElse(null);
        if(location == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new LocationDto(location, locationMeasurementsQuery.findLatestByLocation(uuid), user.isSubbed(location)));
    }
    @GetMapping("/{uuid}/historical")
    ResponseEntity<HistoricalDataDto> getLocationHistorical(@PathVariable Location.LocationUUID uuid) {
        Location location = locationQuery.findByID(uuid).orElse(null);
        if(location == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new HistoricalDataDto(locationMeasurementsQuery.findHistoricalByLocation(uuid)));
    }
    @GetMapping("/{uuid}/snapshot")
    ResponseEntity<SnapshotDataDto> getLocationSnapshot(@PathVariable Location.LocationUUID uuid) {
        Location location = locationQuery.findByID(uuid).orElse(null);
        if(location == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new SnapshotDataDto(
                        locationMeasurementsQuery.findSnapshotByLocation(uuid),
                        locationPredictionsQuery.findLatestByLocation(uuid),
                        locationAnomaliesQuery.findLatestByLocation(uuid)
                ));
    }


}
