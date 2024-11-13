package pe.upc.ruedarentprojectmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.ruedarentprojectmobile.model.Vehicle;
import pe.upc.ruedarentprojectmobile.model.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
   List<Reservation> findByUsuarioSolicitanteIdUser(Long idUser);
   List<Reservation> findByVehicle_IdVehicle(Long idVehicle);
}
