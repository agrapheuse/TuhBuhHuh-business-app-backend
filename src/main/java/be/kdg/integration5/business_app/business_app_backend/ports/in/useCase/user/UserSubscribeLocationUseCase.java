package be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user;

import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserSubscribeLocationCommand;

public interface UserSubscribeLocationUseCase {
    void userSubscribeLocationUseCase(UserSubscribeLocationCommand command);
}
