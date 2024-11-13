package pe.upc.ruedarentprojectmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.ruedarentprojectmobile.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByVehicleType(String vehicleType);
    List<Vehicle> findAllByBrand(String brand);
    List<Vehicle> findByOwnerEmail(String email);
    List<Vehicle> findAllByOwnerIdUser(Long id);

    //Filtracion por dinero
    List<Vehicle> findAllByRentalpriceLessThanEqual(Double rentalprice);
    List<Vehicle> findAllBySellingpriceLessThan(Double sellingprice);
    //filtrar por ubicacion
    List<Vehicle> findAllByLocation(String location);

    //Filtrar por disponibilidad
    List<Vehicle> findAllByState(String State);

    // Filtrar multiple
    List<Vehicle> findAllByVehicleTypeAndBrand(String vehicleType, String brand);
 }
