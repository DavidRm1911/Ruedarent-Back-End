package pe.upc.ruedarentprojectmobile.request;

import lombok.Data;
import pe.upc.ruedarentprojectmobile.model.Reservation;
import pe.upc.ruedarentprojectmobile.model.User;

@Data
public class NotificationUpdateRequest {


    private Long idNotification;
    private String Notificationtype;
    private String message;
    private String CreationDate;
    private String ReadState;
    private Reservation reservation;
    private User destinationUser;
}
