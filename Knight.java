import java.awt.*;
import java.util.*;
/**
 * The Knight class represents a knight in chess.
 * @author Daniel Wang
 * @version 5/3/18
 */
public class Knight extends Piece
{
    /**
     * Instance variables
     */
    public static final int[][] moves = 
        {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    
    /**
     * Creates a new Knight object using the given color and image file
     * @param col the given color
     * @param fileName the given image file
     */
    public Knight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }
    
    /**
     * Returns all the possible destinations of the knight.
     * @return an ArrayList of Location objects representing all the knight's possible 
     * destinations
     */
    public ArrayList<Location> destinations()
    {
        Location myLocation = getLocation();
        int row = myLocation.getRow();
        int col = myLocation.getCol();
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        for(int[] delta : moves)
        {
            int newRow = row+delta[0];
            int newCol = col+delta[1];
            Location loc = new Location(newRow, newCol);
            if(isValidDestination(loc))
            {
                destinationLocations.add(loc);
            }
        }
        return destinationLocations;
    }
}
