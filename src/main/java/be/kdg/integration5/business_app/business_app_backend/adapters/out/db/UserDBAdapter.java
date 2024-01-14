package be.kdg.integration5.business_app.business_app_backend.adapters.out.db;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.UserJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository.UserJPAEntityRepository;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserLoadPort;
import be.kdg.integration5.business_app.business_app_backend.ports.out.user.UserUpdatePort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class UserDBAdapter implements UserLoadPort, UserUpdatePort {
    private final static Logger log = LoggerFactory.getLogger(UserLoadPort.class);
    private final UserJPAEntityRepository userRepository;
    @Override
    public Optional<User> findById(User.UserUUID userUUID) {
        return userRepository.findById(userUUID.uuid()).map(User::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(User::new).collect(Collectors.toList());
    }

    @Override
    public void update(User user) {
        userRepository.save(new UserJpaEntity(user));
    }
}
