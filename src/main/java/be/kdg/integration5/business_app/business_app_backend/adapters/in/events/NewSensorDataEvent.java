package be.kdg.integration5.business_app.business_app_backend.adapters.in.events;

import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record NewSensorDataEvent(
        UUID squareUUID,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        ValueType valueType,
        float value
) implements Event {
}
