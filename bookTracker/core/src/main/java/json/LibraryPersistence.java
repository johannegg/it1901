package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.Book;

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
        objectMapper.registerModule(new SimpleModule().addDeserializer(Book.class, new LibraryDeserializer()));
    }

    public Book readFromLibrary() throws IOException {
        return objectMapper.readerFor(Book.class).readValue(file);
    }
}
