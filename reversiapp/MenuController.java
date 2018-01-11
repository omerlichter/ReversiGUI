package reversiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by Omer on 10/01/2018.
 */
public class MenuController {

    @FXML
    private Button startButton;


    @FXML
    protected void start() {
        Stage stage = (Stage)this.startButton.getScene().getWindow();
        try {
            HBox root = (HBox) FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            Scene scene = new Scene(root,550,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Reversi game");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    protected void settings() {

    }
}
