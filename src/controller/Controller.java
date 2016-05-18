package controller;

/**
 *  @author Abelsen, Tommy
 */

import data.Game;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import view.Appearance;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private char playerTurn = 'X'; // Set to X starting the game as default.
    private Game game;

    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private Canvas playerCanvas;
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

    private GraphicsContext bgGc;
    private GraphicsContext playerGc;
    private Appearance appearance;

    private double firstRowCol;
    private double middleRowCol;
    private double lastRowCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bgGc = backgroundCanvas.getGraphicsContext2D();
        playerGc = playerCanvas.getGraphicsContext2D();
        appearance = new Appearance();

        firstRowCol = playerCanvas.getWidth()/6;
        middleRowCol = playerCanvas.getWidth()/2;
        lastRowCol = playerCanvas.getWidth()/1.2;

        xTurnButton.setSelected(true);

        resetColors();
    }

    private void changePlayerTurn(){
        if (playerTurn == 'X'){
            playerTurn = 'O';

            xTurnButton.setSelected(false);
            oTurnButton.setSelected(true);
        }
        else if (playerTurn == 'O'){
            playerTurn = 'X';

            oTurnButton.setSelected(false);
            xTurnButton.setSelected(true);
        }
    }

    @FXML
    public void drawFigure(MouseEvent event){
        double x = event.getX();
        double y = event.getY();
        if (event.getButton() == MouseButton.PRIMARY && game.setGameMove(gamePos(y), gamePos(x), playerTurn)) {
            playerGc.setLineWidth(10);

            if (playerTurn == 'X') {
                drawX(rowColPos(x), rowColPos(y));
            }
            else if (playerTurn == 'O'){
                drawO(rowColPos(event.getX()), rowColPos(event.getY()));
            }

            changePlayerTurn();
            System.out.println(game);
        }
        else if (event.getButton() == MouseButton.SECONDARY){
            drawWinner();
        }

    }

    private int gamePos(double x){
        if (x < 150)
            return 0;
        else if (x > 300)
            return 2;
        else return 1;
    }

    private double rowColPos(double x){
        if (x < 150)
            return firstRowCol;
        else if (x > 300)
            return lastRowCol;
        else return middleRowCol;
    }

    private void drawX(double x, double y){
        playerGc.setStroke(appearance.getXColor());
        playerGc.clearRect(x - 60,y - 60, 120, 120);
        playerGc.strokeLine(x - 50, y - 50, x + 50, y + 50);
        playerGc.strokeLine(x - 50, y + 50, x + 50, y - 50);
    }

    private void drawO(double x, double y){
        playerGc.setStroke(appearance.getOColor());
        playerGc.clearRect(x - 60,y - 60, 120, 120);
        playerGc.strokeOval(x - 50,y - 50 , 100, 100);
    }

    private void drawAll(char turn){
        playerGc.setLineWidth(10);
        char[][] gameArray = game.getGameMove();
        double x = 0, y = 0;

        for(int i = 0; i < gameArray.length; i++){
            for (int j = 0; j < gameArray.length; j++){
                if(gameArray[i][j] == turn){
                    switch (j){
                        case 0: x = firstRowCol;
                            break;
                        case 1: x = middleRowCol;
                            break;
                        case 2: x = lastRowCol;
                            break;
                    }
                    switch (i){
                        case 0: y = firstRowCol;
                            break;
                        case 1: y = middleRowCol;
                            break;
                        case 2: y = lastRowCol;
                            break;
                    }
                    if (turn == 'X') {
                        drawX(x, y);
                    } else if(turn == 'O'){
                        drawO(x, y);
                    }
                }
            }
        }
    }

    private void drawWinner(){


        { //(1,1) -> (3,3)
            playerGc.setStroke(Color.BLACK);
            playerGc.setLineWidth(12);
            playerGc.rotate(45);
            playerGc.strokeOval(25, -50, Math.sqrt(2) * 450 - 50, 100);
            playerGc.setStroke(Color.GOLD);
            playerGc.setLineWidth(8);
            playerGc.strokeOval(25, -50, Math.sqrt(2) * 450 - 50, 100);
            playerGc.rotate(-45);
        }



        { // (3, 1) -> (1, 3)
            playerGc.rotate(-45);
            playerGc.setStroke(Color.BLACK);
            playerGc.setLineWidth(12);
            playerGc.strokeOval(-299, 244, Math.sqrt(2) * 450 - 39, 150);
            playerGc.setStroke(Color.GOLD);
            playerGc.setLineWidth(8);
            playerGc.strokeOval(-299, 244, Math.sqrt(2) * 450 - 39, 150);
            playerGc.rotate(45);
        }


        { // Horizontal winner graphics.
            playerGc.setStroke(Color.BLACK);
            playerGc.setLineWidth(12);
            playerGc.strokeOval(10, 12.5, 430, 125); // (1, 1) -> (1, 3)
            playerGc.strokeOval(10, 162.5, 430, 125); //(2, 1) -> (2, 3)
            playerGc.strokeOval(10, 312.5, 430, 125); //(3, 1) -> (3, 3)
            playerGc.setStroke(Color.GOLD);
            playerGc.setLineWidth(8);
            playerGc.strokeOval(10, 12.5, 430, 125);
            playerGc.strokeOval(10, 162.5, 430, 125);
            playerGc.strokeOval(10, 312.5, 430, 125);
        }


        { // Vertical winner graphics
            playerGc.setStroke(Color.BLACK);
            playerGc.setLineWidth(12);
            playerGc.strokeOval(12.5, 10, 125, 430); // (1, 1) -> (3, 1)
            playerGc.strokeOval(162.5, 10, 125, 430);// (1, 2) -> (3, 2)
            playerGc.strokeOval(312.5, 10, 125, 430);// (1, 3) -> (3, 3)
            playerGc.setStroke(Color.GOLD);
            playerGc.setLineWidth(8);
            playerGc.strokeOval(12.5, 10, 125, 430);
            playerGc.strokeOval(162.5, 10, 125, 430);
            playerGc.strokeOval(312.5, 10, 125, 430);
        }


    }

    @FXML
    public void changeXColor(Event xcolor){
       appearance.setXColor(xColor.getValue());
        drawAll('X');
    }

    @FXML
    public void changeOColor(Event ocolor){
        appearance.setOColor(oColor.getValue());
        drawAll('O');
    }

    @FXML
    public void changeBackgroundColor(Event bgcolor){
        appearance.setBackgroundColor(backgroundColor.getValue());
        drawBoard();
    }

    @FXML
    public void changeGridColor(Event gridcolor){
        appearance.setGridColor(gridColor.getValue());
        drawBoard();
    }

    @FXML
    public void clearBoard(){
        playerGc.clearRect(0,0,500,500);
        game = new Game();
    }

    @FXML
    public void drawBoard() {
        bgGc.setFill(appearance.getBackgroundColor());
        bgGc.setStroke(appearance.getGridColor());
        bgGc.setLineWidth(3);

        bgGc.fillRect(0, 0, 450, 450); // Background color of the board
        bgGc.strokeRect(0,0,450,450); // Grid outline of the board
        bgGc.strokeLine(150,3,150,450); // First Vertical line
        bgGc.strokeLine(300,3,300,450); // Second vertical line
        bgGc.strokeLine(0, 150, 450, 150); // First Horizontal line
        bgGc.strokeLine(0, 300, 450, 300); // Second Horizontal line
    }

    @FXML
    public void resetColors(){
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
