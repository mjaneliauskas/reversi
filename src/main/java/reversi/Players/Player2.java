package reversi.Players;

import reversi.Coordinates.Coordinates;

import java.util.ArrayList;

public class Player2 implements IPlayer {
    private final PlayerTypes playerType;
    private ArrayList<Coordinates> availableMoves = new ArrayList<>();

    public Player2() {
        this.playerType = PlayerTypes.BLACK;
    }

    public PlayerTypes getPlayerType() {
        return playerType;
    }

    public boolean canMove() {
        return !availableMoves.isEmpty();
    }

    public PlayerTypes getOpponentColor() {
        return PlayerTypes.WHITE;
    }

    public ArrayList<Coordinates> getAvailableMoves() {
        return availableMoves;
    }

    public void setAvailableMoves(ArrayList<Coordinates> availableMoves) {
        this.availableMoves = availableMoves;
    }
}
