package application;
/**
 * @author Faris
 */
public abstract class Pieces {
	
	
    protected double xPositionOfPiece, yPositionOfPiece;
    protected static int startingPoint = 1;
    protected int PieceID, size;
    protected char color;
    
    /**
     * constructor for abstract class pieces
     * @param bx
     * @param by
     */
    public Pieces(double bx, double by) {
        xPositionOfPiece = bx;
        yPositionOfPiece = by;
        PieceID = startingPoint++;
    }
    
    /**
     * method to make sure whether a drone is able to move in x,y position
     * @param x
     * @param y
     * @return
     */
    public boolean isHere(double x, double y) {
    	
        double top = y + size;
        double bottom = y - size;
        double left = x - size;
        double right = x + size;
      
        if((xPositionOfPiece < right && xPositionOfPiece > left) && (yPositionOfPiece < top && yPositionOfPiece > bottom)) {
            return true;
        }
        return false;
    }
    
   /**
    * display pieces
    * @param c
    */
    public void displayPiece(MyCanvas c){
        c.showPiece(xPositionOfPiece, yPositionOfPiece, size, color);
    }
    
    /**
     * moves the drones
     * @param a
     */
    public abstract void moveTheDrone(DroneArena a);
    
    /**
     * saves drone information as string
     * @return
     */
    public String saveDrone() { 
        String save = "";
        save += color + "\n";
        save += PieceID + "\n";
        save += xPositionOfPiece + "\n";
        save += yPositionOfPiece + "\n";
        save+= "\n";
        return save;
    }
    
    /**
     * sets the id of the piece
     * @param givenKey
     */
    public void setid(int givenKey){
        PieceID = givenKey;
    } 
    
    public abstract String toString();
}
