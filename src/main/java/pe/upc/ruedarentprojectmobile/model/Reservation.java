package pe.upc.ruedarentprojectmobile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties("user")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private String initialDate;
    private String endDate;
    private String reservationState;
    private String pickupLocation;
    private String dropOffLocation;
    private String submissionDate;
    private Double totalPrice;
    private String answerDate;

    @ManyToOne
    @JoinColumn(name = "idVehiculo")
    @JsonBackReference("vehicle-reservation")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "idUsuarioSolicitante")
    @JsonBackReference("user-reservation")
    private User usuarioSolicitante;


    public Reservation(String initialDate, String endDate, String reservationState, String pickupLocation, String dropOffLocation, String submissionDate, Double totalPrice, String answerDate, User usuarioSolicitante, Vehicle vehicle) {
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.reservationState = reservationState;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.submissionDate = submissionDate;
        this.totalPrice = totalPrice;
        this.answerDate = answerDate;
        this.usuarioSolicitante = usuarioSolicitante;
        this.vehicle = vehicle;
    }

    @JsonProperty("ownerId")
    public Long getOwnerId() {
        return this.usuarioSolicitante != null ? this.usuarioSolicitante.getIdUser() : null;
    }

    @JsonProperty("vehicleId")
    public Long getVehicleId() {
        return this.vehicle != null ? this.vehicle.getIdVehicle() : null;
    }

    public Reservation(Long idReservation) {
        this.idReservation = idReservation;
    }
}
