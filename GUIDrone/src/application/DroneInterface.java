package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * @author Faris
 */
public class DroneInterface implements Initializable {
	
    @FXML private MenuItem save; 
    @FXML private MenuItem loadArea;
    @FXML private MenuItem exit;
    @FXML private MenuItem about;
    @FXML private Canvas canvas;
    @FXML private Button startAnimation;
    @FXML private Button pauseAnimation;
    @FXML private Button addDrone;
    @FXML private Button addHealerDrone;
    @FXML private Button addEnemyDrone;
    @FXML private VBox vbox;
    @FXML private ScrollPane scrollPane;
    @FXML private Label l;
    
    private MyCanvas customizedCanvas;                      
    private DroneArena arena;               
    private boolean animationOn = false;      

    /**
     * creates the size, canvas, buttons, menus, about, animation for the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      
        int canvasSize = 512;
  
        arena = new DroneArena(canvasSize, canvasSize);
        customizedCanvas = new MyCanvas(canvas, canvasSize, canvasSize);
        startAnimation.setOnAction(actionEvent -> animationOn = true);
        pauseAnimation.setOnAction(actionEvent -> animationOn = false);
        addDrone.setOnAction(actionEvent -> {
            arena.addNewDrone('d');
            displaySystem();
        });
  
        addHealerDrone.setOnAction(actionEvent -> {
            arena.addNewDrone('h');
            displaySystem();
        });
 
        addEnemyDrone.setOnAction(actionEvent -> {
            arena.addNewDrone('e');
            displaySystem();
        });
        
        exit.setOnAction(actionEvent -> System.exit(0));
        save.setOnAction(actionEvent -> arena.saveArenaFile());
        loadArea.setOnAction(actionEvent -> openFile());
     
        about.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Faris's Drone Simulator");
            alert.setHeaderText("Welcome to my drone simulation!\n\nThere are buttons below "
            		+ "with actions on what you can do.\nIn the right, there is an"
            		+ " information panel with information "
            		+ "about each drones position and angle.\nThere are 3 drones, a normal drone, a healer drone, and an enemy drone.");
            alert.show();
        });
  
        new AnimationTimer(){ public void handle(long currentNanoTime) {
      
            if (animationOn) {
                arena.animateAllDrones();
                displaySystem();     
            }
        }}.start();
    }
    
    /**
     * displays the canvas and pieces in the system
     */
    public void displaySystem() {
        customizedCanvas.emptyCanvas();
        arena.showAllDrones(customizedCanvas); 
        drawStatus();
    }
    
    /**
     * sets up information panel
     */
    public void drawStatus() {	
    	l.setText(arena.toString());
    }
    
    /**
     * file handling for loading and saving
     */
    public void openFile() {
    	
        FileChooser chooser = new FileChooser();
        File selFile = chooser.showOpenDialog(null);
        
        if (selFile!=null) {				
            if(selFile.isFile()){ 
                try {
                    FileReader fileReader = new FileReader(selFile);
                    BufferedReader bufReader = new BufferedReader(fileReader);
                    String input = bufReader.readLine();
                    
                    int line = 0, droneCount = 0, xSize = 0, ySize, ID = 0;
                    double x = 0, y = 0, speed = 0, angle = 0;
                    char colour = 'b';
                    
                    while (input!= null) { 		
                        if (line == 0) {		
                            xSize = Integer.parseInt(input);
                        }
                        
                        else if (line == 1) {		
                            ySize = Integer.parseInt(input);
                            arena = new DroneArena(xSize, ySize);	  
                        }
                        
                        else {						
                            if(droneCount == 0) {		
                            	colour = input.charAt(0);
                            }
                            if(droneCount == 1) {		
                            	ID = Integer.parseInt(input);
                            }
                            if(droneCount == 2) {		
                            	x = Double.parseDouble(input);
                            }
                            if(droneCount == 3) {		
                            	y = Double.parseDouble(input);
                            }
                            if(droneCount == 4) {		
                                if (input.length() > 0){
                                    speed = Double.parseDouble(input);
                                }
                            }
                            if(droneCount == 5) {	
                                if (input.length() > 0){
                                    angle = Double.parseDouble(input);
                                }
                                arena.addNewPiece(colour, x, y, ID, speed, angle);
                                droneCount = -1;		
                            }
                            droneCount ++;
                        }
                        line ++;
                        input = bufReader.readLine();
                    }
                    displaySystem();
                    bufReader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
