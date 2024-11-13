package pe.upc.ruedarentprojectmobile.service.Reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.model.User;
import pe.upc.ruedarentprojectmobile.model.Reservation;
import pe.upc.ruedarentprojectmobile.model.Vehicle;
import pe.upc.ruedarentprojectmobile.repository.ReservationRepository;
import pe.upc.ruedarentprojectmobile.repository.UserRepository;
import pe.upc.ruedarentprojectmobile.repository.VehicleRepository;
import pe.upc.ruedarentprojectmobile.request.AddReservationRequest;
import pe.upc.ruedarentprojectmobile.request.ReservationUpdateRequest;

import java.util.List;
import java.util.Optional;

//para despues

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;


    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(AddReservationRequest request) {

        User user = userRepository.findById(request.getUsuarioSolicitante().getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("Acquirer not found with id: " + request.getUsuarioSolicitante().getIdUser()));


        Vehicle vehicle = vehicleRepository.findById(request.getVehicle().getIdVehicle())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + request.getVehicle().getIdVehicle()));


        if (vehicle.getState().equals("Not Available")) {
            throw new IllegalStateException("Vehicle is not available for reservation");
        }


        vehicle.setState("Not Available");
        vehicleRepository.save(vehicle);


        return reservationRepository.save(createReservation(request, user, vehicle));
    }

    private Reservation createReservation(AddReservationRequest request, User user, Vehicle vehicle){
        return new Reservation(
                request.getInitialDate(),
                request.getEndDate(),
                request.getReservationState(),
                request.getPickupLocation(),
                request.getDropOffLocation(),
                request.getSubmissionDate(),
                request.getTotalPrice(),
                request.getAnswerDate(),
                user,
                vehicle
        );
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.findById(id).ifPresent(reservationRepository::delete);

    }

    @Override
    public Reservation updateReservation(ReservationUpdateRequest request, Long reservationId) {
        return reservationRepository.findById(reservationId)
                .map(existingReservation -> updateExistingReservation(existingReservation, request))
                .map(reservationRepository::save)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + reservationId));
    }

    @Override
    public List<Reservation> getReservationsByUsuarioSolicitanteIdUser(Long idClient) {
        return reservationRepository.findByUsuarioSolicitanteIdUser(idClient);
    }

    private Reservation updateExistingReservation (Reservation existingReservation, ReservationUpdateRequest request){



        existingReservation.setInitialDate(request.getInitialDate());
        existingReservation.setEndDate(request.getEndDate());
        existingReservation.setReservationState(request.getReservationState());
        existingReservation.setPickupLocation(request.getPickupLocation());
        existingReservation.setDropOffLocation(request.getDropOffLocation());
        existingReservation.setSubmissionDate(request.getSubmissionDate());
        existingReservation.setTotalPrice(request.getTotalPrice());
        existingReservation.setAnswerDate(request.getAnswerDate());

        Vehicle vehicle = vehicleRepository.findById(request.getVehicle().getIdVehicle())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + request.getVehicle().getIdVehicle()));
        existingReservation.setVehicle(vehicle);

        User acquirer = userRepository.findById(request.getUsuarioSolicitante().getIdUser())
                .orElseThrow(() -> new RuntimeException("Acquirer not found with id: " + request.getUsuarioSolicitante().getIdUser()));
        existingReservation.setUsuarioSolicitante(acquirer);
        return existingReservation;
    }

    @Override
    public List<Reservation> getReservationsByVehicle_IdVehicle(Long idVehicle) {
        return reservationRepository.findByVehicle_IdVehicle(idVehicle);
    }
}
