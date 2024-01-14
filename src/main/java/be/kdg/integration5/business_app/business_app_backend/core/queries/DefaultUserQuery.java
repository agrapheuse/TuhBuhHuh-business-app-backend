package be.kdg.integration5.business_app.business_app_backend.core.queries;

import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.UserQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultUserQuery implements UserQuery {
    private final UserLoadPort userLoadPort;
    @Override
    public Optional<User> findById(User.UserUUID userUUID) {
        return userLoadPort.findById(userUUID);
    }
}
