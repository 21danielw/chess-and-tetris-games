import java.awt.*;
import java.util.*;
/**
 * The SmartPlayer class represents a chess player that looks ahead.
 * @author Daniel Wang
 * @version 5/3/18
 */
public class SmartPlayer extends Player
{
    /**
     * Instance variables
     */
    private int lookAhead;
    
    /**
     * Creates a new SmartPlayer object with the given board, name, color, and number of 
     * moves to look ahead.
     * @param b the given board
     * @param s the given name
     * @param c the given color
     * @param level the given number of moves to look ahead
     * @postcondition the number of moves to look ahead is set to level
     */
    public SmartPlayer(Board b, String s, Color c, int level)
    {
        super(b, s, c);
        lookAhead = level;
    }
    
    /**
     * Returns the score of the board.
     * @return the score
     */
    public int score()
    {
        Board board = getBoard();
        Color ownColor = getColor();
        ArrayList<Location> occupiedLocations = board.getOccupiedLocations();
        int ownSum = 0;
        int otherSum = 0;
        for(Location loc : occupiedLocations)
        {
            Piece p = board.get(loc);
            if(ownColor.equals(p.getColor()))
            {
                ownSum += p.getValue();
            }
            else
            {
                otherSum += p.getValue();
            }
        }
        return ownSum - otherSum;
    }
    
    /**
     * Returns the next move of the player.
     * @return the next move
     */
    public Move nextMove()
    {
        Board board = getBoard();
        ArrayList<Move> allMoves = board.allMoves(getColor());
        int maxScore = Integer.MIN_VALUE;
        Move chosenMove = null;
        for(Move m : allMoves)
        {
            board.executeMove(m);
            /*
            System.out.println(m);
            */
            int num = valueOfMeanestResponse(lookAhead);
            /*
            System.out.println(num);
            */
            if(maxScore < num)
            {
                maxScore = num;
                chosenMove = m;
            }
            board.undoMove(m);
        }
        return chosenMove;
    }
    
    /**
     * Finds the worst possible board value after the given number of moves.
     * @param numMovesLookAhead the given number of moves
     * @return the worst possible board value after the given number of moves
     */
    public int valueOfMeanestResponse(int numMovesLookAhead)
    {
        if(numMovesLookAhead == 0)
        {
            return score();
        }
        Board board = getBoard();
        Color ownColor = getColor();
        Color otherColor;
        if(ownColor.equals(Color.WHITE))
        {
            otherColor = Color.BLACK;
        }
        else
        {
            otherColor = Color.WHITE;
        }
        ArrayList<Move> allOtherMoves = board.allMoves(otherColor);
        if(allOtherMoves.size() == 0)
        {
            if(board.kingUnderAttack(otherColor))
            {
                return 1_000_000;
            }
            return 0;
        }
        int minScore = Integer.MAX_VALUE;
        for(Move m : allOtherMoves)
        {
            board.executeMove(m);
            int num = valueOfBestMove(numMovesLookAhead-1);
            if(minScore > num)
            {
                minScore = num;
            }
            board.undoMove(m);
        }
        return minScore;
    }
    
    /**
     * Finds the maximum board value after the given number of moves if both players play 
     * optimally.
     * @param numMovesLookAhead the given number of moves
     * @return the maximum board value after the given number of moves if both players 
     * play optimally
     */
    public int valueOfBestMove(int numMovesLookAhead)
    {
        if(numMovesLookAhead == 0)
        {
            return score();
        }
        Board board = getBoard();
        Color ownColor = getColor();
        ArrayList<Move> allMoves = board.allMoves(ownColor);
        if(allMoves.size() == 0)
        {
            if(board.kingUnderAttack(ownColor))
            {
                return -1_000_000;
            }
            return 0;
        }
        int maxScore = Integer.MIN_VALUE;
        for(Move m : allMoves)
        {
            board.executeMove(m);
            int num = valueOfMeanestResponse(numMovesLookAhead-1);
            if(maxScore < num)
            {
                maxScore = num;
            }
            board.undoMove(m);
        }
        return maxScore;
    }
}
