package view;

import controller.Controller;
import controller.MouseClickOnCanvasListener;

import javax.swing.*;
import java.awt.*;

import static common.Constants.FRAME_HEIGHT;
import static common.Constants.FRAME_WIDTH;
import static common.PosArrayHelper.emptyPosArray;

public class MainFrame extends JFrame {

    private final Canvas canvas;

    public MainFrame() {

        canvas = new Canvas(emptyPosArray());
        configureMainFrame();

        configureCanvas(canvas);
    }

    public void initializeCanvasMouseListener(Controller controller) {
        canvas.addMouseListener(new MouseClickOnCanvasListener(controller));
    }

    /**
     * Метод, который рисует новое поле с данными, которые передаются в аргументе.
     *
     * @param actualPosArray
     */
    public void printNewPosArray(String[] actualPosArray) {
        canvas.setActualPosArray(actualPosArray);
        canvas.repaint();
    }

    /**
     * Метод, который следует вызвать, когда игра закончилась
     *
     * @param winPlayer - выигравший игрок
     */
    public void endGame(String endGameTitle) {
        canvas.endGame(endGameTitle);
    }

    public void startNewGame() {
        canvas.setEmptyCanvas();
        canvas.setGameActive(true);
        canvas.repaint();
    }

    public boolean isGameOver() {
        return !canvas.isGameActive();
    }

    private void configureCanvas(Canvas canvas) {
        this.add(canvas);
    }

    private void configureMainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setTitle("TicTacToe");
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }
}
