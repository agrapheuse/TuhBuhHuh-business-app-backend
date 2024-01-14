package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity;

import be.kdg.integration5.business_app.business_app_backend.domain.Data;
import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
public class MeasurementJpaEntity implements Data {

    @Id @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @Getter
    private LocationJpaEntity location;
    @Getter @Enumerated(EnumType.STRING)
    private ValueType type;
    @Getter
    private LocalDateTime dateTime;
    @Getter
    private float value;

    public MeasurementJpaEntity(Measurement measurement) {
        this.uuid = measurement.getUuid();
        this.location = new LocationJpaEntity(measurement.getLocation());
        this.type = measurement.getType();
        this.dateTime = measurement.getDateTime();
        this.value = measurement.getValue();
    }

    public MeasurementJpaEntity() {
    }
}
