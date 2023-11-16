package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

/**
 * Test class for testing the BookShelf class and its methods.
 */
public class BookShelfTest {
    private BookShelf bookShelf;
    private Book book1;
    private Book book2;

    /**
     * Method for setting up the test evironment before running each tests.
     */
    @BeforeEach
    void setUp() {
        bookShelf = new BookShelf();
        book1 = new Book();
        book2 = new Book();
        book1.setAuthor("author1");
        book1.setTitle("Title1");
        book1.setBookId("abc");
        book1.setDescription("Description1");
        book1.setPages("1");
        book2.setBookId("def");
    }

    /**
     * Method that tests addBook().
     */
    @Test
    void testAddBook() {
        bookShelf.addBook(book1);
        assertTrue(bookShelf.getBooks().contains(book1));
        assertThrows(IllegalStateException.class, () -> bookShelf.addBook(book1));
    }

    /**
     * Method that tests removeBook().
     */
    @Test
    void testRemoveBook() {
        bookShelf.addBook(book1);
        bookShelf.removeBookById("abc");
        assertFalse(bookShelf.getBooks().contains(book1));
        bookShelf.addBook(book1);
        bookShelf.removeBookById("def");
        assertEquals(1, bookShelf.getBooks().size());

    }

    /**
     * Method that tests setBooks().
     */
    @Test
    void testSetBooks() {
        bookShelf.addBook(book1);
        bookShelf.addBook(book2);
        BookShelf bookShelf2 = new BookShelf();
        bookShelf2.setBooks(Arrays.asList(book1, book2));
        bookShelf.setBooks(Arrays.asList(book1, book2));
        assertEquals(bookShelf.getBooks(), bookShelf2.getBooks());
    }

    /**
     * Method that tests iterator().
     */
    @Test
    void testIterator() {
        bookShelf.addBook(book1);
        bookShelf.addBook(book2);
        Iterator<Book> iterator = bookShelf.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(book1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(book2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Method that tests getBook().
     */
    @Test
    void testGetBook() {
        bookShelf.addBook(book1);
        assertEquals(bookShelf.getBook("abc").getTitle(), "Title1");
        assertThrows(IllegalArgumentException.class, () -> bookShelf.getBook(null));
        assertThrows(IllegalArgumentException.class, () -> bookShelf.getBook("xyz"));
    }
}
