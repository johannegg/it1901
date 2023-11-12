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

public class RemoteDataAccess implements DataAccess {
    
    private URI baseURI;
    private ObjectMapper objectMapper;

    public RemoteDataAccess() {
        this.baseURI = URI.create("http://localhost:8080/api/");
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UsersModule());
    }

    /**
     * Method for getting a user by its email from the server.
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
            return null; //TODO: Add exception

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not getAllUsers(). Something went wrong with the server.", e);
        }
    }

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

    public Book getBookById(String bookId){
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

    public BookShelf getLibrary(){
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