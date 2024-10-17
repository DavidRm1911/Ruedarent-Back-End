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
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlan;

    private String PlanType;
    private String PlanDescription;
    private Double PlanPrice;

    @OneToMany(mappedBy = "plan")
    private List<Student> students;
}
