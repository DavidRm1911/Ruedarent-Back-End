package pe.upc.ruedarentprojectmobile.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import pe.upc.ruedarentprojectmobile.model.Acquirer;
import pe.upc.ruedarentprojectmobile.model.Vehicle;

@Data
public class AddReservationRequest {

    private Long idReservation;

    private Acquirer acquirer;

    private Vehicle vehicle;
}
