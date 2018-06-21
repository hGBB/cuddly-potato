package gameOfLife;

/**
 * The class gameOfLife.Shape is a Blueprint for a certain constellation of living cells
 * an a unique name by which they can be identified.
 */
public class Shape {
    private final String name;
    private final int[][] coordinates;
    private final int shapeColumns;
    private final int shapeRows;

    /**
     * The constructor for a new gameOfLife.Shape.
     *
     * @param name        The name by which the gameOfLife.Shape will be called.
     * @param coordinates An Array of all Coordinates
     *                    (int[X-Coordinate][Y-Coordinate]) which make up a gameOfLife.Cell
     * @param shapeColums The width of the to-be created gameOfLife.Shape.
     * @param shapeRows   The height of the to-be created gameOfLife.Shape.
     */
    public Shape(String name, int[][] coordinates,
                 int shapeColums, int shapeRows) {
        this.name = name;
        this.coordinates = coordinates;
        this.shapeColumns = shapeColums;
        this.shapeRows = shapeRows;
    }

    /**
     * Getter method for the gameOfLife.Shape's name.
     *
     * @return The name of the gameOfLife.Shape.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the Coordinates.
     *
     * @return The Array of a gameOfLife.Shape's Coordinates.
     */
    public int[][] getCoordinates() {
        return coordinates.clone();
    }

    /**
     * Getter method for the Width.
     *
     * @return The number of columns of the gameOfLife.Shape
     */
    public int getShapeColumns() {
        return shapeColumns;
    }

    /**
     * Getter method for the Height.
     *
     * @return The number of rows of the gameOfLife.Shape.
     */
    public int getShapeRows() {
        return shapeRows;
    }
}
