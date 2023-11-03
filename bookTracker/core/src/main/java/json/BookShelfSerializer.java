package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Book;
import core.BookShelf;

public class BookShelfSerializer extends JsonSerializer<BookShelf> {

    @Override
    public void serialize(BookShelf bookShelf, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeArrayFieldStart("books");
        for (Book book : bookShelf) {
            gen.writeObject(book);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
    
}
