package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Book;
import core.BookShelf;

public class BookShelfSerializer extends JsonSerializer<BookShelf> {

    private final BookSerializer bookSerializer = new BookSerializer(); // Create an instance of BookSerializer

    @Override
    public void serialize(BookShelf bookShelf, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray(); // Start an array for bookShelf

        for (Book book : bookShelf) {
            // Use the existing BookSerializer to serialize each book
            bookSerializer.serialize(book, gen, serializers);
        }

        gen.writeEndArray(); // End the array for bookShelf
    }
}
