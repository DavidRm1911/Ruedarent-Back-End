package pe.upc.ruedarentprojectmobile.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.ruedarentprojectmobile.request.AddReservationRequest;
import pe.upc.ruedarentprojectmobile.request.ReservationUpdateRequest;
import pe.upc.ruedarentprojectmobile.response.ApiResponse;
import pe.upc.ruedarentprojectmobile.service.Reservation.ReservationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllReservations() {
        return ResponseEntity.ok(new ApiResponse("success", reservationService.getAllReservations()));
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ApiResponse> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("success", reservationService.getReservationById(id)));
    }

    @GetMapping("/acquirer/{id}")
    public ResponseEntity<ApiResponse> getReservationsByAcquirerId(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("success", reservationService.getReservationsByAcquirer_IdClient(id)));
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<ApiResponse> getReservationsByVehicleId(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("success", reservationService.getReservationsByVehicle_IdVehicle(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.ok(new ApiResponse("success", null));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addReservation(@RequestBody AddReservationRequest reservation) {
        return ResponseEntity.ok(new ApiResponse("success", reservationService.addReservation(reservation)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateReservation(@RequestBody ReservationUpdateRequest reservation, @PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("success", reservationService.updateReservation(reservation, id)));
    }






}
