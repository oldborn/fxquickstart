package edu.tr.mef.comp106.fxquickstart;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author topcus@mef.edu.tr
 */
public class MainPaneController implements Initializable {

    @FXML
    ImageView ivMain;

    @FXML
    Button btnSelectImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSelectImage.setOnAction(btnOnClick);
    }

    public EventHandler<ActionEvent> btnOnClick = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            FileChooser chooser = new FileChooser();
            chooser.setInitialDirectory(new File("src/edu/tr/mef/comp106/fxquickstart/images"));
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(new Stage());
            
            if(file == null) return;
            
            Image image = new Image(file.toURI().toString());
            
            ivMain.setImage(image);
            ivMain.setFitHeight(0);
            ivMain.setFitWidth(0);
            System.out.println(""+ivMain);
        }
    };

}
