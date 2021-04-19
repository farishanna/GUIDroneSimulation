package application;

import java.util.Random;

/**
 * @author Faris
 */
public class Drone extends Pieces {

  protected double speed = 1.85, positionOfDrone;

  /**
   * constructor for drone
   * @param X
   * @param Y
   */
  public Drone(double X, double Y) {
    super(X, Y);  
    size = 9;//size of the drone
    Random random = new Random(System.currentTimeMillis());
    positionOfDrone = random.nextFloat() * 2 * Math.PI;
  }

  /**
   * Method to change sizes of drone
   * @param x
   * @param y
   * @param droneArena
   * @return
   */
  protected int sizeChange(double x, double y, DroneArena droneArena) {
    for( Pieces p : droneArena.getDrones()) {
      if (p instanceof EnemyDrone && p.isHere(x, y)) {
        return  -3;
      }
      else if (p instanceof HealerDrone && p.isHere(x, y)) {
        return  3;
      }
    }
    return 0;
  }


  /**
   * method to move the drone to a new suitable position
   */
  public void moveTheDrone(DroneArena droneArena) {

    Random randAng = new Random();
    double newXPosition = xPositionOfPiece + speed * Math.cos(positionOfDrone);  
    double newYPosition = yPositionOfPiece + speed * Math.sin(positionOfDrone);

    if (!droneArena.moveHere(newXPosition, newYPosition, this)) {
    	
      positionOfDrone += Math.toRadians(randAng.nextInt(360)); //random angle after hitting wall
      
      newXPosition = xPositionOfPiece + speed * Math.cos(positionOfDrone);
      newYPosition = yPositionOfPiece + speed * Math.sin(positionOfDrone);

      this.size += sizeChange(newXPosition, newYPosition, droneArena);;
    }
    xPositionOfPiece = newXPosition;
    yPositionOfPiece = newYPosition;
  }

  @Override
  public String saveDrone() { 
	    String status = "";
	    status += color + "\n";
	    status += PieceID + "\n";
	    status += xPositionOfPiece + "\n";
	    status += yPositionOfPiece + "\n";
	    status += speed + "\n";
	    status += size + "\n";
	    status += positionOfDrone;

	    return status;

	  }

  /**
   * direction of the drone
   * @param sp
   * @param ang
   */
  public void directionOfTheDrone(double sp, double ang) {
    speed = sp;
    positionOfDrone = ang;
  }

  /**
   * tostring to return drone info
   */
  public String toString() {
    return "Drone No." + PieceID + " is at " + String.format("%.2f", xPositionOfPiece) + ", " +
        String.format("%.2f", yPositionOfPiece) + " moving at angle " +
        String.format("%.2f", positionOfDrone);
  }

}
