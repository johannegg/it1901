package ui;

//import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

import core.FileHandler;

public class AppController {
    public AppController() {

    }

    @FXML
    private TextField titleField;

    @FXML
    private Button addBtn;

    @FXML
    private ListView<String> listView;

    FileHandler fileHandler = new FileHandler();

    @FXML
    public void initialize() {
        listView.getItems().addAll(getBookList());
        // Platform.runLater(() -> {
        //     listView.getItems().addAll(getBookList());
        // });
    }

    public List<String> getBookList() {
        return fileHandler.readFromFile("/Users/johannegronliengjedrem/itp/gr2323/bookTracker/core/src/main/java/core/bookTitles.txt");
    }

    @FXML
    public void handleAddBtn() {
        String title = titleField.getText();
        try {
            if (!title.isEmpty()) {
                fileHandler.writeToFile(title, "/Users/johannegronliengjedrem/itp/gr2323/bookTracker/core/src/main/java/core/bookTitles.txt");
                titleField.clear();
                listView.getItems().clear();
                listView.getItems().addAll(getBookList());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
