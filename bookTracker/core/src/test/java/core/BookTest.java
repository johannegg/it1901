package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for testing the BookShelf class and its methods.
 */
public class BookTest {

    private Book book1;
    private Book book2;

    /**
     * Method for setting up the test evironment before running each tests.
     */
    @BeforeEach
    public void setUp() {
        book1 = new Book();
        book2 = new Book();
    }

    /**
     * Method that tests setBookId() and getBookId().
     */
    @Test
    public void testSetAndGetBookId() {
        book1.setBookId("test1");
        book2.setBookId("test2");

        assertEquals("test1", book1.getBookId());
        assertEquals("test2", book2.getBookId());
    }

    /**
     * Method that tests setBookTitle() and getBookTitle().
     */
    @Test
    public void testSetAndGetTitle() {
        book1.setTitle("TitleTest1");
        book2.setTitle("TitleTest2");

        assertEquals("TitleTest1", book1.getTitle());
        assertEquals("TitleTest2", book2.getTitle());
    }

    /**
     * Method that tests setPages() and getPages().
     */
    @Test
    public void testSetAndGetPages() {
        book1.setPages("10");
        book2.setPages("20");

        assertEquals("10", book1.getPages());
        assertEquals("20", book2.getPages());
    }

    /**
     * Method that tests setDescriptin() and getDescription().
     */
    @Test
    public void testSetAndGetDescription() {
        book1.setDescription("TestDescription1");
        book2.setDescription("TestDescription2");

        assertEquals("TestDescription1", book1.getDescription());
        assertEquals("TestDescription2", book2.getDescription());
    }

    /**
     * Method that tests setAuthor() and getAuthor().
     */
    @Test
    public void testSetAndGetAuthor() {
        book1.setAuthor("TestAuthor1");
        book2.setAuthor("TestAuthor2");

        assertEquals("TestAuthor1", book1.getAuthor());
        assertEquals("TestAuthor2", book2.getAuthor());
    }

}