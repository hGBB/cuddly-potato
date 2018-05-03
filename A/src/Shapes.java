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

    public Set<Cell> createBoat(Grid grid) {
        return null;
    }

    public Set<Cell> createBlinker(Grid grid) {
        return null;
    }

    public Set<Cell> createToad(Grid grid) {
        return null;
    }

    public Set<Cell> createGlider(Grid grid) {
        return null;
    }

    private Set<Cell> createSpaceship(Grid grid) {
        return null;
    }

    private Set<Cell> createCluster(Grid grid) {
        return null;
    }

    private int[] getCenter(int gridX, int gridY, int shapeX, int shapeY) {
        int gameX = ((gridX - shapeX) / 2) + shapeX ;
        int gameY = ((gridY - shapeY) / 2) + shapeY ;
        return new int[]{gameX, gameY};
    }

}
