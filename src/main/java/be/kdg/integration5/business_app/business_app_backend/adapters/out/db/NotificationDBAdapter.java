package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.NotificationJpaEnitity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.NotificationJpaEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.out.notification.NotificationCreatePort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.notification.NotificationLoadPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NotificationDBAdapter implements NotificationLoadPort, NotificationCreatePort {
    private static final Logger log = LoggerFactory.getLogger(NotificationDBAdapter.class);
    private final NotificationJpaEntityRepository notificationJpaEntityRepository;

    @Override
    public Optional<Notification> findLatestByUserAndType(User.UserUUID userUUID, ValueType type, Notification.DataTypeNotification notificationType) {
        return notificationJpaEntityRepository
                .findAllByUserAndType(userUUID.uuid(), type, notificationType)
                .stream()
                .map(Optional::of)
                .reduce(Optional.empty(), (maxElement, newElement) -> {
                    if (newElement.get().getTimeCreated().isAfter(maxElement.get().getTimeCreated())) {
                        return newElement;
                    } else {
                        return maxElement;
                    }
                })
                .map(Notification::new);

    }


    @Override
    public Map<ValueType, Optional<Notification>> findAllCurrentDataLatestNotificationsByUser(User.UserUUID userUUID) {
        var notificationMap = new HashMap<ValueType, Optional<Notification>>();

        Arrays.stream(ValueType.values())
                .forEach(v -> notificationMap.put(v, this.findLatestByUserAndType(userUUID, v, Notification.DataTypeNotification.CURRENT_DATA)));

        return notificationMap;
    }

    @Override
    public Map<ValueType, Optional<Notification>> findAllPredictedDataLatestNotificationsByUser(User.UserUUID userUUID) {
        var notificationMap = new HashMap<ValueType, Optional<Notification>>();

        Arrays.stream(ValueType.values())
                .forEach(v -> notificationMap.put(v, this.findLatestByUserAndType(userUUID, v, Notification.DataTypeNotification.PREDICTED_DATA)));

        return notificationMap;
    }

    @Override
    public void createNotification(Notification notification) {
        notificationJpaEntityRepository.save(new NotificationJpaEnitity(notification));
    }
}
