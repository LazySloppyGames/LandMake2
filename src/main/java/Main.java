import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

    private static Land land1;
    private static final int landRows = 20;
    private static final int landColumns = 40;
    private static final int tileSize = 32;

    public static void main(String[] args) {
        System.out.println("Creating a new world...\n");

        land1 = new Land(landRows, landColumns, 0.16);
        land1.createLand();
        land1.showLand();
        configureGameWindow();

    }

    private static void configureGameWindow() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Game");
        config.setWindowedMode(landColumns * tileSize, landRows * tileSize);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new Game(), config);
    }

    public static int[][] getLand() {
        return land1.getLandGrid();
    }

    public static int getLandRows() {
        return landRows;
    }

    public static int getLandColumns() {
        return landColumns;
    }

    public static int getTileSize() { return tileSize; }
}