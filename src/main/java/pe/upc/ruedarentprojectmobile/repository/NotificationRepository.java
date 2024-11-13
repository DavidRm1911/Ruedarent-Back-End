package pe.upc.ruedarentprojectmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.ruedarentprojectmobile.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
