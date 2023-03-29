package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreationRequest;
import de.allianz.springboot.dto.ToDoUpdateRequest;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;
    private final ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasRole('TODO_CREATE')")
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreationRequest toDoCreationRequest){
        return new ResponseEntity<>(this.toDoService.createToDo(modelMapper.map(toDoCreationRequest, ToDo.class)), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('TODO_UPDATE')")
    public ResponseEntity<ToDo> updateToDo(@Valid @RequestBody ToDoUpdateRequest toDoUpdateRequest){
        ToDo toDo = this.toDoService.getId(toDoUpdateRequest.getId());
        modelMapper.map(toDoUpdateRequest, toDo);
        return new ResponseEntity<>(this.toDoService.updateToDo(toDo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TODO_DELETE')")
    public ResponseEntity<?> deleteToDo(@PathVariable("id") Long id){
        this.toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<List<ToDo>> getAllToDos(){
        return new ResponseEntity<>(this.toDoService.getAllToDos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<ToDo> getToDoById(@PathVariable ("id") Long id){
        return new ResponseEntity<>(this.toDoService.getId(id), HttpStatus.CREATED);
    }

    @GetMapping("/isDone")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<List<ToDo>> getDoneToDos(){
        return new ResponseEntity<>(this.toDoService.getDoneToDos(), HttpStatus.OK);
    }

    @GetMapping("/isOpen")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<List<ToDo>> getOpenToDos(){
        return new ResponseEntity<>(this.toDoService.getOpenToDos(), HttpStatus.OK);
    }

    @GetMapping("/isDone/count")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<Long> getNumberOfDoneToDos(){
        return new ResponseEntity<>(this.toDoService.getNumberOfDoneToDos(), HttpStatus.OK);
    }

    @GetMapping("/isOpen/count")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<Long> getNumberOfOpenToDos(){
        return new ResponseEntity<>(this.toDoService.getNumberOfOpenToDos(), HttpStatus.OK);
    }

}
