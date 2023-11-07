package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.User;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("LogInPage.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void stop() {
        RemoteDataAccess dataAccess = new RemoteDataAccess();
        shutdown(dataAccess);
    }

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
