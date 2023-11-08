package json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import core.Book;
import core.BookShelf;

public class BookShelfDeserializer extends JsonDeserializer<BookShelf> {

    @Override
    public BookShelf deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize(node);
    }

    BookShelf deserialize(JsonNode node) throws IOException {
        if (node != null && node.has("books")) {
            JsonNode booksNode = node.get("books");
            List<Book> books = new ArrayList<>();

            if (booksNode.isArray()) {
                for (JsonNode bookNode : booksNode) {
                    if (bookNode != null && bookNode.isObject()) {
                        Book book = new BookDeserializer().deserialize(bookNode);
                        if (book != null) {
                            books.add(book);
                        }
                    }
                }
            }

            BookShelf bookShelf = new BookShelf();
            bookShelf.setBooks(books);
            return bookShelf;
        }
        return null;
    }
}