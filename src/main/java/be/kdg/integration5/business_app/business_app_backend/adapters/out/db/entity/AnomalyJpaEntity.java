package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity;

import be.kdg.integration5.business_app.business_app_backend.domain.Anomaly;
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
public class AnomalyJpaEntity {
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

    public AnomalyJpaEntity(Anomaly anomaly) {
        this.uuid = anomaly.getUuid();
        this.location = new LocationJpaEntity(anomaly.getLocation());
        this.type = anomaly.getType();
        this.dateTime = anomaly.getDateTime();
        this.value = anomaly.getValue();
    }

    public AnomalyJpaEntity() {
    }
}
