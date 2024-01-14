package be.kdg.integration5.business_app.business_app_backend.ports.out.user;

import be.kdg.integration5.business_app.business_app_backend.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserLoadPort {
    Optional<User> findById(User.UserUUID userUUID);
    List<User> findAll();
}
