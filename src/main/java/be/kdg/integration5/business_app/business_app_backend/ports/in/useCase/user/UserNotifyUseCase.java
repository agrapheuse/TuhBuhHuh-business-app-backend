package be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user;

import be.kdg.integration5.business_app.business_app_backend.domain.Measurement;
import be.kdg.integration5.business_app.business_app_backend.domain.Prediction;
import be.kdg.integration5.business_app.business_app_backend.domain.User;

import java.util.List;

public interface UserNotifyUseCase {
    void notifyUser(User user, Measurement measurement);
    void notifyUser(User user, Prediction prediction);
    void notifyUsers(List<User> users, Measurement measurement);
    void notifyUsers(List<User> users, Prediction prediction);
}
