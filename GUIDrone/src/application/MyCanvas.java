package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.canvas.Canvas;
/**
 * @author Faris
 * Class to handle a canvas, used by different GUIs
 */
public class MyCanvas {
	int xCanvasSize = 512;				// constants for relevant sizes
	int yCanvasSize = 512;
    GraphicsContext gc;

    /**
     * constructor sets up relevant Graphics context and size of canvas
     * @param g
     * @param cs
     */
    public MyCanvas(Canvas g, int xcs, int ycs) {
    	gc = g.getGraphicsContext2D();
        xCanvasSize = xcs;
        yCanvasSize = ycs;
    }
    /**
     * Method for an empty canvas with its color
     */
    public void emptyCanvas() {
        gc.setFill(Color.WHITE); //set color to white 
        gc.fillRect(0,  0, xCanvasSize, yCanvasSize);		// clear canvas
    }
    
    /** 
	 * function to convert char c to actual colour used
	 * @param c
	 * @return Color
	 */
    Color colFromChar (char c){
    	Color ans = Color.BLACK;
		switch (c) {
		case 'y' :	ans = Color.YELLOW;
					break;
		case 'w' :	ans = Color.WHITE;
					break;
		case 'r' :	ans = Color.RED;
					break;
		case 'g' :	ans = Color.GREEN;
					break;
		case 'b' :	ans = Color.BLUE;
					break;
		case 'o' :	ans = Color.ORANGE;
					break;
		}
		return ans;
    }
    
    public void setFillColour (Color c) {
		gc.setFill(c);
	}
	/**
	 * show the drone at position x,y , radius r in colour defined by col
	 * @param x
	 * @param y
	 * @param rad
	 * @param col
	 */
	public void showPiece(double x, double y, double rad, char col) {
	 	setFillColour(colFromChar(col));									// set the fill color
		gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);	// fill circle
	}

	public void showPiece(double x, double y, double rad) {
		gc.fillArc(x-rad, y-rad, rad*2, rad*2, 0, 360, ArcType.ROUND);	// fill circle
	}
}
