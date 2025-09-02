package nl.codecafe.crud.controllers;

import jakarta.validation.Valid;
import nl.codecafe.crud.dto.student.StudentCreateDTO;
import nl.codecafe.crud.dto.student.StudentDTO;
import nl.codecafe.crud.dto.student.StudentUpdateDTO;
import nl.codecafe.crud.services.EnrollmentService;
import nl.codecafe.crud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    final private StudentService studentService;
    final private EnrollmentService enrollmentService;

    @Autowired
    public StudentController(StudentService studentService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentCreateDTO createDto) {
        StudentDTO created = studentService.createStudent(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentUpdateDTO updateDto) {
        StudentDTO updated = studentService.updateStudent(id, updateDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        StudentDTO student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
       List<StudentDTO> students = studentService.findAll();
       return ResponseEntity.ok(students);
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentDTO> enrollStudentInCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        StudentDTO updatedStudent = enrollmentService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<StudentDTO> unenrollStudentFromCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        StudentDTO updatedStudent = enrollmentService.unenrollStudentFromCourse(studentId, courseId);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{studentId}/courses/{courseId}/enrolled")
    public ResponseEntity<Map<String, Boolean>> checkEnrollment(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        boolean isEnrolled = enrollmentService.isStudentEnrolledInCourse(studentId, courseId);
        return ResponseEntity.ok(Map.of("enrolled", isEnrolled));
    }
}