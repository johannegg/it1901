package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import core.Book;
import core.BookShelf;
import core.User;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
 * Manages the interactions on the homepage.
 */
public class StartpageController extends DataAccessController {

    private Book book;
    private User loggedInUser;
    private static boolean TestDataAccess = false;

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

    @FXML
    private TextField searchBar;

    @FXML
    Button searchBtn;

    @FXML
    private ListView<String> listView;

    private HashMap<String, String> bookIds;
    private BookShelf library;

    private List<String> imageSrcPop = new ArrayList<>(
            Arrays.asList("gilmore", "heller", "kawaguchi", "mellors", "moshfegh", "rooney", "sittenfeld", "patchett",
                    "keane", "cauley", "sinclair", "verghese", "chambers", "kawakami", "rowley"));

    private List<String> imageSrcPul = new ArrayList<>(
            Arrays.asList("cowie", "diaz", "gage", "hsu", "kingslover", "olorunnipa",
                    "phillips", "cohen", "elliott", "eustace", "ferrer", "rembert",
                    "seuss"));

    /**
     * Sets up the homepage by handling mouse events, and showing the images of the
     * book covers.
     * 
     * @throws IOException If an I/O error occurs while handling the initialization
     *                     process.
     */
    public void initialize() throws IOException {
        if (TestDataAccess == true) {
            this.setDataAccess(new DirectDataAccess());
        }
        this.loggedInUser = this.getDataAccess().getLoggedInUser();
        usernameTag.setText(loggedInUser.getUsername());
        this.library = this.getDataAccess().getLibrary();

        listView.setOnMouseClicked((MouseEvent event) -> {
            String selectedBook = listView.getSelectionModel().getSelectedItem();
            String bookId = bookIds.get(selectedBook);
            if (bookId != null) {
                try {
                    handleListClicked(bookId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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

            imageView.hoverProperty().addListener((observable, oldValue, isHovered) -> {
                if (isHovered) {
                    imageView.setCursor(Cursor.HAND);
                    imageView.setStyle(
                            "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); -fx-border-color: black; -fx-border-width: 3px;");
                } else {
                    imageView.setCursor(Cursor.DEFAULT);
                    imageView.setStyle("");
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

            imageView.hoverProperty().addListener((observable, oldValue, isHovered) -> {
                if (isHovered) {
                    imageView.setCursor(Cursor.HAND);
                    imageView.setStyle(
                            "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); -fx-border-color: black; -fx-border-width: 3px;");
                } else {
                    imageView.setCursor(Cursor.DEFAULT);
                    imageView.setStyle("");
                }
            });

            try {
                PulHBox.getChildren().add(imageView);
            } catch (Exception e) {
                System.out.println("test");
            }
        }
    }

    /**
     * Sets the test data access mode, and controlls whether the application uses
     * test data.
     * 
     * @param bool true to enable test data access, false otherwise.
     */
    public static void setTestDataAccess(boolean bool) {
        TestDataAccess = bool;
    }

    /**
     * Handles the activites related to the search button by looking through the
     * library
     * for books that match the search text
     * 
     * @param event the ActionEvent triggered by the search button.
     */
    public void handleSearchButton(ActionEvent event) {
        String searchText = searchBar.getText().toLowerCase();

        List<String> bookList = new ArrayList<>();
        bookIds = new HashMap<>();
        Boolean bookFound = false;

        for (Book book : library.getBooks()) {
            if (book.getTitle().toLowerCase().contains(searchText)) {
                String textDisplay = book.getTitle() + " - " + book.getAuthor();
                bookList.add(textDisplay);
                bookIds.put(textDisplay, book.getBookId());
                bookFound = true;
            }
        }

        if (!bookFound) {
            String textDisplay = "Book not found";
            bookList.add(textDisplay);
        }
        listView.getItems().clear();
        listView.getItems().addAll(bookList);
    }

    /**
     * Handles the book that is cliccked on in the list after using the search
     * field.
     * Showcases a pop up with book information if book exists.
     * 
     * @param bookId the ID of the book that is clicked on.
     * @throws IOException if an I/O error occurs while retrieving book information.
     */
    private void handleListClicked(String bookId) throws IOException {
        this.book = this.getDataAccess().getBookById(bookId);
        displayBookPopup();
    }

    /**
     * Handles the action when profile button is clicked on by switching to the
     * profile page.
     * 
     * @param event ActionEvent triggered by the profile button.
     * @throws IOException if an I/O error occurs during the scene change to the
     *                     profile page.
     */
    public void handleProfileButton(ActionEvent event) throws IOException {
        changeScene("/ui/ProfilePage.fxml", event);
    }

    /**
     * Handles the action when shelf button is clicked on by switching to the shelf
     * page.
     * 
     * @param event ActionEvent triggered by the shelf button.
     * @throws IOException if an I/O error occurs during the scene change to the
     *                     shelf page.
     */
    public void handleShelfButton(ActionEvent event) throws IOException {
        changeScene("/ui/ShelfPage.fxml", event);
    }

    /**
     * Handles the action when homepage button is clicked on by switching to the
     * homepage.
     * 
     * @param event ActionEvent triggered by the homepage button.
     * @throws IOException if an I/O error occurs during the scene change to the
     *                     homepage.
     */
    public void handleHomePageButton(ActionEvent event) throws IOException {
        changeScene("/ui/Startpage.fxml", event);
    }

    /**
     * Handles the event when an ImageView representing a book cover is clicked.
     * 
     * @param imageView the ImageView clicked, representing a book cover.
     * @throws IOException if an I/O error occurs during the process of handling the
     *                     click event.
     */
    private void handleImgClicked(ImageView imageView) throws IOException {
        String bookId = imageView.getId();
        this.book = this.getDataAccess().getBookById(bookId);
        displayBookPopup();
    }

    /**
     * Displays a popup with information about the selected book,
     * with the ability to add book to shelf.
     * 
     * @throws IOException if an I/O error occurs during the process of dispalying a
     *                     book popup.
     */
    private void displayBookPopup() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Label title = new Label("Title: " + this.book.getTitle());
        Label author = new Label("Author: " + this.book.getAuthor());
        Label pages = new Label("Pages: " + this.book.getPages());
        Label description = new Label("Description: " + this.book.getDescription());
        description.setWrapText(true);

        Button addButton = new Button("Add book");
        addButton.setId("addBook");
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

    /**
     * Adds the currently selected book to the users's shelf.
     * 
     * @throws IOException if an I/O error occurs during the process of adding the
     *                     book to user's shelf.
     */
    public void addBookToShelf() throws IOException {
        this.loggedInUser = this.getDataAccess().getLoggedInUser();
        addBookToUser();
        this.getDataAccess().putUser(loggedInUser);
    }

    /**
     * Adds the current book to the user's bookshelf.
     * Displays an informational alert on whether the book was added or not.
     */
    private void addBookToUser() {
        try {
            this.loggedInUser.getBookShelf().addBook(this.book);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Book successfully added");
            alert.setContentText("You can find all your added books under SHELF");
            alert.showAndWait();
        } catch (IllegalStateException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Could not add book");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Changes the scene to the given file path.
     * 
     * @param filePath the fxml file to change to.
     * @param event    the mouse click.
     * @throws IOException if the file cannot be found.
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