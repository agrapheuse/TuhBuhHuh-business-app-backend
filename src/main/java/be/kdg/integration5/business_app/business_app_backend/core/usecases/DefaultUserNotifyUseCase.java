package be.kdg.integration5.business_app.business_app_backend.core.usecases;

import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserNotifyUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.notification.NotificationCreatePort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.notification.NotificationLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultUserNotifyUseCase implements UserNotifyUseCase {
    private final List<NotificationCreatePort> notificationCreatePorts;
    private final NotificationLoadPort notificationLoadPort;
    @Override
    public void notifyUser(User user, Measurement measurement) {
        var possibleNotification = user.wantsToBeNotified(measurement);
        if (possibleNotification.isEmpty()) {
            return;
        }
        var notification = possibleNotification.get();


        var sendNotification = notificationLoadPort
                .findLatestByUserAndType(user.getUuid(), measurement.getType(), Notification.DataTypeNotification.CURRENT_DATA)
                .map(Notification::isLongAgo)
                .orElse(true);

        if (sendNotification) {
            notificationCreatePorts.forEach(p -> p.createNotification(notification));
        }
    }

    @Override
    public void notifyUser(User user, Prediction prediction) {
        var possibleNotification = user.wantsToBeNotified(prediction);
        if (possibleNotification.isEmpty()) {
            return;
        }
        var notification = possibleNotification.get();

        var sendNotification = notificationLoadPort
                .findLatestByUserAndType(user.getUuid(), prediction.getType(), Notification.DataTypeNotification.PREDICTED_DATA)
                .map(Notification::isLongAgo)
                .orElse(true);
        if (sendNotification) {
            notificationCreatePorts.forEach(p -> p.createNotification(notification));
        }
    }

    @Override
    public void notifyUsers(List<User> users, Measurement measurement) {
        users.forEach(user -> notifyUser(user, measurement));
    }

    @Override
    public void notifyUsers(List<User> users, Prediction prediction) {
        users.forEach(user -> notifyUser(user, prediction));
    }
}
