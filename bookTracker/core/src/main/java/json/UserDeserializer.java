package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import core.User;

public class UserDeserializer extends JsonDeserializer<User> {

     /**
   * Method for deserializing from a json string to a User object. Returns an
   * instance of User.
   *
   * @param p     JsonParser for parsing in json format
   * @param ctxt  context for the deserialization
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
   */
  User deserialize(JsonNode node) {
    if (node instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) node;
      User user = new User();
      JsonNode usernameNode = objectNode.get("username");
      if (usernameNode instanceof TextNode) {
        user.setUsername(usernameNode.asText());
      }
      JsonNode emailNode = objectNode.get("email");
      if (emailNode instanceof TextNode) {
        user.setEmail(emailNode.asText());
      }
      JsonNode passwordNode = objectNode.get("password");
      if (passwordNode instanceof TextNode) {
        user.setPassword(passwordNode.asText());
      }
      return user;
    }
    return null;
  }
}
