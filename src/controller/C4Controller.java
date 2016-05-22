package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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


        drawBackground();
    }


    public void drawBackground(){
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
