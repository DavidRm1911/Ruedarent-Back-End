package pe.upc.ruedarentprojectmobile.model;
import com.fasterxml.jackson.annotation.JsonIgnore;


import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "owners")
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

    public Student(Long idOwner) {
    }

    @JsonProperty("planId")
    public Long getPlanId(){
        return this.plan != null ? this.plan.getIdPlan() : null;
    }

    //   @ManyToOne
    //    @JoinColumn(name = "client_id")
    //    @JsonBackReference(value = "acquirer-reservation")
    //    private Acquirer acquirer;
    //
    //    @ManyToOne
    //    @JoinColumn(name = "vehicle_id")
    //    @JsonBackReference(value = "vehicle-reservation")
    //    private Vehicle vehicle;
    //
    //    @JsonProperty("ownerId")
    //    public Long getOwnerId() {
    //        return this.acquirer != null ? this.acquirer.getIdClient() : null;
    //    }
    //
    //    @JsonProperty("vehicleId")
    //    public Long getVehicleId() {
    //        return this.vehicle != null ? this.vehicle.getIdVehicle() : null;
    //    }

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    public Student(String name, String lastName, String dni, String email, String phone, Integer age, Plan plan) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.plan = plan;
    }

    public Student(Plan plan){
        this.plan = plan;
    }

    // public Reservation(Acquirer acquirer, Vehicle vehicle) {
    //        this.acquirer = acquirer;
    //        this.vehicle = vehicle;
    //    }
}
