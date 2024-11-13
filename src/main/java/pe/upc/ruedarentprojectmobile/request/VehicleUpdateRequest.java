package pe.upc.ruedarentprojectmobile.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.upc.ruedarentprojectmobile.model.User;

@Data
public class VehicleUpdateRequest {
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

    private User owner;
}
