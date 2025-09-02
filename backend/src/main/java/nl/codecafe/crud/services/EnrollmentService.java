package nl.codecafe.crud.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import nl.codecafe.crud.dto.student.StudentDTO;
import nl.codecafe.crud.models.Course;
import nl.codecafe.crud.models.Student;
import nl.codecafe.crud.repositories.CourseRepository;
import nl.codecafe.crud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    final private StudentRepository studentRepository;
    final private CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public StudentDTO enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        if (student.getCourses().contains(course)) {
            throw new IllegalStateException("Student is already enrolled in this course");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);

        Student savedStudent = studentRepository.save(student);

        return StudentDTO.fromEntity(savedStudent);
    }

    @Transactional
    public StudentDTO unenrollStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        if (!student.getCourses().contains(course)) {
            throw new IllegalStateException("Student is not enrolled in this course");
        }

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        Student savedStudent = studentRepository.save(student);

        return StudentDTO.fromEntity(savedStudent);
    }

    public boolean isStudentEnrolledInCourse(Long studentId, Long courseId) {
        return studentRepository.findById(studentId)
                .map(student -> student.getCourses().stream()
                        .anyMatch(course -> course.getId().equals(courseId)))
                .orElse(false);
    }
}
