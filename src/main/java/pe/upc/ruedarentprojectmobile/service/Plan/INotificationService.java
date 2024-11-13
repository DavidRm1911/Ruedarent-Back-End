package pe.upc.ruedarentprojectmobile.service.Plan;

import org.aspectj.weaver.ast.Not;
import pe.upc.ruedarentprojectmobile.model.Notification;
import pe.upc.ruedarentprojectmobile.model.Reservation;
import pe.upc.ruedarentprojectmobile.request.AddNotificationRequest;
import pe.upc.ruedarentprojectmobile.request.NotificationUpdateRequest;

import java.util.List;

public interface INotificationService {

    Notification getNotificationById(Long id);
    List<Notification> getAllNotifications();
    Notification addNotification(AddNotificationRequest notification);
    void deleteNotificationById(Long id);
    Notification updateNotification(NotificationUpdateRequest notification, Long notificationId);

}
