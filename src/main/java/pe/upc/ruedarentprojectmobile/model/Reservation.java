package pe.upc.ruedarentprojectmobile.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.*;
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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private String fechaInicio;
    private String fechaFin;
    private String reservationEstado;
    private String pickupLocation;
    private String dropOffLocation;
    private String fechaSolicitud;
    private Double precioTotal;
    private String fechaRespuesta;

    @ManyToOne
    @JoinColumn(name = "idVehiculo")
    @JsonBackReference
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "idUsuarioSolicitante")
    @JsonBackReference
    private User usuarioSolicitante;

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
