package controller;

import data.C4Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class C4Controller implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(C4Controller.class.getName());


    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private Canvas playerCanvas;
    @FXML
    private Button clearButton;
    @FXML
    private Button resetScore;
    @FXML
    private Label blueWins;
    @FXML
    private Label redWins;
    @FXML
    private Label gameLabel;

    private GraphicsContext backgroundGc;
    private GraphicsContext playerGc;
    private C4Game game;

    private int boardRows;
    private int boardCols;
    private boolean hasWon;

    private int blueWin;
    private int redWin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.setLevel(Level.INFO);
        LOGGER.info("Initializing C4Controller");

        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        playerGc = playerCanvas.getGraphicsContext2D();
        game = new C4Game();

        boardRows = game.getHeight();
        boardCols = game.getWidth();

        reset();

        gameLabel.setFont(Font.font(22));
        setGameLabel();
    }

    private void reset() {
        game.newBoard();
        drawBoard();
        clearScore();
    }

    private void setGameLabel() {
        char c = game.getPlayerTurn();
        if (c == 'B') {
            gameLabel.setText("Blue player to select");
        } else if (c == 'R') {
            gameLabel.setText("Red player to select");
        }
    }

    public void clearScore() {
        blueWin = redWin = 0;
        blueWins.setText("Blue wins: 0");
        redWins.setText("Red wins: 0");
    }

    private void updateWinner() {
        char win = game.getPlayerTurn();
        if (win == 'B') {
            blueWin++;
            gameLabel.setText("Blue player won!");
            blueWins.setText("Blue wins: " + blueWin);
        } else if (win == 'R') {
            redWin++;
            gameLabel.setText("Red player won!");
            redWins.setText("Red wins: " + redWin);
        }
        clearButton.setText("Play Again!");
    }

    @FXML
    public void drawFigure(MouseEvent mouseEvent) {

        if (hasWon) return;
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {

            double X = mouseEvent.getX();

            int column = game.getColumn(getXPos(X));
            int row = game.getRow(column);

            if (game.setGameMove(game.getColumn(getXPos(X)))) {
                hasWon = game.hasWon();
                draw();

                if (hasWon) {
                    updateWinner();
                } else {
                    game.changePlayerTurn();
                    setGameLabel();
                }
            }
        }
    }


    /**
     * Draws the game board to visually represent the 'C4Game'-array board correctly.
     */
    private void draw() {
        playerGc.clearRect(0, 0, playerCanvas.getWidth(), playerCanvas.getHeight());

        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardCols; j++) {
                char square = game.getSquare(i, j);
                Paint squareColor;
                if (square == 'R') {
                    squareColor = Color.DARKRED;
                } else if (square == 'B') {
                    squareColor = Color.DARKBLUE;
                } else {
                    squareColor = Color.GRAY;
                }

                playerGc.setFill(squareColor);
                playerGc.fillOval(getXPos(j) - 25, getYPos(i) - 25, 50, 50);
            }

        }
    }

    private double getXPos(int x) {
        double width = playerCanvas.getWidth();
        switch (x) {
            case 0:
                return width / 7 * 0.5;
            case 1:
                return width / 7 * 1.5;
            case 2:
                return width / 7 * 2.5;
            case 3:
                return width / 7 * 3.5;
            case 4:
                return width / 7 * 4.5;
            case 5:
                return width / 7 * 5.5;
            case 6:
                return width / 7 * 6.5;

        }
        return -1;
    }

    private double getYPos(int y) {
        double height = playerCanvas.getHeight();
        switch (y) {
            case 0:
                return height / 6 * 0.5;
            case 1:
                return height / 6 * 1.5;
            case 2:
                return height / 6 * 2.5;
            case 3:
                return height / 6 * 3.5;
            case 4:
                return height / 6 * 4.5;
            case 5:
                return height / 6 * 5.5;
        }
        return -1;
    }

    private double getXPos(double x) {
        double width = playerCanvas.getWidth();

        if (x < width / 7) {
            return width / 7 * 0.5;
        } else if (x < width / 7 * 2) {
            return width / 7 * 1.5;
        } else if (x < width / 7 * 3) {
            return width / 7 * 2.5;
        } else if (x < width / 7 * 4) {
            return width / 7 * 3.5;
        } else if (x < width / 7 * 5) {
            return width / 7 * 4.5;
        } else if (x < width / 7 * 6) {
            return width / 7 * 5.5;
        } else if (x < width / 7 * 7) {
            return width / 7 * 6.5;
        } else return x;
    }
    /*
    private double getYPos(double y){
        double height = playerCanvas.getHeight();

        if (y < height/6 * 1.05){
            return height/6 * 0.5;
        }
        else if (y < height/6 * 2.05) {
            return height / 6 * 1.5;
        }
        else if (y < height/6 * 3.05){
            return height/6 * 2.5;
        }
        else if (y < height/6 * 4.05){
            return height/6 * 3.5;
        }
        else if (y < height/6 * 5.05){
            return height/6 * 4.5;
        }
        else if (y < height/6 * 6.05){
            return height/6 * 5.5;
        }

        else return y;
    }
    */

    @FXML
    public void clearBoard() {
        playerGc.clearRect(0, 0, playerCanvas.getWidth(), playerCanvas.getHeight());
        game.newBoard();
        hasWon = false;
        clearButton.setText("Clear Board");
        game.changePlayerTurn();
        setGameLabel();
    }

    public void drawBoard() {
        double height = backgroundCanvas.getHeight();
        double width = backgroundCanvas.getWidth();
        int rows = 6;
        int columns = 7;
        double lineWidth = 3;

        backgroundGc.setFill(Color.GREY);
        backgroundGc.setStroke(Color.BLACK);
        backgroundGc.setLineWidth(lineWidth);
        backgroundGc.fillRect(0, 0, width, height);

        for (int i = 0; i < rows; i++) {
            backgroundGc.strokeLine(0, (height / rows) * i, width, (height / rows) * i);
        }
        backgroundGc.strokeLine(0, height, width, height);

        for (int i = 0; i < columns; i++) {
            backgroundGc.strokeLine((width / columns) * i, 0, (width / columns) * i, height);
        }
        backgroundGc.strokeLine(width, 0, width, height);
    }

}
