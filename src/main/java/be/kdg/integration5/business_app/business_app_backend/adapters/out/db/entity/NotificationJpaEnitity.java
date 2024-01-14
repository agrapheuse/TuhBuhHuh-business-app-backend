package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity;

import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NotificationJpaEnitity {
    @Id @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID userUUID;
    private LocalDateTime timeCreated;
    @Enumerated(EnumType.STRING)
    private ValueType type;

    private int threshold;
    private float value;
    @Enumerated(EnumType.STRING)
    private Notification.DataTypeNotification notificationType;

    public NotificationJpaEnitity(Notification notification) {
        this.uuid = notification.getUuid().uuid();
        this.userUUID = notification.getUserUUID().uuid();
        this.timeCreated = notification.getTimeCreated();
        this.type = notification.getType();
        this.threshold = notification.getThreshold();
        this.value = notification.getValue();
        this.notificationType = notification.getNotificationType();
    }


}
