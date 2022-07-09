import java.awt.*;
import java.util.*;
/**
 * The Bishop class represents a bishop in a game of chess.
 * @author Daniel Wang
 * @version 4/25/18
 */
public class Bishop extends Piece
{
    /**
     * Creates a new Bishop object with the given color and image file.
     * @param color the given color
     * @param filename the given image file
     */
    public Bishop(Color color, String filename)
    {
        super(color, filename, 3);
    }
    
    /**
     * Returns all possible destinations of the bishop.
     * @return an ArrayList of Location objects representing all the bishop's possible 
     * destinations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        sweep(destinationLocations, Location.NORTHWEST);
        sweep(destinationLocations, Location.NORTHEAST);
        sweep(destinationLocations, Location.SOUTHWEST);
        sweep(destinationLocations, Location.SOUTHEAST);
        return destinationLocations;
    }
}
