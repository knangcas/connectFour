package knangcas.connectFour.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import knangcas.connectFour.exception.ColumnFullException;
import knangcas.connectFour.model.ConnectBoard;

import java.util.ArrayList;
import java.util.List;

public class GameController {


    @FXML
    private GridPane c4board;

    @FXML
    private Label victoryLabel;

    @FXML
    private Pane victoryPane;

    @FXML
    private Button restartButton;

    @FXML
    private Button exitButton;

    @FXML
    private Circle playerTurnCircle;

    @FXML
    private Circle playerVictoryCircle;

    @FXML
    private Pane column1;

    @FXML
    private Pane column2;

    @FXML
    private Pane column3;

    @FXML
    private Pane column4;

    @FXML
    private Pane column5;

    @FXML
    private Pane column6;

    @FXML
    private Pane column7;

    private List<Pane> columns;


    private ConnectBoard gameBoard;

    private final Color PLAYER1COLOR = Color.web("#a30b00");
    private final Color PLAYER2COLOR = Color.web("#ffc64a");






    public void initialize() {
        columns = new ArrayList<>();
        columns.add(column1);
        columns.add(column2);
        columns.add(column3);
        columns.add(column4);
        columns.add(column5);
        columns.add(column6);
        columns.add(column7);



        gameBoard = new ConnectBoard();
        playerTurnCircle.setFill(PLAYER1COLOR);
        victoryPane.setVisible(false);

    }

    public void playerMove(MouseEvent mouseEvent) {
        int playerTurn = gameBoard.getPlayerTurn();
        Pane pane = (Pane) mouseEvent.getSource();
        int colNum = Integer.parseInt(pane.getId().substring(6));
        int position = -1;
        try {
            position = gameBoard.dropPiece(colNum);

        } catch (ColumnFullException e) {
            System.out.println("col full exception");
        }
        if (gameBoard.checkColumn(colNum)) {
            columns.get(colNum-1).setVisible(false);
        }


        dropPiece(colNum, position);

        if (gameBoard.validate()) {
            victoryLabel.setText("Player Wins!");
            if (gameBoard.getPlayerTurn() == 1) {
                playerVictoryCircle.setFill(PLAYER1COLOR);
            } else {
                playerVictoryCircle.setFill(PLAYER2COLOR);
            }
            playerTurnCircle.setFill(Color.GRAY);
            victoryPane.setVisible(true);

        } else {
            gameBoard.playerTurnChange();
            if (gameBoard.getPlayerTurn() == 1) {
                playerTurnCircle.setFill(PLAYER1COLOR);
            } else {
                playerTurnCircle.setFill(PLAYER2COLOR);
            }
        }


    }

    private void dropPiece(int column, int row) {
        int playerTurn = gameBoard.getPlayerTurn();

        StackPane sP = (StackPane) getStackPane(column-1, row);
        Circle circle = (Circle) sP.getChildren().get(0);
        if (playerTurn == 1) {
            circle.setFill(PLAYER1COLOR);
        } else if (playerTurn == 2) {
            circle.setFill(PLAYER2COLOR);
        }

        //gameBoard.displayBoard();


    }

    private void clearBoard() {
        StackPane sP;
        Circle circle;

        for (int i = 1; i < 43; i++) {
            sP = (StackPane) c4board.getChildren().get(i);
            circle = (Circle) sP.getChildren().get(0);
            circle.setFill(Color.web("#c9c9c9"));
        }
    }

    private Node getStackPane( int col, int row) {
        int x = col * 6 + row;
        return c4board.getChildren().get(x);
    }

    public void restartGame(ActionEvent actionEvent) {
        gameBoard = null;
        columns = null;
        initialize();
        resetPanes();
        clearBoard();
    }

    private void resetPanes() {
        for (Pane pane : columns) {
            pane.setVisible(true);
        }
    }

    public void exitGame(ActionEvent actionEvent) {
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
    }
}