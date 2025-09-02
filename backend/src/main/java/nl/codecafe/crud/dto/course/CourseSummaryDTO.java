package nl.codecafe.crud.dto.course;

import nl.codecafe.crud.models.Course;

public record CourseSummaryDTO(
        Long id,
        String name,
        Integer maxStudents
) {
    public static CourseSummaryDTO fromEntity(Course course) {
        return new CourseSummaryDTO(
                course.getId(),
                course.getName(),
                course.getMaxStudents()
        );
    }
}
