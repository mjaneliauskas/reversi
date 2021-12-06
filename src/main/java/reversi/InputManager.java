package reversi;

import reversi.Board.Columns;
import reversi.Coordinates.Coordinates;

import java.util.Scanner;

public class InputManager extends Base {
    private boolean quit;

    public InputManager() {
        this.quit = false;
    }

    public Coordinates getCoordinatesInput() {
        Scanner scanner = new Scanner(System.in);
        while (!this.quit) {
            printLn("Enter where you want to move (or quit to exit):");
            String input = scanner.nextLine();
            if (input != null) {
                if ("quit".equals(input) || "exit".equals(input)) {
                    this.quit = true;
                    break;
                } else if (input.length() != 2) {
                    printLn("Invalid input");
                    return getCoordinatesInput();
                } else {
                    var inputArray = input.trim().split("");
                    if (isLetter(inputArray[0]) && isDigit(inputArray[1])) {
                        return new Coordinates(Columns.valueOf(inputArray[0].toUpperCase()).getInteger(), Integer.parseInt(inputArray[1]));
                    } else {
                        printLn("Invalid input");
                        return getCoordinatesInput();
                    }
                }
            }
        }
        return null;
    }

    private boolean isDigit(String text) {
        return text.matches("[0-9]");
    }

    private boolean isLetter(String text) {
        return text.matches("[a-zA-Z]");
    }

    public boolean isQuit() {
        return this.quit;
    }
}
