package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing Book. 
 */
public class BookTest {

    private Book book1;
    private Book book2;

    /**
     * Method for setting up the test correctly before running each test.
     */
    @BeforeEach
    public void setUp() {
        book1 = new Book("Book Title 1", "Author 1");
        book2 = new Book("Book Title 2", "Author 2");
    }

    /**
     * Tests getTitle()
     */
    @Test
    public void testGetTitle() {
        assertEquals("Book Title 1", book1.getTitle());
        assertEquals("Book Title 2", book2.getTitle());
    }

    /**
     * Tests getAuthor()
     */
    @Test
    public void testGetAuthor() {
        assertEquals("Author 1", book1.getAuthor());
        assertEquals("Author 2", book2.getAuthor());
    }

    /**
     * Tests setBookId() and getBookId()
     */
    @Test
    public void testSetAndGetBookId() {
        book1.setBookId("test1");
        book2.setBookId("test2");

        assertEquals(1, book1.getBookId());
        assertEquals(2, book2.getBookId());
    }

    /**
     * Tests checkAuthor()
     */
    @Test
    public void testCheckAuthor() {
        assertTrue(book1.checkAuthor(book1)); // Samme forfatter
        assertFalse(book1.checkAuthor(book2)); // Forskjellige forfattere
    }

    /**
     * Tests setTitle()
     */
    @Test
    public void testSetTitle() {
        book1.setTitle("Title Test");
        assertEquals("Title Test", book1.getTitle());
    }

    /**
     * Tests setAuthor()
     */
    @Test
    public void testSetAuthor() {
        book1.setAuthor("Navn Navnesen");
        assertEquals("Navn Navnesen", book1.getAuthor());
    }

    /**
     * Tests setImageSrc()
     */
    //@Test
    //public void testSetImageSrc() {
    //    book1.setImageSrc("/ui/BookImages/gilmore.jpg");
    //      assertEquals("/ui/BookImages/gilmore.jpg", book1.getImageSrc());
    //}

    
}
