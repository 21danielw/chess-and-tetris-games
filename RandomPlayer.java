import java.awt.*;
import java.util.*;
/**
 * The RandomPlayer class represents a chess player that plays randomly.
 * @author Daniel Wang
 * @version 5/3/18
 */
public class RandomPlayer extends Player
{
    /**
     * Creates a new RandomPlayer object with the given board, name, and color.
     * @param b the given board
     * @param s the given name
     * @param c the given color
     */
    public RandomPlayer(Board b, String s, Color c)
    {
        super(b, s, c);
    }
    
    /**
     * Returns the next move of the player.
     * @return the next move
     */
    public Move nextMove()
    {
        ArrayList<Move> allMoves = getBoard().allMoves(getColor());
        if(allMoves.size() == 0)
        {
            return null;
        }
        int index = (int)(Math.random()*allMoves.size());
        return allMoves.get(index);
    }
}
