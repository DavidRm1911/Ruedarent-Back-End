package pe.upc.ruedarentprojectmobile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String vehicleType;
    private String brand;
    private String model;
    private Integer year;
    private String state; //Availabe, Not Available
    private Double rentalprice;
    private Double sellingprice;
    private String location;
    private String url;
    private String description;

    @ManyToOne
    @JoinColumn(name = "idPropietario")
    @JsonBackReference("user-vehicle")
    private User owner;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("vehicle-reservation")
    private List<Reservation> reservations;

    public Vehicle(Long idVehicle, String vehicleType, String brand, String model, Integer year, String state, Double rentalprice, Double sellingprice, String location, String url, String description, User owner) {
        this.idVehicle = idVehicle;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.state = state;
        this.rentalprice = rentalprice;
        this.sellingprice = sellingprice;
        this.location = location;
        this.url = url;
        this.description = description;
        this.owner = owner;
    }


    public Vehicle(Long idVehicle) {

    }

    public Vehicle(String vehicleType, String brand, String model, Integer year, String location, String url, Double rentalprice, Double sellingprice, String description, String state, User user) {
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.state = state;
        this.rentalprice = rentalprice;
        this.sellingprice = sellingprice;
        this.location = location;
        this.url = url;
        this.description = description;
        this.owner = user;
    }

}
