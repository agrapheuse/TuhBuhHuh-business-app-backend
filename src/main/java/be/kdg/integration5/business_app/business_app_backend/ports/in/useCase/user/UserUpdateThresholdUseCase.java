package be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user;

import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserUpdateThresholdCommand;

public interface UserUpdateThresholdUseCase {
    void userUpdateThresholdUseCase(UserUpdateThresholdCommand command);
}
