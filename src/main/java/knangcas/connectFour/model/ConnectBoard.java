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

    public void testing() {
        for (int i = 0; i < northeastDiagonals.size(); i++) {
            System.out.println(northeastDiagonals.get(i).toString());
        }

        for (int i = 0; i < northwestDiagonals.size(); i++) {

            System.out.println(northwestDiagonals.get(i).toString());
        }
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

    private int getColFromSpot(int coordinate) {
        if (coordinate % 6 > 0) {
            return (coordinate / 6);
        }
        return (coordinate / 6) - 1;
    }

    private boolean checkVictory() {
        //upper difference = 5;
        //lower difference = 7;

        //check vertically
        if (lastSpot - (getColFromSpot(lastSpot) * 6) <=3) {
            checkVertically();
        }
        checkVertically();

        //TODO check horizontally;
        //TODO check diagonally down right/up left (7 difference)
        //TODO check diagonally down left/up right (5 difference)


        return false;

    }



    private boolean checkLeftDownRightUp() {
        int row = lastSpot - (6 * getColFromSpot(lastSpot));
        int col = getColFromSpot(lastSpot);
        int result = 0;

        //check southwest
        int place = lastSpot - 5;
        for (int i = col+1; i < 7; i++) {
            if (boardHash.get(place).getIsOccupied() == playerTurn) {
                result++;
            }
            place-=5;
            if (result == 4) {
                return true;
            }
        }
        //check northeast
        place = lastSpot + 5;
        for (int i = col - 1; i > 0; i--) {
            if (boardHash.get(place).getIsOccupied() == playerTurn) {
                result++;
            }
            place+=5;
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
