package json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.Book;
import core.BookShelf;

/**
 * Jackson module for deserializing and serializing Book and BookShelf objects.
 * The module provides serializers and deserializers for the classes Book and
 * BookShelf.
 */
@SuppressWarnings("serial")
public class LibraryModule extends SimpleModule {

  private static final String NAME = "UsersModule";

  /**
   * Constructor that registers all desired serializers and deserializers.
   */
  public LibraryModule() {
    super(NAME, Version.unknownVersion());

    // Add serializers and deserializers for objects related to Users
    addDeserializer(Book.class, new BookDeserializer());
    addDeserializer(BookShelf.class, new BookShelfDeserializer());
    addSerializer(Book.class, new BookSerializer());
    addSerializer(BookShelf.class, new BookShelfSerializer());

  }
}
