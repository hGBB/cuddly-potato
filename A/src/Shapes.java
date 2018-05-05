import java.util.Set;

public class Shapes {
    private String name;
    private int xShape;
    private int yShape;
    private Set<Cell> population;

    public GridImpl createBlock(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 2 , 2);
        grid.setAlive(center[0], center[1], true);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0], center[1] - 1, true);
        grid.setAlive(center[0] - 1, center[1] - 1, true);

        return grid;
    }

    public GridImpl createBoat(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 3 , 3);
        grid.setAlive(center[0] - 1, center[1] - 1, true);
        grid.setAlive(center[0], center[1] - 1, true);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0] + 1, center[1], true);
        grid.setAlive(center[0], center[1] + 1, true);
        return grid;
    }

    public GridImpl createBlinker(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 3 , 1);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0], center[1], true);
        grid.setAlive(center[0] + 1, center[1], true);
        return grid;
    }

    public GridImpl createToad(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 4 , 2);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0] , center[1], true);
        grid.setAlive(center[0] + 1, center[1], true);
        grid.setAlive(center[0] - 2, center[1] + 1, true);
        grid.setAlive(center[0] - 1, center[1] + 1, true);
        grid.setAlive(center[0], center[1] + 1, true);
        return grid;
    }

    public GridImpl createGlider(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 3 , 3);
        grid.setAlive(center[0] - 1, center[1] - 1, true);
        grid.setAlive(center[0], center[1] - 1, true);
        grid.setAlive(center[0] + 1, center[1] - 1, true);
        grid.setAlive(center[0] - 1, center[1], true);
        grid.setAlive(center[0], center[1] + 1, true);
        return grid;
    }

    public GridImpl createSpaceship(GridImpl grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 1 ,1);
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

    private Set<Cell> createCluster(Grid grid) {
        int[] center = getCenter(grid.getColumns(), grid.getRows(), 2 , 2);
        grid.setAlive(center[0], center[1], true);
        grid.setAlive(center[0], center[1], true);
        grid.setAlive(center[0], center[1], true);
        return null;
    }

    private int[] getCenter(int gridX, int gridY, int shapeX, int shapeY) {
        int gameX = ((gridX - shapeX) / 2) + shapeX ;
        int gameY = ((gridY - shapeY) / 2) + shapeY ;
        return new int[]{gameY, gameX}; //TODO überarbeiten sauber colum / row trennen!!!
    }

}
