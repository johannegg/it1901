package restserver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.User;
import json.UsersModule;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { BookTrackerController.class, UsersService.class, LibraryService.class,
        BookTrackerApplication.class })
@SpringBootTest
public class BookTrackerApplicationTest {

    @Autowired
    private BookTrackerController controller;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper usersMapper;
    
    @BeforeAll
    public static void setUp(){
        usersMapper = new ObjectMapper();
        usersMapper.registerModule(new UsersModule());
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        controller.setUsersFileName("../restserver/src/test/resources/testUsers.json");
    }

    @AfterEach
    public void afterEach() throws Exception {
        testDeleteAllMapping();
        try {
            putDefaultUser();
        } catch (Exception e) {
            fail("Tried to put default user");
        }
    }

    @Test
    public void testDeleteAllMapping() throws Exception {
        this.mockMvc.perform(delete("/api/users/delete"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostUser() throws Exception {
        User postUser = new User("post@mail.com", "postUser", "password123");
        this.mockMvc.perform(post("/api/users/" + postUser.getUsername())
                .content(usersMapper.writeValueAsString(postUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetUser() throws Exception {
        System.out.println("test");
        String username1 = "test";
        this.mockMvc.perform(get("/api/users/" + username1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists());
    }

    @Test
    public void testGetUserFail() throws Exception {
        String username = "doesntexist";
        this.mockMvc.perform(get("/api/users/" + username))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void testPutUser() throws Exception {
        User putUser = new User("put@mail.com", "putUser", "password1234");
        this.mockMvc.perform(put("/api/users/" + putUser.getUsername())
                .content(usersMapper.writeValueAsString(putUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testgetLibrary() throws Exception{
        this.mockMvc.perform(get("/api/library"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books").exists());
    }

    public void putDefaultUser() throws Exception {
        User putUser = new User("test@gmail.com", "test", "test1234");
        this.mockMvc.perform(put("/api/users/" + putUser.getUsername())
                .content(usersMapper.writeValueAsString(putUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}