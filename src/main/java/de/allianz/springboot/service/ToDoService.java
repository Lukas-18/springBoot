package de.allianz.springboot.service;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public void createToDo(ToDo toDo){
        if(toDo.getId() != null){
            this.toDoRepository.save(toDo);
        }
    }

    public void updateToDo(ToDo toDo){

    }

    public void deleteToDo(ToDo toDo){
        this.toDoRepository.delete(toDo);
    }

    public void readAllToDos(List<ToDo> toDoList){
        this.toDoRepository.findAll();
    }

    public void readDoneToDos(List<ToDo> toDoList){

    }

    public void readOpenToDos(List<ToDo> toDoList){
        this.toDoRepository.count();
    }

    public void readNumberOfDoneToDos(List<ToDo> toDoList){

    }

    public void readNumberOfOpenToDos(List<ToDo> toDoList){

    }
}
