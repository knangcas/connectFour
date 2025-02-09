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
    private ConnectBoard gameBoard;

    private final Color PLAYER1COLOR = Color.web("#a30b00");
    private final Color PLAYER2COLOR = Color.web("#ffc64a");




    public void initialize() {
        gameBoard = new ConnectBoard();
        playerTurnCircle.setFill(PLAYER1COLOR);
        victoryPane.setVisible(false);

    }
    public void spotClick(MouseEvent mouseEvent) {

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
            //do an alert
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

        for (int i = 1; i < 44; i++) {
            sP = (StackPane) c4board.getChildren().get(i);
            circle = (Circle) sP.getChildren().get(0);
            circle.setFill(Color.WHITE);
        }
    }

    private Node getStackPane( int col, int row) {
        int x = col * 6 + row;
        return c4board.getChildren().get(x);
        /*for (Node node : c4board.getChildren()) {
            if (node != null) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return node;
                }
            }
        }*/
        //return null;
    }

    public void restartGame(ActionEvent actionEvent) {
        gameBoard = null;
        initialize();
        clearBoard();
    }

    public void exitGame(ActionEvent actionEvent) {
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
    }
}