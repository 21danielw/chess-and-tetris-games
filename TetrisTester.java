/**
 * The TetrisTester class launches a game of Tetris.
 * @author Daniel Wang
 * @version 3/29/18
 */
public class TetrisTester
{
    /**
     * Launches a game of Tetris.
     * @param args command line arguments
     * @postcondition the game is finished
     */
    public static void main(String[] args)
    {
        Tetris t = new Tetris();
        t.play();
    }
}