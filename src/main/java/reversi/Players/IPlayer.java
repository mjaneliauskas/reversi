package reversi.Players;

import reversi.Coordinates.Coordinates;

import java.util.ArrayList;

public interface IPlayer {
    boolean canMove();
    ArrayList<Coordinates> getAvailableMoves();
    void setAvailableMoves(ArrayList<Coordinates> availableMoves);
    PlayerTypes getPlayerType();
    PlayerTypes getOpponentColor();
}
