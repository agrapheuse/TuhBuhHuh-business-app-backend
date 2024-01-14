package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.PredictionJpaEntity;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PredictionJpaEntityRepository extends JpaRepository<PredictionJpaEntity, UUID> {
    Optional<PredictionJpaEntity> findFirstByLocationUuidAndTypeOrderByDateMadeDesc(UUID uuid, ValueType valueType);

    Optional<PredictionJpaEntity> findFirstByLocationUuidAndTypeAndTimestampAfter(UUID uuid, ValueType valueType, LocalDateTime localDateTime);
}
