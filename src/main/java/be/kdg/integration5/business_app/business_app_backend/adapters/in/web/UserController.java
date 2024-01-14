package be.kdg.integration5.business_app.business_app_backend.adapters.in.web;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses.UserDto;
import be.kdg.integration5.business_app.business_app_backend.auth.UserFactory;
import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserSubscribeLocationCommand;
import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserUnsubscribeLocationCommand;
import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserUpdateThresholdCommand;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.LocationQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserSubscribeLocationUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserUnsubscribeLocationUsecase;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserUpdateThresholdUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.NotificationQuery;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserFactory userFactory;
    private final LocationQuery locationQuery;
    private final NotificationQuery notificationQuery;
    private final UserSubscribeLocationUseCase userSubscribeLocationUsecase;
    private final UserUnsubscribeLocationUsecase userUnsubscribeLocationUsecase;
    private final UserUpdateThresholdUseCase userUpdateThresholdUseCase;

    @GetMapping
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal Jwt token) {
        User user = userFactory.getUserFromToken(token);
        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UserDto(
                        user,
                        notificationQuery.findAllCurrentDataLatestNotificationsByUser(user.getUuid()),
                        notificationQuery.findAllPredictedDataLatestNotificationsByUser(user.getUuid())
                ));

    }
    @PatchMapping("/subscribe-location/{locationUUID}")
    public ResponseEntity<String> subscribeLocation(@AuthenticationPrincipal Jwt token, @PathVariable Location.LocationUUID locationUUID) {
        log.debug("Subscribing to Location: " + locationUUID.uuid().toString());
        User user = userFactory.getUserFromToken(token);
        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Location location = locationQuery.findByID(locationUUID).orElse(null);
        if(location == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        if(!user.isSubbed(location)) {
            userSubscribeLocationUsecase.userSubscribeLocationUseCase(new UserSubscribeLocationCommand(user, location));
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PatchMapping("/update-threshold")
    public ResponseEntity<String> updateThreshold(@AuthenticationPrincipal Jwt token, @RequestBody Map<String,Object> body) {
        log.debug("Threshold to update: " + body.get("threshold") + " -> " + body.get("value"));
        User user = userFactory.getUserFromToken(token);
        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        userUpdateThresholdUseCase.userUpdateThresholdUseCase(new UserUpdateThresholdCommand(user, ValueType.valueOf((String) body.get("threshold")), (Integer) body.get("value")));

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PatchMapping("/unsubscribe-location/{locationUUID}")
    public ResponseEntity<String> unsubscribeLocation(@AuthenticationPrincipal Jwt token, @PathVariable Location.LocationUUID locationUUID) {
        log.debug("Unsubscribing from Location: " + locationUUID.uuid().toString());
        User user = userFactory.getUserFromToken(token);
        if(user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Location location = locationQuery.findByID(locationUUID).orElse(null);
        if(location == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        userUnsubscribeLocationUsecase.userUnsubscribeLocationUseCase(new UserUnsubscribeLocationCommand(user, location));

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
