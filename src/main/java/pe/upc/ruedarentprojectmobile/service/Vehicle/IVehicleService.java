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
}
