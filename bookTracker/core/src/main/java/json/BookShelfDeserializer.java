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

/**
 * Deserializer for BookShelf objects.
 * Converts the BookShelf json data to BookShelf objects.
 */
public class BookShelfDeserializer extends JsonDeserializer<BookShelf> {

    /**
     * Deserializes a json representation of a BookShelf object.
     * 
     * @param p    JsonParser for reading json content.
     * @param ctxt DeserializationContext for handling deserialization
     *             configuration.
     * @return instance of the deserialized BookShelf object, or null if
     *         deserialization fails.
     * @throws IOException             if an I/O error occurs during
     *                                 deserialization.
     * @throws JsonProcessingException if a Json processing error occurs during
     *                                 deserialization.
     */
    @Override
    public BookShelf deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
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