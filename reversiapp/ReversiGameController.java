package reversiapp;

import core.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

    private ReversiBoardController reversiBoardController;
    private double mouseX;
    private double mouseY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // initialize the game
        Drawer drawer = new GUIDrawer(this);
        GameFlow gameFlow = new GameFlow(8, drawer);


        this.reversiBoardController = new ReversiBoardController(gameFlow.getBoard());
        this.reversiBoardController.setPrefWidth(400);
        this.reversiBoardController.setPrefHeight(400);
        root.getChildren().add(0, reversiBoardController);
        this.reversiBoardController.draw();

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 150;
            this.reversiBoardController.setPrefWidth(boardNewWidth);
            this.reversiBoardController.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            this.reversiBoardController.setPrefHeight(newValue.doubleValue());
            this.reversiBoardController.draw();
        });

        reversiBoardController.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
                gameFlow.runOneTurn();
            }
        });
    }

    public void draw() {
        this.currentPlayer.setText("BLACK");
        this.blackPlayerScore.setText("2");
        this.whitePlayerScore.setText("2");
        this.reversiBoardController.draw();
    }

    public double getMouseX() {
        return this.mouseX;
    }

    public double getMouseY() {
        return this.mouseY;
    }

    public ReversiBoardController getReversiBoardController() {
        return this.reversiBoardController;
    }
}
