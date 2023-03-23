package de.allianz.springboot.database;

import de.allianz.springboot.controller.ToDoController;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import de.allianz.springboot.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    // DEPENDENCY INJECTION
    private final ToDoRepository toDoRepository;

    @Override
    public void run(String... args) throws Exception {

        final ToDo todo1 = new ToDo(null, "Wäsche", "Wäsche waschen", "21.03.2023", "24.03.2023", 2, true);
        final ToDo todo2 = new ToDo(null, "Putzen", "Wohnung putzen", "21.03.2023", "31.03.2023", 1, false);
        final ToDo todo3 = new ToDo(null, "Kochen", "Abendessen kochen", "21.03.2023", "21.03.2023", 3, false);

        toDoRepository.save(todo1);
        toDoRepository.saveAll(Arrays.asList(todo2, todo3));
        System.out.println("Anzahl Todos: " + toDoRepository.count());
    }
}
