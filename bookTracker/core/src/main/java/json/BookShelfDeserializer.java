package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import core.Book;
import core.BookShelf;

public class BookShelfDeserializer extends JsonDeserializer<BookShelf>{

    private BookDeserializer bookDeserializer = new BookDeserializer();

    @Override
    public BookShelf deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize((JsonNode) node);
    }

    private BookShelf deserialize(JsonNode node) throws IOException {
        if (node instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) node;
            BookShelf bookShelf = new BookShelf();
            JsonNode bookShelfNode = objectNode.get("books");
            if (bookShelfNode instanceof ArrayNode) {
                for (JsonNode bookNode : ((ArrayNode) bookShelfNode)) {
                    Book book = bookDeserializer.deserialize(bookNode);
                    if (book != null) {
                        bookShelf.addBook(book);
                    }
                }
            }
            return bookShelf;
        }
        return null;
    }
}
