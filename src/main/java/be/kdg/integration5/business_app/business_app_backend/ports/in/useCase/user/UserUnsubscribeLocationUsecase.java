package be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user;

import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserSubscribeLocationCommand;
import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserUnsubscribeLocationCommand;

public interface UserUnsubscribeLocationUsecase {
    void userUnsubscribeLocationUseCase(UserUnsubscribeLocationCommand command);
}
