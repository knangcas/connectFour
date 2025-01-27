package knangcas.connect4.model;

import knangcas.connect4.exception.ColumnFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ConnectBoard {


    HashMap<Integer, Spot> boardHash;
    List<Stack<Integer>> columns;


    private int playerTurn;
    public ConnectBoard() {
        boardHash = new HashMap<>();
        columns = new ArrayList<>();

        for (int i = 0; i < 6; i ++) {
            columns.add(new Stack<Integer>());
        }

        for (int i = 1; i < 43; i++) {
            boardHash.put(i, new Spot(i));
        }

        playerTurn = 1;
    }


    public void playerMove(int playerNumber, int colNumber) {
        if (columns.get(colNumber-1).size() == 6) {
            throw new ColumnFullException("Current column is full");
        }

        columns.get(colNumber-1).push(playerNumber);
        boardHash.get(spotConversion(colNumber-1, columns.get(colNumber-1).size())).setIsOccupied(playerNumber);


    }

    private int spotConversion(int col, int spot) {
        return (7-spot) + (col * 6);

    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }




}
