package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Users;

/**
 * Persistence class for reading and writing to users
 */
public class UsersPersistence {

    private File file = new File("../core/src/main/java/resources/users.json");
    private ObjectMapper objectMapper;

    /**
     * Creates a new UsersPersistence instance and sets the objectMapper
     */
    public UsersPersistence() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UsersModule());
        objectMapper.registerModule(new LibraryModule());
    }

    public Users readFromUsers() throws IOException {
        return objectMapper.readerFor(Users.class).readValue(file);
    }

    public void writeToUsers(Users users) throws IOException {
        try (Writer writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, users);
        }
    }

}
