package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity;

import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Table
@Entity
public class PredictionJpaEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private LocationJpaEntity location;

    private LocalDateTime timestamp;
    private LocalDateTime dateMade;
    @Getter @Enumerated(EnumType.STRING)
    private ValueType type;
    private float value;

    public PredictionJpaEntity(Prediction prediction) {
        this.uuid = prediction.getUuid();
        this.location = new LocationJpaEntity(prediction.getLocation());
        this.timestamp = prediction.getTimestamp();
        this.dateMade = prediction.getDateMade();
        this.type = prediction.getType();
        this.value = prediction.getValue();
    }

    public PredictionJpaEntity() {

    }
}
