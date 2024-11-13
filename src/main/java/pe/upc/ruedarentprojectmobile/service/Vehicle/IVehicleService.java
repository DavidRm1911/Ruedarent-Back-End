package pe.upc.ruedarentprojectmobile.service.Vehicle;

import pe.upc.ruedarentprojectmobile.model.Vehicle;
import pe.upc.ruedarentprojectmobile.request.AddVehicleRequest;
import pe.upc.ruedarentprojectmobile.request.VehicleUpdateRequest;

import java.util.List;

public interface IVehicleService {
    Vehicle getVehicleById(Long id);
    List<Vehicle> getAllVehicles();
    Vehicle addVehicle(AddVehicleRequest vehicle);
    void deleteVehicleById(Long id);
    Vehicle updateVehicle(VehicleUpdateRequest vehicle, Long vehicleId);
    List<Vehicle> getVehiclesByVehicleType(String vehicleType);
    List<Vehicle> getVehiclesByBrand(String brand);
    List<Vehicle> getVehiclesByOwnerEmail(String email);
    List<Vehicle> findVehicleByOwnerId(Long id);
    List<Vehicle> getVehiclesByState(String state);
    List<Vehicle> getVehiclesByRentalPriceLessThan(Double rentalPrice);
    List<Vehicle> getVehiclesBySellingPriceLessThan(Double sellingPrice);
    List<Vehicle> getVehiclesByLocation(String location);
    List<Vehicle> getVehiclesByVehicleTypeAndBrand(String vehicleType, String brand);

}
