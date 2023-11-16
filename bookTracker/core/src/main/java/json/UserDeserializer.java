package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import core.BookShelf;
import core.User;

/**
 * Deserializer for User objects.
 * Converts the User JSON data to User objects.
 */
public class UserDeserializer extends JsonDeserializer<User> {

  /**
   * Method for deserializing from a json string to a User object. Returns an
   * instance of User.
   *
   * @param p    JsonParser for parsing in json format
   * @param ctxt context for the deserialization
   */
  @Override
  public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
      JsonProcessingException {
    final JsonNode node = p.getCodec().readTree(p);
    return deserialize(node);
  }

  /**
   * Takes in an inseance of JsonNode, and deserializes it. Returns an instance of
   * User.
   *
   * @param node node to be deserialized
   * @return User object
   * @throws IOException
   * @throws JacksonException
   */
  User deserialize(JsonNode node) throws IOException {
    if (node instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) node;
      User user = new User();
      user.setUsername(objectNode.get("username").asText());
      user.setEmail(objectNode.get("email").asText());
      user.setPassword(objectNode.get("password").asText());
      user.setLoggedIn(objectNode.get("loggedIn").asBoolean());

      JsonNode bookShelfNode = objectNode.get("bookShelf");
      if (bookShelfNode instanceof ObjectNode) {
        BookShelf bookShelf = new BookShelfDeserializer().deserialize(bookShelfNode);
        user.setBookShelf(bookShelf);
      }
      return user;
    }
    return null;
  }
}
