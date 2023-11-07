package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.User;

public class UserSerializer extends JsonSerializer<User> {

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
