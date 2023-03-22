package de.allianz.springboot.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class ToDoUpdateRequest {

    @NotNull
    @Positive
    private final Long id;
    @NotBlank
    private final String title;
    @NotBlank
    private final String description;
    @NotBlank
    private final String creationDate;
    @NotBlank
    private final String dueDate;
    @NotBlank
    private final Integer priority;
    @NotNull
    private final Boolean isDone;
}
