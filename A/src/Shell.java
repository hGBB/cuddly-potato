import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is the Shell for Conway's Game of Life.
 */
public final class Shell {

    /**
     * The constructor for the utility class.
     */
    private Shell() {
    }

    /**
     * This is the main method of Conway's Game of Life.
     *
     * @param args Arguments of type String
     * @throws IOException Buffered Reader requires to be able to throw
     *                     IOExceptions
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdin
                = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);
    }

    /**
     * The core method which works with the User input and calls the methods
     * after evaluating if the User's input fits the interface's requirements.
     *
     * @param stdin The input given by the User.
     * @throws IOException BufferedReader requires IOException.
     */
    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        int xAxis;
        int yAxis;
        GridImpl gol = null;
        ShapeCollection shapeCollection = new ShapeCollection();
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
                        if (noNegativeGridSize(xAxis, yAxis)) {
                            gol = new GridImpl(xAxis, yAxis);
                        }
                        break;
                    case 'a':
                        if (initialized(gol)) {
                            xAxis = Integer.parseInt(tokens[1]);
                            yAxis = Integer.parseInt(tokens[2]);
                            if (chosenInputIsOnGrid(gol, xAxis, yAxis)) {
                                if (!gol.isAlive(xAxis, yAxis)) {
                                    gol.setAlive(xAxis, yAxis, true);
                                }
                            }
                        }
                        break;
                    case 'd':
                        if (initialized(gol)) {
                            xAxis = Integer.parseInt(tokens[1]);
                            yAxis = Integer.parseInt(tokens[2]);
                            if (chosenInputIsOnGrid(gol, xAxis, yAxis)) {
                                if (gol.isAlive(xAxis, yAxis)) {
                                    gol.setAlive(xAxis, yAxis, false);
                                }
                            }
                        }
                        break;
                    case 'g':
                        if (initialized(gol)) {
                            gol.next();
                            System.out.println("Generation: "
                                    + gol.getGenerations());
                        }
                        break;
                    case 'p':
                        if (initialized(gol)) {
                            System.out.println(gol);
                        }
                        break;
                    case 'c':
                        if (initialized(gol)) {
                            gol.clear();
                        }
                        break;
                    case 'r':
                        xAxis = Integer.parseInt(tokens[1]);
                        yAxis = Integer.parseInt(tokens[2]);
                        if (initialized(gol)) {
                            if (noNegativeGridSize(xAxis, yAxis)) {
                                gol.resize(xAxis, yAxis);
                            }
                        }
                        break;
                    case 's':
                        if (initialized(gol)) {
                            gol.clear();
                            callShape(gol, tokens[1], shapeCollection);
                        }
                        break;
                    case 'h':
                        System.out.println("Hello and welcome to Conway's"
                                + " Game of Life \n"
                                + "This is the List of all commands and how"
                                + " to use them. \n"
                                + "x determines the X Coordinate and y the "
                                + "Y Coordinates of the Board \n"
                                + "First you have to create a new Grid using \n"
                                + "NEW x y \n"
                                + "Creates a new Board of the size x * y \n"
                                + "ALIVE x y \n"
                                + "Sets the cell located at x y to alive \n"
                                + "DEAD x y \n"
                                + "Sets the cell located at x y to dead \n"
                                + "GENERATE \n"
                                + "This command creates the next Generation"
                                + " of cells \n"
                                + "If a living Cell has 2 or 3 neighboring "
                                + "living Cells it stays alive \n"
                                + "in any other case the living Cell dies. \n"
                                + "If a dead Cell has exactly 3 living"
                                + " neighboring Cells it is born. \n"
                                + "PRINT \n"
                                + "This command prints the Board "
                                + "on the console \n"
                                + "CLEAR \n"
                                + "This command kills all living Cells"
                                + " of the Board \n"
                                + "RESIZE x y \n"
                                + "Resizes the Board but keeps all Cells "
                                + "as long as they don't fall off the Board \n"
                                + "Shape s \n"
                                + "Loads a predefined Shape on the Grid. \n"
                                + "This is the list of all available Shapes: \n"
                                + "Block \n"
                                + "Boat \n"
                                + "Blinker \n"
                                + "Toad \n"
                                + "Glider \n"
                                + "Spaceship \n"
                                + "Pulsar \n"
                                + "Bipole \n"
                                + "Tripole \n"
                                + "r-Pentomino \n"
                                + "Please be aware the shapes are case "
                                + "sensitive. \n"
                                + "QUIT \n"
                                + "Ends the program");
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

    /**
     * Help-method to check if the User's input according to the Grid
     * interface's requirements.
     *
     * @param tokens The User input split into String[] tokens.
     * @return True if the Input can be processed else false.
     */
    private static boolean checkInput(String[] tokens) {
        if (tokens.length > 3 || tokens[0].hashCode() == 0) {
            error("Input has not the correct syntax. Try 'Help'.");
            return false;
        } else if (tokens.length == 3
                && (!tokens[0].substring(0, 1).matches("[nadrNADR]")
                || notANumber(tokens[1]) || notANumber(tokens[2]))) {
            error("Input has not the correct syntax. Command + number"
                    + " + number expected.");
            return false;
        } else if (tokens.length == 2
                && (!tokens[0].substring(0, 1).matches("[sS]"))) {
            error("Input has not the correct syntax. Command "
                    + "+ number expected.");
            return false;
        } else if (tokens.length == 1
                && !tokens[0].substring(0, 1).matches("[gpchqGPCHQ]")) {
            error("Input has not the correct syntax. Command expected.");
            return false;
        }
        return true;
    }

    /**
     * Help-method to check if the User input contains
     * numbers for certain calls.
     *
     * @param number A String being checked if it contains solely a number.
     * @return Weather the String can be cast into an Integer.
     */
    private static boolean notANumber(String number) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    /**
     * Help-method to check if the Game of Life Grid has been initialized to
     * prevent NullPointerExceptions.
     *
     * @param grid An instance of the Game of Life Grid.
     * @return Weather the grid has been initialized.
     */
    private static boolean initialized(GridImpl grid) {
        if (grid != null) {
            return true;
        } else {
            error("Grid hasn't been initialized yet! Try 'NEW + number "
                    + "+ number' to create a grid.");
            return false;
        }
    }

    /**
     * Help-Method to check weather the User's input does'nt fall of the grid.
     *
     * @param grid An instance of the Game of Life Grid.
     * @param col  The User input for the X Coordinate.
     * @param row  The User input for the Y Coordinate.
     * @return If the Grid is wide enough.
     */
    private static boolean chosenInputIsOnGrid(Grid grid, int col, int row) {
        if (grid.getColumns() > col && grid.getRows() > row
                && col >= 0 && row >= 0) {
            return true;
        } else {
            error("The chosen Cell is out of bounds! Stay on the grid "
                    + "colums = " + grid.getColumns() + " and rows = "
                    + grid.getRows());
            return false;
        }
    }

    /**
     * Help-Method to determine if the User's input is a positive number.
     *
     * @param col The User's input for the X Coordinate.
     * @param row The User's input for the Y Coordinate.
     * @return X Coordinate and Y Coordinate >= 0
     */
    private static boolean noNegativeGridSize(int col, int row) {
        if (col >= 0 && row >= 0) {
            return true;
        } else {
            error("It is not possible to create a grid with negative "
                    + "numbers!");
            return false;
        }
    }

    /**
     * This method uses a Shape Collection to create predefined Shapes
     * centered on the Grid.
     *
     * @param gol An instance of the Game of Life grid.
     * @param token The User input calling a certain Shape.
     * @param shapes A predefined Collection of Shapes.
     */
    private static void callShape(GridImpl gol, String token,
                                  ShapeCollection shapes) {
        for (Shapes sh : shapes.getShapesCollection()) {
            if (token.matches(sh.getName())) {
                if (gol.getColumns() < sh.getShapeColumns()
                        || gol.getRows() < sh.getShapeRows()) {
                    error("The shape you tried to load does not "
                            + "fit on the grid! Please resize using "
                            + "the command: 'r " +
                            (sh.getShapeColumns() + 1) + " "
                            + (sh.getShapeRows() + 1) + "'");
                    return;
                }
                for (int[] coords : sh.getCoordinates()) {
                    int gameX = (gol.getColumns() - sh.getShapeColumns())
                            / 2 + coords[0];
                    int gameY = (gol.getRows() - sh.getShapeRows())
                            / 2 + coords[1];
                    gol.setAlive(gameX, gameY, true);
                }
                return;
            }
        }
        error("The shape " + token + " is not in the collection! "
                + "Try Help for a list of valid shapes");
    }

    /**
     * Help-method to standardize the Error messages printed on the console.
     *
     * @param msg A message helping the User to prevent incorrect inputs.
     */
    private static void error(String msg) {
        System.out.println("Error! " + msg);
    }
}
