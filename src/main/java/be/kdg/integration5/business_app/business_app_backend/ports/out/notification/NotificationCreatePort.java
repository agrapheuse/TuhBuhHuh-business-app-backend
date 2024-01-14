package be.kdg.integration5.business_app.business_app_backend.ports.out.notification;

import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.User;

public interface NotificationCreatePort {
    void createNotification(Notification notification);
}
