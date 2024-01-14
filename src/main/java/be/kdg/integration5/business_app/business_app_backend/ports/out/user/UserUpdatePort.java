package be.kdg.integration5.business_app.business_app_backend.ports.out.user;

import be.kdg.integration5.business_app.business_app_backend.domain.User;

public interface UserUpdatePort {
    void update(User user);
}
