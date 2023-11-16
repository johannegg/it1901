package json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.User;
import core.Users;

/**
 * Jackson module for deserializing and serializing User and Users objects.
 * The module provides serializers and deserializers for the classes User and
 * Users.
 */
@SuppressWarnings("serial")
public class UsersModule extends SimpleModule {

  private static final String NAME = "SalonModule";

  /**
   * Constructor that registers all desired serializers and deserializers.
   */
  public UsersModule() {
    super(NAME, Version.unknownVersion());

    addSerializer(Users.class, new UsersSerializer());
    addDeserializer(Users.class, new UsersDeserializer());
    addSerializer(User.class, new UserSerializer());
    addDeserializer(User.class, new UserDeserializer());
  }
}
