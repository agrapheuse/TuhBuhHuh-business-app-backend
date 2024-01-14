package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeasurementJpaEntityRepository extends JpaRepository<MeasurementJpaEntity, UUID> {

    Optional<MeasurementJpaEntity> findFirstByLocationUuidAndTypeOrderByDateTimeDesc(UUID location_uuid, ValueType type);

    List<MeasurementJpaEntity> findByLocationUuidAndTypeOrderByDateTimeDesc(UUID uuid, ValueType valueType);

    Optional<MeasurementJpaEntity> findFirstByLocationUuidAndTypeAndDateTimeAfter(UUID uuid, ValueType valueType, LocalDateTime localDateTime);
}
