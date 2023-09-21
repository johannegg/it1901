package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppController {
    public AppController() {

    }

    @FXML
    private TextField titleField;

    @FXML
    private Button addBtn;

    @FXML
    private ListView<String> listView;

    @FXML
    public void initialize() {
        System.out.println("here");
    }

    @FXML
    public void handleAddBtn(){
        
    }

}
