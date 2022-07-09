import java.awt.Color;
import java.util.concurrent.Semaphore;
/**
 * The Tetrad class represents a tetrad in the game of Tetris.
 * @author Daniel Wang
 * @version 3/29/18
 */
public class Tetrad
{
    /**
     * Instance variables
     */
    private Block[] blocks;
    private MyBoundedGrid<Block> grid;
    private Semaphore lock;
    private boolean created;
    
    /**
     * Creates a new Tetrad object using the given MyBoundedGrid object.
     * @param g the given MyBoundedGrid object
     * @postcondition lock is initialized to be a new Semaphore object, 
     * grid is set to g, blocks is initialized to be 
     * a new array of type Block of length 4, 
     * created is initialized to be true if the tetrad can be placed in the grid and 
     * false otherwise
     */
    public Tetrad(MyBoundedGrid<Block> g)
    {
        lock = new Semaphore(1, true);
        int whichTetrad = (int)(Math.random()*7);
        Color c;
        Location[] locs = new Location[4];
        if(whichTetrad == 0)
        {
            c = Color.RED;
            locs[0] = new Location(1, 0);
            locs[1] = new Location(0, 0);
            locs[2] = new Location(2, 0);
            locs[3] = new Location(3, 0);
        }
        else if(whichTetrad == 1)
        {
            c = Color.GRAY;
            locs[0] = new Location(0, 0);
            locs[1] = new Location(0, -1);
            locs[2] = new Location(0, 1);
            locs[3] = new Location(1, 0);
        }
        else if(whichTetrad == 2)
        {
            c = Color.CYAN;
            locs[0] = new Location(0, 0);
            locs[1] = new Location(0, 1);
            locs[2] = new Location(1, 0);
            locs[3] = new Location(1, 1);
        }
        else if(whichTetrad == 3)
        {
            c = Color.YELLOW;
            locs[0] = new Location(2, 0);
            locs[1] = new Location(0, 0);
            locs[2] = new Location(1, 0);
            locs[3] = new Location(2, 1);
        }
        else if(whichTetrad == 4)
        {
            c = Color.MAGENTA;
            locs[0] = new Location(2, 0);
            locs[1] = new Location(0, 0);
            locs[2] = new Location(1, 0);
            locs[3] = new Location(2, -1);
        }
        else if(whichTetrad == 5)
        {
            c = Color.BLUE;
            locs[0] = new Location(0, 0);
            locs[1] = new Location(0, 1);
            locs[2] = new Location(1, -1);
            locs[3] = new Location(1, 0);
        }
        else
        {
            c = Color.GREEN;
            locs[0] = new Location(0, 0);
            locs[1] = new Location(0, -1);
            locs[2] = new Location(1, 0);
            locs[3] = new Location(1, 1);
        }
        
        grid = g;
        blocks = new Block[locs.length];
        Location[] realLocs = new Location[locs.length];
        int pivotRow = 0;
        int pivotColumn = grid.getNumCols()/2-1;
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
            blocks[i].setColor(c);
            realLocs[i] = new Location(pivotRow+locs[i].getRow(), 
                    pivotColumn+locs[i].getCol());
        }
        if(areEmpty(g, realLocs))
        {
            created = true;
            addToLocations(g, realLocs);
        }
        else
        {
            created = false;
        }
    }
    
    /**
     * Checks whether the tetrad is placed in the grid.
     * @return created
     */
    public boolean isCreated()
    {
        return created;
    }
    
    /**
     * Puts the tetrad's blocks into the given MyBoundedGrid object 
     * at the given locations.
     * @param g the given MyBoundedGrid object
     * @param locs the given locations
     * @postcondition the tetrad's blocks are put into g at the given locations
     */
    private void addToLocations(MyBoundedGrid<Block> g, Location[] locs)
    {
        for(int i = 0; i < locs.length; i++)
        {
            blocks[i].putSelfInGrid(g, locs[i]);
        }
    }
    
    /**
     * Removes the tetrad's blocks from the grid.
     * @postcondition the tetrad's blocks are removed from grid
     * @return an array of Location objects, representing the former locations 
     * of the tetrad's blocks in grid
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[blocks.length];
        for(int i = 0; i < blocks.length; i++)
        {
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return locs;
    }
    
    /**
     * Checks whether the given locations in the given MyBoundedGrid object are empty.
     * @param g the given MyBoundedGrid object
     * @param locs the given locations
     * @return true if the given locations in g are all empty, false otherwise
     */
    private boolean areEmpty(MyBoundedGrid<Block> g, Location[] locs)
    {
        for(Location loc : locs)
        {
            if(!g.isValid(loc) || g.get(loc) != null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Translates the tetrad by the given amounts if possible.
     * @param deltaRow amount to move vertically
     * @param deltaCol amount to move horizontally
     * @postcondition the tetrad is translated by the given amounts if possible; 
     * otherwise, the tetrad is not moved
     * @return true if the tetrad can be translated by the given amounts, false otherwise
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            Location[] locs = new Location[blocks.length];
            Location[] newLocs = new Location[blocks.length];
            for(int i = 0; i < blocks.length; i++)
            {
                Location loc = blocks[i].getLocation();
                locs[i] = loc;
                newLocs[i] = new Location(loc.getRow()+deltaRow, loc.getCol()+deltaCol);
            }
            removeBlocks();
            if(!areEmpty(grid, newLocs))
            {
                addToLocations(grid, locs);
                return false;
            }
            addToLocations(grid, newLocs);
            return true;
        }
        catch(InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }
    
    /**
     * Rotates the tetrad by 90 degrees clockwise if possible.
     * @postcondition the tetrad is rotated by 90 degrees clockwise if possible; 
     * otherwise, the tetrad is not moved
     * @return true if the tetrad can be rotated, false otherwise
     */
    public boolean rotate()
    {
        try
        {
            lock.acquire();
            Location[] locs = new Location[blocks.length];
            Location[] newLocs = new Location[blocks.length];
            int row0 = blocks[0].getLocation().getRow();
            int col0 = blocks[0].getLocation().getCol();
            for(int i = 0; i < blocks.length; i++)
            {
                Location loc = blocks[i].getLocation();
                locs[i] = loc;
                newLocs[i] = new Location(row0-col0+loc.getCol(), row0+col0-loc.getRow());
            }
            removeBlocks();
            if(!areEmpty(grid, newLocs))
            {
                addToLocations(grid, locs);
                return false;
            }
            addToLocations(grid, newLocs);
            return true;
        }
        catch(InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }
}