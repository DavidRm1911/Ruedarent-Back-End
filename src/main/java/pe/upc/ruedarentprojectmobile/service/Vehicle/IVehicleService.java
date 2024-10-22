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
    List<Vehicle> getVehiclesByStudentDni(String dni);
    List<Vehicle> findVehicleByOwner_IdStudent(Long id);
    List<Vehicle> getVehiclesByIsAvailable(Boolean isAvailable);
    List<Vehicle> getVehiclesByRentalPriceLessThan(Double rentalPrice);
    List<Vehicle> getVehiclesBySellingPriceLessThan(Double sellingPrice);
    List<Vehicle> getVehiclesByUbication(String location);
    List<Vehicle> getVehiclesByVehicleTypeAndBrand(String vehicleType, String brand);

}
