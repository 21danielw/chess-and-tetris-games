import java.awt.*;
/**
 * The Player class represents a chess player.
 * @author Daniel Wang
 * @version 5/1/18
 */
public abstract class Player
{
    /**
     * Instance variables
     */
    private Board board;
    private String name;
    private Color color;
    
    /**
     * Creates a new Player object with the given board, name, and color.
     * @param b the given board
     * @param s the given name
     * @param c the given color
     * @postcondition the player's board is set to b, the player's name is set to s, 
     * the player's color is set to c
     */
    public Player(Board b, String s, Color c)
    {
        board = b;
        name = s;
        color = c;
    }
    
    /**
     * Returns the board the player is playing on.
     * @return the player's board
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Returns the name of the player.
     * @return the player's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the color of the player.
     * @return the player's color
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Returns the next move of the player.
     * @return the player's next move
     */
    public abstract Move nextMove();
}
