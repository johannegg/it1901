package ui;

import java.io.IOException;

import core.Book;
import core.BookShelf;
import core.User;
import javafx.scene.effect.DropShadow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the StartPage.fxml.
 * Manages the interactions on the shelf page.
 */
public class ShelfController extends DataAccessController {

    private String bookId;
    private Book book;
    private User loggedInUser;
    private static boolean TestDataAccess = false;

    @FXML
    private Button profileButton;

    @FXML
    private Button shelfButton;

    @FXML
    private Button homePageButton;

    @FXML
    private Pane pane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane shelfTilepane;

    @FXML
    private Label usernameTag;

    /**
     * Sets up the shelf page by displaying the books that are in the user's
     * bookshelf.
     * 
     * @throws IOException If an I/O error occurs while handling the initialization
     *                     process.
     */
    public void initialize() throws IOException {
        if (TestDataAccess == true) {
            this.setDataAccess(new DirectDataAccess());
        }
        this.loggedInUser = this.getDataAccess().getLoggedInUser();
        usernameTag.setText(this.loggedInUser.getUsername());

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        shelfTilepane.setVgap(80);
        shelfTilepane.setHgap(30);

        try {
            addBookShelf(shelfTilepane);
        } catch (IOException e) {
            e.printStackTrace();
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
     * Creates a graphical representation of a book button with associated labels
     * for title and author.
     * The button displays the book's cover image and triggers an action when
     * clicked.
     *
     * @param book The Book object for which the button is created.
     * @return A Node representing the book button with title and author labels.
     */
    private Node createBookButton(Book book) {
        GridPane outerGrid = new GridPane();
        Button button = new Button();
        Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/bookDefault.png"));

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);

        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();
        button.setPrefWidth(imageWidth);
        button.setPrefHeight(imageHeight);

        button.setOnAction(event -> {
            try {
                handleImgClicked(button);
                System.out.println("clicked");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button.hoverProperty().addListener((observable, oldValue, isHovered) -> {
            if (isHovered) {
                button.setCursor(Cursor.HAND);
                button.setStyle("-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 1);");
            } else {
                button.setCursor(Cursor.DEFAULT);
                button.setStyle("-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0), 0, 0, 0, 0);");
            }
        });

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        button.setEffect(dropShadow);

        button.setId(book.getBookId());

        Label titleLabel = new Label(book.getTitle());
        Label authorLabel = new Label(book.getAuthor());

        Font customTitle = Font.font("Arial", FontWeight.BOLD, 16);
        Font customAuthor = Font.font("Arial", 14);

        titleLabel.setFont(customTitle);
        authorLabel.setFont(customAuthor);

        GridPane labelGrid = new GridPane();
        labelGrid.add(titleLabel, 0, 0);
        labelGrid.add(authorLabel, 0, 1);
        labelGrid.setAlignment(Pos.CENTER);

        outerGrid.add(button, 0, 0);
        outerGrid.add(labelGrid, 0, 1);

        return outerGrid;

    }

    /**
     * Adds the the logged in user's bookshelf so it can be displayed in shelf page.
     * 
     * @throws IOException if an I/O error occurs during the process of adding the
     *                     user's bookshelf to the shelf page.
     */
    public void addBookShelf(TilePane tilePane) throws IOException {
        BookShelf bookShelf = this.loggedInUser.getBookShelf();
        System.out.println(bookShelf);
        for (Book book : bookShelf) {
            this.book = book;
            Node bookButton = createBookButton(book);
            shelfTilepane.getChildren().add(bookButton);
        }
    }

    /**
     * Handles the event when a button representing a book cover is clicked.
     * 
     * @param button the button clicked on, representing a book cover.
     * @throws IOException if an I/O error occurs during the process of handling the
     *                     click event.
     */
    private void handleImgClicked(Button button) throws IOException {
        this.bookId = button.getId();
        this.book = this.getDataAccess().getBookById(bookId);
        displayBookPopup();
    }

    /**
     * Displays a popup with information about the selected book,
     * with the ability to remove the book from shelf.
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

        Button removeButton = new Button("Remove book");
        removeButton.setId("removeBook");
        removeButton.setOnAction(e -> {
            System.out.println("Book removed from shelf");
            try {
                removeBookFromShelf();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        Button doneButton = new Button("Done");
        doneButton.setId("doneBtn");
        doneButton.setOnAction(e -> {
            stage.close();
            shelfTilepane.getChildren().clear();
            try {
                initialize();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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
        layout.add(removeButton, 0, 4);
        layout.add(doneButton, 0, 5);

        stage.setOnCloseRequest(event -> {
            shelfTilepane.getChildren().clear();
            try {
                initialize();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Scene scene = new Scene(layout, 500, 300);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Removes the currently selected book from the users's shelf.
     * 
     * @throws IOException if an I/O error occurs during the process of removing the
     *                     book from user's shelf.
     */
    public void removeBookFromShelf() throws IOException {
        User newUser = this.loggedInUser;
        newUser.getBookShelf().removeBookById(book.getBookId());
        this.getDataAccess().putUser(newUser);
        System.out.println(book.getTitle() + " removed from shelf");
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