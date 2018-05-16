public class Shapes {
    private String name;
    private int[][] coordinates;
    private int shapeColumns;
    private int shapeRows;

    public Shapes(String name, int[][] coordinates,
                  int shapeColums, int shapeRows) {
        this.name = name;
        this.coordinates = coordinates;
        this.shapeColumns = shapeColums;
        this.shapeRows = shapeRows;
    }

    public String getName() {
        return name;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public int getShapeColumns() {
        return shapeColumns;
    }

    public int getShapeRows() {
        return shapeRows;
    }
}
