package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickOnCanvasListener implements MouseListener {

    private final Controller controller;

    public MouseClickOnCanvasListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        int X = e.getX();
        int Y = e.getY();

        boolean isGameOver = controller.getView().isGameOver();

        System.out.println("X = " + X + " Y = " + Y);
        if (!isGameOver) {
            int clickedPosition = controller.getClickedSquareNumber(X, Y);
            System.out.println("clicked square number is " + clickedPosition);
            controller.processNewClick(clickedPosition);
        } else {
            System.out.println("New game has started!");
            controller.gameOver();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
