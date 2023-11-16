package json;

import core.Book;
import core.BookShelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for testing the LibraryPersistence class and its methods.
 */
public class LibraryPersistenceTest {

    private LibraryPersistence libraryPersistence;

    /**
     * Method for setting up the test evironment before running each tests.
     */
    @BeforeEach
    public void setUp() {
        File testFile = new File("../core/src/test/resources/testLibrary.json");
        libraryPersistence = new LibraryPersistence();
        libraryPersistence.setFile(testFile);
    }

    /**
     * Mathod that tests the reading functionality of the LibraryPersistence class.
     *
     * @throws IOException if an error occurs during reading the file.
     */
    @Test
    public void testReadFromLibrary() throws IOException {
        BookShelf bookShelf = libraryPersistence.readFromLibrary();

        assertNotNull(bookShelf);
        assertEquals(3, bookShelf.getBooks().size());

        Book firstBook = bookShelf.getBooks().get(0);
        assertEquals("Trust", firstBook.getTitle());
        assertEquals("Hernan Diaz", firstBook.getAuthor());
        assertEquals("416", firstBook.getPages());
        assertEquals("diaz", firstBook.getBookId());
        assertEquals("A compelling novel that won the Pulitzer Prize for Fiction in 2023.", firstBook.getDescription());

    }
}
