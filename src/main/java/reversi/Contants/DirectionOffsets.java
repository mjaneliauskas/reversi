package reversi.Contants;

import reversi.Coordinates.Coordinates;

public enum DirectionOffsets {
    Up(new Coordinates(0,-1)),Down(new Coordinates(0,1)),Left(new Coordinates(-1,0)),Right(new Coordinates(1,0)),
    UpLeft(new Coordinates(-1,-1)),UpRight(new Coordinates(1,-1)),DownLeft(new Coordinates(-1,1)),DownRight(new Coordinates(1,1));

    private final Coordinates offset;

    DirectionOffsets(Coordinates offset) {
        this.offset = offset;
    }
    public Coordinates getOffset(){ return offset;}
}
