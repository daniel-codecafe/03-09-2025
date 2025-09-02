package nl.codecafe.crud.services;

import jakarta.persistence.EntityNotFoundException;
import nl.codecafe.crud.dto.course.CourseCreateDTO;
import nl.codecafe.crud.dto.course.CourseDTO;
import nl.codecafe.crud.dto.course.CourseUpdateDTO;
import nl.codecafe.crud.models.Course;
import nl.codecafe.crud.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    final private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(CourseDTO::fromEntity)
                .toList();
    }

    public CourseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        return CourseDTO.fromEntity(course);
    }

    public CourseDTO createCourse(CourseCreateDTO createDto) {
        Course course = createDto.toEntity();
        Course savedCourse = courseRepository.save(course);
        return CourseDTO.fromEntity(savedCourse);
    }

    public CourseDTO updateCourse(Long id, CourseUpdateDTO updateDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));

        updateDto.updateEntity(course);
        Course savedCourse = courseRepository.save(course);
        return CourseDTO.fromEntity(savedCourse);
    }
}
