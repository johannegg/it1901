package json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.User;
import core.Users;
    
@SuppressWarnings("serial")
public class UsersModule extends SimpleModule{

  private static final String NAME = "SalonModule";

  /**
   * Constructor that registers all desired serializers and deserializers.
   */
  public UsersModule() {
    super(NAME, Version.unknownVersion());

    // Add serializers and deserializers for objects related to Users
    addSerializer(Users.class, new UsersSerializer());
    addDeserializer(Users.class, new UsersDeserializer());
    addSerializer(User.class, new UserSerializer());
    addDeserializer(User.class, new UserDeserializer());
  }
}
