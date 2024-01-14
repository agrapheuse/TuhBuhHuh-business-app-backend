package be.kdg.integration5.business_app.business_app_backend.domain;

import be.kdg.integration5.business_app.business_app_backend.adapters.in.events.NewSensorDataEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record SensorData(
        UUID squareUUID,
        LocalDateTime timestamp,
        ValueType valueType,
        float sensorDataValue
) {
    public SensorData(NewSensorDataEvent event) {
        this(
                event.squareUUID(),
                event.timestamp(),
                event.valueType(),
                event.value()
        );
    }
}
