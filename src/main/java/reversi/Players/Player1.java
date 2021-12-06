package reversi.Players;

import reversi.Coordinates.Coordinates;

import java.util.ArrayList;

public class Player1 implements IPlayer {
    private final PlayerTypes playerType;
    private ArrayList<Coordinates> availableMoves = new ArrayList<>();

    public Player1() {
        this.playerType = PlayerTypes.WHITE;
    }

    public PlayerTypes getPlayerType() {
        return playerType;
    }

    public PlayerTypes getOpponentColor() {
        return PlayerTypes.BLACK;
    }

    public boolean canMove() {
        return !availableMoves.isEmpty();
    }

    public ArrayList<Coordinates> getAvailableMoves() {
        return availableMoves;
    }

    public void setAvailableMoves(ArrayList<Coordinates> availableMoves) {
        this.availableMoves = availableMoves;
    }
}
