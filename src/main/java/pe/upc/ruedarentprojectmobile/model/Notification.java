package pe.upc.ruedarentprojectmobile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    private String notificationType;
    private String message;
    private LocalDateTime creationDate;
    private String readState;

    @ManyToOne
    @JoinColumn(name = "idReservation")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "idUsuarioDestino")
    @JsonBackReference("user-notification")
    private User destinationUser;

    public Notification(String notificationType, String message, LocalDateTime creationDate, String readState, Reservation reservation, User destinationUser) {
        this.notificationType = notificationType;
        this.message = message;
        this.creationDate = creationDate;
        this.readState = readState;
        this.reservation = reservation;
        this.destinationUser = destinationUser;
    }

    public Notification(String creationDate, String notificationtype, String message, String readState, Reservation reservation, User user) {
        this.creationDate = LocalDateTime.parse(creationDate);
        this.notificationType = notificationtype;
        this.message = message;
        this.readState = readState;
        this.reservation = reservation;
        this.destinationUser = user;

    }
}
