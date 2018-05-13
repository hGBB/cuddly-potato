import java.util.Set;

public class Shapes {
    private String name;
    private int[][] cellsAlive;
    private int xShape;
    private int yShape;
    private Set<Cell> population;


    public GridImpl createBlock(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 2, 2);
        grid.setAlive(center[0], center[1], true);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0], center[1] - 1, true);
        grid.setAlive(center[0] - 1, center[1] - 1, true);

        return grid;
    }

    public GridImpl createBoat(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 3, 3);
        grid.setAlive(center[0] - 1, center[1] - 1, true);
        grid.setAlive(center[0], center[1] - 1, true);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0] + 1, center[1], true);
        grid.setAlive(center[0], center[1] + 1, true);
        return grid;
    }

    public GridImpl createBlinker(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 3, 1);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0], center[1], true);
        grid.setAlive(center[0] + 1, center[1], true);
        return grid;
    }

    public GridImpl createToad(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 4, 2);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0], center[1], true);
        grid.setAlive(center[0] + 1, center[1], true);
        grid.setAlive(center[0] - 2, center[1] + 1, true);
        grid.setAlive(center[0] - 1, center[1] + 1, true);
        grid.setAlive(center[0], center[1] + 1, true);
        return grid;
    }

    public GridImpl createGlider(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 3, 3);
        grid.setAlive(center[0] - 1, center[1] - 1, true);
        grid.setAlive(center[0], center[1] - 1, true);
        grid.setAlive(center[0] + 1, center[1] - 1, true);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0], center[1] + 1, true);
        return grid;
    }

    public GridImpl createSpaceship(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 1, 1);
        grid.setAlive(center[0] - 1, center[1] - 2, true);
        grid.setAlive(center[0] + 2, center[1] - 2, true);
        grid.setAlive(center[0] - 2, center[1] - 1, true);
        grid.setAlive(center[0] - 2, center[1], true);
        grid.setAlive(center[0] - 2, center[1] + 1, true);
        grid.setAlive(center[0] - 1, center[1] + 1, true);
        grid.setAlive(center[0], center[1] + 1, true);
        grid.setAlive(center[0] + 1, center[1] + 1, true);
        grid.setAlive(center[0] + 2, center[1], true);
        return grid;
    }

    public GridImpl createCluster(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 2, 2);
        // top left quarter
        grid.setAlive(center[0] + 2, center[1], true);
        grid.setAlive(center[0] + 3, center[1], true);
        grid.setAlive(center[0] + 3, center[1] + 1, true);
        grid.setAlive(center[0] + 4, center[1] + 1, true);
        grid.setAlive(center[0], center[1] + 2, true);
        grid.setAlive(center[0] + 3, center[1] + 2, true);
        grid.setAlive(center[0] + 5, center[1] + 2, true);
        grid.setAlive(center[0], center[1] + 3, true);
        grid.setAlive(center[0] + 1, center[1] + 3, true);
        grid.setAlive(center[0] + 2, center[1] + 3, true);
        grid.setAlive(center[0] + 4, center[1] + 3, true);
        grid.setAlive(center[0] + 5, center[1] + 3, true);
        grid.setAlive(center[0] + 1, center[1] + 4, true);
        grid.setAlive(center[0] + 3, center[1] + 4, true);
        grid.setAlive(center[0] + 5, center[1] + 4, true);
        grid.setAlive(center[0] + 2, center[1] + 5, true);
        grid.setAlive(center[0] + 3, center[1] + 5, true);
        grid.setAlive(center[0] + 4, center[1] + 5, true);
        // top right quarter
        grid.setAlive(center[0] + 9, center[1], true);
        grid.setAlive(center[0] + 10, center[1], true);
        grid.setAlive(center[0] + 8, center[1] + 1, true);
        grid.setAlive(center[0] + 9, center[1] + 1, true);
        grid.setAlive(center[0] + 7, center[1] + 2, true);
        grid.setAlive(center[0] + 9, center[1] + 2, true);
        grid.setAlive(center[0] + 12, center[1] + 2, true);
        grid.setAlive(center[0] + 7, center[1] + 3, true);
        grid.setAlive(center[0] + 8, center[1] + 3, true);
        grid.setAlive(center[0] + 10, center[1] + 3, true);
        grid.setAlive(center[0] + 11, center[1] + 3, true);
        grid.setAlive(center[0] + 12, center[1] + 3, true);
        grid.setAlive(center[0] + 7, center[1] + 4, true);
        grid.setAlive(center[0] + 9, center[1] + 4, true);
        grid.setAlive(center[0] + 11, center[1] + 4, true);
        grid.setAlive(center[0] + 8, center[1] + 5, true);
        grid.setAlive(center[0] + 9, center[1] + 5, true);
        grid.setAlive(center[0] + 10, center[1] + 5, true);
        // bottom left quarter
        grid.setAlive(center[0] + 2, center[1] + 12, true);
        grid.setAlive(center[0] + 3, center[1] + 12, true);
        grid.setAlive(center[0] + 3, center[1] + 11, true);
        grid.setAlive(center[0] + 4, center[1] + 11, true);
        grid.setAlive(center[0], center[1] + 10, true);
        grid.setAlive(center[0] + 3, center[1] + 10, true);
        grid.setAlive(center[0] + 5, center[1] + 10, true);
        grid.setAlive(center[0], center[1] + 9, true);
        grid.setAlive(center[0] + 1, center[1] + 9, true);
        grid.setAlive(center[0] + 2, center[1] + 9, true);
        grid.setAlive(center[0] + 4, center[1] + 9, true);
        grid.setAlive(center[0] + 5, center[1] + 9, true);
        grid.setAlive(center[0] + 1, center[1] + 8, true);
        grid.setAlive(center[0] + 3, center[1] + 8, true);
        grid.setAlive(center[0] + 5, center[1] + 8, true);
        grid.setAlive(center[0] + 2, center[1] + 7, true);
        grid.setAlive(center[0] + 3, center[1] + 7, true);
        grid.setAlive(center[0] + 4, center[1] + 7, true);
        // bottom right quarter
        grid.setAlive(center[0] + 9, center[1] + 12, true);
        grid.setAlive(center[0] + 10, center[1] + 12, true);
        grid.setAlive(center[0] + 8, center[1] + 11, true);
        grid.setAlive(center[0] + 9, center[1] + 11, true);
        grid.setAlive(center[0] + 7, center[1] + 10, true);
        grid.setAlive(center[0] + 9, center[1] + 10, true);
        grid.setAlive(center[0] + 12, center[1] + 10, true);
        grid.setAlive(center[0] + 7, center[1] + 9, true);
        grid.setAlive(center[0] + 8, center[1] + 9, true);
        grid.setAlive(center[0] + 10, center[1] + 9, true);
        grid.setAlive(center[0] + 11, center[1] + 9, true);
        grid.setAlive(center[0] + 12, center[1] + 9, true);
        grid.setAlive(center[0] + 7, center[1] + 8, true);
        grid.setAlive(center[0] + 9, center[1] + 8, true);
        grid.setAlive(center[0] + 11, center[1] + 8, true);
        grid.setAlive(center[0] + 8, center[1] + 7, true);
        grid.setAlive(center[0] + 9, center[1] + 7, true);
        grid.setAlive(center[0] + 10, center[1] + 7, true);
        return grid;
    }

    private int[] getCenter(int gridX, int gridY, int shapeX, int shapeY) {
        int gameX = 2; // ((gridX - shapeX) / 2) ;
        int gameY = 2; // ((gridY - shapeY) / 2) + shapeY ;
        return new int[]{gameY, gameX}; //TODO überarbeiten sauber colum
        // / row trennen!!!
    }

}
