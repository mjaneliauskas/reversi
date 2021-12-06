package reversi.tests;

import org.junit.Assert;
import reversi.Board.Board;
import org.junit.Test;

import reversi.Contants.ReversiGameConstants;
import reversi.Coordinates.Coordinates;
import reversi.Players.Player1;
import reversi.Tiles.TilesStates;

import static org.junit.Assert.*;

public class BoardTests {
    @Test
    public void moveIsOutOfBoundaryWithNegativeValues() {
        //Arrange
        var board = new Board();
        board.prepareBoard();
        var move = new Coordinates(-1, 0);
        var player = new Player1();
        //Act
        var result = board.isLegalMove(move, player);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void setTileStateTest() {
        var board = new Board();
        board.prepareBoard();
        board.setBoardStateAtCoords(0, 0, TilesStates.WHITE);
        var state = board.getTileStateAtCoords(0, 0);
        Assert.assertEquals(TilesStates.WHITE, state);

    }

    @Test
    public void moveIsOutOfBoundaryTestOverfilledValues() {
        //Arrange
        var board = new Board();
        board.prepareBoard();
        var move = new Coordinates(ReversiGameConstants.BOARD_SIZE + 1, ReversiGameConstants.BOARD_SIZE + 1);
        var player = new Player1();

        //Act
        var result = board.isLegalMove(move, player);
        //Assert
        assertFalse(result);
    }

    @Test
    public void makeLegalMove() {
        //Arrange
        var board = new Board();
        board.prepareBoard();
        board.setBoardStateAtCoords(0, 0, TilesStates.WHITE);
        board.setBoardStateAtCoords(0, 1, TilesStates.BLACK);
        var player = new Player1();
        //Act
        board.makeMove(0, 2, player);
        var state = board.getTileStateAtCoords(0, 2);
        //Assert
        assertEquals(TilesStates.WHITE, state);
    }

    @Test
    public void flipTile() {
        //Arrange
        var board = new Board();
        board.prepareBoard();
        board.setBoardStateAtCoords(0, 0, TilesStates.WHITE);
        board.setBoardStateAtCoords(0, 1, TilesStates.BLACK);
        var player = new Player1();
        //Act
        board.makeMove(0, 2, player);
        var state = board.getTileStateAtCoords(0, 1);
        //Assert
        assertEquals(TilesStates.WHITE, state);
    }

    @Test
    public void isCoordinatesValidLeftUpperCorner() {
        var board = new Board();
        var result = board.isValidCoords(0, 0);
        assertTrue(result);
    }

    @Test
    public void isCoordinatesValidLeftBottomCorner() {
        var board = new Board();
        var result = board.isValidCoords(0, ReversiGameConstants.BOARD_SIZE - 1);
        assertTrue(result);
    }

    @Test
    public void isCoordinatesValidRightUpperCorner() {
        var board = new Board();
        var result = board.isValidCoords(ReversiGameConstants.BOARD_SIZE - 1, 0);
        assertTrue(result);
    }

    @Test
    public void isCoordinatesValidRightBottomCorner() {
        var board = new Board();
        var result = board.isValidCoords(ReversiGameConstants.BOARD_SIZE - 1, ReversiGameConstants.BOARD_SIZE - 1);
        assertTrue(result);
    }

}
