package nl.codecafe.crud.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import nl.codecafe.crud.dto.course.CourseCreateDTO;
import nl.codecafe.crud.dto.course.CourseDTO;
import nl.codecafe.crud.dto.course.CourseUpdateDTO;
import nl.codecafe.crud.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    final private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseCreateDTO createDto) {
        CourseDTO created = courseService.createCourse(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseUpdateDTO updateDto) {
        CourseDTO updated = courseService.updateCourse(id, updateDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        CourseDTO course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseDTOs = courseService.findAll();
        return ResponseEntity.ok(courseDTOs);
    }
}
