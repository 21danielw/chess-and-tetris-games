import java.awt.*;
import java.util.*;
/**
 * The Rook class represents a rook in chess.
 * @author Daniel Wang
 * @version 5/3/18
 */
public class Rook extends Piece
{
    /**
     * Creates a new Rook object with the given color and image file.
     * @param color the given color
     * @param filename the given image file
     */
    public Rook(Color color, String filename)
    {
        super(color, filename, 5);
    }
    
    /**
     * Returns all the possible destinations of the rook.
     * @return an ArrayList of Location objects representing all the rook's possible 
     * destinations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        sweep(destinationLocations, Location.NORTH);
        sweep(destinationLocations, Location.WEST);
        sweep(destinationLocations, Location.EAST);
        sweep(destinationLocations, Location.SOUTH);
        return destinationLocations;
    }
}
