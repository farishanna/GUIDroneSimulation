package application;

/**
 * @author Faris
 * 
 */
public class EnemyDrone extends Drone {
   
	/**
	 * enemy drone constructor
	 * @param a
	 * @param b
	 */
    public EnemyDrone(double a, double b) {
    	super(a, b);
        
        speed = 2.2;
        size = 8;
        color = 'r';
    }
    
    @Override
    protected int sizeChange(double x, double y, DroneArena droneArena) {
      return 0;
    }
    @Override
    public boolean isHere(double a, double b) {
        //include size of piece + extra radius
        double up = b + size + size * 2;
        double down = b - size - size * 2;
        double left = a - size - size * 2;
        double right = a + size + size * 2;
        
        return (xPositionOfPiece <= right && xPositionOfPiece >= left) && (yPositionOfPiece <= up && yPositionOfPiece >= down);
    }
    @Override
    public String toString() {
        return "Enemy Drone No." + PieceID + " at " + String.format("%.2f", xPositionOfPiece) + ", " +
                String.format("%.2f", yPositionOfPiece) + " moving at angle " +
                String.format("%.2f", positionOfDrone);
    }
}
