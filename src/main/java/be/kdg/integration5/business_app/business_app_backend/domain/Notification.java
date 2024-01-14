package be.kdg.integration5.business_app.business_app_backend.domain;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.NotificationJpaEnitity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@Setter
public class Notification {

    public record NotificationUUID(UUID uuid){}

    private NotificationUUID uuid;
    private User.UserUUID userUUID;
    private LocalDateTime timeCreated;
    private ValueType type;
    private int threshold;
    private float value;
    private DataTypeNotification notificationType;

    public enum DataTypeNotification {
        CURRENT_DATA, PREDICTED_DATA
    }

    public Notification(NotificationJpaEnitity notificationJpaEnitity) {
        this.uuid = new NotificationUUID(notificationJpaEnitity.getUuid());
        this.userUUID = new User.UserUUID(notificationJpaEnitity.getUserUUID());
        this.timeCreated = notificationJpaEnitity.getTimeCreated();
        this.type = notificationJpaEnitity.getType();
        this.threshold = notificationJpaEnitity.getThreshold();
        this.value = notificationJpaEnitity.getValue();
        this.notificationType = notificationJpaEnitity.getNotificationType();
    }

    public Notification(User user, Measurement measurement) {
        this.uuid = new NotificationUUID(UUID.randomUUID());
        this.userUUID = user.getUuid();
        this.timeCreated = LocalDateTime.now();
        this.type = measurement.getType();
        this.threshold = user.getThresholds().get(measurement.getType());
        this.value = measurement.getValue();
        this.notificationType = DataTypeNotification.CURRENT_DATA;
    }

    public Notification(User user, Prediction prediction) {
        this.uuid = new NotificationUUID(UUID.randomUUID());
        this.userUUID = user.getUuid();
        this.timeCreated = LocalDateTime.now();
        this.type = prediction.getType();
        this.threshold = user.getThresholds().get(prediction.getType());
        this.value = prediction.getValue();
        this.notificationType = DataTypeNotification.PREDICTED_DATA;
    }

    public boolean isLongAgo() {
        var diff = ChronoUnit.MINUTES.between(LocalDateTime.now(), this.timeCreated);
        return diff > 30;
    }

}
