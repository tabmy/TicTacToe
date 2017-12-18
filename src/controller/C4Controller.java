package controller;

import data.C4Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class C4Controller implements Initializable{

    private static final Logger LOGGER = Logger.getLogger(C4Controller.class.getName());


    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private Canvas playerCanvas;

    private GraphicsContext backgroundGc;
    private GraphicsContext playerGc;
    private C4Game game;

    private int boardrows;
    private int boardcols;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        LOGGER.setLevel(Level.INFO);
        LOGGER.info("Initializing C4Controller");

        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        playerGc = playerCanvas.getGraphicsContext2D();
        game = new C4Game();

        game.fillGameBoard();

        boardrows = game.getHeight();
        boardcols = game.getWidth();

        drawBoard();
    }


    @FXML
    public void drawFigure(MouseEvent mouseEvent){
         char playerTurn = game.getPlayerTurn();

        if (mouseEvent.getButton() == MouseButton.PRIMARY){


            double X = mouseEvent.getX();
            double Y = mouseEvent.getY();

            int column = game.getColumn(getXPos(X));
            int row = game.getRow(column);

            if (game.setGameMove(game.getColumn(getXPos(X)))) {
               /* if (playerTurn == 'R') {
                    playerGc.setFill(Color.DARKRED);
                } else if (playerTurn == 'B') {
                    playerGc.setFill(Color.DARKBLUE);
                }
                playerGc.clearRect(getXPos(X) - 25, getYPos(Y) - 25, 50, 50);
                playerGc.fillOval(getXPos(X) - 25, getYPos(Y) - 25, 50, 50);
                */

               draw();

                game.changePlayerTurn();
            }

            //System.out.println("x: " + mouseEvent.getX() + "\ny: " + mouseEvent.getY() + "\n");
            //System.out.println(getXPos(X));
            //System.out.println(game.getColumn(getXPos(X)));
            System.out.println(game);
            System.out.println(row + "\n" + column);


        }
    }

    /**
     * Draws the game board to visually represent the 'C4Game'-array board correctly.
     */
    private void draw(){
        playerGc.clearRect(0,0, playerCanvas.getWidth(), playerCanvas.getHeight());

        for (int i = 0; i < boardrows ; i++) {
            for (int j = 0; j < boardcols; j++) {
                char square = game.getSquare(i,j);
                Paint squareColor;
                if (square == 'R'){ squareColor = Color.DARKRED;}
                else if (square == 'B'){ squareColor = Color.DARKBLUE;}
                else {
                    squareColor = Color.GRAY;}

                playerGc.setFill(squareColor);
                playerGc.fillOval( getXpos(j) - 25, getYpos(i) - 25, 50, 50);
            }

        }
    }

    private double getXpos(int x){
        double width = playerCanvas.getWidth();
        switch (x){
            case 0: return width/7 * 0.5;
            case 1: return width/7 * 1.5;
            case 2: return width/7 * 2.5;
            case 3: return width/7 * 3.5;
            case 4: return width/7 * 4.5;
            case 5: return width/7 * 5.5;
            case 6: return width/7 * 6.5;

        }
        return -1;
    }

    private double getYpos(int y){
        double height = playerCanvas.getHeight();
        switch (y){
            case 0: return height/6 * 0.5;
            case 1: return height/6 * 1.5;
            case 2: return height/6 * 2.5;
            case 3: return height/6 * 3.5;
            case 4: return height/6 * 4.5;
            case 5: return height/6 * 5.5;
        }
        return -1;
    }

    private double getXPos(double x){
        double width = playerCanvas.getWidth();

        if (x < width/7){
            return width/7 * 0.5;
        }
        else if (x < width/7 * 2){
            return width/7 * 1.5;
        }
        else if (x < width/7 * 3){
            return width/7 * 2.5;
        }
        else if (x < width/7 * 4){
            return width/7 * 3.5;
        }
        else if (x < width/7 * 5){
            return width/7 * 4.5;
        }
        else if (x < width/7 * 6){
            return width/7 * 5.5;
        }
        else if (x < width/7 * 7){
            return width/7 * 6.5;
        }

        else return x;
    }

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

    @FXML
    public void clearBoard(){
        playerGc.clearRect(0,0,playerCanvas.getWidth(), playerCanvas.getHeight());
    }

    public void drawBoard(){
        double height = backgroundCanvas.getHeight();
        double width = backgroundCanvas.getWidth();
        int rows = 6;
        int columns = 7;
        double lineWidth = 3;

        backgroundGc.setFill(Color.GREY);
        backgroundGc.setStroke(Color.BLACK);
        backgroundGc.setLineWidth(lineWidth);
        backgroundGc.fillRect(0, 0, width, height);

        for(int i = 0; i < rows; i++){
            backgroundGc.strokeLine(0, (height/rows) * i , width, (height/rows) * i);
        }
        backgroundGc.strokeLine(0, height, width, height);

        for(int i = 0; i < columns; i++){
            backgroundGc.strokeLine((width/columns) * i, 0, (width/columns) * i, height);
        }
        backgroundGc.strokeLine(width , 0, width , height );
    }

}
