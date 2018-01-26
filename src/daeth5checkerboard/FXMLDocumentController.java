/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daeth5checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author david
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML public AnchorPane anchorPane;
    @FXML public MenuItem blueGridColor;
    @FXML public MenuItem defaultGridColor;
    @FXML public MenuItem grid3x3;
    @FXML public MenuItem grid8x8;
    @FXML public MenuItem grid10x10;
    @FXML public MenuItem grid16x16;
    @FXML public MenuBar menuBar;
    
    private CheckerBoard checkerBoard;
    private Stage stage;
    private double menuBarHeight;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void startup(Stage stage) {
        this.stage = stage;
        menuBarHeight = menuBar.getHeight()*2;
        anchorPane.setPrefSize(stage.getWidth(), stage.getHeight());
        checkerBoard = new CheckerBoard(8, 8, stage.getWidth(), (stage.getHeight() - menuBarHeight));
        ChangeListener<Number> listener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            checkerBoard = new CheckerBoard(checkerBoard.getNumRows(), checkerBoard.getNumCols(), stage.getWidth(), (stage.getHeight() - menuBarHeight), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
            refreshBoard();
        };
        this.stage.widthProperty().addListener(listener);
        this.stage.heightProperty().addListener(listener);
        
        refreshBoard();
    }

    @FXML
    private void handleColorChange(ActionEvent event) {
        if(checkerBoard != null && checkerBoard.getBoard() != null) {

            if(event.getSource() == blueGridColor)
                checkerBoard = new CheckerBoard(checkerBoard.getNumRows(), checkerBoard.getNumCols(), stage.getWidth(), (stage.getHeight() - menuBarHeight), Color.SKYBLUE, Color.DARKBLUE);    
            else if(event.getSource() == defaultGridColor)
                checkerBoard = new CheckerBoard(checkerBoard.getNumRows(), checkerBoard.getNumCols(), stage.getWidth(), (stage.getHeight() - menuBarHeight), Color.RED, Color.BLACK);
            refreshBoard();
        }
    }
    @FXML
    private void handleGridSizeChange(ActionEvent event) {
        if(checkerBoard != null && checkerBoard.getBoard() != null) {
            if(event.getSource() == grid3x3)
                checkerBoard = new CheckerBoard(3, 3, stage.getWidth(), (stage.getHeight() - menuBarHeight), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
            else if(event.getSource() == grid8x8)
                checkerBoard = new CheckerBoard(8, 8, stage.getWidth(), (stage.getHeight() - menuBarHeight), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
            else if(event.getSource() == grid10x10)
                checkerBoard = new CheckerBoard(10, 10, stage.getWidth(), (stage.getHeight() - menuBarHeight), checkerBoard.getLightColor(), checkerBoard.getDarkColor());
            else if(event.getSource() == grid16x16)
                checkerBoard = new CheckerBoard(16, 16, stage.getWidth(), (stage.getHeight() - menuBarHeight), checkerBoard.getLightColor(), checkerBoard.getDarkColor());

            refreshBoard();
        }
    }
    
    @FXML
    private void handleClear(){
        anchorPane.getChildren().clear();
    }
    
    @FXML
    private void handleExit() {
        stage.close();
    }
    
    private void refreshBoard() {
        anchorPane.getChildren().clear();
        anchorPane.setPrefSize((stage.getHeight() - menuBarHeight), stage.getHeight());
        anchorPane.getChildren().addAll(checkerBoard.build());
    }
    
}