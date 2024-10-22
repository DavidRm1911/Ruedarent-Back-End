package pe.upc.ruedarentprojectmobile.service.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.model.Plan;
import pe.upc.ruedarentprojectmobile.model.Student;
import pe.upc.ruedarentprojectmobile.repository.PlanRepository;
import pe.upc.ruedarentprojectmobile.repository.StudentRepository;
import pe.upc.ruedarentprojectmobile.request.AddStudentRequest;
import pe.upc.ruedarentprojectmobile.request.StudentUpdateRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    private final PlanRepository planRepository;

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(AddStudentRequest student) {

        // Acquirer acquirer = acquirerRepository.findById(request.getAcquirer().getIdClient())
        //                .orElseGet(() -> {
        //                    Acquirer newAcquirer = new Acquirer(request.getAcquirer().getIdClient());
        //                    return acquirerRepository.save(newAcquirer);
        //                });
        //        request.setAcquirer(acquirer);

        Plan plan = planRepository.findById(student.getPlan().getIdPlan())
                .orElseGet(()-> {
                    Plan newPlan = new Plan(student.getPlan().getIdPlan());
                    return planRepository.save(newPlan);
                });
        student.setPlan(plan);

        //return reservationRepository.save(createReservation(request, acquirer, vehicle));


        if (plan == null) {

            throw new RuntimeException("Plan not found");
        }

        return studentRepository.save(createStudent(student, plan));
    }

    private Student createStudent(AddStudentRequest request, Plan plan){
        return new Student(
                request.getName(),
                request.getLastName(),
                request.getDni(),
                request.getEmail(),
                request.getPhone(),
                request.getAge(),
                plan
        );
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.findById(id).ifPresent(studentRepository::delete);
    }

    @Override
    public Student updateStudent(StudentUpdateRequest request, Long studentId) {
        return studentRepository.findById(studentId)
                .map(existingStudent -> updateExistingStudent(existingStudent, request))
                .map(studentRepository::save)
                .orElse(null);
    }

    private Student updateExistingStudent(Student existingStudent, StudentUpdateRequest request){



        existingStudent.setName(request.getName());
        existingStudent.setLastName(request.getLastName());
        existingStudent.setDni(request.getDni());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setPhone(request.getPhone());
        existingStudent.setAge(request.getAge());

        Plan plan = planRepository.findById(request.getPlan().getIdPlan())
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + request.getPlan().getIdPlan()));
        existingStudent.setPlan(plan);

        existingStudent.setPlan(plan);

        return existingStudent;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student getStudentByDni(String dni) {
        return studentRepository.findByDni(dni);
    }

    @Override
    public List<Student> getStudentByPlanType(String planType) {
        return studentRepository.findByPlanPlanType(planType);
    }

    @Override
    public List<Student> getStudentByPlanIdPlan(Long id) {
        return studentRepository.findByPlan_IdPlan(id);

    }

}
