package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller connected to StartPage.fxml
 */
public class StartPageController {

    private List<String> imageSrcPop = new ArrayList<>(
            Arrays.asList("gilmore", "heller", "kawaguchi", "mellors", "moshfegh", "rooney"));

    private List<String> imageSrcPul = new ArrayList<>(
            Arrays.asList("cowie.jpg", "diaz.jpeg", "gage.jpeg", "hsu.jpeg", "kingslover.png", "olorunnipa.jpg", "phillips.jpeg"));

    @FXML
    private HBox PopHBox;

    @FXML
    private HBox PulHBox;

    @FXML
    private Label ProfileButton;

    public void handleProfileButton(MouseEvent event) throws IOException {
        changeScene("Profile.fxml", event);
    }
    
    /**
     * Sets up the Start Page by showing the book images
     */
    public void initialize() {

        for (String img : imageSrcPop) {
            ImageView imageView = new ImageView();
            Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/" + img + ".jpg"));
            
            imageView.setImage(image);
            imageView.setX(170);
            imageView.setY(10);
            imageView.setFitWidth(110);
            imageView.setPreserveRatio(true);

            try {
                PopHBox.getChildren().add(imageView);
            } catch (Exception e) {
                System.out.println("test");
            }
        }

        for (String img : imageSrcPul) {
            ImageView imageView = new ImageView();
            Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/" + img));
            
            imageView.setImage(image);
            imageView.setX(170);
            imageView.setY(10);
            imageView.setFitWidth(110);
            imageView.setPreserveRatio(true);

            try {
                PulHBox.getChildren().add(imageView);
            } catch (Exception e) {
                System.out.println("test");
            }
        }

    }

    /**
     * Changes the scne to the given file path
     * @param filePath      the fxml file to change to
     * @param event         the mouse click
     * @throws IOException  if the file cannot be found
     */
    private void changeScene(String filePath, MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(filePath));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
}
