package knangcas.connectFour;


import knangcas.connectFour.exception.ColumnFullException;
import knangcas.connectFour.model.ConnectBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColumnTest {

    ConnectBoard connectBoard;
    @BeforeEach
    public void setup() {connectBoard = new ConnectBoard();
    }

    @AfterEach
    public void tearDown() { connectBoard = null;}


    @Test
    void testException() {

        connectBoard.dropPiece(1);
        connectBoard.dropPiece(1);
        connectBoard.dropPiece(1);
        connectBoard.dropPiece(1);
        connectBoard.dropPiece(1);
        connectBoard.dropPiece(1);
        ColumnFullException e = assertThrows(ColumnFullException.class, () -> connectBoard.dropPiece(1));
        assertTrue(e.getMessage().equalsIgnoreCase("Current column is full"));

    }

    @Test
    void testException2() {

        connectBoard.dropPiece(2);
        connectBoard.dropPiece(2);
        connectBoard.dropPiece(2);
        connectBoard.dropPiece(2);
        connectBoard.dropPiece(2);
        connectBoard.dropPiece(2);
        ColumnFullException e = assertThrows(ColumnFullException.class, () -> connectBoard.dropPiece(2));
        assertTrue(e.getMessage().equalsIgnoreCase("Current column is full"));

    }



}
