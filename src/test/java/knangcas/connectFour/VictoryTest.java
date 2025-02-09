package knangcas.connectFour;

import knangcas.connectFour.exception.ColumnFullException;
import knangcas.connectFour.model.ConnectBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VictoryTest {

    ConnectBoard connectBoard;
    @BeforeEach
    public void setup() {connectBoard = new ConnectBoard();
    }

    @AfterEach
    public void tearDown() { connectBoard = null;}

    @Test
    void testVerticals1() {
        assertEquals(1,connectBoard.dropPiece(1));
        assertEquals(2,connectBoard.dropPiece(1));
        assertEquals(3,connectBoard.dropPiece(1));
        assertEquals(4,connectBoard.dropPiece(1));
        assertTrue(connectBoard.validate());
    }

    @Test
    void testVerticals2() {

        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(1));
        assertEquals(2, connectBoard.dropPiece(1));
        connectBoard.playerTurnChange();
        assertEquals(3,connectBoard.dropPiece(1));
        assertEquals(4,connectBoard.dropPiece(1));
        assertEquals(5,connectBoard.dropPiece(1));
        assertEquals(6,connectBoard.dropPiece(1));
        assertTrue(connectBoard.validate());
    }

    @Test
    void testVerticals3() {

        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(1));

        connectBoard.playerTurnChange();
        assertEquals(2,connectBoard.dropPiece(1));
        assertEquals(3,connectBoard.dropPiece(1));
        assertEquals(4,connectBoard.dropPiece(1));
        assertEquals(5,connectBoard.dropPiece(1));
        assertTrue(connectBoard.validate());
    }



    @Test
    void testHorizontals1() {

        assertEquals(1,connectBoard.dropPiece(5));
        assertEquals(1,connectBoard.dropPiece(2));
        assertEquals(1,connectBoard.dropPiece(3));
        assertEquals(1,connectBoard.dropPiece(4));
        assertTrue(connectBoard.validate());

    }
    @Test
    void testHorizontals2() {

        assertEquals(1,connectBoard.dropPiece(1));
        assertEquals(1,connectBoard.dropPiece(2));
        assertEquals(1,connectBoard.dropPiece(3));
        assertEquals(1,connectBoard.dropPiece(4));
        assertTrue(connectBoard.validate());

    }

    @Test
    void testHorizontals3() {

        assertEquals(1,connectBoard.dropPiece(5));
        assertEquals(1,connectBoard.dropPiece(6));
        assertEquals(1,connectBoard.dropPiece(3));
        assertEquals(1,connectBoard.dropPiece(4));
        assertTrue(connectBoard.validate());

    }

    @Test
    void testHorizontals4() {

        assertEquals(1,connectBoard.dropPiece(7));
        assertEquals(1,connectBoard.dropPiece(6));
        assertEquals(1,connectBoard.dropPiece(5));
        assertEquals(1,connectBoard.dropPiece(4));
        assertTrue(connectBoard.validate());

    }

    @Test
    void testDiagonals1() {

        assertEquals(1, connectBoard.dropPiece(1)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(2)); //2
        connectBoard.playerTurnChange();
        assertEquals(2, connectBoard.dropPiece(2)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(3)); //2
        connectBoard.playerTurnChange();
        assertEquals(2, connectBoard.dropPiece(3)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(4)); //2
        connectBoard.playerTurnChange();
        assertEquals(3, connectBoard.dropPiece(3)); //1
        connectBoard.playerTurnChange();
        assertEquals(2, connectBoard.dropPiece(4)); //2
        connectBoard.playerTurnChange();
        assertEquals(3, connectBoard.dropPiece(4)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(7)); //2
        connectBoard.playerTurnChange();
        assertEquals(4, connectBoard.dropPiece(4)); //1
        assertTrue(connectBoard.validate());

    }

    @Test
    void testDiagonals2() {

        assertEquals(1, connectBoard.dropPiece(7)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(6)); //2
        connectBoard.playerTurnChange();
        assertEquals(2, connectBoard.dropPiece(6)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(5)); //2
        connectBoard.playerTurnChange();
        assertEquals(2, connectBoard.dropPiece(5)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(4)); //2
        connectBoard.playerTurnChange();
        assertEquals(3, connectBoard.dropPiece(5)); //1
        connectBoard.playerTurnChange();
        assertEquals(2, connectBoard.dropPiece(4)); //2
        connectBoard.playerTurnChange();
        assertEquals(3, connectBoard.dropPiece(4)); //1
        connectBoard.playerTurnChange();
        assertEquals(1, connectBoard.dropPiece(1)); //2
        connectBoard.playerTurnChange();
        assertEquals(4, connectBoard.dropPiece(4)); //1
        assertTrue(connectBoard.validate());

    }







}
