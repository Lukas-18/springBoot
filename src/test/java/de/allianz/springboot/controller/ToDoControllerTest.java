package de.allianz.springboot.controller;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @MockBean
    private ModelMapper modelMapper;

    private ToDo todo1;
    private ToDo todo2;
    private ToDo todo3;
    private List<ToDo> toDoList = new ArrayList<>();

    @BeforeEach
    public void init(){
        todo1 = new ToDo(1L, "Wäsche", "Wäsche waschen", "21.03.2023", "24.03.2023", 2, true);
        todo2 = new ToDo(2L, "Putzen", "Wohnung putzen", "21.03.2023", "31.03.2023", 1, false);
        todo3 = new ToDo(3L, "Kochen", "Abendessen kochen", "21.03.2023", "21.03.2023", 3, false);
        this.toDoList.addAll(Arrays.asList(todo1, todo2, todo3));
    }

    // WIESO ÜBERGIBT MAN DIE ID MANUELL UND NICHT "NULL" ??

    @Test
    public void createToDo(){

    }

    @Test
    public void updateToDo(){

    }

    @Test
    public void deleteToDo(){

    }

    @Test
    public void getAllToDos() throws Exception{
        when(this.toDoService.getAllToDos()).thenReturn(this.toDoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {
                                        "id": 1,
                                        "title": "Wäsche",
                                        "description": "Wäsche waschen",
                                        "creationDate": "21.03.2023",
                                        "dueDate": "24.03.2023",
                                        "priority": 2,
                                        "isDone": true
                                    },
                                    {
                                        "id": 2,
                                        "title": "Putzen",
                                        "description": "Wohnung putzen",
                                        "creationDate": "21.03.2023",
                                        "dueDate": "31.03.2023",
                                        "priority": 1,
                                        "isDone": false
                                    },
                                    {
                                        "id": 3,
                                        "title": "Kochen",
                                        "description": "Abendessen kochen",
                                        "creationDate": "21.03.2023",
                                        "dueDate": "21.03.2023",
                                        "priority": 3,
                                        "isDone": false
                                    }
                                ]
                                """
                ));
    }

    @Test
    public void getToDoById(){

    }
}
