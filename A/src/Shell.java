import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Shell {
    private Shell() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);
    }

    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        int xAxis;
        int yAxis;
        GridImpl gol = null;
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
                        xAxis = Integer.parseInt(tokens[1]);
                        yAxis = Integer.parseInt(tokens[2]);
                        gol = new GridImpl(xAxis, yAxis);
                        break;
                    case 'a':
                        if(initialized(gol)) {
                            xAxis = Integer.parseInt(tokens[1]);
                            yAxis = Integer.parseInt(tokens[2]);
                            if (!gol.isAlive(xAxis, yAxis)) {
                                gol.setAlive(xAxis, yAxis, true);
                            }
                        }
                        break;
                    case 'd':
                        if (initialized(gol)) {
                            xAxis = Integer.parseInt(tokens[1]);
                            yAxis = Integer.parseInt(tokens[2]);
                            if (!gol.isAlive(xAxis, yAxis)) {
                                gol.setAlive(xAxis, yAxis, false);
                            }
                        }
                        break;
                    case 'g':
                        if (initialized(gol)) {
                            gol.next();
                        }
                        break;
                    case 'p':
                        if (initialized(gol)) {
                            System.out.println(gol.toString());
                        }
                        break;
                    case 'c':
                        if (initialized(gol)) {
                            gol.clear();
                        }
                        break;
                    case 'r':
                        if (initialized(gol)) {
                            gol.resize(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                        }
                        break;
                    case 's':
                        if (initialized(gol)) {
                            // TODO: write set of shapes
                        }
                        break;
                    case 'h':
                        // TODO: write help
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
        } else if (tokens.length == 3 && (!tokens[0].matches("[nadr]") || notANumber(tokens[1]) || notANumber(tokens[2]))) {
            error("Input has not the correct syntax. Command + number + number expected.");
            return false;
        } else if (tokens.length == 2 && (!tokens[0].matches("[s]"))) { // TODO: add filter for shapes
            error("Input has not the correct syntax. Command + number expected.");
            return false;
        } else if (tokens.length == 1 && !tokens[0].matches("[gpchq]")) {
            error("Input has not the correct syntax. Command expected.");
            return false;
        }
        return true;
    }

    private static boolean notANumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private static boolean initialized(GridImpl grid) {
        if (grid == null) {
            error("Grid hasn't been initialized yet! Try 'NEW + number + number' to create a grid.");
            return false;
        } else {
            return true;
        }
    }
}
