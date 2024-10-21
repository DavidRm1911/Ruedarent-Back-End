package pe.upc.ruedarentprojectmobile.service.Vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.model.Student;
import pe.upc.ruedarentprojectmobile.model.Vehicle;
import pe.upc.ruedarentprojectmobile.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

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
        Student student = Optional.ofNullable(studentRepository.findByDni(request.getOwner().getDni()))
                .orElseGet(() -> {
                    Student newStudent = new Student(request.getOwner().getDni());
                    return studentRepository.save(newStudent);

                });
        request.setOwner(student);
        return vehicleRepository.save(createVehicle(request, student));
    }

    private Vehicle createVehicle(AddVehicleRequest request, Student student){
        return new Vehicle(
                request.getBrand(),
                request.getModel(),
                request.getColor(),
                request.getVehicleType(),
                request.getImageUrl(),
                student
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
        existingVehicle.setColor(request.getColor());
        existingVehicle.setModel(request.getModel());
        existingVehicle.setImageUrl(request.getImageUrl());

        Student student = studentRepository.findByDni(request.getOwner().getDni());
        existingVehicle.setOwner(student);
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
    public List<Vehicle> getVehiclesByStudentDni(String dni) {
        return vehicleRepository.findByOwnerDni(dni);
    }



}
