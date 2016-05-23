package controller;

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
 * Created by Tommy on 21.05.2016.
 */
public class C4Controller implements Initializable{


    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private Canvas playerCanvas;

    private GraphicsContext backgroundGc;
    private GraphicsContext playerGc;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        playerGc = playerCanvas.getGraphicsContext2D();


        drawBoard();
    }

    @FXML
    public void drawFigure(MouseEvent mouseEvent){
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            double X = mouseEvent.getX();
            double Y = mouseEvent.getY();

            playerGc.setStroke(Color.RED);
            playerGc.setLineWidth(5);
            playerGc.strokeOval(getXPos(X) - 5, getYPos(Y) - 5, 10, 10);

            System.out.println("x: " + mouseEvent.getX() + "\ny: " + mouseEvent.getY() + "\n");

        }
    }

    private double getXPos(double x){
        double width = playerCanvas.getWidth();

        if (width == 0)  x++;

        if (x < width/7){
            System.out.println(width/7 * 0.5);
            return width/7 * 0.5;
        }
        else if (x < width/7 * 2){
            System.out.println(width/7 * 1.5);
            return width/7 * 1.5;
        }
        else if (x < width/7 * 3){
            System.out.println(width/7 * 2.5);
            return width/7 * 2.5;
        }
        else if (x < width/7 * 4){
            System.out.println(width/7 * 3.5);
            return width/7 * 3.5;
        }
        else if (x < width/7 * 5){
            System.out.println(width/7 * 4.5);
            return width/7 * 4.5;
        }
        else if (x < width/7 * 6){
            System.out.println(width/7 * 5.5);
            return width/7 * 5.5;
        }
        else if (x < width/7 * 7){
            System.out.println(width/7 * 6.5);
            return width/7 * 6.5;
        }

        return x;
    }

    private double getYPos(double y){
        double height = playerCanvas.getHeight();

        if (y == 0) y++;

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

        return y;
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
        backgroundGc.setFill(Color.WHITE);
        backgroundGc.setStroke(Color.BLACK);
        backgroundGc.setLineWidth(lineWidth);
        backgroundGc.fillRect(0,0,250,250);

        for(int i = 0; i < rows; i++){
            backgroundGc.strokeLine(0, (height/rows) * i + lineWidth, width, (height/rows) * i + lineWidth);
        }
        backgroundGc.strokeLine(0, height - lineWidth, width, height - lineWidth);

        for(int i = 0; i < columns; i++){
            backgroundGc.strokeLine((width/columns) * i, lineWidth, (width/columns) * i, height - lineWidth);
        }   backgroundGc.strokeLine(width - lineWidth, lineWidth, width - lineWidth, height - lineWidth);
    }

}
