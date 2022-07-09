import java.awt.*;
import java.util.*;
/**
 * The Piece class represents a chess piece.
 * @author Daniel Wang
 * @version 5/1/18
 */
public abstract class Piece
{
    /**
     * Instance variables
     */
    
    //the board this piece is on
    private Board board;

    //the location of this piece on the board
    private Location location;

    //the color of the piece
    private Color color;

    //the file used to display this piece
    private String imageFileName;

    //the approximate value of this piece in a game of chess
    private int value;
    
    /**
     * Constructs a new Piece object with the given attributes.
     * @param col the given color
     * @param fileName the given image file
     * @param val the given value
     * @postcondition the piece's color is set to col, the piece's image file name is set 
     * to fileName, the piece's value is set to val
     */
    public Piece(Color col, String fileName, int val)
    {
        color = col;
        imageFileName = fileName;
        value = val;
    }
    
    /**
     * Returns the board this piece is on.
     * @return this piece's board
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Returns the location of this piece on the board.
     * @return this piece's location
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Returns the color of this piece.
     * @return this piece's color
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Returns the name of the file used to display this piece.
     * @return the name of this piece's image file
     */
    public String getImageFileName()
    {
        return imageFileName;
    }
    
    /**
     * Returns a number representing the relative value of this piece.
     * @return this piece's value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed.
     * @precondition (1) this piece is not contained in a grid (2)
     * loc is valid in gr
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     * @postcondition this piece has been put into brd at loc, 
     * the piece previously in brd at loc is removed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                    "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board.
     * @precondition this piece is contained in a board
     * @postcondition this piece has been removed from its board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                    "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed.
     * @precondition (1) This piece is contained in a grid
     * (2) newLocation is valid in the grid of this piece
     * @param newLocation the new location
     * @postcondition this piece has been moved to newLocation, 
     * the piece previously at newLocation is removed
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }
    
    /**
     * Checks whether the given location is a valid destination for this piece.
     * @param dest the given location
     * @return true if dest is a valid destination for this piece, false otherwise
     */
    public boolean isValidDestination(Location dest)
    {
        return board.isValid(dest) && 
                (board.get(dest) == null || !board.get(dest).getColor().equals(color));
    }
    
    /**
     * Returns all the possible destinations for this piece.
     * @return an ArrayList of Location objects representing all this piece's possible 
     * destinations
     */
    public abstract ArrayList<Location> destinations();
    
    /**
     * Adds the valid destinations in the given direction to the given list of locations.
     * @param dests the given list of locations
     * @param direction the given direction
     * @postcondition the valid destinations in the given direction are added to list
     */
    public void sweep(ArrayList<Location> dests, int direction)
    {
        Location delta;
        if(direction == Location.NORTHWEST)
        {
            delta = new Location(-1, -1);
        }
        else if(direction == Location.NORTH)
        {
            delta = new Location(-1, 0);
        }
        else if(direction == Location.NORTHEAST)
        {
            delta = new Location(-1, 1);
        }
        else if(direction == Location.WEST)
        {
            delta = new Location(0, -1);
        }
        else if(direction == Location.EAST)
        {
            delta = new Location(0, 1);
        }
        else if(direction == Location.SOUTHWEST)
        {
            delta = new Location(1, -1);
        }
        else if(direction == Location.SOUTH)
        {
            delta = new Location(1, 0);
        }
        else
        {
            delta = new Location(1, 1);
        }
        Location currentLocation = new Location(location.getRow()+delta.getRow(), 
                location.getCol()+delta.getCol());
        while(board.isValid(currentLocation) && board.get(currentLocation) == null)
        {
            dests.add(currentLocation);
            currentLocation = new Location(currentLocation.getRow()+delta.getRow(), 
                    currentLocation.getCol()+delta.getCol());
        }
        if(board.isValid(currentLocation) && 
                !board.get(currentLocation).color.equals(color))
        {
            dests.add(currentLocation);
        }
    }
}
