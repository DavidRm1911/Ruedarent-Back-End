package pe.upc.ruedarentprojectmobile.service.Reservation;

import pe.upc.ruedarentprojectmobile.model.Reservation;
import pe.upc.ruedarentprojectmobile.request.AddReservationRequest;
import pe.upc.ruedarentprojectmobile.request.ReservationUpdateRequest;

import java.util.List;

public interface IReservationService {
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();
    Reservation addReservation(AddReservationRequest reservation);
    void deleteReservationById(Long id);
    Reservation updateReservation(ReservationUpdateRequest reservation, Long reservationId);
    List<Reservation> getReservationsByAcquirer_IdClient(Long idClient);
    List<Reservation> getReservationsByVehicle_IdVehicle(Long idVehicle);
}
