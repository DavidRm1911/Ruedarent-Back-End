package pe.upc.ruedarentprojectmobile.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-vehicle")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "destinationUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-notification")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "usuarioSolicitante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-reservation")
    private List<Reservation> reservations;

    public User(Long idUser) {
        this.idUser = idUser;
    }
}
