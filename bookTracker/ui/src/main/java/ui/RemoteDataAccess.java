package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.User;
import json.UsersModule;

public class RemoteDataAccess {
    private URI baseURI;
    private ObjectMapper objectMapper;

    public RemoteDataAccess() {
        this.baseURI = URI.create("http://localhost:8080/users/");
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UsersModule());
    }

    /**
     * Method for getting a user by its email from the server.
     */

    public User getUserByUsername(String username) {
        try {
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve(username))
                    .header("Accept", "application/json").GET().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new IllegalArgumentException("Brukeren eksisterer ikke");
            }
            return objectMapper.readValue(response.body(), User.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Kunne ikke getUserByUsername(" + username + "). Feil med server.", e);
        }
    }

    public void postUser(User user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new UsersModule());
            String json = objectMapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder(baseURI.resolve(user.getUsername()))
                    .header("Accept", "application/json").header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(json)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201) {
                throw new IllegalArgumentException("Kunne ikke sende bruker-objekt til server.");
            }
        } catch (IOException | InterruptedException e) {
            throw new IllegalArgumentException("Kunne ikke sende bruker-objekt til server.");
        }
    }

}
