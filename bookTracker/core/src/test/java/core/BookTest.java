package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        book1 = new Book("Book Title 1", "Author 1");
        book2 = new Book("Book Title 2", "Author 2");
    }

    @Test
    public void testGetTitle() {
        assertEquals("Book Title 1", book1.getTitle());
        assertEquals("Book Title 2", book2.getTitle());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("Author 1", book1.getAuthor());
        assertEquals("Author 2", book2.getAuthor());
    }

    @Test
    public void testSetAndGetBookId() {
        book1.setBookId(1);
        book2.setBookId(2);

        assertEquals(1, book1.getBookId());
        assertEquals(2, book2.getBookId());
    }

    @Test
    public void testCheckAuthor() {
        assertTrue(book1.checkAuthor(book1)); // Samme forfatter
        assertFalse(book1.checkAuthor(book2)); // Forskjellige forfattere
    }
}
