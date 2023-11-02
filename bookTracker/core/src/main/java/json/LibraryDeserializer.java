package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import core.Book;

public class LibraryDeserializer extends JsonDeserializer<Book> {

    @Override
    public Book deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize(node);
    }

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
            JsonNode descriptionNode = objectNode.get("description");
            if (descriptionNode instanceof TextNode) {
                book.setDescription(descriptionNode.asText());
            }
            return book;
        }
        return null;
    }
}
