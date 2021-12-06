package reversi.Tiles;

public class Tile {

    private TilesStates state;

    public Tile(TilesStates state) {
        this.state = state;
    }

    public TilesStates getTileState() {
        return state;
    }

    public void setTileState(TilesStates state) {
        this.state = state;
    }
}
