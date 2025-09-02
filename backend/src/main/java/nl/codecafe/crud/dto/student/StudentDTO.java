package nl.codecafe.crud.dto.student;

import nl.codecafe.crud.dto.course.CourseSummaryDTO;
import nl.codecafe.crud.models.Student;

import java.util.List;

public record StudentDTO(
        Long id,
        String name,
        Integer age,
        List<CourseSummaryDTO> courses
) {
    public static StudentDTO fromEntity(Student student) {
        List<CourseSummaryDTO> courseDtos = student.getCourses()
                .stream()
                .map(CourseSummaryDTO::fromEntity)
                .toList();

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                courseDtos
        );
    }
}
