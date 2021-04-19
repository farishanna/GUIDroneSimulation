package application;

import javafx.stage.FileChooser;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author Faris
 */
public class DroneArena {
	

    private final int droneXAxisSize, droneYAxisSize;
    private final ArrayList<Pieces> noOfDrones = new ArrayList<>();
    Random random;

    /**
     * constructor for drone arena
     * @param a
     * @param b
     */
    public DroneArena(int a, int b) {
    	
        droneXAxisSize = a;
        droneYAxisSize = b;
        random = new Random();			
    }
    
    /**
     * method to show all the drones
     * @param c
     */
    public void showAllDrones(MyCanvas c) {
        for (Pieces pieces : noOfDrones) {       
            pieces.displayPiece(c);
        }
    }
    
    /**
     * array list for the number of drones
     * @return
     */
    public ArrayList<Pieces> getDrones() {
        return noOfDrones;
      } 
    
    /**
     * method to add a new drone
     * @param objectType
     */
    public void addNewDrone(char objectType) {
        boolean ifDroneClash = true;
        double randomXValues = random.nextInt(droneXAxisSize); 
        double randomYValues = random.nextInt(droneYAxisSize);
        while(ifDroneClash) {
            if(getDronePosition(randomXValues, randomYValues) == null) {      
                ifDroneClash = false;
            }
            randomXValues = random.nextInt(droneXAxisSize);		
            randomYValues = random.nextInt(droneYAxisSize);		
        }
        if(objectType == 'd'){           
            Drone newDrone = new Drone(randomXValues, randomYValues);
            noOfDrones.add(newDrone);
        } else if(objectType == 'h'){     
            HealerDrone newHealerDrone = new HealerDrone(randomXValues, randomYValues);
            noOfDrones.add(newHealerDrone);
        } else if(objectType == 'e'){     
            EnemyDrone newEnemyDrone = new EnemyDrone(randomXValues, randomYValues);
            noOfDrones.add(newEnemyDrone);
        }
    }
    
    /**
     * method to add a new piece
     * @param color
     * @param xCoordinate
     * @param yCoordinate
     * @param ID
     * @param speed
     * @param angle
     */
    public void addNewPiece(char color, double xCoordinate, double yCoordinate, int ID, double speed, double angle){
        if (color == 'w'){       
            Drone newDrone = new Drone(xCoordinate,yCoordinate);
            newDrone.directionOfTheDrone(speed,angle);
            newDrone.setid(ID);
            noOfDrones.add(newDrone);
        }
        else if(color == 'g'){       
            HealerDrone newHealerDrone = new HealerDrone(xCoordinate, yCoordinate);
            newHealerDrone.setid(ID);
            noOfDrones.add(newHealerDrone);
        }
        else if (color == 'r'){     
            EnemyDrone newEnemyDrone = new EnemyDrone(xCoordinate,yCoordinate);
            newEnemyDrone.directionOfTheDrone(speed,angle);
            newEnemyDrone.setid(ID);
            noOfDrones.add(newEnemyDrone);
        }
    }
    
    /**
     * tostring to return the area size with the position of the drones
     */
    public String toString() {
    	
        StringBuilder locations = new StringBuilder("The area is " + droneXAxisSize + " x " + droneYAxisSize);
        
        for (Pieces pieces : noOfDrones) {
            locations.append("\n");
            locations.append(pieces.toString());
        }
        return locations.toString();
    }
    
    /**
     * method to get the position of drones
     * @param a
     * @param b
     * @return
     */
    public Pieces getDronePosition(double a, double b) {
   
        for (Pieces d : noOfDrones) {
            if(d instanceof HealerDrone) {
                if (d.isHere(a, b)) {
                    return d;       
                }
            }
        }
        for (Pieces d : noOfDrones) {
            if(d instanceof EnemyDrone) {
                if (d.isHere(a, b)) {
                    return d;      
                }
            }
        }
        for (Pieces d : noOfDrones) {
            if(d instanceof Drone && !(d instanceof EnemyDrone)) {
                if (d.isHere(a, b)) {
                    return d;      
                }
            }
        }
        return null;		
    }
    
    /**
     * moves drone to appropriate position
     * @param xCoordinate
     * @param yCoordinate
     * @param current
     * @return
     */
    public boolean moveHere(double xCoordinate, double yCoordinate, Pieces current) {
    	
        if(xCoordinate < droneXAxisSize && xCoordinate > 0 && yCoordinate < droneYAxisSize && yCoordinate > 0) {
            return getDronePosition(xCoordinate, yCoordinate) == null || getDronePosition(xCoordinate, yCoordinate) == current;
        }
        return false;
    }
    
    /**
     * animate the drones
     */
    public void animateAllDrones() {
        for (Pieces d : noOfDrones) {
            d.moveTheDrone(this);
        }
    }
    
    /**
     * gives the information needed of the arena for saving
     * @return
     */
    public String saveAreaData() {
        StringBuilder saveData = new StringBuilder();
        saveData.append(droneXAxisSize).append("\n");
        saveData.append(droneYAxisSize);
        for (Pieces d : noOfDrones) {
            saveData.append("\n");
            saveData.append(d.saveDrone());
        }
        return saveData.toString();
    }
    
    /**
     * saves arena
     */
    public void saveArenaFile() {
    
        FileChooser chooser = new FileChooser();
        File approve = chooser.showSaveDialog(null);
        
        if (approve!=null) {
            try {
                FileWriter outFileWriter = new FileWriter(approve);   
                PrintWriter writer = new PrintWriter(outFileWriter);
                writer.println(saveAreaData());                    
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
