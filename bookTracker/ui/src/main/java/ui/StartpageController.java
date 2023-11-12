package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.Book;
import core.BookShelf;
import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

/**
 * Controller connected to Startpage.fxml
 */
public class StartpageController {

    @FXML
    private HBox PopHBox;

    @FXML
    private HBox PulHBox;

    @FXML
    private Button profileButton;

    @FXML
    private Button shelfButton;

    @FXML
    private Button homePageButton;

    @FXML
    private Label usernameTag;

    private Book book;
    private RemoteDataAccess dataAccess;
    private User loggedInUser;

    private List<String> imageSrcPop = new ArrayList<>(
            Arrays.asList("gilmore", "heller", "kawaguchi", "mellors", "moshfegh", "rooney", "sittenfeld", "patchett",
                    "keane", "cauley", "sinclair", "verghese", "chambers", "kawakami", "rowley"));

    private List<String> imageSrcPul = new ArrayList<>(
            Arrays.asList("cowie", "diaz", "gage", "hsu", "kingslover", "olorunnipa",
                    "phillips", "cohen", "elliott", "eustace", "ferrer", "rembert",
                    "seuss"));

    /**
     * Sets up the Start Page by showing the book images
     */
    public void initialize() {
        dataAccess = new RemoteDataAccess();
        this.loggedInUser = dataAccess.getLoggedInUser();
        usernameTag.setText(loggedInUser.getUsername());

        for (String img : imageSrcPop) {
            ImageView imageView = new ImageView();
            Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/" + img + ".jpeg"));
            imageView.setImage(image);
            imageView.setX(170);
            imageView.setY(10);
            imageView.setFitHeight(160);
            imageView.setPreserveRatio(true);
            imageView.setId(img);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    handleImgClicked(imageView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            try {
                PopHBox.getChildren().add(imageView);
            } catch (Exception e) {
                System.out.println("test");
            }
        }

        for (String img : imageSrcPul) {
            ImageView imageView = new ImageView();
            Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/" + img + ".jpeg"));
            imageView.setImage(image);
            imageView.setX(170);
            imageView.setY(10);
            imageView.setFitHeight(160);
            imageView.setPreserveRatio(true);
            imageView.setId(img);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    handleImgClicked(imageView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            try {
                PulHBox.getChildren().add(imageView);
            } catch (Exception e) {
                System.out.println("test");
            }
        }
    }

    public void handleProfileButton(ActionEvent event) throws IOException {
        changeScene("/ui/ProfilePage.fxml", event);
    }

    public void handleShelfButton(ActionEvent event) throws IOException {
        changeScene("/ui/ShelfPage.fxml", event);
    }

    public void handleHomePageButton(ActionEvent event) throws IOException {
        changeScene("/ui/HomePage.fxml", event);
    }

    private void handleImgClicked(ImageView imageView) throws IOException {
        String bookId = imageView.getId();
        this.book = dataAccess.getBookById(bookId);
        displayBookPopup();
    }

    private void displayBookPopup() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Label title = new Label("Title: " + this.book.getTitle());
        Label author = new Label("Author: " + this.book.getAuthor());
        Label pages = new Label("Pages: " + this.book.getPages());
        Label description = new Label("Description: " + this.book.getDescription());

        Button addButton = new Button("Add book");
        addButton.setOnAction(e -> {
            try {
                addBookToShelf();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        Button doneButton = new Button("Done");
        doneButton.setOnAction(e -> {
            stage.close();
        });

        VBox labels = new VBox(10, title, author, pages, description);
        labels.setPadding(new Insets(10));

        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10));
        layout.add(title, 0, 0);
        layout.add(author, 0, 1);
        layout.add(pages, 0, 2);
        layout.add(description, 0, 3);
        layout.add(addButton, 0, 4);
        layout.add(doneButton, 0, 5);

        Scene scene = new Scene(layout, 500, 300);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void addBookToShelf() throws IOException {
        this.loggedInUser = dataAccess.getLoggedInUser();
        try {
            loggedInUser.getBookShelf().addBook(this.book);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Book successfully added");
            alert.setContentText("You can find all your added books under SHELF");
            alert.showAndWait();
            dataAccess.putUser(loggedInUser);
        } catch (IllegalStateException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Could not add book");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    // kunne legge bookShelf i users

    /**
     * Changes the scne to the given file path
     * 
     * @param filePath the fxml file to change to
     * @param event    the mouse click
     * @throws IOException if the file cannot be found
     */
    private void changeScene(String filePath, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(filePath));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
