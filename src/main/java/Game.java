import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

    // Make sure all of these are in dispose() function to avoid memory leaks!
    private SpriteBatch batch;
    private Texture landTexture;
    private Texture waterTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Load the image file into a FileHandle object.
        FileHandle waterImage = Gdx.files.internal("water.png");
        FileHandle landImage = Gdx.files.internal("land.png");

        // Pass the FileHandle object to the Texture constructor.
        landTexture = new Texture(landImage);
        waterTexture = new Texture(waterImage);
    }

    @Override
    public void render() {
        // Clear the screen.
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the image to the screen. Remember, Bottom-left is (0,0).
        // Image is drawn from bottom-left too, so 0,0 draws the image with its bottom-left at (0,0)
        // Draw matrix from the bottom up
        batch.begin();
        int[][] land = Main.getLand();
        int rows = Main.getLandRows();
        int cols = Main.getLandColumns();
        // Draw the land
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = 0; c < cols; c++) {
                if (land[r][c] > 0)
                    batch.draw(landTexture, Main.getTileSize() * c, ((rows - 1) - r) * Main.getTileSize());
                else
                    batch.draw(waterTexture, Main.getTileSize() * c, ((rows - 1) - r) * Main.getTileSize());
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        // Dispose of the texture when we are finished with it.
        waterTexture.dispose();
        landTexture.dispose();
        batch.dispose();
    }
}
