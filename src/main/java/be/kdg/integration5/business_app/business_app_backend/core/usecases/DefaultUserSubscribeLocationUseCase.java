package be.kdg.integration5.business_app.business_app_backend.core.usecases;

import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserSubscribeLocationCommand;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserSubscribeLocationUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultUserSubscribeLocationUseCase implements UserSubscribeLocationUseCase {
    private final UserUpdatePort userUpdatePort;
    @Override
    public void userSubscribeLocationUseCase(UserSubscribeLocationCommand command) {
        command.user().subscribeToLocation(command.location());
        userUpdatePort.update(command.user());
    }
}
