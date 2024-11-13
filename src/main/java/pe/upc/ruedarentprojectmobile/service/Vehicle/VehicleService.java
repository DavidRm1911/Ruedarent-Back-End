package pe.upc.ruedarentprojectmobile.service.Vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.model.User;
import pe.upc.ruedarentprojectmobile.model.Vehicle;
import pe.upc.ruedarentprojectmobile.repository.UserRepository;
import pe.upc.ruedarentprojectmobile.repository.VehicleRepository;
import pe.upc.ruedarentprojectmobile.request.AddVehicleRequest;
import pe.upc.ruedarentprojectmobile.request.VehicleUpdateRequest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle addVehicle(AddVehicleRequest request) {



        User user = userRepository.findById(request.getOwner().getIdUser())
                .orElseGet(() -> {
                    User newUser = new User(request.getOwner().getIdUser());
                    return userRepository.save(newUser);
                });

        request.setOwner(user);
        return vehicleRepository.save(createVehicle(request, user));
    }

    private Vehicle createVehicle(AddVehicleRequest request, User user){
        return new Vehicle(
                // private Long idVehicle;
                //
                //    private String vehicleType;
                //    private String brand;
                //    private String model;
                //    private Integer year;
                //    private String state; //Availabe, Not Available
                //    private Double rentalprice;
                //    private Double sellingprice;
                //    private String location;
                //    private String url;//imagen
                //    private String description;
                request.getVehicleType(),
                request.getBrand(),
                request.getModel(),
                request.getYear(),
                request.getLocation(),
                request.getUrl(),
                request.getRentalprice(),
                request.getSellingprice(),
                request.getDescription(),
                request.getState(),
                user
        );
    }

    @Override
    public void deleteVehicleById(Long id) {
        vehicleRepository.findById(id).ifPresent(vehicleRepository::delete);
    }

    @Override
    public Vehicle updateVehicle(VehicleUpdateRequest request, Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(existingVehicle -> updateExistingVehicle(existingVehicle, request))
                .map(vehicleRepository::save)
                .orElse(null);
    }

    private Vehicle updateExistingVehicle(Vehicle existingVehicle, VehicleUpdateRequest request){
        existingVehicle.setBrand(request.getBrand());
        existingVehicle.setVehicleType(request.getVehicleType());
        existingVehicle.setYear(request.getYear());
        existingVehicle.setModel(request.getModel());
        existingVehicle.setUrl(request.getUrl());
        existingVehicle.setRentalprice(request.getRentalprice());
        existingVehicle.setSellingprice(request.getSellingprice());
        existingVehicle.setDescription(request.getDescription());
        existingVehicle.setState(request.getState());
        existingVehicle.setLocation(request.getLocation());


        User user = userRepository.findById(request.getOwner().getIdUser())
                .orElseGet(() -> {
                    User newStudent = new User(request.getOwner().getIdUser());
                    return userRepository.save(newStudent);
                });
        existingVehicle.setOwner(user);
        return existingVehicle;
    }

    @Override
    public List<Vehicle> getVehiclesByVehicleType(String vehicleType) {
        return vehicleRepository.findAllByVehicleType(vehicleType);
    }

    @Override
    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findAllByBrand(brand);
    }

    @Override
    public List<Vehicle> getVehiclesByOwnerEmail(String email) {
        return vehicleRepository.findByOwnerEmail(email);
    }

    @Override
    public List<Vehicle> findVehicleByOwnerId(Long id) {
        return vehicleRepository.findAllByOwnerIdUser(id);
    }

    @Override
    public List<Vehicle> getVehiclesByState(String state) {
        return vehicleRepository.findAllByState(state);
    }

    @Override
    public List<Vehicle> getVehiclesByRentalPriceLessThan(Double rentalPrice) {
        return vehicleRepository.findAllByRentalpriceLessThanEqual(rentalPrice);
    }

    @Override
    public List<Vehicle> getVehiclesBySellingPriceLessThan(Double sellingPrice) {
        return vehicleRepository.findAllBySellingpriceLessThan(sellingPrice);
    }

    @Override
    public List<Vehicle> getVehiclesByLocation(String location) {
        return vehicleRepository.findAllByLocation(location);
    }

    @Override
    public List<Vehicle> getVehiclesByVehicleTypeAndBrand(String vehicleType, String brand) {
        return vehicleRepository.findAllByVehicleTypeAndBrand(vehicleType, brand);
    }


}
