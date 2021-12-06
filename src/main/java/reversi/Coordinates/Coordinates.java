package reversi.Coordinates;

public record Coordinates(int x, int y) {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
