package de.allianz.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory!")
    private String title;
    @NotBlank(message = "Name is mandatory!")
    private String description;
    @NotBlank(message = "Name is mandatory!")
    private String creationDate;
    @NotBlank(message = "Name is mandatory!")
    private String dueDate;
    @NotBlank(message = "Name is mandatory!")
    private Integer priority;
    @NotNull
    private Boolean isDone;

}
