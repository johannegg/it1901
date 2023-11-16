package restserver;

import java.io.IOException;

import org.springframework.stereotype.Service;

import core.BookShelf;
import json.LibraryPersistence;

/**
 * Service class for managing library in application.
 */
@Service
public class LibraryService {

    LibraryPersistence persistence = new LibraryPersistence();

    /**
     * Retrieves the library containg available books.
     * 
     * @return the library as a BookShelf object.
     * @throws IOException if the reading of book data fron persistence fails.
     */
    public BookShelf getLibrary() throws IOException {
        return persistence.readFromLibrary();
    }

}
