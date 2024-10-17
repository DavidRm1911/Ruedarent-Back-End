package pe.upc.ruedarentprojectmobile.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicle;

    private String brand;
    private String model;
    private String color;
    private String vehicleType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Student owner;

    @OneToMany(mappedBy = "vehicle")
    private List<Reservation> reservations;
}
