package nl.codecafe.crud.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import nl.codecafe.crud.dto.student.StudentCreateDTO;
import nl.codecafe.crud.dto.student.StudentDTO;
import nl.codecafe.crud.dto.student.StudentUpdateDTO;
import nl.codecafe.crud.models.Student;
import nl.codecafe.crud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(@Autowired StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        return StudentDTO.fromEntity(student);
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(StudentDTO::fromEntity).toList();
    }

    public StudentDTO createStudent(StudentCreateDTO createDto) {
        Student student = createDto.toEntity();
        Student savedStudent = studentRepository.save(student);
        return StudentDTO.fromEntity(savedStudent);
    }

    public StudentDTO updateStudent(Long id, StudentUpdateDTO updateDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));

        updateDto.updateEntity(student);
        Student savedStudent = studentRepository.save(student);
        return StudentDTO.fromEntity(savedStudent);
    }
}
