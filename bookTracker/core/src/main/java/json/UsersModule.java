package json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import core.User;

/**
 * Module for serializing and deserializing Users objects. Includes seralizers
 * for the classes Users and User.
 */
@SuppressWarnings("serial")
public class UsersModule extends SimpleModule {

  private static final String NAME = "UsersModule";

  /**
   * Constructor that registers all desired serializers and deserializers.
   */
  public UsersModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(User.class, new UserSerializer());
    addDeserializer(User.class, new UserDeserializer());
  }
}