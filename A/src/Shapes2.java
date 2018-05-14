public class Shapes2 {
    private String name;
    private int[][] coordinates;
    int shapeColums;
    int shapeRows;

    public Shapes2(String name, int[][] coordinates, int shapeColums, int shapeRows) {
        this.name = name;
        this.coordinates = coordinates;
        this.shapeColums = shapeColums;
        this.shapeRows = shapeRows;
    }

    public String getName() {
        return name;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public int getShapeColums() {
        return shapeColums;
    }

    public int getShapeRows() {
        return shapeRows;
    }
}
