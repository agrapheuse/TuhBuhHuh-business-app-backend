package be.kdg.integration5.business_app.business_app_backend.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Prediction implements Data {
    private final UUID uuid;
    private final Location location;
    private final LocalDateTime timestamp;
    private final LocalDateTime dateMade;
    private final ValueType type;
    private final float value;

    public Prediction(UUID uuid, Location location, LocalDateTime timestamp, LocalDateTime dateMade, ValueType type, float value) {
        this.uuid = uuid;
        this.location = location;
        this.type = type;
        this.timestamp = timestamp;
        this.dateMade = dateMade;
        this.value = value;
    }
}
