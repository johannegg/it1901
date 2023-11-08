package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.BookShelf;

/**
 * Persistence class for reading from library
 */
public class LibraryPersistence {
    
    private File file = new File("../core/src/main/java/resources/library.json");
    private ObjectMapper objectMapper;

    /**
     * Creates a new LibraryPersistence instance and sets the objectMapper
     */
    public LibraryPersistence() {
        objectMapper = new ObjectMapper();
        LibraryModule mod = new LibraryModule();
        objectMapper.registerModule(mod);
    }

    public BookShelf readFromLibrary() throws IOException {
        return objectMapper.readerFor(BookShelf.class).readValue(file);
    }
    
}
