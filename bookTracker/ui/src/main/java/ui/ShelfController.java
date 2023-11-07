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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import json.LibraryPersistence;

public class ShelfController {
    @FXML
    private Button profileButton;

    @FXML
    private Button shelfButton;

    @FXML
    private Button homePageButton;
    
    @FXML
    private ScrollBar scroll;
    
    @FXML
    private Pane pane;

    @FXML
    private ScrollPane scrollPane;

    private User user; 
    private TilePane shelfTilePane;
    private double lastX = 0;
    private LibraryPersistence libraryPersistence;
    


     public void initialize() {
        shelfTilePane = createShelfTilePane();
        scrollPane.setContent(shelfTilePane);

        shelfTilePane.setOnMousePressed(event -> lastX = event.getSceneX());
        shelfTilePane.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - lastX;
            double newX = shelfTilePane.getLayoutX() + deltaX;
            shelfTilePane.setLayoutX(newX);
            lastX = event.getSceneX();
        });

        try{
            addBookShelf(shelfTilePane);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private TilePane createShelfTilePane() {
        TilePane tilePane = new TilePane();
        tilePane.setVgap(70); // Adjust the vertical gap as needed
        tilePane.setHgap(50);
        tilePane.setPrefColumns(4); // Display 5 books in a row
        tilePane.setPrefWidth(1000); // Set the width of the TilePane

        return tilePane;

    }

    public void addBookShelf(TilePane tilePane) throws IOException{
        BookShelf bookShelf = libraryPersistence.readFromLibrary();
        for(Book book: bookShelf){
            Node bookInfoView = createBookInfoView(book);
            tilePane.getChildren().add(bookInfoView);
        }
    }

    private Node createBookInfoView(Book book){
        ImageView imageView = createBookImageView();
        Label titleLabel = new Label("Book Title");
        Label authorLabel = new Label("Author");

        titleLabel.setFont(new Font(16));
        titleLabel.setStyle("-fx-font-weight: bold"); 
        authorLabel.setFont(new Font(14));

        VBox labelContainer = new VBox();
        labelContainer.getChildren().addAll(titleLabel, authorLabel);
        labelContainer.setAlignment(Pos.CENTER);

        StackPane bookInfoView = new StackPane();
        bookInfoView.getChildren().addAll(imageView,labelContainer);

        Insets margin = new Insets(0,0,0,25);
        StackPane.setMargin(labelContainer, margin);

        return bookInfoView;
    }

    private ImageView createBookImageView() {
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/bookDefault.png"));
        imageView.setImage(image);
        imageView.setFitWidth(180); // Adjust the width as needed
        imageView.setPreserveRatio(true);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        imageView.setEffect(dropShadow);
        return imageView;
    }

    public void handleProfileButton(ActionEvent event) throws IOException {
        changeScene("/ui/Profile.fxml", event);
    }

    public void handleShelfButton(ActionEvent event) throws IOException {
        changeScene("/ui/ShelfPage.fxml", event);
    }

    public void handleHomePageButton(ActionEvent event) throws IOException {
        changeScene("/ui/Startpage.fxml", event);
    }

    /**
     * Changes the scene to the given file path
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

    //mangler evnen til å fjerene bok fra shelf. 
}
