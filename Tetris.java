/**
 * The Tetris class implements the game of Tetris.
 * @author Daniel Wang
 * @version 3/29/18
 */
public class Tetris implements ArrowListener
{
    /**
     * Instance variables
     */
    private MyBoundedGrid<Block> grid;
    private TetrisBlockDisplay display;
    private Tetrad activeTetrad;
    private int score;
    private int level;
    private int waitTime;
    
    /**
     * Creates a new Tetris object.
     * @postcondition grid is initialized to be a new MyBoundedGrid object 
     * with 20 rows and 10 columns, 
     * display is initialized to be a new TetrisBlockDisplay object, 
     * score is initialized to 0, level is initialized to 1, 
     * waitTime is initialized to 1000 milliseconds (1 second), 
     * activeTetrad is initialized to be a new Tetrad object, 
     * the game window is opened
     */
    public Tetris()
    {
        grid = new MyBoundedGrid<Block>(20, 10);
        display = new TetrisBlockDisplay(grid);
        display.setArrowListener(this);
        score = 0;
        level = 1;
        waitTime = 1000;
        display.setTitle("Level: " + level + " Score: " + score);
        activeTetrad = new Tetrad(grid);
        display.showBlocks();
    }
    
    /**
     * Rotates the active tetrad when the up arrow key is pressed.
     * @precondition the up arrow key is pressed
     * @postcondition activeTetrad is rotated 90 degrees clockwise, 
     * the game window is redrawn to show the current state of the game
     */
    public void upPressed()
    {
        activeTetrad.rotate();
        display.showBlocks();
    }
    
    /**
     * Translates the active tetrad down by one row when the down arrow key is pressed.
     * @precondition the down arrow key is pressed
     * @postcondition activeTetrad is translated down by one row, 
     * the game window is redrawn to show the current state of the game
     */
    public void downPressed()
    {
        activeTetrad.translate(1, 0);
        display.showBlocks();
    }
    
    /**
     * Translates the active tetrad to the left by one column 
     * when the left arrow key is pressed.
     * @precondition the left arrow key is pressed
     * @postcondition activeTetrad is translated to the left by one row,
     * the game window is redrawn to show the current state of the game
     */
    public void leftPressed()
    {
        activeTetrad.translate(0, -1);
        display.showBlocks();
    }
    
    /**
     * Translates the active tetrad to the right by one column 
     * when the right arrow key is pressed.
     * @precondition the right arrow key is pressed
     * @postcondition activeTetrad is translated to the right by one row, 
     * the game window is redrawn to show the current state of the game
     */
    public void rightPressed()
    {
        activeTetrad.translate(0, 1);
        display.showBlocks();
    }
    
    /**
     * Drops the active tetrad to the bottom when the space bar is pressed.
     * @precondition the space bar is pressed
     * @postcondition activeTetrad is dropped to the bottom, 
     * the game window is redrawn to show the current state of the game
     */
    public void spacePressed()
    {
        boolean canMoveDown = true;
        while(canMoveDown)
        {
            canMoveDown = activeTetrad.translate(1, 0);
        }
        display.showBlocks();
    }
    
    /**
     * Plays one round of Tetris.
     * @postcondition the game is finished
     */
    public void play()
    {
        System.out.println("Welcome to Tetris!");
        boolean isCreated = true;
        boolean canMoveDown = true;
        while(isCreated)
        {
            try
            {
                Thread.sleep(waitTime);
                canMoveDown = activeTetrad.translate(1, 0);
                display.setTitle("Level: " + level + " Score: " + score);
                display.showBlocks();
                if(!canMoveDown)
                {
                    clearCompletedRows();
                    activeTetrad = new Tetrad(grid);
                    isCreated = activeTetrad.isCreated();
                    if(isCreated)
                    {
                        display.setTitle("Level: " + level + " Score: " + score);
                        display.showBlocks();
                    }
                }
            }
            catch(InterruptedException e)
            {
                
            }
        }
        System.out.println("You lost!");
        System.out.println("Your final score is " + score);
    }
    
    /**
     * Checks whether the given row of the grid is completely filled with blocks.
     * @param row the given row
     * @return true if the given row is completely filled with blocks, false otherwise
     */
    private boolean isCompletedRow(int row)
    {
        for(int i = 0; i < grid.getNumCols(); i++)
        {
            Location loc = new Location(row, i);
            if(grid.get(loc) == null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Moves down every row of the grid above the given row by one row.
     * @param row the given row
     * @postcondition every row above the given row is moved down by one row
     */
    private void clearRow(int row)
    {
        for(int i = row-1; i >= 0; i--)
        {
            for(int j = 0; j < grid.getNumCols(); j++)
            {
                Location loc = new Location(i, j);
                Location newLoc = new Location(i+1, j);
                Block b = grid.get(loc);
                if(b == null)
                {
                    grid.put(newLoc, null);
                }
                else
                {
                    b.removeSelfFromGrid();
                    b.putSelfInGrid(grid, newLoc);
                }
            }
        }
    }
    
    /**
     * Clears all the completed rows of the grid.
     * @postcondition all the completed rows are cleared, score is incremented based on 
     * how many rows have been completed, level is incremented by one, 
     * waitTime is divided by 1.01
     */
    private void clearCompletedRows()
    {
        int numRowsCompleted = 0;
        for(int i = 0; i < grid.getNumRows(); i++)
        {
            if(isCompletedRow(i))
            {
                clearRow(i);
                numRowsCompleted++;
            }
        }
        score += level*numRowsCompleted*numRowsCompleted;
        level++;
        waitTime /= 1.01;
    }
}