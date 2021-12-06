package reversi.Board;

import reversi.Contants.ReversiGameConstants;
import reversi.Coordinates.Coordinates;
import reversi.Tiles.Tile;
import reversi.Tiles.TilesStates;

import java.util.List;

public class BoardGui {


    public void printBoard(Tile[][] tileMap) {
        clearScreen();
        printBoardColumns();
        for (var y = 0; y < ReversiGameConstants.BOARD_SIZE; y++) {
            StringBuilder text = new StringBuilder(ReversiGameConstants.SEPARATOR + y + ReversiGameConstants.SEPARATOR);
            for (var x = 0; x < ReversiGameConstants.BOARD_SIZE; x++) {
                var tileState = tileMap[x][y].getTileState().getTileSymbol();
                text.append(tileState).append(ReversiGameConstants.SEPARATOR);
            }
            printLn(text.toString());
        }
    }

    public void printBoardColumns() {
        StringBuilder text = new StringBuilder("| |");
        for (var i = 0; i < ReversiGameConstants.BOARD_SIZE; i++) {
            text.append(Columns.getEnum(i).toString()).append(addSeparatorToEndOfText());
        }
        printLn(text.toString());
    }
    public void showAvailableMovesOfPlayer(Tile[][] tileMap, List<Coordinates> availableMoves) {
        for (var availableMove : availableMoves) {
            tileMap[availableMove.getX()][availableMove.getY()].setTileState(TilesStates.AVAILABLE_MOVE);
        }
    }

    public void hideAvailableMovesOfPlayer(Tile[][] tileMap) {
        for (var y = 0; y < ReversiGameConstants.BOARD_SIZE; y++) {
            for (var x = 0; x < ReversiGameConstants.BOARD_SIZE; x++) {
                if (tileMap[x][y].getTileState() == TilesStates.AVAILABLE_MOVE) {
                    tileMap[x][y].setTileState(TilesStates.EMPTY);
                }
            }
        }
    }
    public static void clearScreen() {
        System.out.flush();
    }

    public void printLn(String text) {
        System.out.println(text);
    }

    public void printLn() {
        System.out.println();
    }

    public String addSeparatorToEndOfText() {
        return ReversiGameConstants.SEPARATOR;
    }

}
