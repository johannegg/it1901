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

public class LibraryModuleTest {

    private ObjectMapper objectMapper;
    private BookShelf bookShelf;
    private Book book;

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

    @Test
    public void testBookSerialization() throws JsonProcessingException {
        String serializedBook = objectMapper.writeValueAsString(book);

        assertNotNull(serializedBook);
    }

    @Test
    public void testBookShelfSerialization() throws JsonProcessingException {
        String serializedBookShelf = objectMapper.writeValueAsString(bookShelf);
        assertNotNull(serializedBookShelf);
    }

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

    @Test
    void deserializeShouldReturnNullForNullNode() throws Exception {
        BookDeserializer bookDeserializer = new BookDeserializer();
        JsonNode nullNode = null;
        Book result1 = bookDeserializer.deserialize(nullNode);
        assertNull(result1);
        BookShelfDeserializer BookShelfDeserializer = new BookShelfDeserializer();
        BookShelf result2 = BookShelfDeserializer.deserialize(nullNode);
        assertNull(result2);
    }

}
