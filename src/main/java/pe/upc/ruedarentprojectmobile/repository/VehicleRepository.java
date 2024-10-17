package pe.upc.ruedarentprojectmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.ruedarentprojectmobile.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByVehicleType(String vehicleType);
    List<Vehicle> findAllByBrand(String brand);
    List<Vehicle> findByOwnerDni(String dni);
}
