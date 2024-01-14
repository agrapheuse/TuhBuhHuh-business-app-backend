package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import be.kdg.integration5.business_app.business_app_backend.domain.User;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.*;

@AllArgsConstructor
@Table @Entity
public class UserJpaEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Getter @Setter
    private UUID uuid;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "threshold")
    @Column(name = "value")
    @Getter @Setter
    private Map<ValueType, Integer> thresholds;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @Getter @Setter
    private List<LocationJpaEntity> locations;

    public UserJpaEntity() {
    }

    public UserJpaEntity(UUID uuid, String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thresholds = new HashMap<>();
        Arrays.stream(ValueType.values()).forEach(t -> this.thresholds.put(t, 0));
        this.locations = new ArrayList<>();
    }

    public UserJpaEntity(User user) {
        this.uuid = user.getUuid().uuid();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.thresholds = user.getThresholds();
        this.locations = new ArrayList<>(user.getLocations().stream().map(LocationJpaEntity::new).toList());
    }
}
