package pe.upc.ruedarentprojectmobile.model;
import com.fasterxml.jackson.annotation.JsonIgnore;



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
    @JsonIgnore
    private List<Student> students;

    public Plan(String planType) {
        this.planType = planType;
    }
}
