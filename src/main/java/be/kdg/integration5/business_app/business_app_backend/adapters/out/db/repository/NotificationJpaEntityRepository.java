package be.kdg.integration5.business_app.business_app_backend.adapters.out.db.repository;

import be.kdg.integration5.business_app.business_app_backend.adapters.out.db.entity.NotificationJpaEnitity;
import be.kdg.integration5.business_app.business_app_backend.domain.Notification;
import be.kdg.integration5.business_app.business_app_backend.domain.ValueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationJpaEntityRepository extends JpaRepository<NotificationJpaEnitity, UUID> {


    @Query("""
        select n from NotificationJpaEnitity n
        where n.userUUID = :userUUID and n.type = :type and n.notificationType = :notificationType
    """)
    List<NotificationJpaEnitity> findAllByUserAndType(UUID userUUID, ValueType type, Notification.DataTypeNotification notificationType);

}
