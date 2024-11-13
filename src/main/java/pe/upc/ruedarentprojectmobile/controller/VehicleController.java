package pe.upc.ruedarentprojectmobile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.ruedarentprojectmobile.model.Vehicle;
import pe.upc.ruedarentprojectmobile.request.AddVehicleRequest;
import pe.upc.ruedarentprojectmobile.request.VehicleUpdateRequest;
import pe.upc.ruedarentprojectmobile.response.ApiResponse;
import pe.upc.ruedarentprojectmobile.service.Vehicle.VehicleService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;


    @GetMapping("/all")
    ResponseEntity<ApiResponse> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }

    @GetMapping("/vehicle/{id}")
    ResponseEntity<ApiResponse> getVehicleById(@PathVariable Long id) {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            return ResponseEntity.ok(new ApiResponse("success", vehicle));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/vehicleType/{vehicleType}")
    ResponseEntity<ApiResponse> getVehiclesByVehicleType(@PathVariable String vehicleType) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByVehicleType(vehicleType);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }

    @GetMapping("/brand/{brand}")
    ResponseEntity<ApiResponse> getVehiclesByBrand(@PathVariable String brand) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByBrand(brand);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }

    @GetMapping("/student/{email}")
    ResponseEntity<ApiResponse> getVehiclesByOwnerEmail(@PathVariable String email) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByOwnerEmail(email);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }


    @DeleteMapping("/delete/{id}")
    ResponseEntity<ApiResponse> deleteVehicleById(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.ok(new ApiResponse("success", null));
    }

    @PostMapping("/add")
    ResponseEntity<ApiResponse> addVehicle(@RequestBody AddVehicleRequest vehicle) {
        Vehicle theVehicle = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(new ApiResponse("success", theVehicle));
    }

    @PutMapping("/update/{vehicleId}")
    ResponseEntity<ApiResponse> updateVehicle(@RequestBody VehicleUpdateRequest vehicle, @PathVariable Long vehicleId) {
        Vehicle theVehicle = vehicleService.updateVehicle(vehicle, vehicleId);
        return ResponseEntity.ok(new ApiResponse("success", theVehicle));
    }

    @GetMapping("/isAvailable/{isAvailable}")
    ResponseEntity<ApiResponse> getVehiclesByIsAvailable(@PathVariable String isAvailable) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByState(isAvailable);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }

    @GetMapping("/rentalPriceLessThan/{rentalPrice}")
    ResponseEntity<ApiResponse> getVehiclesByRentalPriceLessThan(@PathVariable Double rentalPrice) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByRentalPriceLessThan(rentalPrice);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }

    @GetMapping("/sellingPriceLessThan/{sellingPrice}")
    ResponseEntity<ApiResponse> getVehiclesBySellingPriceLessThan(@PathVariable Double sellingPrice) {
        List<Vehicle> vehicles = vehicleService.getVehiclesBySellingPriceLessThan(sellingPrice);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }

    @GetMapping("/ubication/{location}")
    ResponseEntity<ApiResponse> getVehiclesByUbication(@PathVariable String location) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByLocation(location);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }

    @GetMapping("/vehicleTypeAndBrand/{vehicleType}/{brand}")
    ResponseEntity<ApiResponse> getVehiclesByVehicleTypeAndBrand(@PathVariable String vehicleType, @PathVariable String brand) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByVehicleTypeAndBrand(vehicleType, brand);
        return ResponseEntity.ok(new ApiResponse("success", vehicles));
    }


}
