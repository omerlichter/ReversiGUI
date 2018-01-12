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
    private Button settingsButton;


    @FXML
    protected void start() {
        Stage stage = (Stage)this.startButton.getScene().getWindow();
        try {
            HBox root = (HBox) FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            Scene scene = new Scene(root, 555, 430);
            scene.getStylesheets().add((getClass().getResource("application.css").toExternalForm()));
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
        Stage stage = (Stage)this.settingsButton.getScene().getWindow();
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Scene scene = new Scene(root, 555, 430);
            scene.getStylesheets().add((getClass().getResource("application.css").toExternalForm()));
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Reversi game/Settings");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
