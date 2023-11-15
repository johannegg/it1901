package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import core.User;
import core.Users;

public class UsersDeserializer extends JsonDeserializer<Users> {

    private UserDeserializer userDeserializer = new UserDeserializer();

    /**
     * Method for deserializing from a json string to a Users object. Returns an
     * instance of Users.
     *
     * @param p    JsonParser for parsing in json format
     * @param ctxt context for the deserialization
     */
    @Override
    public Users deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        return deserialize((JsonNode) node);
    }

    /**
     * Takes in an inseance of JsonNode, and deserializes it. Returns an instance of
     * Users.
     *
     * @param node node to be deserialized
     * @return Users object
     * @throws IOException
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
