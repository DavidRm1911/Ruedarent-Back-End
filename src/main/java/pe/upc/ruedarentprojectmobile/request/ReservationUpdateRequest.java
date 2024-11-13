package pe.upc.ruedarentprojectmobile.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import pe.upc.ruedarentprojectmobile.model.User;
import pe.upc.ruedarentprojectmobile.model.Vehicle;

@Data
public class ReservationUpdateRequest {

    private Long idReservation;
    private String InitialDate;
    private String EndDate;
    private String reservationState;
    private String pickupLocation;
    private String dropOffLocation;
    private String SubmissionDate;
    private Double TotalPrice;
    private String AnswerDate;


    private User usuarioSolicitante;


    private Vehicle vehicle;
}
