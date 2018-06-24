package model;

/**
 * The class model.Shape is a Blueprint for a certain constellation of living cells
 * an a unique name by which they can be identified.
 */
public class Shape {
    private final String name;
    private final int[][] coordinates;
    private final int shapeColumns;
    private final int shapeRows;

    /**
     * The constructor for a new model.Shape.
     *
     * @param name        The name by which the model.Shape will be called.
     * @param coordinates An Array of all Coordinates
     *                    (int[X-Coordinate][Y-Coordinate]) which make up a model.Cell
     * @param shapeColums The width of the to-be created model.Shape.
     * @param shapeRows   The height of the to-be created model.Shape.
     */
    public Shape(String name, int[][] coordinates,
                 int shapeColums, int shapeRows) {
        this.name = name;
        this.coordinates = coordinates;
        this.shapeColumns = shapeColums;
        this.shapeRows = shapeRows;
    }

    /**
     * Getter method for the model.Shape's name.
     *
     * @return The name of the model.Shape.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the Coordinates.
     *
     * @return The Array of a model.Shape's Coordinates.
     */
    public int[][] getCoordinates() {
        return coordinates.clone();
    }

    /**
     * Getter method for the Width.
     *
     * @return The number of columns of the model.Shape
     */
    public int getShapeColumns() {
        return shapeColumns;
    }

    /**
     * Getter method for the Height.
     *
     * @return The number of rows of the model.Shape.
     */
    public int getShapeRows() {
        return shapeRows;
    }
}
