package be.kdg.integration5.business_app.business_app_backend.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Measurement implements Data {
    private final UUID uuid;
    private final Location location;
    private final LocalDateTime dateTime;
    private final ValueType type;
    private final float value;

    public Measurement(UUID uuid, Location location, LocalDateTime dateTime, ValueType type, float value) {
        this.uuid = uuid;
        this.location = location;
        this.type = type;
        this.dateTime = dateTime;
        this.value = value;
    }
}
