package reversiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Omer on 11/01/2018.
 */
public class SettingsController implements Initializable {

    @FXML
    private ComboBox<String> startPlayerComboBox;

    @FXML
    private ComboBox<String> playerOColorComboBox;

    @FXML
    private ComboBox<String> playerXColorComboBox;

    @FXML
    private ComboBox<String> boardSizeComboBox;

    @FXML
    private Button backToMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // player start combo box settings
        this.startPlayerComboBox.getItems().add("O Player");
        this.startPlayerComboBox.getItems().add("X Player");

        // player o color combo box
        this.playerXColorComboBox.getItems().add("Black");
        this.playerXColorComboBox.getItems().add("White");
        this.playerXColorComboBox.getItems().add("Orange");
        this.playerXColorComboBox.getItems().add("Yellow");
        this.playerXColorComboBox.getItems().add("Red");
        this.playerXColorComboBox.getItems().add("Green");
        this.playerXColorComboBox.getItems().add("Blue");

        // player x color combo box
        this.playerOColorComboBox.getItems().add("Black");
        this.playerOColorComboBox.getItems().add("White");
        this.playerOColorComboBox.getItems().add("Orange");
        this.playerOColorComboBox.getItems().add("Yellow");
        this.playerOColorComboBox.getItems().add("Red");
        this.playerOColorComboBox.getItems().add("Green");
        this.playerOColorComboBox.getItems().add("Blue");


        for (int i = 4; i <= 20; i++) {
            this.boardSizeComboBox.getItems().add(i + " X " + i);
        }

        // read the values from the settings file
        File settingsFile = new File("Settings.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(settingsFile)));
            String startPlayer = bufferedReader.readLine();
            startPlayer = startPlayer + " Player";
            this.startPlayerComboBox.setValue(startPlayer);
            String OColor = bufferedReader.readLine();
            this.playerOColorComboBox.setValue(OColor);
            String XColor = bufferedReader.readLine();
            this.playerXColorComboBox.setValue(XColor);
            String boardSize = bufferedReader.readLine();
            boardSize = boardSize + " X " + boardSize;
            this.boardSizeComboBox.setValue(boardSize);
            bufferedReader.close();
        } catch (Exception e) {
            // default values
            this.startPlayerComboBox.setValue("O Player");
            this.playerOColorComboBox.setValue("White");
            this.playerXColorComboBox.setValue("Black");
            this.boardSizeComboBox.setValue("8 X 8");
        }
    }

    /**
     * save to file
     */
    @FXML
    public void save() {
        File settingsFile = new File("Settings.txt");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(settingsFile)));
            printWriter.println(this.startPlayerComboBox.getValue().charAt(0));
            printWriter.println(this.playerOColorComboBox.getValue());
            printWriter.println(this.playerXColorComboBox.getValue());
            printWriter.println(this.boardSizeComboBox.getValue().toString().split(" ")[0]);
        } catch (IOException e) {

        } finally {
            printWriter.close();
        }
    }

    @FXML
    public void backToMenu() {
        Stage stage = (Stage)this.backToMenu.getScene().getWindow();
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));
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
}
