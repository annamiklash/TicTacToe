package controller;

import view.MainFrame;

import java.util.ArrayList;
import java.util.List;

import static common.PosArrayHelper.emptyPosArray;

public class Controller {

    private final List<int[]> intervalForPositionsList = new ArrayList<>();
    private final List<int[]> winnigPositionsList = new ArrayList<>();

    private final MainFrame view;
    private String[] actualPosArray;
    private boolean isX = true;

    public Controller(MainFrame view) {
        this.view = view;
        this.actualPosArray = emptyPosArray();
        view.initializeCanvasMouseListener(this);
        initializeIntervalForPositionList();
        initializeWinningPositionsList();
    }


    public int getClickedSquareNumber(int x, int y) {
        for (int[] interval : intervalForPositionsList) {
            if (x >= interval[0] && x <= interval[2]) {
                if (y >= interval[1] && y <= interval[3]) {
                    return intervalForPositionsList.indexOf(interval);
                }
            }
        }
        return 0;
    }

    public void processNewClick(int clickedPosition) {
        final String currentPlayer = getCurrentPlayer(isX);
        if (actualPosArray[clickedPosition].equals("")) {
            actualPosArray[clickedPosition] = currentPlayer;

            for (int[] winningPositionArray : winnigPositionsList) {

                if (actualPosArray[winningPositionArray[0]].equals(currentPlayer)
                        && actualPosArray[winningPositionArray[1]].equals(currentPlayer)
                        && actualPosArray[winningPositionArray[2]].equals(currentPlayer)) {
                    view.endGame("Player " + currentPlayer + " has won!");
                    return;
                }

            }

            if (isTie()) {
                view.endGame("You tied");
                return;
            }


            view.printNewPosArray(actualPosArray);

            isX = !isX;
        }
    }


    public MainFrame getView() {
        return view;
    }

    public void gameOver() {
        isX = true;
        actualPosArray = emptyPosArray();
        this.view.startNewGame();

    }

    private void initializeWinningPositionsList() {
        winnigPositionsList.add(new int[]{0, 1, 2});
        winnigPositionsList.add(new int[]{3, 4, 5});
        winnigPositionsList.add(new int[]{6, 7, 8});
        winnigPositionsList.add(new int[]{0, 3, 6});
        winnigPositionsList.add(new int[]{1, 4, 7});
        winnigPositionsList.add(new int[]{2, 5, 8});
        winnigPositionsList.add(new int[]{0, 4, 8});
        winnigPositionsList.add(new int[]{2, 4, 6});

    }


    private void initializeIntervalForPositionList() {
        intervalForPositionsList.add(0, new int[]{0, 0, 200, 200});
        intervalForPositionsList.add(1, new int[]{200, 0, 400, 200});
        intervalForPositionsList.add(2, new int[]{400, 0, 600, 200});
        intervalForPositionsList.add(3, new int[]{0, 200, 200, 400});
        intervalForPositionsList.add(4, new int[]{200, 200, 400, 400});
        intervalForPositionsList.add(5, new int[]{400, 200, 600, 400});
        intervalForPositionsList.add(6, new int[]{0, 400, 200, 600});
        intervalForPositionsList.add(7, new int[]{200, 400, 400, 600});
        intervalForPositionsList.add(8, new int[]{400, 400, 600, 600});

    }


    private boolean isTie() {
        for (String position : actualPosArray) {
            if ("".equals(position)) {
                return false;
            }
        }
        return true;
    }

    private String getCurrentPlayer(boolean isX) {
        if (isX) {
            return "X";
        } else {
            return "O";
        }
    }

}
