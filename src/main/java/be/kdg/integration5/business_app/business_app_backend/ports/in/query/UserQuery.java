package be.kdg.integration5.business_app.business_app_backend.ports.in.query;

import be.kdg.integration5.business_app.business_app_backend.domain.User;

import java.util.Optional;

public interface UserQuery {
    Optional<User> findById(User.UserUUID userUUID);
}
