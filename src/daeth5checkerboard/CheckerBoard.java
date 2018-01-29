/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeth5checkerboard;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author david
 */

public class CheckerBoard {
    
    private AnchorPane anchorPane;
    private int numRows;
    private int numCols;
    private double boardWidth;
    private double boardHeight;
    private double rectSideLength;
    private Color lightColor;
    private Color darkColor;
    private Rectangle rectangle;
    
    
    
    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight) { //constructor to call the other constructor
        this(numRows, numCols, boardWidth, boardHeight, Color.RED, Color.BLACK);
    }
    public CheckerBoard(int numRows, int numCols, double boardWidth, double boardHeight, Color lightColor, Color darkColor) {   //constructor that takes in colors
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.lightColor = lightColor;
        this.darkColor = darkColor;
        if(boardWidth / numCols > boardHeight / numRows){
            rectSideLength = boardHeight / numRows;
        }
        else{
            rectSideLength = boardWidth / numCols;
        }
    }

    public AnchorPane build() { //builds the anchor pane
        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(boardWidth, boardHeight);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                rectangle = new Rectangle(rectSideLength, rectSideLength);  //sets the blocks
                rectangle.setFill(darkColor); //fill in with dark
                if((row + col)%2 != 1){  //used mod instead of flag to keep track of light/dark
                    rectangle.setFill(lightColor);
                }
                anchorPane.setTopAnchor(rectangle, (double) (col*rectSideLength+(boardHeight-rectSideLength*numCols)/(numCols/(numCols/2))));
                anchorPane.setLeftAnchor(rectangle, (double) (row*rectSideLength+(boardWidth-rectSideLength*numRows)/(numRows/(numRows/2))));
                anchorPane.getChildren().add(rectangle);
            }
        }
        return anchorPane;
    }

    public AnchorPane getBoard()
    {
        return anchorPane;
    }
    
    public int getNumRows() {
        return numRows; 
    }
    
    public int getNumCols() {
        return numCols; 
    }
    
    public double getWidth() {   
        return boardWidth; 
    }
    
    public double getHeight(){
        return boardHeight; 
    }
    
    public Color getLightColor(){
        return lightColor; 
    }
    
    public Color getDarkColor(){
        return darkColor; 
    }
    
    public double getRectangleWidth(){
        return rectSideLength; 
    }
    
    public double getRectangleHeight(){
        return rectSideLength;
    }

}
