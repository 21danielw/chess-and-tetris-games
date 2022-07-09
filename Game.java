import java.awt.*;
/**
 * The Game class plays a game of chess.
 * @author Daniel Wang
 * @version 4/25/18
 */
public class Game
{
    /**
     * Instance variables
     */
    public static final int WAIT_TIME = 1;
    
    /**
     * Plays a turn for the given player, and checks whether the game still continues.
     * @param board the game's board
     * @param display the game's display
     * @param player the given player
     * @return true if the game still continues, false otherwise
     */
    private static boolean nextTurn(Board board, BoardDisplay display, Player player)
    {
        display.setTitle(player.getName());
        Move nextMove = player.nextMove();
        if(nextMove == null)
        {
            return false;
        }
        System.out.println(nextMove);
        board.executeMove(nextMove);
        
        display.clearColors();
        display.setColor(nextMove.getSource(), Color.YELLOW);
        display.setColor(nextMove.getDestination(), Color.GREEN);
        
        Color playerColor = player.getColor();
        Color otherColor;
        if(playerColor.equals(Color.BLACK))
        {
            otherColor = Color.WHITE;
        }
        else
        {
            otherColor = Color.BLACK;
        }
        if(board.kingUnderAttack(otherColor) && board.allMoves(otherColor).size() == 0)
        {
            System.out.println("Checkmate");
            return false;
        }
        if(board.allMoves(otherColor).size() == 0)
        {
            System.out.println("Stalemate");
            return false;
        }
        if(board.kingUnderAttack(otherColor))
        {
            System.out.println("Check");
        }
        
        try
        {
            Thread.sleep(WAIT_TIME);
        }
        catch(InterruptedException e)
        {
            
        }
        
        return true;
    }
    
    /**
     * Plays a game of chess with the given board, display, and players.
     * @param board the given board
     * @param display the given display
     * @param white the player playing White
     * @param black the player playing Black
     * @postcondition the game is finished
     */
    public static void play(Board board, BoardDisplay display, Player white, Player black)
    {
        boolean gameContinues = true;
        while(gameContinues)
        {
            gameContinues = nextTurn(board, display, white);
            if(!gameContinues)
            {
                return;
            }
            gameContinues = nextTurn(board, display, black);
            if(!gameContinues)
            {
                return;
            }
        }
    }
}
