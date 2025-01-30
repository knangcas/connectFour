package knangcas.connectFour.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import knangcas.connectFour.exception.ColumnFullException;
import knangcas.connectFour.model.ConnectBoard;

public class HelloController {


    @FXML
    private GridPane c4board;

    @FXML
    private Label victoryLabel;

    @FXML
    private Pane victoryPane;

    private ConnectBoard gameBoard;




    public void initialize() {
        gameBoard = new ConnectBoard();
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

        if (position == -2) {
            victoryLabel.setText("Player " + gameBoard.getPlayerTurn() + " wins!");
            c4board.setVisible(false);
            victoryPane.setVisible(true);

        }

        dropPiece(colNum, position);


        /*if (gameBoard.validate()) {
            System.out.println(playerTurn + " wins");
        }*/



    }

    private void dropPiece(int column, int row) {
        int playerTurn = gameBoard.getPlayerTurn();

        StackPane sP = (StackPane) getStackPane(column-1, row);
        Circle circle = (Circle) sP.getChildren().get(0);
        if (playerTurn == 1) {
            circle.setFill(Color.RED);
        } else if (playerTurn == 2) {
            circle.setFill(Color.YELLOW);
        }

        //gameBoard.displayBoard();


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
}