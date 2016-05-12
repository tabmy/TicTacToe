package view;

import javafx.scene.paint.Color;

/**
 * Created by Tommy on 12.05.2016.
 */
public class Appearance {

    private Color XColor;
    private Color OColor;
    private Color gridColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;


    public Color getXColor(){
        return XColor;
    }
    public Color getOColor(){
        return OColor;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setXColor(Color newColor){
        XColor = newColor;
    }

    public void setOColor(Color newColor){
        OColor = newColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

}
