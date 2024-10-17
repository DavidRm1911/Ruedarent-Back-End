package pe.upc.ruedarentprojectmobile.service.Student;

import pe.upc.ruedarentprojectmobile.model.Student;
import pe.upc.ruedarentprojectmobile.request.AddStudentRequest;
import pe.upc.ruedarentprojectmobile.request.StudentUpdateRequest;

import java.util.List;

public interface IStudentService {
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student addStudent(AddStudentRequest request);
    void deleteStudentById(Long id);
    Student updateStudent(StudentUpdateRequest student, Long studentId);
    Student getStudentByEmail(String email);
    Student getStudentByDni(String dni);
    List<Student> getStudentByPlanType(String planType);
}
