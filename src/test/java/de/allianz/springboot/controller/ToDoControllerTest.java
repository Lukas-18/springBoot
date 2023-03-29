package de.allianz.springboot.controller;

import de.allianz.springboot.config.PasswordEncoderConfig;
import de.allianz.springboot.config.PasswordEncoderConfigTest;
import de.allianz.springboot.config.SecureConfig;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Import(PasswordEncoderConfigTest.class)
@WebMvcTest(ToDoController.class)
//@WithMockUser
//@ContextConfiguration(classes = {SecureConfig.class, PasswordEncoderConfig.class})
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

    @Test
    @WithMockUser
    public void createToDo() throws Exception {
        ToDo todo4 = new ToDo(4L, "Neu", "Neues ToDo", "21.03.2023", "24.03.2023", 2, false);
        when(this.toDoService.createToDo(any())).thenReturn(todo4);
        when(this.modelMapper.map(any(), any())).thenReturn(todo4);


        this.mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                    {
                                        "id": null,
                                        "title": "Neu",
                                        "description": "Neues ToDo",
                                        "creationDate": "21.03.2023",
                                        "dueDate": "24.03.2023",
                                        "priority": 2,
                                        "isDone": false
                                    }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                        """
                                    {
                                        "id": 4,
                                        "title": "Neu",
                                        "description": "Neues ToDo",
                                        "creationDate": "21.03.2023",
                                        "dueDate": "24.03.2023",
                                        "priority": 2,
                                        "isDone": false
                                    }
                                """
                ));


    }

    @Test
    @WithMockUser
    public void updateToDo() throws Exception{
        ToDo updatedToDo = new ToDo(5L, "Update", "Geupdates ToDo", "21.03.2023", "24.03.2023", 2, false);
        when(this.toDoService.updateToDo(any())).thenReturn(updatedToDo);

        mockMvc.perform(MockMvcRequestBuilders.put("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                    "id": 5,
                                    "title": "Update",
                                    "description": "Geupdates ToDo",
                                    "creationDate": "21.03.2023",
                                    "dueDate": "24.03.2023",
                                    "priority": 2,
                                    "isDone": false
                                }"""))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                    {
                                        "id": 5,
                                        "title": "Update",
                                        "description": "Geupdates ToDo",
                                        "creationDate": "21.03.2023",
                                        "dueDate": "24.03.2023",
                                        "priority": 2,
                                        "isDone": false
                                    }
                                """
                ));

    }

    @Test
    @WithMockUser(username="user", password="1234")
    public void deleteToDo() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/todo/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void getAllToDos() throws Exception{
        when(this.toDoService.getAllToDos()).thenReturn(this.toDoList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/todo"))
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
    @WithMockUser
    public void getToDoById() throws Exception {
        when(this.toDoService.getId(2L)).thenReturn(this.todo2);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/todo/2"))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                        """
                                {
                                    "id": 2,
                                    "title": "Putzen",
                                    "description": "Wohnung putzen",
                                    "creationDate": "21.03.2023",
                                    "dueDate": "31.03.2023",
                                    "priority": 1,
                                    "isDone": false
                                }
                                """
                ));

    }
}
