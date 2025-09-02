package nl.codecafe.crud.dto.student;

import nl.codecafe.crud.models.Student;

public record StudentSummaryDTO(
        Long id,
        String name,
        Integer age
) {
    public static StudentSummaryDTO fromEntity(Student student) {
        return new StudentSummaryDTO(
                student.getId(),
                student.getName(),
                student.getAge()
        );
    }
}

