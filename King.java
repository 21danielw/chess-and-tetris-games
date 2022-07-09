import java.awt.*;
import java.util.*;
/**
 * The King class represents a king in chess.
 * @author Daniel Wang
 * @version 5/1/18
 */
public class King extends Piece
{
    /**
     * Creates a new King object using the given color and image file.
     * @param col the given color
     * @param fileName the given image file
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }
    
    /**
     * Returns all possible destinations of the king.
     * @return an ArrayList of Location objects representing all the king's possible 
     * destinations
     */
    public ArrayList<Location> destinations()
    {
        Location myLocation = getLocation();
        int row = myLocation.getRow();
        int col = myLocation.getCol();
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        for(int i = -1; i <= 1; i++)
        {
            for(int j = -1; j <= 1; j++)
            {
                if(i != 0 || j != 0)
                {
                    int newRow = row + i;
                    int newCol = col + j;
                    Location loc = new Location(newRow, newCol);
                    if(isValidDestination(loc))
                    {
                        destinationLocations.add(loc);
                    }
                }
            }
        }
        return destinationLocations;
    }
}
