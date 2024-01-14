package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity;

import be.kdg.integration5.business_app.business_app_backend.domain.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Table @Entity
public class LocationJpaEntity {
    @Id @JdbcTypeCode(Types.VARCHAR)
    @Setter
    private UUID uuid;
    @Setter
    private double topLeftLat;
    @Setter
    private double topLeftLng;
    @Setter
    private double bottomRightLat;
    @Setter
    private double bottomRightLng;

    public LocationJpaEntity() {

    }

    public LocationJpaEntity(Location location) {
        this.uuid = location.getUuid().uuid();
        this.topLeftLat = location.getTopLeft().lat;
        this.topLeftLng = location.getTopLeft().lng;
        this.bottomRightLat = location.getBottomRight().lat;
        this.bottomRightLng = location.getBottomRight().lng;
    }
}
