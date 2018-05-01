import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class Shell {
    private Shell() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);
    }

    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;

        while (!quit) {
            System.out.println("gol> ");
            String input = stdin.readLine();
            if (input == null) {
                break;
            }
            String[] tokens = input.trim().split("\\s+");
            if (checkInput(tokens)) {

                switch (input.toLowerCase().charAt(0)) {
                    case 'n':
                        ;
                        break;
                    case 'a':
                        ;
                        break;
                    case 'd':
                        ;
                        break;
                    case 'g':
                        ;
                        break;
                    case 'p':
                        ;
                        break;
                    case 'c':
                        ;
                        break;
                    case 'r':
                        break;
                    case 's':
                        break;
                    case 'h':
                        break;
                    case 'q':
                        quit = true;
                        break;
                    default:
                        error("Not a valid command!");
                }


            }
        }
    }

    private static void error(String msg) {
        System.out.println("Error! " + msg);
    }

    private static boolean checkInput(String[] tokens) {
        if (tokens.length > 3 || tokens.length == 0) {
            error("Input has not the correct syntax. Try 'Help'.");
            return false;
        } else if (tokens.length == 3 && !(isANumber(tokens[1]) && isANumber(tokens[2]))) {
            error("Input has not the correct syntax. Command + number + number expected.");
            return false;
        } else if (tokens.length == 2 && !isANumber(tokens[1])) {
            error("Input has not the correct syntax. Command + number expected.");
            return false;
        } else if (tokens.length == 1)
        return true;
    }

    private static boolean isANumber(String number) {
        try {
            Integer.parseInt(number);

        } catch (NumberFormatException e) {
            error("Not a number!");
            return false;
        }
        return true;
    }
}
