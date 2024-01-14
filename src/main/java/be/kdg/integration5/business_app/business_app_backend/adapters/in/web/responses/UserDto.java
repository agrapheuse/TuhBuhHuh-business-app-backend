package be.kdg.integration5.business_app.business_app_backend.adapters.in.web.responses;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public record UserDto(
        UUID uuid,
        String firstName,
        String lastName,
        List<LocationDto> locations,
        Map<ValueType, Integer> thresholds,
        Map<ValueType, Optional<Notification>> currentNotifications,
        Map<ValueType, Optional<Notification>> predictedNotifications

) {
    public UserDto(
            User user,
            Map<ValueType, Optional<Notification>> currentNotifications,
            Map<ValueType, Optional<Notification>> predictedNotifications
    ) {
        this (
                user.getUuid().uuid(),
                user.getFirstName(),
                user.getLastName(),
                user.getLocations().stream().map(LocationDto::new).toList(),
                user.getThresholds(),
                currentNotifications,
                predictedNotifications
        );
    }
}
