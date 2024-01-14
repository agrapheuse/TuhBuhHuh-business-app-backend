package be.kdg.integration5.business_app.business_app_backend.core.usecases;

import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserNewUseCase;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DefaultUserNewUseCase implements UserNewUseCase {
    private final UserUpdatePort userUpdatePort;
    @Override
    public void userNewUseCase(User user) {
        userUpdatePort.update(user);
    }
}
