package reversi.Board;

import java.util.HashMap;
import java.util.Map;

public enum Columns {
    A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);
    private static final Map<Object, Object> map = new HashMap<>();
    private final int value;

    Columns(int value) {
        this.value = value;
    }

    static {
        for (Columns column : Columns.values()) {
            map.put(column.value, column);
        }
    }

    public int getInteger() {
        return value;
    }

    public static Columns getEnum(int column) {
        return (Columns) map.get(column);
    }
}
