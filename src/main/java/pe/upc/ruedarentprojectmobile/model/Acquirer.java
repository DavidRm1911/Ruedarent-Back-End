package pe.upc.ruedarentprojectmobile.model;


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
public class Acquirer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String phone;
    private String dni;

    @OneToMany(mappedBy = "acquirer")
    private List<Reservation> reservations;


}
