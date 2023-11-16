package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.User;
import core.Users;

/**
 * Serializer for Users objects.
 * Converts Users objects to JSON string.
 */
public class UsersSerializer extends JsonSerializer<Users> {

  /**
   * Writes a specified instance of User object as a JSON string to a
   * Json-generator.
   * 
   * @param users       Users object to convert to JSON string.
   * @param gen         assigned generator to use during serialization.
   * @param serializers SerializerProvider to store the handled serialized JSON
   *                    string.
   * @throws IOException if an I/O error occurs during serialization.
   */
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
