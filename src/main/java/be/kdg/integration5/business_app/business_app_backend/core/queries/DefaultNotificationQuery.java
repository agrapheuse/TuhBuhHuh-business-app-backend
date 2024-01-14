package be.kdg.integration5.business_app.business_app_backend.core.queries;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.NotificationJpaEnitity;
import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.NotificationQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.out.notification.NotificationLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DefaultNotificationQuery implements NotificationQuery {

    private final NotificationLoadPort notificationLoadPort;


    @Override
    public Map<ValueType, Optional<Notification>> findAllCurrentDataLatestNotificationsByUser(User.UserUUID userUUID) {
        var latestNotifications = notificationLoadPort.findAllCurrentDataLatestNotificationsByUser(userUUID);
        for(Map.Entry<ValueType, Optional<Notification>> entry : latestNotifications.entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            if (entry.getValue().get().isLongAgo()) {
                latestNotifications.put(entry.getKey(), Optional.empty());
            }
        }
        return latestNotifications;
    }

    @Override
    public Map<ValueType, Optional<Notification>> findAllPredictedDataLatestNotificationsByUser(User.UserUUID userUUID) {
        var latestNotifications = notificationLoadPort.findAllPredictedDataLatestNotificationsByUser(userUUID);
        for(Map.Entry<ValueType, Optional<Notification>> entry : latestNotifications.entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            if (entry.getValue().get().isLongAgo()) {
                latestNotifications.put(entry.getKey(), Optional.empty());
            }
        }
        return latestNotifications;
    }
}
