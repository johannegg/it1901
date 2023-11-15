package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.BookShelf;

/**
 * Persistence class for reading from the library.
 */
public class LibraryPersistence {
    
    private File file = new File("../core/src/main/java/resources/library.json");
    private ObjectMapper objectMapper;

    /**
     * Creates a new LibraryPersistence instance and sets the objectMapper with a module 
     * for handling serialization and deserialization of Book and BookShelf.
     */
    public LibraryPersistence() {
        objectMapper = new ObjectMapper();
        LibraryModule mod = new LibraryModule();
        objectMapper.registerModule(mod);
    }

    /**
     * Reads the BookShelf from the library json file.
     * 
     * @return  the BookShelf object read from the library.
     * @throws IOException  if an I/O error occurs during the reading process.
     */
    public BookShelf readFromLibrary() throws IOException {
        return objectMapper.readerFor(BookShelf.class).readValue(file);
    }
    
}
