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
    private Double rentalprice;
    private Double sellingprice;
    private String description;

    private String ubication;


    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Student owner;

    @OneToMany(mappedBy = "vehicle")
    @JsonManagedReference(value = "vehicle-reservation")
    private List<Reservation> reservations;

    public Vehicle(String brand, String model, String color, String vehicleType, String imageUrl, Double rentalprice, Double sellingprice, String description, Boolean isAvailable, String ubication, Student student) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.vehicleType = vehicleType;
        this.imageUrl = imageUrl;
        this.rentalprice = rentalprice;
        this.sellingprice = sellingprice;
        this.description = description;
        this.ubication = ubication;

        this.isAvailable = isAvailable;



        this.owner = student;
    }

    public Vehicle(Long idVehicle) {

    }

    public void setAvailable(boolean b) {

    }
}
