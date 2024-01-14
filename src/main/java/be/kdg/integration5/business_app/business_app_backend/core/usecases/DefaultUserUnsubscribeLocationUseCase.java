package be.kdg.integration5.business_app.business_app_backend.core.usecases;

import be.kdg.integration5.business_app.business_app_backend.ports.in.command.UserUnsubscribeLocationCommand;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserUnsubscribeLocationUsecase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultUserUnsubscribeLocationUseCase implements UserUnsubscribeLocationUsecase {
    private final UserUpdatePort userUpdatePort;
    @Override
    public void userUnsubscribeLocationUseCase(UserUnsubscribeLocationCommand command) {
        command.user().unsubscribeToLocation(command.location());
        userUpdatePort.update(command.user());
    }
}
