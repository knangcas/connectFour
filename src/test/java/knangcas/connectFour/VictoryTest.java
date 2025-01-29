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
    public void testHorizontal() {
        try {
            assertEquals(connectBoard.playerMove(1, 1), false);
            assertEquals(connectBoard.playerMove(2, 2), false);
            assertEquals(connectBoard.playerMove(1, 1), false);
            assertEquals(connectBoard.playerMove(2, 3), false);
            assertEquals(connectBoard.playerMove(1, 1), false);
            assertEquals(connectBoard.playerMove(2, 4), false);
            assertEquals(connectBoard.playerMove(1, 1), true);
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHorizontal2() {

            assertEquals(connectBoard.playerMove(1, 1), false);
            assertEquals(connectBoard.playerMove(2, 1), false);
            assertEquals(connectBoard.playerMove(1, 1), false);
            assertEquals(connectBoard.playerMove(2, 1), false);
            assertEquals(connectBoard.playerMove(1, 1), false);
            assertEquals(connectBoard.playerMove(2, 1), false);
            ColumnFullException thrown = assertThrows(ColumnFullException.class, () -> connectBoard.playerMove(1,1));
            assertTrue(thrown.getMessage().equalsIgnoreCase("Current column is full"));

    }

    @Test
    public void nothing() {

    }



}
