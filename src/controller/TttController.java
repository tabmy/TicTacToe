package controller;

/**
 * @author Abelsen, Tommy
 */


import data.TttGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import view.TttAppearance;

import java.net.URL;
import java.util.ResourceBundle;

public class TttController implements Initializable {

    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private Canvas playerCanvas;
    @FXML
    private Canvas winnerCanvas;
    @FXML
    private ColorPicker xColor;
    @FXML
    private ColorPicker oColor;
    @FXML
    private ColorPicker backgroundColor;
    @FXML
    private ColorPicker gridColor;
    @FXML
    private RadioButton xTurnButton;
    @FXML
    private RadioButton oTurnButton;
    @FXML
    private Label xWonTimes;
    @FXML
    private Label oWonTimes;

    private GraphicsContext bgGc, playerGc, winnerGc;
    private TttAppearance appearance;
    private TttGame game;

    private double firstRowCol, middleRowCol, lastRowCol;
    private boolean hasWon = false;
    private char playerTurn = 'X'; // Set to X starting the game as default.

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bgGc = backgroundCanvas.getGraphicsContext2D();
        playerGc = playerCanvas.getGraphicsContext2D();
        winnerGc = winnerCanvas.getGraphicsContext2D();
        appearance = new TttAppearance();

        firstRowCol = playerCanvas.getWidth() / 6;
        middleRowCol = playerCanvas.getWidth() / 2;
        lastRowCol = playerCanvas.getWidth() / 1.2;

        xTurnButton.setSelected(true);

        resetColors();
    }

    @FXML
    public void changePlayerTurn() {

        if (playerTurn == 'X') {
            playerTurn = 'O';

            xTurnButton.setSelected(false);
            oTurnButton.setSelected(true);
        } else if (playerTurn == 'O') {
            playerTurn = 'X';

            oTurnButton.setSelected(false);
            xTurnButton.setSelected(true);
        }
    }

    @FXML
    public void drawFigure(MouseEvent event) {
        if (!hasWon) {
            double xPos = event.getX();
            double yPos = event.getY();
            if (event.getButton() == MouseButton.PRIMARY && game.setGameMove(gamePos(yPos), gamePos(xPos), playerTurn)) {

                if (playerTurn == 'X') {
                    drawCross(rowColPos(xPos), rowColPos(yPos));
                } else if (playerTurn == 'O') {
                    drawCircle(rowColPos(event.getX()), rowColPos(event.getY()));
                }

                changePlayerTurn();
                //System.out.println(game);

                int[] winner = game.hasWon();
                if (winner != null) {
                    hasWon = true;
                    for (int i : winner) {
                        drawWinner(i);
                    }
                }
            }
        }
    }

    private int gamePos(double d) {
        if (d < 150)
            return 0;
        else if (d > 300)
            return 2;
        else return 1;
    }

    private double rowColPos(double d) {
        if (d < 150)
            return firstRowCol;
        else if (d > 300)
            return lastRowCol;
        else return middleRowCol;
    }

    private void drawCross(double x, double y) {
        playerGc.setStroke(appearance.getXColor());
        playerGc.setLineWidth(10);
        playerGc.clearRect(x - 60, y - 60, 120, 120);
        playerGc.strokeLine(x - 50, y - 50, x + 50, y + 50);
        playerGc.strokeLine(x - 50, y + 50, x + 50, y - 50);
    }

    private void drawCircle(double x, double y) {
        playerGc.setStroke(appearance.getOColor());
        playerGc.setLineWidth(10);
        playerGc.clearRect(x - 60, y - 60, 120, 120);
        playerGc.strokeOval(x - 50, y - 50, 100, 100);
    }

    private void drawAll(char turn) {
        playerGc.setLineWidth(10);
        char[][] gameArray = game.getGameMove();
        double x = 0, y = 0;

        for (int i = 0; i < gameArray.length; i++) {
            for (int j = 0; j < gameArray.length; j++) {
                if (gameArray[i][j] == turn) {
                    switch (j) {
                        case 0:
                            x = firstRowCol;
                            break;
                        case 1:
                            x = middleRowCol;
                            break;
                        case 2:
                            x = lastRowCol;
                            break;
                    }
                    switch (i) {
                        case 0:
                            y = firstRowCol;
                            break;
                        case 1:
                            y = middleRowCol;
                            break;
                        case 2:
                            y = lastRowCol;
                            break;
                    }
                    if (turn == 'X') {
                        drawCross(x, y);
                    } else if (turn == 'O') {
                        drawCircle(x, y);
                    }
                }
            }
        }
    }

    private void drawWinner(int x) {

        switch (x) {

            // Diagonal Winner graphics:

            case 1: { // Diagonal winner: (1,1) -> (3,3)
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.rotate(45);
                winnerGc.strokeOval(25, -62.5, Math.sqrt(2) * 450 - 50, 125);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(25, -62.5, Math.sqrt(2) * 450 - 50, 125);
                winnerGc.rotate(-45);
            }
            break;

            case 2: { // Diagonal winner: (3, 1) -> (1, 3)
                winnerGc.rotate(135);
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.strokeOval(-292.5, -380, Math.sqrt(2) * 450 - 50, 125);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(-292.5, -380, Math.sqrt(2) * 450 - 50, 125);
                winnerGc.rotate(-135);
            }
            break;

            // Horizontal winner graphics.

            case 3: {   // (1, 1) -> (1, 3)
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.strokeOval(10, 12.5, 430, 125);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(10, 12.5, 430, 125);
            }
            break;

            case 4: {   //(2, 1) -> (2, 3)
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.strokeOval(10, 162.5, 430, 125);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(10, 162.5, 430, 125);
            }
            break;

            case 5: {   //(3, 1) -> (3, 3)
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.strokeOval(10, 312.5, 430, 125);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(10, 312.5, 430, 125);
            }
            break;

            // Vertical winner graphics

            case 6: {   // (1, 1) -> (3, 1)
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.strokeOval(12.5, 10, 125, 430);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(12.5, 10, 125, 430);
            }
            break;

            case 7: {   // (1, 2) -> (3, 2)
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.strokeOval(162.5, 10, 125, 430);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(162.5, 10, 125, 430);
            }
            break;

            case 8: {   // (1, 3) -> (3, 3)
                winnerGc.setStroke(Color.BLACK);
                winnerGc.setLineWidth(12);
                winnerGc.strokeOval(312.5, 10, 125, 430);
                winnerGc.setStroke(Color.GOLD);
                winnerGc.setLineWidth(8);
                winnerGc.strokeOval(312.5, 10, 125, 430);
            }
            break;
        }
        updateWinnerLabels();
    }

    private void updateWinnerLabels() {
        byte xWonTimes = game.getxWonTimes();
        byte oWonTimes = game.getoWonTimes();

        if (xWonTimes != 1 && xWonTimes >= 0) {
            this.xWonTimes.setText("X has won: " + xWonTimes + " times.");
        } else if (xWonTimes == 1) {
            this.xWonTimes.setText("X has won: " + xWonTimes + " time.");
        }
        if (oWonTimes != 1 && oWonTimes >= 0) {
            this.oWonTimes.setText("O has won: " + oWonTimes + " times.");
        } else if (oWonTimes == 1) {
            this.oWonTimes.setText("O has won: " + oWonTimes + " time.");
        }
    }

    @FXML
    public void resetCounters() {
        game.clearWonTimes();
        updateWinnerLabels();
    }

    @FXML
    public void changeXColor() {
        appearance.setXColor(xColor.getValue());
        drawAll('X');
    }

    @FXML
    public void changeOColor() {
        appearance.setOColor(oColor.getValue());
        drawAll('O');
    }

    @FXML
    public void changeBackgroundColor() {
        appearance.setBackgroundColor(backgroundColor.getValue());
        drawBoard();
    }

    @FXML
    public void changeGridColor() {
        appearance.setGridColor(gridColor.getValue());
        drawBoard();
    }

    @FXML
    public void clearBoard() {
        playerGc.clearRect(0, 0, 450, 450);
        winnerGc.clearRect(0, 0, 450, 450);
        hasWon = false;
        game = new TttGame();
    }

    @FXML
    public void drawBoard() {
        bgGc.setFill(appearance.getBackgroundColor());
        bgGc.setStroke(appearance.getGridColor());
        bgGc.setLineWidth(3);

        bgGc.fillRect(0, 0, 450, 450); // Background color of the board
        bgGc.strokeRect(0, 0, 450, 450); // Grid outline of the board
        bgGc.strokeLine(150, 3, 150, 450); // First Vertical line
        bgGc.strokeLine(300, 3, 300, 450); // Second vertical line
        bgGc.strokeLine(0, 150, 450, 150); // First Horizontal line
        bgGc.strokeLine(0, 300, 450, 300); // Second Horizontal line
    }

    @FXML
    public void resetColors() {
        appearance.setXColor(Color.BLACK);
        appearance.setOColor(Color.BLACK);
        appearance.setBackgroundColor(Color.WHITE);
        appearance.setGridColor(Color.BLACK);

        xColor.setValue(appearance.getXColor());
        oColor.setValue(appearance.getOColor());
        backgroundColor.setValue(appearance.getBackgroundColor());
        gridColor.setValue(appearance.getGridColor());

        drawBoard();
        clearBoard();
    }

}
