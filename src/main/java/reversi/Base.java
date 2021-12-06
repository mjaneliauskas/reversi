package reversi;

import reversi.Contants.ReversiGameConstants;

public class Base {
    public void print(String text) {
        System.out.print(text);
    }

    public void printLn(String text) {
        System.out.println(text);
    }

    public void printLn() {
        System.out.println();
    }

    public String addSeparatorToEndOfText() {
        return ReversiGameConstants.SEPARATOR;
    }

    public static void clearScreen() {
        System.out.flush();
    }
}
