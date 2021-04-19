package application;
/**
 * @author Faris
 */
public class HealerDrone extends Pieces {
	
	/**
	 * constructor for healer drone
	 * @param X
	 * @param Y
	 */
    public HealerDrone(double X, double Y) {
        super(X, Y);
        
        size = 9;
        color = 'g'; 
    }
    
    @Override
    public String toString() {
        return "HealerDrone No." + PieceID + " is at " + xPositionOfPiece + ", " + yPositionOfPiece;
    }
    @Override
    public void moveTheDrone(DroneArena a) { }
}
