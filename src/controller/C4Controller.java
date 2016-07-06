package controller;

import data.C4Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Abelsen, Tommy
 */
public class C4Controller implements Initializable{

    private boolean playerTurn;

    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private Canvas playerCanvas;

    private GraphicsContext backgroundGc;
    private GraphicsContext playerGc;
    private C4Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        playerGc = playerCanvas.getGraphicsContext2D();
        game = new C4Game();

        game.fillGameBoard();

        drawBoard();
    }

    private void changePlayerTurn(){
        playerTurn = !playerTurn;
    }

    @FXML
    public void drawFigure(MouseEvent mouseEvent){
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            double X = mouseEvent.getX();
            double Y = mouseEvent.getY();

            if(playerTurn) {
                playerGc.setFill(Color.DARKRED);
            }else{
                playerGc.setFill(Color.DARKBLUE);
            }
            playerGc.clearRect(getXPos(X)- 25 , getYPos(Y) - 25, 50, 50);
            playerGc.fillOval(getXPos(X) - 25, getYPos(Y) - 25, 50, 50);

            //System.out.println("x: " + mouseEvent.getX() + "\ny: " + mouseEvent.getY() + "\n");
            changePlayerTurn();
            System.out.println(game);
        }
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
