package be.kdg.integration5.business_app.business_app_backend.ports.in.query;

import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;

import java.util.Map;
import java.util.Optional;

public interface NotificationQuery {

    Map<ValueType, Optional<Notification>> findAllCurrentDataLatestNotificationsByUser(User.UserUUID userUUID);
    Map<ValueType, Optional<Notification>> findAllPredictedDataLatestNotificationsByUser(User.UserUUID userUUID);
}
