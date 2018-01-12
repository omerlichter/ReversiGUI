package reversiapp;

import core.*;
import core.Cell;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {

    @FXML
    private HBox root;

    @FXML
    private Text currentPlayer;

    @FXML
    private Text blackPlayerScore;

    @FXML
    private Text whitePlayerScore;

    @FXML
    private Text message;

    @FXML
    private Button backToMenuButton;

    private ReversiBoardController reversiBoardController;
    private double mouseX;
    private double mouseY;
    private Cell curentPlayerVal;
    private String messageVal;

    private Color blackPlayerColor;
    private Color whitePlayerColor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // read the settings from the file
        GameFlow gameFlow = createGameFromFile();

        this.curentPlayerVal = Cell.BLACK;
        this.messageVal = "";

        // create the gui board
        this.reversiBoardController = new ReversiBoardController(gameFlow.getBoard());
        this.reversiBoardController.setPrefWidth(400);
        this.reversiBoardController.setPrefHeight(400);
        root.getChildren().add(0, reversiBoardController);
        this.reversiBoardController.draw();

        // listeners...
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 200;
            this.reversiBoardController.setPrefWidth(boardNewWidth);
            this.reversiBoardController.draw();
        });

        // listeners...
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            this.reversiBoardController.setPrefHeight(newValue.doubleValue() - 40);
            this.reversiBoardController.draw();
        });

        // listeners...
        reversiBoardController.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
                gameFlow.runOneTurn();
            }
        });

        // set players color
        this.reversiBoardController.setPlayerOColor(this.whitePlayerColor);
        this.reversiBoardController.setPlayerXColor(this.blackPlayerColor);

        // initialize the game
        gameFlow.initializeGame();
    }

    /**
     * draw the game
     */
    public void draw() {
        switch (this.curentPlayerVal) {
            case BLACK:
                this.currentPlayer.setText("BLACK");
                break;
            case WHITE:
                this.currentPlayer.setText("WHITE");
                break;
        }
        this.blackPlayerScore.setText("" + this.reversiBoardController.getBoard().getNumOfBlackCells());
        this.whitePlayerScore.setText("" + this.reversiBoardController.getBoard().getNumOFWhiteCells());
        this.reversiBoardController.draw();
        this.message.setText(this.messageVal);
        this.messageVal = "";
    }

    /**
     * get function
     * @return mouse X pos
     */
    public double getMouseX() {
        return this.mouseX;
    }

    /**
     * get function
     * @return mouse Y pos
     */
    public double getMouseY() {
        return this.mouseY;
    }

    /**
     * get function
     * @return the reversi board controller
     */
    public ReversiBoardController getReversiBoardController() {
        return this.reversiBoardController;
    }

    /**
     * set function
     * @param currentPlayer the current player color
     */
    public void setCurrentPlayer(Cell currentPlayer) {
        this.curentPlayerVal = currentPlayer;
    }

    /**
     * set function
     * @param message message
     */
    public void setMessage(String message) {
        this.messageVal = message;
    }

    public void backToMenu() {
        Stage stage = (Stage)this.backToMenuButton.getScene().getWindow();
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

    public void endGame(String playerWin) {
        try {
            StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("EndGame.fxml"));
            Label label = (Label) root.getChildren().get(0);
            label.setText(playerWin);
            Stage stage = new Stage();
            stage.setTitle("End Game!");
            Scene scene = new Scene(root, 250, 150);
            scene.getStylesheets().add((getClass().getResource("application.css").toExternalForm()));
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create game flow from file
     * @return the game flow
     */
    private GameFlow createGameFromFile() {
        int boardSize = 8;
        boolean playerOFirst = true;

        // read the values from the settings file
        File settingsFile = new File("Settings.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(settingsFile)));
            String startPlayer = bufferedReader.readLine();
            if (startPlayer.equals("X")) {
                playerOFirst = false;
            }
            String OColor = bufferedReader.readLine();
            this.whitePlayerColor = Color.valueOf(OColor);
            String XColor = bufferedReader.readLine();
            this.blackPlayerColor = Color.valueOf(XColor);
            String boardSizeString = bufferedReader.readLine();
            boardSize = Integer.parseInt(boardSizeString);
            bufferedReader.close();
        } catch (Exception e) {
            // default values
            this.blackPlayerColor = Color.BLACK;
            this.whitePlayerColor = Color.WHITE;
        }

        // initialize the game
        Drawer drawer = new GUIDrawer(this);
        Player firstPlayer;
        Player secondPlayer;
        if (playerOFirst) {
            firstPlayer = new LocalPlayer(drawer, Cell.WHITE);
            secondPlayer = new LocalPlayer(drawer, Cell.BLACK);
        } else {
            firstPlayer = new LocalPlayer(drawer, Cell.BLACK);
            secondPlayer = new LocalPlayer(drawer, Cell.WHITE);
        }
        // create the game flow
        GameFlow gameFlow = new GameFlow(boardSize, drawer, firstPlayer, secondPlayer);
        return gameFlow;
    }
}
