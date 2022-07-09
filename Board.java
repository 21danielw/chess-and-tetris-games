import java.awt.*;
import java.util.*;
/**
 * The Board class represents a rectangular game board containing Piece objects.
 * @author Daniel Wang
 * @version 4/25/18
 */
public class Board extends BoundedGrid<Piece>
{
    /**
     * Constructs a new Board with the given dimensions.
     */
    public Board()
    {
        super(8, 8);
    }
    
    /**
     * Undoes the given move.
     * @param move the given move
     * @precondition move has already been made on the board
     * @postcondition piece has moved back to its source, and any captured piece 
     * is returned to its location
     */
    public void undoMove(Move move)
    {
        Piece piece = move.getPiece();
        Location source = move.getSource();
        Location dest = move.getDestination();
        Piece victim = move.getVictim();

        piece.moveTo(source);

        if (victim != null)
            victim.putSelfInGrid(piece.getBoard(), dest);
    }
    
    /**
     * Returns all the moves that the given color can make.
     * @param color the given color
     * @return an ArrayList of Move objects representing all the possible moves for color
     */
    public ArrayList<Move> allMoves(Color color)
    {
        if(kingUnderAttack(color))
        {
            ArrayList<Location> occupiedLocations = getOccupiedLocations();
            ArrayList<Move> moves = new ArrayList<Move>();
            for(Location loc : occupiedLocations)
            {
                Piece currentPiece = get(loc);
                if(currentPiece.getColor().equals(color))
                {
                    ArrayList<Location> destinations = currentPiece.destinations();
                    for(Location destinationLocation : destinations)
                    {
                        Move m = new Move(currentPiece, destinationLocation);
                        executeMove(m);
                        if(!kingUnderAttack(color))
                        {
                            moves.add(m);
                        }
                        undoMove(m);
                    }
                }
            }
            return moves;
        }
        
        ArrayList<Location> occupiedLocations = getOccupiedLocations();
        ArrayList<Move> moves = new ArrayList<Move>();
        for(Location loc : occupiedLocations)
        {
            Piece currentPiece = get(loc);
            if(currentPiece.getColor().equals(color))
            {
                ArrayList<Location> destinations = currentPiece.destinations();
                for(Location destinationLocation : destinations)
                {
                    Move m = new Move(currentPiece, destinationLocation);
                    executeMove(m);
                    if(!kingUnderAttack(color))
                    {
                        moves.add(m);
                    }
                    undoMove(m);
                }
            }
        }
        return moves;
    }
    
    /**
     * Executes the given move.
     * @param move the given move
     * @postcondition the piece of move is moved to the destination of move
     */
    public void executeMove(Move move)
    {
        move.getPiece().moveTo(move.getDestination());
    }
    
    /**
     * Checks whether the king of the given color is under attack.
     * @param col the given color
     * @return true if the king is under attack, false otherwise
     */
    public boolean kingUnderAttack(Color col)
    {
        ArrayList<Location> occupiedLocations = getOccupiedLocations();
        for(Location loc : occupiedLocations)
        {
            Piece currentPiece = get(loc);
            if(!currentPiece.getColor().equals(col))
            {
                ArrayList<Location> destinations = currentPiece.destinations();
                for(Location destinationLocation : destinations)
                {
                    Piece destinationPiece = get(destinationLocation);
                    if(destinationPiece != null && destinationPiece instanceof King)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
