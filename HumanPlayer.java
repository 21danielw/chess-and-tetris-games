import java.awt.*;
import java.util.*;
/**
 * The HumanPlayer class represents a human chess player.
 * @author Daniel Wang
 * @version 5/1/18
 */
public class HumanPlayer extends Player
{
    /**
     * Instance variables
     */
    private BoardDisplay display;
    
    /**
     * Creates a new HumanPlayer object with the given board, name, color, 
     * and board display.
     * @param b the given board
     * @param s the given name
     * @param c the given color
     * @param bd the given board display
     * @postcondition the human player's board display is set to bd
     */
    public HumanPlayer(Board b, String s, Color c, BoardDisplay bd)
    {
        super(b, s, c);
        display = bd;
    }
    
    /**
     * Returns the next move of the human player.
     * @return the human player's next move
     */
    public Move nextMove()
    {
        ArrayList<Move> allMoves = getBoard().allMoves(getColor());
        if(allMoves.size() == 0)
        {
            return null;
        }
        Move m = display.selectMove();
        while(!allMoves.contains(m))
        {
            m = display.selectMove();
        }
        return m;
    }
}
