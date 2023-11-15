package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Book;

/**
 * Serializer for Book objects.
 * Converts Book objects to json string.
 */
public class BookSerializer extends JsonSerializer<Book> {

    /**
     * Writes a specified instance of Book object as a json string to a json generator.
     * 
     * Format for the 'book' paramter:
     * {"title": "..."
     *  "author": "..."
     *  "pages": "..."
     *  "bookID": "..."
     *  "description": "..."
     * }
     * 
     * @param book  Book object to convert to json string.
     * @param gen  Assigned generator to use during serialization.
     * @param serializers  SerializerProvider to store the handled serialized json string.
     * @throws IOException  if an I/O error occurs during serialization.
     */
    @Override
    public void serialize(Book book, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("title", book.getTitle());
        gen.writeStringField("author", book.getAuthor());
        gen.writeStringField("pages", book.getPages());
        gen.writeStringField("bookId", book.getBookId());
        gen.writeStringField("description", book.getDescription());

        gen.writeEndObject();
    }

}
