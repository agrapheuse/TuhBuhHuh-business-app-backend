package be.kdg.integration5.business_app.business_app_backend.domain;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@AllArgsConstructor
public class User {

    private final Logger log = LoggerFactory.getLogger(User.class);



    public record UserUUID(UUID uuid) {
        public UserUUID(String strUUID) {
            this(UUID.fromString(strUUID));
        }
    }

    @Getter
    private UserUUID uuid;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter @Setter
    private List<Location> locations;
    @Getter @Setter
    private Map<ValueType, Integer> thresholds;
    public User (UserJpaEntity userJpaEntity) {
        this.uuid = new UserUUID(userJpaEntity.getUuid());
        this.firstName = userJpaEntity.getFirstName();
        this.lastName = userJpaEntity.getLastName();
        this.thresholds = userJpaEntity.getThresholds();
        this.locations = new ArrayList<Location>(userJpaEntity.getLocations().stream().map(Location::new).toList());
    }
    public void subscribeToLocation(Location location) {
        log.debug("User: " + this.uuid.uuid().toString() + " " + this.firstName);
        log.debug("Locations: " + this.locations.size());
        log.debug("Location to subscribe to: " + location.getUuid().uuid());
        if(!isSubbed(location)) {
            this.locations.add(location);
        } else {
            log.debug("Already subbed to this location.");
        }
        log.debug("Current Subscribed Locations: " + locations.size());
    }
    public void unsubscribeToLocation(Location location) {
        log.debug("User: " + this.uuid.uuid().toString() + " " + this.firstName);
        log.debug("Location to unsubscribe from: " + location.getUuid().uuid());
        log.debug("Locations: " + this.locations.size());
        if(isSubbed(location)) {
            this.locations.removeIf(x -> x.getUuid().uuid().equals(location.getUuid().uuid()));
        }
        log.debug("Current Subscribed Locations: " + locations.size());
    }
    public void updateThreshold(ValueType valueType, int value) {
        thresholds.put(valueType, value);
    }


    public Optional<Notification> wantsToBeNotified(Measurement measurement) {
        var threshhold = this.thresholds.get(measurement.getType());
        if (threshhold == null) {
            throw new RuntimeException("The User does not have the correct Thresholds");
        }

        if (
                measurement.getValue() < threshhold
                || !this.isSubbed(measurement.getLocation())
        ) {
            return Optional.empty();
        }

        return Optional.of(new Notification(this, measurement));
    }

    public Optional<Notification> wantsToBeNotified(Prediction prediction) {
        var threshhold = this.thresholds.get(prediction.getType());
        if (threshhold == null) {
            throw new RuntimeException("The User does not have the correct Thresholds");
        }

        if (
                prediction.getValue() < threshhold
                || !this.isSubbed(prediction.getLocation())
        ) {
            return Optional.empty();
        }

        return Optional.of(new Notification(this, prediction));
    }


    public boolean isSubbed(Location location) {
        // log.debug("checking if subbed to " + location.getUuid().toString());
        return locations.contains(location);
    }

}
