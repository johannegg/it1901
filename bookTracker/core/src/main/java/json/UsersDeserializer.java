package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import core.User;
import core.Users;

/**
 * Deserializer for User objects.
 * Converts the User JSON data to User objects.
 */
public class UsersDeserializer extends JsonDeserializer<Users> {

    private UserDeserializer userDeserializer = new UserDeserializer();

    /**
     * Method for deserializing from a JSON string to a User object. Returns an
     * instance of User.
     *
     * @param p  JsonParser for parsing in JSON format.
     * @param ctxt  context for deserialization.
     * @return  an instance of the deserialized User object, or null if
     *          deserialization fails.
     * @throws IOException  if an I/O error occurs during deserialization.
     * @throws JsonProcessingException  if a JSON processing error occurs during
     *                                 deserialization.
     */
    @Override
    public Users deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize((JsonNode) node);
    }

    /**
     * Takes in an instance of JsonNode and deserializes it. Returns an instance of
     * User.
     *
     * @param node  node to be deserialized.
     * @return  User object if deserialization is successful, or null if
     *          deserialization fails.
     * @throws IOException  if an I/O error occurs during deserialization.
     * @throws JacksonException  if a Jackson error occurs during deserialization.
     */
    private Users deserialize(JsonNode node) throws IOException {
        if (node instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) node;
            Users users = new Users();
            JsonNode usersNode = objectNode.get("users");
            if (usersNode instanceof ArrayNode) {
                for (JsonNode userNode : ((ArrayNode) usersNode)) {
                    User user = userDeserializer.deserialize(userNode);
                    if (user != null) {
                        users.addUser(user);
                    }
                }
            }
            return users;
        }
        return null;
    }

}
