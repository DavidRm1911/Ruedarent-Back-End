package pe.upc.ruedarentprojectmobile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Student owner;

    @OneToMany(mappedBy = "vehicle")
    @JsonManagedReference(value = "vehicle-reservation")
    private List<Reservation> reservations;

    public Vehicle(String brand, String model, String color, String vehicleType, String imageUrl, Student student) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.vehicleType = vehicleType;
        this.imageUrl = imageUrl;
        this.owner = student;
    }

    public Vehicle(Long idVehicle) {

    }
}
