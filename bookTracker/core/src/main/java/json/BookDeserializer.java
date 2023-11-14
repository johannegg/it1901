package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import core.Book;

public class BookDeserializer extends JsonDeserializer<Book> {

    @Override
    public Book deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize(node);
    }
    /**
     * Takes in an instance of JsonNode, and deserializes it. Returns an instance of
     * Book.
     * @param node node to be deserialized
     * @return Book object
     */
    Book deserialize(JsonNode node) {
        if (node instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) node;
            Book book = new Book();
            JsonNode titleNode = objectNode.get("title");
            if (titleNode instanceof TextNode) {
                book.setTitle(titleNode.asText());
            }
            JsonNode authorNode = objectNode.get("author");
            if (authorNode instanceof TextNode) {
                book.setAuthor(authorNode.asText());
            }
            JsonNode pagesNode = objectNode.get("pages");
            if (pagesNode instanceof TextNode) {
                book.setPages(pagesNode.asText());
            }
            JsonNode bookIdNode = objectNode.get("bookId");
            if (bookIdNode instanceof TextNode) {
                book.setBookId(bookIdNode.asText());
            }
            JsonNode descriptionNode = objectNode.get("description");
            if (descriptionNode instanceof TextNode) {
                book.setDescription(descriptionNode.asText());
            }
            return book;
        }
        return null;
    }
}