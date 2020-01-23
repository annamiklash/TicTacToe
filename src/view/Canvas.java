package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static common.Constants.CANVAS_HEIGHT;
import static common.Constants.CANVAS_WIDTH;
import static common.PosArrayHelper.emptyPosArray;

public class Canvas extends JPanel {

    private String[] actualPosArray;
    private String endGameTitle;
    private boolean isGameActive = true;
    private Map<Integer, int[]> coordinateMap;

    public Canvas(String[] actualPosArray) {
        configureMainPanel();
        initCanvasCoordinatesMap();
        this.actualPosArray = actualPosArray;
    }

    public void endGame(String endGameTitle) {
        this.endGameTitle = endGameTitle;
        isGameActive = false;
        this.repaint();
    }


    public void setActualPosArray(String[] actualPosArray) {
        this.actualPosArray = actualPosArray;
    }

    public void setEmptyCanvas() {
        this.actualPosArray = emptyPosArray();
    }

    public void setGameActive(boolean gameActive) {

        isGameActive = gameActive;
    }

    public boolean isGameActive() {
        return isGameActive;
    }

    private void initCanvasCoordinatesMap() {
        coordinateMap = new HashMap<>();

        coordinateMap.put(0, new int[]{0, 0});
        coordinateMap.put(1, new int[]{200, 0});
        coordinateMap.put(2, new int[]{400, 0});
        coordinateMap.put(3, new int[]{0, 200});
        coordinateMap.put(4, new int[]{200, 200});
        coordinateMap.put(5, new int[]{400, 200});
        coordinateMap.put(6, new int[]{0, 400});
        coordinateMap.put(7, new int[]{200, 400});
        coordinateMap.put(8, new int[]{400, 400});
    }

    private void configureMainPanel() {
        this.setLayout(null);
        this.setBackground(Color.CYAN);
        this.setMinimumSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGameActive) {
            printSharp(g);
            printPositions(g);
        } else {
            printEndGameScreen(g);
        }
    }

    private void printEndGameScreen(Graphics g) {
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString(endGameTitle, 100, 270);
        g.drawString("Click anywhere to start a new game!", 20, 300);
    }

    private void printPositions(Graphics g) {
        for (int i = 0; i < actualPosArray.length; i++) {
            final String actualPosition = actualPosArray[i];

            if (!actualPosition.equals("")) {
                int[] coordinates = coordinateMap.get(i);

                if ("X".equals(actualPosition)) {
                    printX(g, coordinates);
                } else if ("O".equals(actualPosition)) {
                    printO(g, coordinates);
                }
            }
        }
    }

    private void printO(Graphics g, int[] coordinates) {
        int X = coordinates[0];
        int Y = coordinates[1];
        g.drawOval(X + 30, Y + 30, 140, 140);
    }

    private void printX(Graphics g, int[] coordinates) {
        int X = coordinates[0];
        int Y = coordinates[1];
        g.drawLine(X, Y, X + 200, Y + 200);
        g.drawLine(X, Y + 200, X + 200, Y);
    }

    private void printSharp(Graphics g) {
        g.drawLine(CANVAS_WIDTH / 3, 0, CANVAS_WIDTH / 3, CANVAS_HEIGHT);
        g.drawLine(CANVAS_WIDTH / 3 * 2, 0, CANVAS_WIDTH / 3 * 2, CANVAS_HEIGHT);
        g.drawLine(0, CANVAS_HEIGHT / 3, CANVAS_WIDTH, CANVAS_HEIGHT / 3);
        g.drawLine(0, CANVAS_HEIGHT / 3 * 2, CANVAS_WIDTH, CANVAS_HEIGHT / 3 * 2);
    }


}
