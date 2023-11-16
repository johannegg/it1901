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

/**
 * Deserializer for Book objects.
 * Converts the Book json data to Book objects.
 */
public class BookDeserializer extends JsonDeserializer<Book> {

    /**
     * Deserializes a Json representation of a Book object.
     * 
     * @param p JsonParser for reading json content.
     * @param ctxt DeserializationContext for handling deserialization configuration.
     * @return an instance of the deserialized Book object, or null if deserialization fails.
     * @throws IOException if an I/O error occurs during deserialization.
     * @throws JsonProcessingException if a json processing error occurs during deserialization.
     */
    @Override
    public Book deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize(node);
    }

    /**
     * Takes in an instance of JsonNode, and deserializes it. Returns an instance of
     * Book.
     * 
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