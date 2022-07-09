/**
 * The Move class represents a single move, 
 * in which a piece moves to a destination location.
 * Since a move can be undone, the Move class also keeps 
 * track of the source location and any captured victim.
 * @author Daniel Wang
 * @version 5/1/18
 */
public class Move
{
    /**
     * Instance variables
     */
    private Piece piece;          //the piece being moved
    private Location source;      //the location being moved from
    private Location destination; //the location being moved to
    private Piece victim;         //any captured piece at the destination
    
    /**
     * Creates a new Move object for moving the given piece to the given destination.
     * @param piece the given piece
     * @param destination the given destination
     * @postcondition the move's piece is set to piece, 
     * the move's source is set to the location of piece,
     * the move's destination is set to destination,
     * the move's victim is set to the piece at the move's destination
     */
    public Move(Piece piece, Location destination)
    {
        this.piece = piece;
        this.source = piece.getLocation();
        this.destination = destination;
        this.victim = piece.getBoard().get(destination);

        if (source.equals(destination))
            throw new IllegalArgumentException("Both source and dest are " + source);
    }
    
    /**
     * Returns the piece being moved.
     * @return the move's piece
     */
    public Piece getPiece()
    {
        return piece;
    }
    
    /**
     * Returns the location being moved from
     * @return the move's source
     */
    public Location getSource()
    {
        return source;
    }
    
    /**
     * Returns the location being moved to.
     * @return the move's destination
     */
    public Location getDestination()
    {
        return destination;
    }
    
    /**
     * Returns the piece being captured at the destination, if any.
     * @return the move's victim
     */
    public Piece getVictim()
    {
        return victim;
    }
    
    /**
     * Returns a String description of the move.
     * @return a string containing the move's piece, the move's source, 
     * the move's destination, and the move's victim
     */
    public String toString()
    {
        return piece + " from " + source + " to " + destination + " containing " + victim;
    }
    
    /**
     * Returns true if this move is equivalent to the given one.
     * @param x the given move
     * @return true if this move is equal to x, false otherwise
     */
    public boolean equals(Object x)
    {
        Move other = (Move)x;
        return piece == other.getPiece() && source.equals(other.getSource()) &&
            destination.equals(other.getDestination()) && victim == other.getVictim();
    }
    
    /**
     * Returns a hash code for this move, such that equivalent moves have the same 
     * hash code.
     * @return an integer value equal to the sum of the hash codes of the move's piece, 
     * source, and destination
     */
    public int hashCode()
    {
        return piece.hashCode() + source.hashCode() + destination.hashCode();
    }
}
