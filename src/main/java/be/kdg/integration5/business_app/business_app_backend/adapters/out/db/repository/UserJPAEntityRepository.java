package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJPAEntityRepository extends JpaRepository<UserJpaEntity, UUID> {
}
