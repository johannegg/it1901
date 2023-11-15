package json;

import core.Book;
import core.BookShelf;
import core.User;
import core.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

public class UsersModuleTest {

    private ObjectMapper objectMapper;
    private Users users;
    private User user1, user2;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UsersModule());

        BookShelf bookShelf = new BookShelf();
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("Johanne");
        book.setPages("123");
        book.setBookId("10");
        book.setDescription("beskrivelse av bok");
        bookShelf.addBook(book);

        user1 = new User();
        user1.setEmail("camilla@ntnu.no");
        user1.setUsername("camilla");
        user1.setPassword("passord123");
        user1.setBookShelf(bookShelf);
        user1.setLoggedIn(true);

        user2 = new User();
        user2.setEmail("suzanne@ntnu.no");
        user2.setUsername("suzanne");
        user2.setPassword("passord321");
        user2.setBookShelf(bookShelf);
        user2.setLoggedIn(false);

        users = new Users();
        users.addUser(user1);
        users.addUser(user2);
    }

    @Test
    public void testUsersSerializationAndDeserialization() throws JsonProcessingException {

        String serializedUsers = objectMapper.writeValueAsString(users);
        assertNotNull(serializedUsers, "Serialized users should not be null");

        Users deserializedUsers = objectMapper.readValue(serializedUsers, Users.class);
        assertNotNull(deserializedUsers, "Deserialized users should not be null");

        assertEquals(users.getUsers().size(), deserializedUsers.getUsers().size(),
                "The number of users should be the same after deserialization");

        for (int i = 0; i < users.getUsers().size(); i++) {
            User originalUser = users.getUsers().get(i);
            User deserializedUser = deserializedUsers.getUsers().get(i);

            assertEquals(originalUser.getUsername(), deserializedUser.getUsername(),
                    "Usernames should be the same after deserialization");
            assertEquals(originalUser.getEmail(), deserializedUser.getEmail(),
                    "Emails should be the same after deserialization");
            assertEquals(originalUser.getPassword(), deserializedUser.getPassword(),
                    "Passwords should be the same after deserialization");
            assertEquals(originalUser.isLoggedIn(), deserializedUser.isLoggedIn(),
                    "LoggedIn status should be the same after deserialization");

            assertNotNull(deserializedUser.getBookShelf(),
                    "BookShelf should not be null after deserialization");
            assertEquals(originalUser.getBookShelf().getBooks().size(),
                    deserializedUser.getBookShelf().getBooks().size(),
                    "BookShelf size should be the same after deserialization");
        }

    }

    @Test
    public void testUserDeserializationFromJsonString() throws IOException {
        String json = "{\"username\":\"camilla\",\"email\":\"camilla@ntnu.no\",\"password\":\"passord123\",\"loggedIn\":true,\"bookShelf\":{\"books\":[]}}";
        JsonParser parser = objectMapper.getFactory().createParser(json);
        DeserializationContext ctxt = objectMapper.getDeserializationContext();
        User user = new UserDeserializer().deserialize(parser, ctxt);

        assertNotNull(user, "User deserialized from JSON string should not be null");
        assertEquals("camilla", user.getUsername(), "Usernames should match");
        assertEquals("camilla@ntnu.no", user.getEmail(), "Emails should match");
        assertEquals("passord123", user.getPassword(), "Passwords should match");
        assertTrue(user.isLoggedIn(), "User should be logged in");
    }

}
