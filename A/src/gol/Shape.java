package gol;

/**
 * The class gol.Shape is a Blueprint for a certain constellation of living cells
 * an a unique name by which they can be identified.
 */
public class Shape {
    private String name;
    private int[][] coordinates;
    private int shapeColumns;
    private int shapeRows;

    /**
     * The constructor for a new gol.Shape.
     *
     * @param name        The name by which the gol.Shape will be called.
     * @param coordinates An Array of all Coordinates
     *                    (int[X-Coordinate][Y-Coordinate]) which make up a gol.Cell
     * @param shapeColums The width of the to-be created gol.Shape.
     * @param shapeRows   The height of the to-be created gol.Shape.
     */
    public Shape(String name, int[][] coordinates,
                 int shapeColums, int shapeRows) {
        this.name = name;
        this.coordinates = coordinates;
        this.shapeColumns = shapeColums;
        this.shapeRows = shapeRows;
    }

    /**
     * Getter method for the gol.Shape's name.
     *
     * @return The name of the gol.Shape.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the Coordinates.
     *
     * @return The Array of a gol.Shape's Coordinates.
     */
    public int[][] getCoordinates() {
        return coordinates.clone();
    }

    /**
     * Getter method for the Width.
     *
     * @return The number of columns of the gol.Shape
     */
    public int getShapeColumns() {
        return shapeColumns;
    }

    /**
     * Getter method for the Height.
     *
     * @return The number of rows of the gol.Shape.
     */
    public int getShapeRows() {
        return shapeRows;
    }
}
