package json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Users;

/**
 * Persistence class for reading and writing to users.
 */
public class UsersPersistence {

    private File file = new File("../core/src/main/java/resources/users.json");
    private ObjectMapper objectMapper;

    //for test purposes
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Creates a new UsersPersistence instance and sets the objectMapper with a module for handling
     * serialization and deserialization of User and Users.
     */
    public UsersPersistence() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UsersModule());
        objectMapper.registerModule(new LibraryModule());
    }

    /**
     * Reads the Users from the users json file.
     * 
     * @return  the Users object read from the users json file.
     * @throws IOException  if an I/O error occurs during the reading process.
     */
    public Users readFromUsers() throws IOException {
        return objectMapper.readerFor(Users.class).readValue(file);
    }

    /**
     * Writes the Users to the users json file.
     * 
     * @param users  the Users object written to the users json file.
     * @throws IOException  if an I/O error occurs during the writing process.
     */
    public void writeToUsers(Users users) throws IOException {
        try (Writer writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, users);
        }
    }

}
