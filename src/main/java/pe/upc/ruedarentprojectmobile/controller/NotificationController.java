package pe.upc.ruedarentprojectmobile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.ruedarentprojectmobile.model.Notification;
import pe.upc.ruedarentprojectmobile.request.AddNotificationRequest;
import pe.upc.ruedarentprojectmobile.request.NotificationUpdateRequest;
import pe.upc.ruedarentprojectmobile.response.ApiResponse;
import pe.upc.ruedarentprojectmobile.service.Plan.INotificationService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/notifications")
public class NotificationController {
    private final INotificationService notificationService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllNotifications(){
        try {
            List<Notification> notifications = notificationService.getAllNotifications();
            return ResponseEntity.ok(new ApiResponse("Found", notifications));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{notificationId}/notification")
    public ResponseEntity<ApiResponse> getNotification(@PathVariable Long notificationId){
        try {
            Notification notification = notificationService.getNotificationById(notificationId);
            return ResponseEntity.ok(new ApiResponse("Found", notification));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @DeleteMapping("/delete/{notificationId}/plan")
    public ResponseEntity<ApiResponse> deleteNotification(@PathVariable Long notificationId){
        try {
            notificationService.deleteNotificationById(notificationId);
            return ResponseEntity.ok(new ApiResponse("Deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNotification(@RequestBody AddNotificationRequest notification){
        try {
            Notification theNotification = notificationService.addNotification(notification);
            return ResponseEntity.ok(new ApiResponse("Notification added", theNotification));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }


    //  @PutMapping("/update/{id}")
    //    public ResponseEntity<ApiResponse> updateReservation(@RequestBody ReservationUpdateRequest reservation, @PathVariable Long id) {
    //        return ResponseEntity.ok(new ApiResponse("success", reservationService.updateReservation(reservation, id)));
    //    }
    @PutMapping("/update/{notificationId}/notification")
    public ResponseEntity<ApiResponse> updateNotification(@RequestBody NotificationUpdateRequest notification, @PathVariable Long notificationId){
        try {
            Notification thePlan = notificationService.updateNotification(notification, notificationId);
            return ResponseEntity.ok(new ApiResponse("Notification updated", thePlan));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

}
