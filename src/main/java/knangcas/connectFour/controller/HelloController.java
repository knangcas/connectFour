package knangcas.connectFour.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import knangcas.connectFour.model.ConnectBoard;

public class HelloController {


    @FXML
    private GridPane c4board;

    private ConnectBoard gameBoard;




    public void initialize() {
        gameBoard = new ConnectBoard();

    }
    public void spotClick(MouseEvent mouseEvent) {

    }

    public void playerMove(MouseEvent mouseEvent) {
        int playerTurn = gameBoard.getPlayerTurn();
        Pane pane = (Pane) mouseEvent.getSource();
        int colNum = Integer.parseInt(pane.getId().substring(5));
        int rowNum = dropPiece(colNum);


    }

    private int dropPiece(int column) {
        int playerTurn = gameBoard.getPlayerTurn();
        for (int i = 5; i >= 0; i++) {
            StackPane sP = (StackPane) getStackPane(column-1, i);
            Circle circle = (Circle) sP.getChildren().get(0);
            if (!circle.getFill().equals(Color.WHITE)) {
                if (playerTurn == 1) {
                    circle.setFill(Color.YELLOW);
                    return i;
                } else if (playerTurn == 2) {
                    circle.setFill(Color.RED);
                    return i;
                }


            }
        }
        return -1;
    }

    private Node getStackPane( int col, int row) {
        for (Node node : c4board.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}