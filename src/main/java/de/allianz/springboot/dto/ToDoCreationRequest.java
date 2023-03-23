package de.allianz.springboot.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

//DTO = Data Transfer Object

@Getter
@ToString
@RequiredArgsConstructor
public class ToDoCreationRequest {
    @NotBlank
    private String title;
    @NotBlank
    private final String description;
    @NotBlank
    private final String creationDate;
    @NotBlank
    private final String dueDate;
    @NotNull
    @Positive
    private final Integer priority;
    @NotNull
    private final Boolean isDone;
}
