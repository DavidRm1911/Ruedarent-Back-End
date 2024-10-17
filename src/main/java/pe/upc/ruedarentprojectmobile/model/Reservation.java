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

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "acquirer-reservation")
    private Acquirer acquirer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference(value = "vehicle-reservation")
    private Vehicle vehicle;

    @JsonProperty("ownerId")
    public Long getOwnerId() {
        return this.acquirer != null ? this.acquirer.getIdClient() : null;
    }

    @JsonProperty("vehicleId")
    public Long getVehicleId() {
        return this.vehicle != null ? this.vehicle.getIdVehicle() : null;
    }

    public Reservation(Acquirer acquirer, Vehicle vehicle) {
        this.acquirer = acquirer;
        this.vehicle = vehicle;
    }
}
