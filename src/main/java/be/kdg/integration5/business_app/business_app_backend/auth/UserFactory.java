package be.kdg.integration5.business_app.business_app_backend.auth;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.UserJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import be.kdg.integration5.business_app.business_app_backend.ports.in.query.UserQuery;
import be.kdg.integration5.business_app.business_app_backend.ports.in.useCase.user.UserNewUseCase;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserFactory {
    private static final Logger log = LoggerFactory.getLogger(UserFactory.class);

    private final UserQuery userQuery;
    private final UserNewUseCase userNewUseCase;


    public User getUserFromToken(Jwt token){
        Optional<User> optionalUser = userQuery.findById(new User.UserUUID(token.getClaimAsString("user_id")));
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        var thresholds = new HashMap<ValueType, Integer>();
        Arrays.stream(ValueType.values()).forEach(t -> thresholds.put(t, 0));

        User user = new User(new UserJpaEntity(new User.UserUUID(token.getClaimAsString("user_id")).uuid(), token.getClaimAsString("given_name"), token.getClaimAsString("family_name"), thresholds, new ArrayList<>()));
        userNewUseCase.userNewUseCase(user);
        optionalUser = userQuery.findById(new User.UserUUID(token.getClaimAsString("user_id")));
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }
}
