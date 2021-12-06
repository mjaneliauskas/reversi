package reversi.Tiles;

public enum TilesStates {
    WHITE("●"),
    BLACK("◯"),
    EMPTY("·"),
    AVAILABLE_MOVE("A");
    private final String symbol;

    TilesStates(String symbol) {
        this.symbol = symbol;
    }

    public String getTileSymbol() {
        return symbol;
    }
}
