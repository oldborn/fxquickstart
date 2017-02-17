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
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
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

    int width;
    int height;
    
    int[][] binaryMatrix;
    int[][] labelMatrix;
    
    
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
            
            width = (int) image.getWidth();
            height = (int) image.getHeight();
            
            binaryMatrix = new int[height][width];
            labelMatrix = new int[height][width];
            
            PixelReader pixelReader = image.getPixelReader();
            
            
            
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    labelMatrix[i][j] = 0;
                    if(pixelReader.getColor(j,i).equals(Color.BLACK)){
                        binaryMatrix[i][j] = 0;
                    }else{
                        binaryMatrix[i][j] = 1;
                    }
                }
            }
            
            for(int i = 0; i < width; i++){
                binaryMatrix[0][i] = 0;
                binaryMatrix[height-1][i] = 0;
            }
            
            for(int i = 0; i < height; i++){
                binaryMatrix[i][0] = 0;
                binaryMatrix[i][width-1] = 0;
            }
            
            int label = 0;
            for(int i = 0; i < height-1; i++){
                for(int j = 0; j < width-1; j++){
                    if(binaryMatrix[i][j] == 1){
                        if(binaryMatrix[i-1][j] == 0 && binaryMatrix[i][j-1] == 0){
                            label++;
                            labelMatrix[i][j] = label;
                        }else if(binaryMatrix[i-1][j] == 1 && binaryMatrix[i][j-1] == 0){
                            labelMatrix[i][j] = labelMatrix[i-1][j];
                        }else if(binaryMatrix[i-1][j] == 0 && binaryMatrix[i][j-1] == 1){
                            labelMatrix[i][j] = labelMatrix[i][j-1];
                        }else{
                            // u and c are 1.
                            if(labelMatrix[i-1][j] == labelMatrix[i][j-1]){
                                labelMatrix[i][j] = labelMatrix[i-1][j];
                            }else{
                                labelMatrix[i][j] = labelMatrix[i-1][j];
                                correctLabel(labelMatrix[i-1][j], labelMatrix[i][j-1]);
                            }
                        }
                    }
                }
            }
            ivMain.setImage(image);
            ivMain.setFitHeight(0);
            ivMain.setFitWidth(0);
            System.out.println(""+ivMain);
        }
    };

    private void correctLabel(int label1, int label2){
        int min = Math.min(label1, label2);
        int max = Math.max(label1, label2);
        
        // TODO
        
    }
    
}
