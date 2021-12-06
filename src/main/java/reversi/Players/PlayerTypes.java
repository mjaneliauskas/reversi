package reversi.Players;

import reversi.Tiles.TilesStates;

public enum PlayerTypes {
    WHITE(TilesStates.WHITE),
    BLACK(TilesStates.BLACK);
    private final TilesStates tilesState;

    PlayerTypes(TilesStates tilesState) {
        this.tilesState = tilesState;
    }

    public String getSymbol() {
        return tilesState.getTileSymbol();
    }

    public TilesStates getTileType() {
        return tilesState;
    }

}
