package pe.upc.ruedarentprojectmobile.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.upc.ruedarentprojectmobile.model.Plan;

@Data
public class AddStudentRequest {
    private Long idOwner;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String phone;
    private String dni;
    private Plan plan;
}
