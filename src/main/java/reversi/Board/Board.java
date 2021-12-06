package reversi.Board;

import reversi.Contants.DirectionOffsets;
import reversi.Contants.ReversiGameConstants;
import reversi.Coordinates.Coordinates;
import reversi.Players.IPlayer;
import reversi.Tiles.Tile;
import reversi.Tiles.TilesStates;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Tile[][] tileMap;

    public Board() {
        this.tileMap = new Tile[ReversiGameConstants.BOARD_SIZE][ReversiGameConstants.BOARD_SIZE];
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public void setBoardStateAtCoords(int x, int y, TilesStates state) {
        tileMap[x][y].setTileState(state);
    }

    public TilesStates getTileStateAtCoords(int x, int y) {
        return tileMap[x][y].getTileState();
    }

    public void prepareBoard() {
        for (var y = 0; y < ReversiGameConstants.BOARD_SIZE; y++)
            for (var x = 0; x < ReversiGameConstants.BOARD_SIZE; x++) {
                tileMap[x][y] = new Tile(TilesStates.EMPTY);
            }
        int middle = ReversiGameConstants.BOARD_SIZE / 2;
        tileMap[middle - 1][middle - 1].setTileState(TilesStates.BLACK);
        tileMap[middle - 1][middle].setTileState(TilesStates.WHITE);
        tileMap[middle][middle].setTileState(TilesStates.BLACK);
        tileMap[middle][middle - 1].setTileState(TilesStates.WHITE);
    }

    public boolean isLegalMove(Coordinates coordinates, IPlayer player) {
        if (coordinates == null) {
            return false;
        }
        var tileX = coordinates.getX();
        var tileY = coordinates.getY();
        if (!isValidCoords(tileX, tileY) || !isTileEmpty(tileX, tileY)) {
            return false;
        }
        var isLegal = false;
        for (var offset : DirectionOffsets.values()) {
            isLegal = isLegalInDirection(tileX, tileY, player, offset) || isLegal;
            if (isLegal)
                return true;
        }
        return false;
    }

    public boolean isValidCoords(int tileX, int tileY) {
        return tileX < ReversiGameConstants.BOARD_SIZE && tileX >= 0 && tileY < ReversiGameConstants.BOARD_SIZE && tileY >= 0;
    }

    private boolean isTileEmpty(int tileX, int tileY) {

        return this.getTileStateAtCoords(tileX, tileY) == TilesStates.EMPTY;
    }

    private boolean isLegalInDirection(int tileX, int tileY, IPlayer player, DirectionOffsets direction) {
        var newTileX = tileX + direction.getOffset().getX();
        var newTileY = tileY + direction.getOffset().getY();
        if (isValidCoords(newTileX, newTileY)) {
            if (isTileEmpty(newTileX, newTileY)) {
                return false;
            }
            if (this.getTileStateAtCoords(newTileX, newTileY) == player.getPlayerType().getTileType()) {
                return !isTileEmpty(tileX, tileY);
            } else
                return this.isLegalInDirection(newTileX, newTileY, player, direction);
        }
        return false;
    }

    public void makeMove(int x, int y, IPlayer currentPlayer) {
        this.setBoardStateAtCoords(x, y, currentPlayer.getPlayerType().getTileType());
        flipTiles(x, y, currentPlayer);
    }

    public void flipTiles(int startingX, int startingY, IPlayer currentPlayer) {
        for (var directionOffset : DirectionOffsets.values()) {
            if (isLegalInDirection(startingX, startingY, currentPlayer, directionOffset)) {
                flipTilesInDirection(startingX, startingY, currentPlayer, directionOffset);
            }
        }
    }

    private boolean flipTilesInDirection(int tileX, int tileY, IPlayer player, DirectionOffsets direction) {
        var newTileX = tileX + direction.getOffset().getX();
        var newTileY = tileY + direction.getOffset().getY();
        if (isValidCoords(newTileX, newTileY)) {
            if (isTileEmpty(newTileX, newTileY)) {
                return false;
            }
            if (this.getTileStateAtCoords(newTileX, newTileY) == player.getPlayerType().getTileType()) {
                return true;
            } else {
                this.setBoardStateAtCoords(newTileX, newTileY, player.getPlayerType().getTileType());
                return this.flipTilesInDirection(newTileX, newTileY, player, direction);

            }
        }
        return false;
    }

    public ArrayList<Coordinates> getAvailableMoves(IPlayer currentPlayer) {
        var availableMoves = new ArrayList<Coordinates>();
        var allPlayerTiles = findAllTileCoordinatesByTileColor(currentPlayer.getOpponentColor().getTileType());
        for (var move : allPlayerTiles) {
            var offsets = getOffsetsAroundTile(move);
            for (var tile : offsets) {
                if (isLegalMove(tile, currentPlayer))
                    availableMoves.add(tile);
            }
        }
        return availableMoves;
    }

    public int GetTilesCountOfColor(TilesStates tileType) {
        return findAllTileCoordinatesByTileColor(tileType).size();
    }

    public List<Coordinates> findAllTileCoordinatesByTileColor(TilesStates tileType) {
        var listOfCoordinates = new ArrayList<Coordinates>();
        for (var y = 0; y < ReversiGameConstants.BOARD_SIZE; y++) {
            for (var x = 0; x < ReversiGameConstants.BOARD_SIZE; x++) {
                if (tileMap[x][y].getTileState() == tileType) {
                    listOfCoordinates.add(new Coordinates(x, y));
                }
            }
        }
        return listOfCoordinates;
    }

    private ArrayList<Coordinates> getOffsetsAroundTile(Coordinates tileCoordinates) {
        var offsets = new ArrayList<Coordinates>();
        int tempX = tileCoordinates.getX();
        int tempY = tileCoordinates.getY();
        if (tileCoordinates.getY() >= 1) {
            tempY = tileCoordinates.getY() - 1;
        }
        if (tileCoordinates.getX() >= 1)
            tempX = tileCoordinates.getX() - 1;

        for (int y = tempY; y <= tileCoordinates.getY() + 1 && y < ReversiGameConstants.BOARD_SIZE; y++) {
            for (int x = tempX; x <= tileCoordinates.getX() + 1 && x < ReversiGameConstants.BOARD_SIZE; x++) {
                if ((x != tileCoordinates.getX() || y != tileCoordinates.getY()) && isTileEmpty(x, y)) {
                    offsets.add(new Coordinates(x, y));
                }
            }
        }
        return offsets;

    }

}
