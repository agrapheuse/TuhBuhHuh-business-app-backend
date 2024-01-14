package be.kdg.integration5.business_app.business_app_backend.ports.out.notification;

import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;

import java.util.Map;
import java.util.Optional;

public interface NotificationLoadPort {
    Optional<Notification> findLatestByUserAndType(User.UserUUID userUUID, ValueType type, Notification.DataTypeNotification notificationType);
    Map<ValueType, Optional<Notification>> findAllCurrentDataLatestNotificationsByUser(User.UserUUID userUUID);
    Map<ValueType, Optional<Notification>> findAllPredictedDataLatestNotificationsByUser(User.UserUUID userUUID);

}
