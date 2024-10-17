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

    private String planType;
    private String planDescription;
    private Double planPrice;

    @OneToMany(mappedBy = "plan")
    private List<Student> students;

    public Plan(String planType) {
    }
}
