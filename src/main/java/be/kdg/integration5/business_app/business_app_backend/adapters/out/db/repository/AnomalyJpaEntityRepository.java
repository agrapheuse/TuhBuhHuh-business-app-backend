package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.AnomalyJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.MeasurementJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnomalyJpaEntityRepository extends JpaRepository<AnomalyJpaEntity, UUID> {

    Optional<AnomalyJpaEntity> findFirstByLocationUuidOrderByDateTimeDesc(UUID uuid);

    Optional<AnomalyJpaEntity> findFirstByLocationUuidAndTypeOrderByDateTimeDesc(UUID location_uuid, ValueType type);

    List<AnomalyJpaEntity> findByLocationUuidAndTypeOrderByDateTimeDesc(UUID uuid, ValueType valueType);

    Optional<AnomalyJpaEntity> findFirstByLocationUuidAndTypeAndDateTimeAfter(UUID uuid, ValueType valueType, LocalDateTime localDateTime);
}
