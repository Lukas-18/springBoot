package de.allianz.springboot.repository;

import de.allianz.springboot.entity.ToDo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ToDoRepositoryTest {

    private final ToDoRepository toDoRepository;
    private ToDo todo1;
    private ToDo todo2;
    private ToDo todo3;

    @BeforeEach
    void init(){
        todo1 = new ToDo(null, "Wäsche", "Wäsche waschen", "21.03.2023", "24.03.2023", 2, true);
        todo2 = new ToDo(null, "Putzen", "Wohnung putzen", "21.03.2023", "31.03.2023", 1, false);
        todo3 = new ToDo(null, "Kochen", "Abendessen kochen", "21.03.2023", "21.03.2023", 3, false);

        this.toDoRepository.save(todo1);
        this.toDoRepository.save(todo2);
        this.toDoRepository.save(todo3);
    }

    @Test
    void findAllByIsDoneIsTrue(){
        assertTrue(toDoRepository.findAllByIsDoneIsTrue().contains(todo1));
        assertFalse(toDoRepository.findAllByIsDoneIsTrue().contains(todo3));
    }

    @Test
    void findAllByIsDoneIsFalse(){
        assertTrue(toDoRepository.findAllByIsDoneIsFalse().contains(todo2));
        assertFalse(toDoRepository.findAllByIsDoneIsFalse().contains(todo1));
    }

    @Test
    void countAllByIsDone(){
        assertEquals(1, toDoRepository.countAllByIsDone(true));
        assertEquals(2, toDoRepository.countAllByIsDone(false));
    }

}
