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

/**
 * Test class for testing the UsersModule class and its methods.
 */
public class UsersModuleTest {

        private ObjectMapper objectMapper;
        private Users users;
        private User user1, user2;

        /**
         * Method for setting up the test evironment before running each tests.
         */
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

        /**
         * Method that tests serialization and deserialization of Users objects.
         *
         * @throws JsonProcessingException if JSON processing fails.
         * 
         */
        @Test
        public void testUsersSerializationAndDeserialization() throws JsonProcessingException {

                String serializedUsers = objectMapper.writeValueAsString(users);
                assertNotNull(serializedUsers);
                Users deserializedUsers = objectMapper.readValue(serializedUsers, Users.class);
                assertNotNull(deserializedUsers);
                assertEquals(users.getUsers().size(), deserializedUsers.getUsers().size());

                for (int i = 0; i < users.getUsers().size(); i++) {
                        User originalUser = users.getUsers().get(i);
                        User deserializedUser = deserializedUsers.getUsers().get(i);

                        assertEquals(originalUser.getUsername(), deserializedUser.getUsername());
                        assertEquals(originalUser.getEmail(), deserializedUser.getEmail());
                        assertEquals(originalUser.getPassword(), deserializedUser.getPassword());
                        assertEquals(originalUser.isLoggedIn(), deserializedUser.isLoggedIn());

                        assertNotNull(deserializedUser.getBookShelf());
                        assertEquals(originalUser.getBookShelf().getBooks().size(),
                                        deserializedUser.getBookShelf().getBooks().size());
                }

        }

        /**
         * Method that tests the deserialization of a User object from a JSON string.
         *
         * @throws IOException if an I/O error occurs.
         * 
         */
        @Test
        public void testUserDeserializationFromJsonString() throws IOException {
                String json = "{\"username\":\"camilla\",\"email\":\"camilla@ntnu.no\",\"password\":\"passord123\",\"loggedIn\":true,\"bookShelf\":{\"books\":[]}}";
                JsonParser parser = objectMapper.getFactory().createParser(json);
                DeserializationContext ctxt = objectMapper.getDeserializationContext();
                User user = new UserDeserializer().deserialize(parser, ctxt);

                assertNotNull(user);
                assertEquals("camilla", user.getUsername());
                assertEquals("camilla@ntnu.no", user.getEmail());
                assertEquals("passord123", user.getPassword());
                assertTrue(user.isLoggedIn());
        }

}
