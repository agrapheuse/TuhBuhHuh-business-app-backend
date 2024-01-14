package be.kdg.integration5.business_app.business_app_backend.core.usecases;

import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserUpdateThresholdCommand;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserUpdateThresholdUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultUserUpdateThresholdUseCase implements UserUpdateThresholdUseCase {
    private final UserUpdatePort userUpdatePort;
    @Override
    public void userUpdateThresholdUseCase(UserUpdateThresholdCommand command) {
        command.user().updateThreshold(command.valueType(), command.value());
        userUpdatePort.update(command.user());
    }
}
