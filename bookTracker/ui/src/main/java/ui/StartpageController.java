package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class StartpageController {
    
    private List<String> imageSrcPop = new ArrayList<>(Arrays.asList("gilmore", "heller", "kawaguchi", "mellors", "moshfegh", "rooney"));

    
    @FXML
    private HBox PopHBox;


    public void initialize() {

        for (String img : imageSrcPop) {
            ImageView imageView = new ImageView();
            //Image image = new Image("File:ui/src/main/resources/ui/BookImages/" + img + ".jpg");
            Image image = new Image(getClass().getResourceAsStream("/ui/BookImages/" + img + ".jpg"));
            imageView.setImage(image);

            PopHBox.getChildren().add(imageView);
        }
    }
    



    

    

}
