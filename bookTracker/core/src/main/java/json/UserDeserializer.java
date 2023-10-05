package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import core.User;

public class UserDeserializer extends JsonDeserializer<User> {
    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize(node);
    }

    User deserialize(JsonNode node) {
        if (node instanceof ObjectNode objectNode) {
            User book = new User();

            JsonNode emailNode = objectNode.get("email");
            if (emailNode instanceof TextNode) {
                book.setEmail(emailNode.asText());
            }

            JsonNode usernameNode = objectNode.get("username");
            if (usernameNode instanceof TextNode) {
                book.setUsername(usernameNode.asText());
            }

            JsonNode passwordNode = objectNode.get("password");
            if (passwordNode instanceof TextNode) {
                book.setPassword(passwordNode.asText());
            }

            return book;
        }
        return null;
    }

}
