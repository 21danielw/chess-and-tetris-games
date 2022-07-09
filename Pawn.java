import java.awt.*;
import java.util.*;
/**
 * The Pawn class represents a pawn in chess.
 * @author Daniel Wang
 * @version 5/1/18
 */
public class Pawn extends Piece
{
    /**
     * Creates a new Pawn object using the given color and image file.
     * @param col the given color
     * @param filename the given image file
     */
    public Pawn(Color col, String filename)
    {
        super(col, filename, 1);
    }
    
    /**
     * Returns all the possible destinations of the pawn.
     * @return an ArrayList of Location objects representing all the pawn's possible 
     * destinations
     */
    public ArrayList<Location> destinations()
    {
        Location myLocation = getLocation();
        int row = myLocation.getRow();
        int col = myLocation.getCol();
        
        Color ownColor = getColor();
        
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        
        if(ownColor.equals(Color.BLACK))
        {
            int newRow = row+1;
            int newCol = col;
            Location loc = new Location(newRow, newCol);
            if(isEmptyDestination(loc))
            {
                destinationLocations.add(loc);
            }
            
            if(row == 1 && isEmptyDestination(loc))
            {
                int secondNewRow = row+2;
                int secondNewCol = col;
                Location secondLoc = new Location(secondNewRow, secondNewCol);
                if(isEmptyDestination(secondLoc))
                {
                    destinationLocations.add(secondLoc);
                }
            }
            
            newRow = row+1;
            newCol = col-1;
            loc = new Location(newRow, newCol);
            if(occupiedByOpponentsPiece(loc))
            {
                destinationLocations.add(loc);
            }
            
            newRow = row+1;
            newCol = col+1;
            loc = new Location(newRow, newCol);
            if(occupiedByOpponentsPiece(loc))
            {
                destinationLocations.add(loc);
            }
        }
        else
        {
            int newRow = row-1;
            int newCol = col;
            Location loc = new Location(newRow, newCol);
            if(isEmptyDestination(loc))
            {
                destinationLocations.add(loc);
            }
            if(row == 6 && isEmptyDestination(loc))
            {
                int secondNewRow = row-2;
                int secondNewCol = col;
                Location secondLoc = new Location(secondNewRow, secondNewCol);
                if(isEmptyDestination(secondLoc))
                {
                    destinationLocations.add(secondLoc);
                }
            }
            
            newRow = row-1;
            newCol = col-1;
            loc = new Location(newRow, newCol);
            if(occupiedByOpponentsPiece(loc))
            {
                destinationLocations.add(loc);
            }
            
            newRow = row-1;
            newCol = col+1;
            loc = new Location(newRow, newCol);
            if(occupiedByOpponentsPiece(loc))
            {
                destinationLocations.add(loc);
            }
        }
        
        return destinationLocations;
    }
    
    /**
     * Checks whether the given location is empty
     * @param dest the given location
     * @return true if dest is empty, false otherwise
     */
    public boolean isEmptyDestination(Location dest)
    {
        Board ownBoard = getBoard();
        return ownBoard.isValid(dest) && ownBoard.get(dest) == null;
    }
    
    /**
     * Checks whether the given location is occupied by one of the opponent's pieces.
     * @param dest the given location
     * @return true if dest is occupied by one of the opponent's pieces, false otherwise
     */
    public boolean occupiedByOpponentsPiece(Location dest)
    {
        Board ownBoard = getBoard();
        Color ownColor = getColor();
        return ownBoard.isValid(dest) && 
                ownBoard.get(dest) != null && 
                !ownBoard.get(dest).getColor().equals(ownColor);
    }
}
