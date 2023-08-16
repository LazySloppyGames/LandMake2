import com.badlogic.gdx.Gdx;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Land {

    private int landRows;
    private int landCols;
    private final int totalLand;
    private final int maxGrowth;
    private int currentLand;
    private final int[][] landGrid;

    public Land(int rows, int cols, double percent) {
        landRows = rows;
        if (landRows < 10)
            landRows = 10;
        landCols = cols;
        if (landCols < 10)
            landCols = 10;
        if (percent != 0.16 && percent != 0.24 && percent != 0.32)
            percent = 0.24;
        landGrid = new int[landRows][landCols];
        this.totalLand = (int)((double)rows * (double)cols * percent);
        this.maxGrowth = (int)((double)landRows * (double)landCols * 0.045); // 4.5% of total land
    }

    public void showLand() {
        for (int row = 0; row < landRows; row++) {
            for (int col = 0; col < landCols; col++) {
                System.out.print(landGrid[row][col]);
            }
            System.out.println();
        }
    }

    public void countLand() {
        currentLand = 0;
        for (int row = 0; row < landRows; row++) {
            for (int col = 0; col < landCols; col++) {
                if (landGrid[row][col] != 0)
                    currentLand++;
            }
        }
    }

    public void createLand() {

        Random random = new Random();

        // 20 buffer of 3: 0 - 19  3 to 16   (16 - 3 + 1) + 3  (0 - 19) + 3 ( int randomNumber = random.nextInt(max - min + 1) + min;

        do {
            // Distance from the edge to start building
            int edgeBuffer = 3;

            // Choose random start location
            // Formula is rnd(max - min + 1) + min
            int c = random.nextInt(landCols - edgeBuffer * 2) + edgeBuffer;
            // rnd(max - 1(0 to n-1) - 3 (buffer) - 3(min) + 1(offset 0 min)) + 3(main)
            int r = random.nextInt(landRows - edgeBuffer * 2) + edgeBuffer;

            // Size to grow this land piece for
            int landGrowth = random.nextInt(maxGrowth) + 1;

            do {
                // Increase the land elevation when you add it
                if (landGrid[r][c]< 9)
                    landGrid[r][c]++;
                if (landGrid[r + 1][c]< 9)
                    landGrid[r + 1][c]++;
                if (landGrid[r][c + 1]< 9)
                    landGrid[r][c + 1]++;

                // Move land drawing cursor one square at random
                int nextDirection = random.nextInt(4);
                switch (nextDirection) {
                    case 0 -> c--;
                    case 1 -> r--;
                    case 2 -> c++;
                    case 3 -> r++;
                }

                // If reach the outer ring of the world, quit
                if (c == (landCols - 1) || c == 0 || r == (landRows - 1) || r == 0)
                    break;

                landGrowth--;
            } while (landGrowth != 0);
            countLand();
        } while (currentLand < totalLand);
    }

    public int[][] getLandGrid() {
        return landGrid;
    }
}
