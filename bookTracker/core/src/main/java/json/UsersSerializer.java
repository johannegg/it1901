package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.User;
import core.Users;

public class UsersSerializer extends JsonSerializer<Users> {

    @Override
    public void serialize(Users users, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart("users");
        for (User user : users) {
          gen.writeObject(user);
        }

        gen.writeEndArray();
        gen.writeEndObject();

    }

}
