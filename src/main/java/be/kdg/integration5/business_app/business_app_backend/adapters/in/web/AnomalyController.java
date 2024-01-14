package be.kdg.integration5.business_app.business_app_backend.adapters.in.web;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses.AnomaliesDto;
import be.kdg.integration5.business_app.business_app_backend.auth.UserFactory;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationAnomaliesQuery;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users/anomalies")
public class AnomalyController {
    private final static Logger log = LoggerFactory.getLogger(AnomalyController.class);
    private final UserFactory userFactory;
    private final LocationAnomaliesQuery locationAnomaliesQuery;

    @GetMapping
    public ResponseEntity<AnomaliesDto> getForUser(@AuthenticationPrincipal Jwt token) {
        User user = userFactory.getUserFromToken(token);
        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AnomaliesDto(locationAnomaliesQuery.findLatestByLocations(user.getLocations())));
    }
}
