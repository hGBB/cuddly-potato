/**
 * The class Shapes is a Blueprint for a certain constellation of living cells
 * an a unique name by which they can be identified.
 */
public class Shapes {
    private String name;
    private int[][] coordinates;
    private int shapeColumns;
    private int shapeRows;

    /**
     * The constructor for a new Shape.
     *
     * @param name        The name by which the Shape will be called.
     * @param coordinates An Array of all Coordinates
     *                    (int[X-Coordinate][Y-Coordinate]) which make up a Cell
     * @param shapeColums The width of the to-be created Shape.
     * @param shapeRows   The height of the to-be created Shape.
     */
    public Shapes(String name, int[][] coordinates,
                  int shapeColums, int shapeRows) {
        this.name = name;
        this.coordinates = coordinates;
        this.shapeColumns = shapeColums;
        this.shapeRows = shapeRows;
    }

    /**
     * Getter method for the Shape's name.
     *
     * @return The name of the Shape.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the Coordinates.
     *
     * @return The Array of a Shape's Coordinates.
     */
    public int[][] getCoordinates() {
        return coordinates;
    }

    /**
     * Getter method for the Width.
     *
     * @return The number of columns of the Shape
     */
    public int getShapeColumns() {
        return shapeColumns;
    }

    /**
     * Getter method for the Height.
     *
     * @return The number of rows of the Shape.
     */
    public int getShapeRows() {
        return shapeRows;
    }
}
