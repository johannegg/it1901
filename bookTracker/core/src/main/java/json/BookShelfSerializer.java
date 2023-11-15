package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Book;
import core.BookShelf;

/**
 * Serializer for BookShelf objects.
 * Converts BookShelf objects to json string.
 */
public class BookShelfSerializer extends JsonSerializer<BookShelf> {

    private final BookSerializer bookSerializer = new BookSerializer(); 

    /**
     * Writes a specified instance of Book object as a json string to a json generator.
     * Each book in the BookShelf is individually serialized using the BookSerializer.
     * 
     * @param bookShelf  BookShelf object to convert to json string.
     * @param gen  Assigned generator to use during serialization.
     * @param serializers  SerializerProvider to store the handled serialized json string.
     * @throws IOException  if an I/O error occurs during serialization.
     */
    @Override
    public void serialize(BookShelf bookShelf, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray(); 
        for (Book book : bookShelf) {
            
            bookSerializer.serialize(book, gen, serializers);
        }

        gen.writeEndArray(); 
    }
}
