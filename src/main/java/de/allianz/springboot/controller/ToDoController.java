package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreationRequest;
import de.allianz.springboot.dto.ToDoUpdateRequest;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import de.allianz.springboot.service.ToDoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping
    public ToDo createToDo(@Valid @RequestBody ToDoCreationRequest toDoCreationRequest){
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoCreationRequest.getTitle());
        toDo.setDescription(toDoCreationRequest.getDescription());
        toDo.setCreationDate(toDoCreationRequest.getCreationDate());
        toDo.setDueDate(toDoCreationRequest.getDueDate());
        toDo.setPriority(toDoCreationRequest.getPriority());
        toDo.setIsDone(toDoCreationRequest.getIsDone());
        return this.toDoService.createToDo(toDo);
    }

    @PutMapping
    public ToDo updateToDo(@Valid @RequestBody ToDoUpdateRequest toDoUpdateRequest){
        ToDo toDo = new ToDo();
        toDo.setId(toDoUpdateRequest.getId());
        toDo.setTitle(toDoUpdateRequest.getTitle());
        toDo.setDescription(toDoUpdateRequest.getDescription());
        toDo.setCreationDate(toDoUpdateRequest.getCreationDate());
        toDo.setDueDate(toDoUpdateRequest.getDueDate());
        toDo.setPriority(toDoUpdateRequest.getPriority());
        toDo.setIsDone(toDoUpdateRequest.getIsDone());
        return this.toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable("id") Long id){
        this.toDoService.deleteToDo(id);
    }

    @GetMapping
    public List<ToDo> getAllToDos(){
        return (List<ToDo>) this.toDoService.getAllToDos();
    }

    @GetMapping("/isDone")
    public List<ToDo> getDoneToDos(){
        return this.toDoService.getDoneToDos();
    }

    @GetMapping("/isOpen")
    public List<ToDo> getOpenToDos(){
        return this.toDoService.getOpenToDos();
    }

    @GetMapping("/isDone/count")
    public Long getNumberOfDoneToDos(){
        return this.toDoService.getNumberOfDoneToDos();
    }

    @GetMapping("/isOpen/count")
    public Long getNumberOfOpenToDos(){
        return this.toDoService.getNumberOfOpenToDos();
    }

}
