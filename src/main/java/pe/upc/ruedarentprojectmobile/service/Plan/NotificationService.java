package pe.upc.ruedarentprojectmobile.service.Plan;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.model.Notification;
import pe.upc.ruedarentprojectmobile.model.Reservation;
import pe.upc.ruedarentprojectmobile.model.User;
import pe.upc.ruedarentprojectmobile.repository.NotificationRepository;
import pe.upc.ruedarentprojectmobile.repository.ReservationRepository;
import pe.upc.ruedarentprojectmobile.repository.UserRepository;
import pe.upc.ruedarentprojectmobile.request.AddNotificationRequest;
import pe.upc.ruedarentprojectmobile.request.NotificationUpdateRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService{
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;


    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(()-> new RuntimeException("Plan not found"));
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification addNotification(AddNotificationRequest request) {
       User user = userRepository.findById(request.getDestinationUser().getIdUser())
               .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getDestinationUser().getIdUser()));

         Reservation reservation = reservationRepository.findById(request.getReservation().getIdReservation())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + request.getReservation().getIdReservation()));

        return notificationRepository.save(createNotification(request, user, reservation));
    }



    private Notification createNotification(AddNotificationRequest request, User user, Reservation reservation){
        return new Notification(
                //    private Long idNotification;
                //    private String Notificationtype;
                //    private String message;
                //    private String CreationDate;
                //    private String ReadState;
                //    private Reservation reservation;
                //    private User destinationUser;

                request.getCreationDate(),
                request.getNotificationtype(),
                request.getMessage(),
                request.getReadState(),

                reservation,
                user

        );
    }

    @Override
    public void deleteNotificationById(Long id) {
        notificationRepository.findById(id).ifPresentOrElse(notificationRepository::delete,() -> {throw new RuntimeException("Notification not found");});
    }

    @Override
    public Notification updateNotification(NotificationUpdateRequest request, Long notificationId) {
        return notificationRepository.findById(notificationId)
                .map(existingNotification -> updateExistingNotification(existingNotification, request))
                .map(notificationRepository::save)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + notificationId));
    }

    private Notification updateExistingNotification( Notification existingNotification, NotificationUpdateRequest request){
        existingNotification.setCreationDate(LocalDateTime.parse(request.getCreationDate()));
        existingNotification.setNotificationType(request.getNotificationtype());
        existingNotification.setMessage(request.getMessage());
        existingNotification.setCreationDate(LocalDateTime.parse(request.getCreationDate()));
        existingNotification.setReadState(request.getReadState());

        Reservation reservation = reservationRepository.findById(request.getReservation().getIdReservation())
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + request.getReservation().getIdReservation()));

        existingNotification.setReservation(reservation);

        User destinationUser = userRepository.findById(request.getDestinationUser().getIdUser())
                .orElseThrow(() -> new RuntimeException("Destination User not found with id: " + request.getDestinationUser().getIdUser()));

        existingNotification.setDestinationUser(destinationUser);

        return existingNotification;
    }


}
