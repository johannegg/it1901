package json;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.User;

public class UserSerializer {

    public static void serialize(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String username = user.getUsername();
        objectMapper.writeValue(new File("bookTracker/core/src/main/java/resources/" + username + ".json"), user);
    }

}
