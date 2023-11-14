package restserver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import json.LibraryModule;
import json.UsersModule;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { BookTrackerController.class, UsersService.class, LibraryService.class,
        BookTrackerApplication.class })
@WebMvcTest
public class BookTrackerApplicationTest {

    @Autowired
    private BookTrackerController controller;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @BeforeEach
    public void setup() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UsersModule());
        objectMapper.registerModule(new LibraryModule());

        controller.setUsersFileName("../restserver/src/test/resources/testUsers.json");
    }

    @Test
    public void testGetUser() throws Exception {
        String user1Username = "test";
        this.mockMvc.perform(get("/api/users/" + user1Username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists());
    }
}
