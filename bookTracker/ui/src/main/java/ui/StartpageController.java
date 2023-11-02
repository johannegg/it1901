package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import json.LibraryPersistence;

/**
 * Controller connected to Startpage.fxml
 */
public class StartpageController {

    @FXML
    private HBox PopHBox;

    @FXML
    private HBox PulHBox;

    @FXML
    private Label ProfileButton;

    private LibraryPersistence libraryPersistence;
    private Book book;

    private List<String> imageSrcPop = new ArrayList<>(
            Arrays.asList("gilmore", "heller", "kawaguchi", "mellors", "moshfegh", "rooney", "sittenfeld", "patchett",
                    "keane", "cauley", "sinclair", "verghese", "chambers", "kawakami", "rowley"));

    private List<String> imageSrcPul = new ArrayList<>(
            Arrays.asList("cowie.jpg", "diaz.jpeg", "gage.jpeg", "hsu.jpeg", "kingslover.png", "olorunnipa.jpg",
                    "phillips.jpeg", "cohen.jpeg", "elliott.jpeg", "eustace.jpeg", "ferrer.jpeg", "rembert.jpeg",
                    "seuss.jpeg"));
   
    /**
     * Sets up the Start Page by showing the book images
     */
    public void initialize() {
        libraryPersistence = new LibraryPersistence();

        for (String img : imageSrcPop) {

            ImageView imageView = new ImageView();
            Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/" + img + ".jpg"));
            imageView.setImage(image);
            imageView.setX(170);
            imageView.setY(10);
            imageView.setFitWidth(110);
            imageView.setPreserveRatio(true);

            imageView.setId(img);
            book = new Book(img);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleImgClicked(imageView));

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

    public void handleProfileButton(MouseEvent event) throws IOException {
        changeScene("Profile.fxml", event);
    }

    private void handleImgClicked(ImageView imageView) {
        //sjekk med if setning om bok finnes
        String bookId = imageView.getId();
        displayBookPopup();
    }

    private void displayBookPopup() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Label title = new Label("Title:");
        Label author = new Label("Author:");

        Button addButton = new Button("Add book");
        addButton.setOnAction(e -> {
            System.out.println("Book added to shelf");
            //bok legges til i shelf
            

        });

        Button doneButton = new Button("Done");
        doneButton.setOnAction(e -> {
            stage.close();
        });

        VBox labels = new VBox(10, title, author);
        labels.setPadding(new Insets(10));
        
        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10));
        layout.add(title, 0, 0);
        layout.add(author, 0, 1);
        layout.add(addButton, 0, 2);
        layout.add(doneButton, 0, 3);

        Scene scene = new Scene(layout, 500, 300);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Changes the scne to the given file path
     * 
     * @param filePath the fxml file to change to
     * @param event    the mouse click
     * @throws IOException if the file cannot be found
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
