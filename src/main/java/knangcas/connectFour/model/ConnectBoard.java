package knangcas.connectFour.model;

import knangcas.connectFour.exception.ColumnFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ConnectBoard {


    HashMap<Integer, Spot> boardHash;
    List<Stack<Integer>> columns;

    List<List<Integer>> northwestDiagonals;
    List<List<Integer>> northeastDiagonals;

    private int lastSpot;


    private int playerTurn;
    public ConnectBoard() {
        //instantiate data structures
        boardHash = new HashMap<>();
        columns = new ArrayList<>();
        northeastDiagonals = new ArrayList<>();
        northwestDiagonals = new ArrayList<>();

        //populate data structures
        //hardcoding this feels bad because it isn't scalable, but connect4 has defined constraints.
        for (int i = 0; i < 6; i ++) {
            columns.add(new Stack<Integer>());
        }

        for (int i = 1; i < 43; i++) {
            boardHash.put(i, new Spot(i));
        }

        populateDiagonals();
        playerTurn = 1;
    }

    //hardcoding the board. not good practice, but it works.
    private void populateDiagonals() {
        for (int i = 0; i < 6; i++) {
            northeastDiagonals.add(new ArrayList<>());
            northwestDiagonals.add(new ArrayList<>());
        }

        int startValue = 4;
        int startValue2 = 12;
        int startValueHelper = 5;

        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                for (int j = startValue; j <= ((startValue - 1) * 5 + startValue); j += 5) {
                    northeastDiagonals.get(i).add(j);
                }
                startValue++;
            } else {
                for (int j = startValue2; j <= (startValueHelper * 5) + startValue2; j += 5) {
                    northeastDiagonals.get(i).add(j);
                }
                startValue2 += 6;
                startValueHelper--;
            }
        }

        startValue = 40;
        startValue2 = 36;
        startValueHelper = 5;
        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                for (int j = startValue; j >= (startValue - ((startValue - 37) * 7)); j -= 7) {
                    northwestDiagonals.get(i).add(j);
                }
                startValue++;
            } else {
                for (int j = startValue2; j >= (startValue2 - (startValueHelper * 7)); j -= 7) {
                    northwestDiagonals.get(i).add(j);
                }
                startValue2 -= 6;
                startValueHelper--;
            }

        }
    }




    public boolean playerMove(int playerNumber, int colNumber) {
        if (columns.get(colNumber-1).size() == 6) {
            throw new ColumnFullException("Current column is full");
        }
        lastSpot = spotConversion(colNumber-1, columns.get(colNumber-1).size());
        columns.get(colNumber-1).push(playerNumber);
        boardHash.get(lastSpot).setIsOccupied(playerNumber);

        if(checkVictory()) {
            return true;
        }

        playerTurnChange();
        return false;
    }

    private int getColFromSpot(int coordinate) {
        if (coordinate % 6 > 0) {
            return (coordinate / 6);
        }
        return (coordinate / 6) - 1;
    }

    private boolean checkVictory() {
        if (lastSpot - (getColFromSpot(lastSpot) * 6) <=3) {
            if (checkVertically()) {
                return true;
            }
        }

        if (checkHorizontally()) {
            return true;
        }

        return checkDiagonals();

    }

    private boolean checkDiagonals() {
        if (checkNortheast()) {
            return true;
        }
        if (checkNorthwest()) {
            return true;
        }
        return false;
    }


    private boolean checkNorthwest() {
        int row = lastSpot - (6 * getColFromSpot(lastSpot));
        int col = getColFromSpot(lastSpot);
        int result = 0;

        //hard coding feels awful.
        if (lastSpot < 4 || lastSpot == 7 || lastSpot == 8 || lastSpot ==13 || lastSpot > 39 || lastSpot == 35 || lastSpot == 30 || lastSpot == 36) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (northwestDiagonals.get(i).contains(lastSpot)) {
                return validateDiagonal(northwestDiagonals.get(i));
            }
        }
        return false;
    }

    private boolean checkNortheast() {
        int row = lastSpot - (6 * getColFromSpot(lastSpot));
        int col = getColFromSpot(lastSpot);
        int result = 0;

        //hard coding feels awful.
        if ((lastSpot > 3 && lastSpot < 7) || lastSpot == 11 || lastSpot == 18 || lastSpot == 12 || (lastSpot > 36 && lastSpot < 40) || lastSpot == 32 || lastSpot == 21 || lastSpot == 25) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (northeastDiagonals.get(i).contains(lastSpot)) {
                return validateDiagonal(northwestDiagonals.get(i));
            }
        }
        return false;
    }

    private boolean validateDiagonal(List<Integer> diagonal) {
        int result = 0;
        for (int n : diagonal) {
            if (boardHash.get(n).getIsOccupied() == playerTurn) {
                result++;
            }
            if (result == 4) {
                return true;
            }
        }
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

    private boolean checkHorizontally() {
        int col = getColFromSpot(lastSpot);
        int row = lastSpot - (col * 6);
        int result = 0;
        for (int i = row; i <= row + (6*6); i+=6) {
            if (boardHash.get(i).getIsOccupied()==playerTurn) {
                result++;
            }
            if (result == 4) {
                return true;
            }
        }

        return false;

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
