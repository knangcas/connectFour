package knangcas.connectFour.model;

import knangcas.connectFour.exception.ColumnFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ConnectBoard {


    HashMap<Integer, Spot> boardHash;
    List<Stack<Integer>> columns;

    private int lastSpot;


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
        lastSpot = spotConversion(colNumber-1, columns.get(colNumber-1).size());
        columns.get(colNumber-1).push(playerNumber);
        boardHash.get(lastSpot).setIsOccupied(playerNumber);

        checkVictory();
        playerTurnChange();


    }

    private int getRowFromSpot(int coordinate) {
        if (coordinate % 6 > 0) {
            return (coordinate / 6);
        }
        return (coordinate / 6) - 1;
    }

    private boolean checkVictory() {
        //upper difference = 5;
        //lower difference = 7;

        //check vertically
        if (lastSpot - (getRowFromSpot(lastSpot) * 6) <=3) {
            checkVertically();
        }

        //TODO check horizontally;
        //TODO check diagonally down right/up left (7 difference)
        //TODO check diagonally down left/up right (5 difference)


        return false;

    }

    private boolean checkVertically() {
        int result = 0;
        for (int i = lastSpot+1; i < lastSpot+4; i++) {
            if (boardHash.get(i).getIsOccupied() == playerTurn) {
                result++;
            }
        }
        return result == 4;
    }

    private void playerTurnChange() {
        if (playerTurn == 1) {
            playerTurn = 2;
        } else {
            playerTurn = 1;
        }
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
