package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.User;

/**
 * Class for the JavaFX application.
 * Initializes and starts the JavaFX app, setting up the primary stage and
 * scene.
 */
public class App extends Application {

    /**
     * Starts the JavaFX app by setting the initial scene.
     * Loads the login page as the first view.
     *
     * @param stage the primary stage for this app.
     * @throws IOException if there is an issue loading the FXML file for the login
     *                     page.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("LogInPage.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Stops the app and logs out the currently logged in user.
     */
    @Override
    public void stop() {
        RemoteDataAccess dataAccess = new RemoteDataAccess();
        shutdown(dataAccess);
    }

    /**
     * Handles the shutdown process, including logging out the
     * current user.
     *
     * @param dataAccess the data access object used to interact with the user data.
     */
    private void shutdown(RemoteDataAccess dataAccess) {
        if (dataAccess.getLoggedInUser() != null) {
            User user = dataAccess.getLoggedInUser();
            user.setLoggedIn(false);
            dataAccess.putUser(user);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
