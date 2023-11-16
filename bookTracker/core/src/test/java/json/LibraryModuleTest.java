package json;

import core.Book;
import core.BookShelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

/**
 * Test class for testing the LibraryModule class and its methods.
 */
public class LibraryModuleTest {

    private ObjectMapper objectMapper;
    private BookShelf bookShelf;
    private Book book;

    /**
     * Method for setting up the test evironment before running each tests.
     */
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new LibraryModule());
        book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("Rowling");
        book.setPages("900");
        book.setBookId("12345");
        book.setDescription("Bokbeskrivelse");
        bookShelf = new BookShelf();
        bookShelf.addBook(book);
    }

    /**
     * Method that test serialization of Book object.
     *
     * @throws JsonProcessingException if JSON processing fails.
     */
    @Test
    public void testBookSerialization() throws JsonProcessingException {
        String serializedBook = objectMapper.writeValueAsString(book);
        assertNotNull(serializedBook);
    }

    /**
     * Method that test serialization of BookShelf objet.
     *
     * @throws JsonProcessingException if JSON processing fails.
     */
    @Test
    public void testBookShelfSerialization() throws JsonProcessingException {
        String serializedBookShelf = objectMapper.writeValueAsString(bookShelf);
        assertNotNull(serializedBookShelf);
    }

    /**
     * Method that test deserialization of Book objet.
     *
     * @throws JsonProcessingException if JSON processing fails.
     */
    @Test
    public void testBookDeserialization() throws JsonProcessingException {
        String jsonBook = objectMapper.writeValueAsString(book);
        Book deserializedBook = objectMapper.readValue(jsonBook, Book.class);

        assertNotNull(deserializedBook);
        assertEquals(book.getTitle(), deserializedBook.getTitle());
        assertEquals(book.getAuthor(), deserializedBook.getAuthor());
        assertEquals(book.getPages(), deserializedBook.getPages());
        assertEquals(book.getBookId(), deserializedBook.getBookId());
        assertEquals(book.getDescription(), deserializedBook.getDescription());

    }

    /**
     * Tests that deserializing a null JSON node results in a null object.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void deserializeShouldReturnNullForNullNode() throws IOException {
        BookDeserializer bookDeserializer = new BookDeserializer();
        JsonNode nullNode = null;
        Book result1 = bookDeserializer.deserialize(nullNode);
        assertNull(result1);
        BookShelfDeserializer BookShelfDeserializer = new BookShelfDeserializer();
        BookShelf result2 = BookShelfDeserializer.deserialize(nullNode);
        assertNull(result2);
    }

}
