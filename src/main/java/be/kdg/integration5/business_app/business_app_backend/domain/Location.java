package be.kdg.integration5.business_app.business_app_backend.domain;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.LocationJpaEntity;
import com.google.maps.model.LatLng;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Location {

    public record LocationUUID(UUID uuid) {
        public LocationUUID(String strUUID) {
            this(UUID.fromString(strUUID));
        }
    }

    @Getter
    private LocationUUID uuid;
    @Getter
    private LatLng topLeft;
    @Getter
    private LatLng bottomRight;

    public Location(LatLng topLeft, LatLng bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Location(LocationUUID uuid, LatLng topLeft, LatLng bottomRight) {
        this.uuid = uuid;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }
    public Location(LocationJpaEntity locationJpaEntity) {
        this.uuid = new LocationUUID(locationJpaEntity.getUuid());
        this.topLeft = new LatLng(
                locationJpaEntity.getTopLeftLat(),
                locationJpaEntity.getTopLeftLng()
        );
        this.bottomRight = new LatLng(
                locationJpaEntity.getBottomRightLat(),
                locationJpaEntity.getBottomRightLng()
        );
    }
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Location location = (Location) o;
        // field comparison
        return Objects.equals(getUuid(), location.getUuid());
    }
}
