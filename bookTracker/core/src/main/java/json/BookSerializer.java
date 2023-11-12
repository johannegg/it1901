package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Book;

public class BookSerializer extends JsonSerializer<Book> {

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
