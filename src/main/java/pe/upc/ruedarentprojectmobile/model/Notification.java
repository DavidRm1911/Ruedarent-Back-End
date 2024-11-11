package pe.upc.ruedarentprojectmobile.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    private String tipoNotification;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String estadoLectura;

    @ManyToOne
    @JoinColumn(name = "idReservation")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "idUsuarioDestino")
    @JsonBackReference
    private User usuarioDestino;
}