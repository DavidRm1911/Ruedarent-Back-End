package pe.upc.ruedarentprojectmobile.controller;


import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.ruedarentprojectmobile.model.Student;
import pe.upc.ruedarentprojectmobile.request.AddStudentRequest;
import pe.upc.ruedarentprojectmobile.request.StudentUpdateRequest;
import pe.upc.ruedarentprojectmobile.response.ApiResponse;
import pe.upc.ruedarentprojectmobile.service.Student.StudentService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/owners")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ApiResponse("success", students));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(new ApiResponse("success", student) );
    }

    @GetMapping("/owner/email/{email}")
    public ResponseEntity<ApiResponse> getStudentByEmail(@PathVariable String email) {
        Student student = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(new ApiResponse("success", student));
    }


    @GetMapping("/owner/dni/{dni}")
    public ResponseEntity<ApiResponse> getStudentByDni(@PathVariable String dni) {
        Student student = studentService.getStudentByDni(dni);
        return ResponseEntity.ok(new ApiResponse("success", student));
    }

    @GetMapping("/owner/planType/{planType}")
    public ResponseEntity<ApiResponse> getStudentByPlanType(@PathVariable String planType){
        List<Student> students = studentService.getStudentByPlanType(planType);
        return ResponseEntity.ok(new ApiResponse("success", students));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody AddStudentRequest student){
        try {
            Student theStudent = studentService.addStudent(student);
            return ResponseEntity.ok(new ApiResponse("add student success", theStudent));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok(new ApiResponse("delete student success", null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateStudent(@RequestBody StudentUpdateRequest request, @PathVariable Long id){
        try {
            Student theStudent = studentService.updateStudent(request, id);
            return ResponseEntity.ok(new ApiResponse("update student success", theStudent));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



}
