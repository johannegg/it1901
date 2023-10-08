package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.User;

public class UserSerializer extends JsonSerializer<User>{

    public static void serialize(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String username = user.getUsername();
        objectMapper.writeValue(new File("../core/src/main/java/resources/" + username + ".json"), user);
    }

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serialize'");
    }

}
