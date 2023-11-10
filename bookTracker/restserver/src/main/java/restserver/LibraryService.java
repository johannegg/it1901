package restserver;

import java.io.IOException;

import org.springframework.stereotype.Service;

import core.BookShelf;
import json.LibraryPersistence;

@Service
public class LibraryService {
    LibraryPersistence persistence = new LibraryPersistence();

    public BookShelf getLibrary() throws IOException{
        return persistence.readFromLibrary();
    }

}
