import java.awt.*;
import java.util.*;
/**
 * The Queen class represents a queen in chess.
 * @author Daniel Wang
 * @version 5/3/18
 */
public class Queen extends Piece
{
    /**
     * Creates a new Queen object with the given color and image file.
     * @param color the given color
     * @param filename the given image file
     */
    public Queen(Color color, String filename)
    {
        super(color, filename, 9);
    }
    
    /**
     * Returns all the possible destinations of the queen.
     * @return an ArrayList of Location objects representing all the queen's possible 
     * destinations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        sweep(destinationLocations, Location.NORTHWEST);
        sweep(destinationLocations, Location.NORTH);
        sweep(destinationLocations, Location.NORTHEAST);
        sweep(destinationLocations, Location.WEST);
        sweep(destinationLocations, Location.EAST);
        sweep(destinationLocations, Location.SOUTHWEST);
        sweep(destinationLocations, Location.SOUTH);
        sweep(destinationLocations, Location.SOUTHEAST);
        return destinationLocations;
    }
}
