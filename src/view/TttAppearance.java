package view;

import javafx.scene.paint.Color;

/**
 * @author Abelsen, Tommy
 */
public class TttAppearance {

    private Color XColor;
    private Color OColor;
    private Color gridColor;
    private Color backgroundColor;


    public Color getXColor() {
        return XColor;
    }

    public Color getOColor() {
        return OColor;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }


    public void setXColor(Color XColor) {
        this.XColor = XColor;
    }

    public void setOColor(Color OColor) {
        this.OColor = OColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void randomColors(){
        XColor = new Color(Math.random(),Math.random(), Math.random(), 1);
        OColor = new Color(Math.random(),Math.random(), Math.random(), 1);
        gridColor = new Color(Math.random(),Math.random(), Math.random(), 1);
        backgroundColor = new Color(Math.random(),Math.random(), Math.random(), 1);
    }

}
