package pe.upc.ruedarentprojectmobile.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.upc.ruedarentprojectmobile.model.Student;

@Data
public class VehicleUpdateRequest {
    private Long idVehicle;

    private String brand;
    private String model;
    private String color;
    private String vehicleType;
    private String imageUrl;
    private Double rentalprice;
    private Double sellingprice;
    private String description;

    private String isAvailable;

    private String ubication;

    private Student owner;
}
