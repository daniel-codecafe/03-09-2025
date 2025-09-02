package nl.codecafe.crud.dto.student;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import nl.codecafe.crud.models.Student;

public record StudentUpdateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @Min(value = 0, message = "Age must be at least 0")
        @Max(value = 150, message = "Age cannot exceed 150")
        Integer age

) {
    public void updateEntity(Student student) {
        student.setName(this.name);
        student.setAge(this.age);
    }
}
