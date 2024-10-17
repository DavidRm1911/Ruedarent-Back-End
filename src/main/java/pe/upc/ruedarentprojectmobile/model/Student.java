package pe.upc.ruedarentprojectmobile.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOwner;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String phone;
    private String dni;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicles;
}
