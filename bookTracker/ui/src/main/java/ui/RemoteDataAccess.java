package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.Book;
import core.BookShelf;
import core.User;
import core.Users;
import json.UsersModule;

/**
 * Implements of the DataAccess interface for remote data access.
 * The class communicates with a remote API to retrieve and manipulate data.
 */
public class RemoteDataAccess implements DataAccess {

    private URI baseURI;
    private ObjectMapper objectMapper;

    /**
     * Construct a new instance with the default base URI, and
     * initiliazes the object mappwer with a custom module.
     */
    public RemoteDataAccess() {
        this.baseURI = URI.create("http://localhost:8080/api/");
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UsersModule());
    }

    /**
     * Method for getting a user by its username from the server.
     * 
     * @param username the username of the wanted user.
     * @throws IllegalArgumentException if user is not can not be found.
     */
    public User getUserByUsername(String username) {
        try {
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve("users/" + username))
                    .header("Accept", "application/json").GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new IllegalArgumentException("User is not found");
            }
            return objectMapper.readValue(response.body(), User.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "Could not getUserByUsername(" + username + "). Something wrong with the server.", e);
        }
    }

    /**
     * Gets the current logged-in user from the server.
     * 
     * @return the User object representing the logged-in user.
     * @throws IllegalArgumentException if logged-in user is not found.
     * @RuntimeException if sn HTTP error occurs during the request or if
     *                   there's an issue with the server.
     */
    public User getLoggedInUser() {
        try {
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve("users"))
                    .header("Accept", "application/json").GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new IllegalArgumentException("Logged in user is not found");
            } else if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get logged in user: HTTP error code: " + response.statusCode());
            }

            Users users = objectMapper.readValue(response.body(), Users.class);
            for (User user : users) {
                if (user.isLoggedIn()) {
                    return user;
                }
            }
            return null;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not getAllUsers(). Something went wrong with the server.", e);
        }
    }

    /**
     * Gets a list of all the users from the server.
     * 
     * @return the Users object representing the list of users.
     * @throws IllegalArgumentExcrption if the users are not found.
     * @throws RuntimeException         if if sn HTTP error occurs during the
     *                                  request or if
     *                                  there's an issue with the server.
     */
    public Users getUsers() {
        try {
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve("users"))
                    .header("Accept", "application/json").GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new IllegalArgumentException("Users not found");
            } else if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get users: HTTP error code: " + response.statusCode());
            }

            return objectMapper.readValue(response.body(), Users.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not getUsers(). Something went wrong with the server.", e);
        }
    }

    /**
     * Sendes a POST request to the server to create or update a user.
     * 
     * @param user the User object to be sent to the server.
     * @throws IllegalArgumentException if the server has an issue with the request.
     * @throws IllegalArgumentException if an error occurs during the process of
     *                                  sending the user object to the server.
     */
    public void postUser(User user) {
        try {
            String json = objectMapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve("users/" + user.getUsername()))
                    .header("Accept", "application/json").header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(json)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201) {
                throw new IllegalArgumentException("Could not send user object to server.");
            }
        } catch (IOException | InterruptedException e) {
            throw new IllegalArgumentException("Could not send user object to server..", e);
        }
    }

    /**
     * Sends a PUT request to the server to update an existing user.
     * 
     * @param user the User object containing the updated information to be sent to
     *             the server.
     * @throws IllegalArgumentException if the server has an issue with the request.
     * @throws IllegalArgumentException if an error occurs during the process of
     *                                  updating the user object to the server.
     */
    public void putUser(User user) {
        try {
            String json = objectMapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder(URI.create(baseURI + "users/" + user.getUsername()))
                    .header("Accept", "application/json").header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(json)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IllegalArgumentException("Could not update user object to server.");
            }
        } catch (IOException | InterruptedException e) {
            throw new IllegalArgumentException("Could not update user object to server.", e);
        }
    }

    /**
     * Gets a book from the server based on its book ID.
     * 
     * @param bookId the identifier of the book to be retrieved.
     * @return the Book object representing the book with the specified ID.
     * @IllegalArgumentException if the book with the gived ID is not found.
     * @IllegalArgumentException if an error occurs during the process of retrieving
     *                           the book from the server.
     */
    public Book getBookById(String bookId) {
        try {
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve("library/" + bookId))
                    .header("Accept", "application/json").GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new IllegalArgumentException("The book does not exist");
            }
            return objectMapper.readValue(response.body(), Book.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "Could not getBookById(" + bookId + "). Something wrong with the server.", e);
        }
    }

    /**
     * Gets the entire library from the server.
     * 
     * @return the BookShelf object representing library.
     * @throws IllegalArgumentException if the library is not found.
     * @throws RuntimeExcrption         if an error occurs during the request or if
     *                                  there's an issue with the server.
     */
    public BookShelf getLibrary() {
        try {
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve("library"))
                    .header("Accept", "application/json").GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new IllegalArgumentException("Library not found");
            } else if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to get library: HTTP error code: " + response.statusCode());
            }

            return objectMapper.readValue(response.body(), BookShelf.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not getLibrary(). Something wrong with the server.", e);
        }
    }

}