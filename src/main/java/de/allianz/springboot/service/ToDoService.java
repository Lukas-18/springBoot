package de.allianz.springboot.service;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDo createToDo(ToDo toDo){
        return this.toDoRepository.save(toDo);
    }

    public ToDo updateToDo(ToDo toDo){
        ToDo updatedToDo = toDoRepository.findById(toDo.getId()).orElseThrow(
                () -> new EntityNotFoundException("Not found!")
        );
        updatedToDo.setTitle(toDo.getTitle());
        updatedToDo.setDescription(toDo.getDescription());
        updatedToDo.setCreationDate(toDo.getCreationDate());
        updatedToDo.setDueDate(toDo.getDueDate());
        updatedToDo.setPriority(toDo.getPriority());
        updatedToDo.setIsDone(toDo.getIsDone());
        return this.toDoRepository.save(updatedToDo);
    }

    public void deleteToDo(Long id){
        this.toDoRepository.deleteById(id);
    }

    public List<ToDo> getAllToDos(){
        return (List<ToDo>) this.toDoRepository.findAll();
    }

    public List<ToDo> getDoneToDos(){
        /* manueller Ansatz:
        List<ToDo> todos = (List<ToDo>) toDoRepository.findAll();
        List<ToDo> doneToDos = new ArrayList<>();
        for (ToDo todo: todos){
            if(todo.getIsDone().equals(true)){
                doneToDos.add(todo);
            }
        }
        return doneToDos;
         */
        return this.toDoRepository.findAllByIsDoneIsTrue();
    }

    public List<ToDo> getOpenToDos(){
        return this.toDoRepository.findAllByIsDoneIsFalse();
    }

    public Long getNumberOfDoneToDos(){
        return this.toDoRepository.countAllByIsDone(true);
    }

    public Long getNumberOfOpenToDos(){
        return this.toDoRepository.countAllByIsDone(false);
    }

    public ToDo getId(Long id){
        return toDoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found!")
        );
    }
}
