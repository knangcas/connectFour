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
        for (int i = 0; i < 7; i ++) {
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

        System.out.println(northwestDiagonals.toString() + "= nwD");
        System.out.println(northeastDiagonals.toString() + "= neD");
    }

    public int dropPiece(int col) {
        if (columns.get(col-1).size() == 6) {
            throw new ColumnFullException("Current column is full");
        }
        columns.get(col-1).push(playerTurn);
        lastSpot = spotConversion(col-1, columns.get(col-1).size());
        boardHash.get(lastSpot).setIsOccupied(playerTurn);
//        if (checkVictory()) {
//            return -2;
//        }

        return columns.get(col-1).size();
    }








    public void playerMove(int playerNumber, int colNumber) {
        playerTurn = playerNumber;
        if (columns.get(colNumber-1).size() == 6) {
            throw new ColumnFullException("Current column is full");
        }
        lastSpot = spotConversion(colNumber-1, columns.get(colNumber-1).size());
        columns.get(colNumber-1).push(playerNumber);
        boardHash.get(lastSpot).setIsOccupied(playerNumber);

        /*if(checkVictory()) {
            return true;
        }

        playerTurnChange();
        return false;*/
    }

    private int getColFromSpot(int coordinate) {
        if (coordinate % 6 > 0) {
            return (coordinate / 6);
        }
        return (coordinate / 6) - 1;
    }

    public boolean validate() {
        return checkVictory();
    }

    private boolean checkVictory() {
        System.out.println("Last Spot = " + lastSpot);

        if (lastSpot - (getColFromSpot(lastSpot) * 6) <=3) {
            if (checkVertically()) {
                System.out.println("Vertical!");
                return true;
            }
        }

        if (checkHorizontally()) {
            System.out.println("Horizontal!");
            return true;
        }

        return checkDiagonals();

    }

    private boolean checkDiagonals() {
        if (checkNortheast()) {
            System.out.println("Northeast!");
            return true;
        }
        if (checkNorthwest()) {
            System.out.println("Northwest!");
            return true;
        }
        return false;
    }


    private boolean checkNortheast() {
        int row = lastSpot - (6 * getColFromSpot(lastSpot));
        int col = getColFromSpot(lastSpot);
        int result = 0;

        //hard coding feels awful.
        if (lastSpot < 4 || lastSpot == 7 || lastSpot == 8 || lastSpot ==13 || lastSpot > 39 || lastSpot == 35 || lastSpot == 30 || lastSpot == 36) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (northeastDiagonals.get(i).contains(lastSpot)) {
                return validateDiagonal(northeastDiagonals.get(i));
            }
        }
        return false;
    }

    private boolean checkNorthwest() {
        int row = lastSpot - (6 * getColFromSpot(lastSpot));
        int col = getColFromSpot(lastSpot);
        int result = 0;

        //hard coding feels awful.
        if ((lastSpot > 3 && lastSpot < 7) || lastSpot == 11 || lastSpot == 18 || lastSpot == 12 || (lastSpot > 36 && lastSpot < 40) || lastSpot == 32 || lastSpot == 31 || lastSpot == 25) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (northwestDiagonals.get(i).contains(lastSpot)) {
                return validateDiagonal(northwestDiagonals.get(i));
            }
        }
        return false;
    }

    private boolean validateDiagonal(List<Integer> diagonal) {
        System.out.println(diagonal.toString());
        for (int i = 0; i < diagonal.size() - 3 ; i++) {
            int result = 0;
            for (int j = i; j < i+4; j++) {
                if (boardHash.get(diagonal.get(j)).getIsOccupied() == playerTurn) {
                    result++;
                }
                if (result == 4) {
                    return true;
                }
            }
        }
        return false;
//        for (int n : diagonal) {
//            if (boardHash.get(n).getIsOccupied() == playerTurn) {
//                result++;
//            }
//            if (result == 4) {
//                return true;
//            }
//        }
        //return false;
    }



    private boolean checkVertically() {
        int result = 0;
        for (int i = lastSpot+1; i < lastSpot+4; i++) {
            System.out.println("spot: " + i + " occupied: " + boardHash.get(i).getIsOccupied());
            if (boardHash.get(i).getIsOccupied() == playerTurn) {

                result++;
            }
        }
        return result == 3;
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


    public void displayBoard() {

        System.out.println("Connect4 Board--------");

        System.out.println("\n 0 = free spot, 1 = player1, 2 = player2");
        for (int i = 1; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                System.out.print(boardHash.get(i + (6 *j)).getIsOccupied());
            }
            System.out.println();
        }
        System.out.println();
    }

    public void playerTurnChange() {
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
