package reversi;

import reversi.Board.Board;
import reversi.Board.BoardGui;
import reversi.Players.IPlayer;
import reversi.Players.Player1;
import reversi.Players.Player2;

import java.util.ArrayList;
import java.util.List;

public class Game extends Base {
    private final BoardGui boardGui;
    private List<IPlayer> players;
    private IPlayer currentPlayer;
    private final Board board;
    private final InputManager inputManager;

    public static void main(String[] args) {
        var game = new Game();
        game.playGame();
    }

    public Game() {
        this.players = new ArrayList<>();
        this.players.add(new Player1());
        this.players.add(new Player2());
        this.currentPlayer = players.get(0);
        this.board = new Board();
        this.boardGui = new BoardGui();
        this.board.prepareBoard();
        this.inputManager = new InputManager();
    }

    private void playGame() {
        currentPlayer.setAvailableMoves(board.getAvailableMoves(currentPlayer));
        if (currentPlayer.canMove()) {
            boardGui.showAvailableMovesOfPlayer(board.getTileMap(), currentPlayer.getAvailableMoves());
            boardGui.printBoard(board.getTileMap());
            boardGui.hideAvailableMovesOfPlayer(board.getTileMap());
            printLn();
            printLn("Current Player: " + currentPlayer.getPlayerType() + " " + currentPlayer.getPlayerType().getSymbol());
            printLn();
            var inputCoordinates = inputManager.getCoordinatesInput();
            while (!board.isLegalMove(inputCoordinates, currentPlayer)) {
                printLn("Illegal move");
                inputCoordinates = inputManager.getCoordinatesInput();
            }
            if (inputManager.isQuit()) return;
            board.makeMove(inputCoordinates.getX(), inputCoordinates.getY(), currentPlayer);
        } else {
            boardGui.printBoard(board.getTileMap());
            printLn(currentPlayer.getPlayerType() + " " + currentPlayer.getPlayerType().getSymbol() + "Player has no legal moves. Skipping.");
            if (board.getAvailableMoves(nextPlayer(players, currentPlayer)).isEmpty()) {
                printLn("Game Ended");
                System.exit(0);
            }

        }
        currentPlayer = nextPlayer(players, currentPlayer);
        playGame();

    }

    private static IPlayer nextPlayer(List<IPlayer> players, IPlayer currentPlayer) {
        var index = 0;
        if (players.indexOf(currentPlayer) + 1 < players.size()) index = players.indexOf(currentPlayer) + 1;
        return players.get(index);
    }

}
