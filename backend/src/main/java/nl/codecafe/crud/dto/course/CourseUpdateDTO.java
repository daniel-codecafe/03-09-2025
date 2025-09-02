package nl.codecafe.crud.dto.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import nl.codecafe.crud.models.Course;

public record CourseUpdateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @Min(value = 1, message = "maxStudents must be at least 1")
        @Max(value = 30, message = "maxStudents cannot exceed 30")
        Integer maxStudents
) {
    public void updateEntity(Course course) {
        course.setName(this.name);
        course.setMaxStudents(this.maxStudents);
    }
}
