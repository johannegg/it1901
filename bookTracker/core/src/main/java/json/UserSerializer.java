package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.User;

/**
 * Serializer for User objects.
 * Converts User objects to json string.
 */
public class UserSerializer extends JsonSerializer<User> {

    /**
     * Writes a specified instance of User object as a json string to a
     * JsonGenerator.
     * 
     * @param user        User object to convert to json string.
     * @param gen         Assigned generator to use during serialization.
     * @param serializers SerializerProvider to store the handled serialized json
     *                    string
     * @throws IOException if an I/O error occurs during serialization.
     */
    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("email", user.getEmail());
        gen.writeStringField("password", user.getPassword());
        gen.writeBooleanField("loggedIn", user.isLoggedIn());
        gen.writeObjectField("bookShelf", user.getBookShelf());

        gen.writeEndObject();
    }

}
