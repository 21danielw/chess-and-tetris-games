import java.awt.Color;
/**
* The BLock class encapsulates a Block abstraction 
* which can be placed into a Gridworld style grid.
* @author Daniel Wang
* @version 3/6/18
*/
public class Block
{
    /**
     * Instance variables
     */
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    
    /**
    * Constructs a blue block.
    * @postcondition color is set to blue, grid and location are both set to null
    */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }
    
    /**
    * Gets the color of the block.
    * @return color
    */
    public Color getColor()
    {
        return color;
    }
    
    /**
    * Sets the color of the block to the given color.
    * @param newColor the given color
    * @postcondition the color of the block is set to newColor
    */
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
    /**
    * Gets the grid that contains the block.
    * @return the grid that contains the block, or null if the block is not in any grid
    */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }
    
    /**
    * Gets the location of the block.
    * @return the location of the block, or null if the block is not in any grid
    */
    public Location getLocation()
    {
        return location;
    }
    
    /**
    * Removes the block from its original grid.
    * @postcondition the block is removed from its original grid, 
    *                grid is set to null, location is set to null
    */
    public void removeSelfFromGrid()
    {
        if(grid != null)
        {
            grid.remove(location);
            grid = null;
            location = null;
        }
    }
    
    /**
    * Puts itself in the given grid at the given location.
    * @param gr the given grid
    * @param loc the given location
    * @postcondition the current block is removed from its original grid, 
    *                grid is set to gr, location is set to loc, 
    *                the block originally at loc in gr is removed, 
    *                the current block is put into gr at loc
    */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        if(grid != null)
        {
            grid.remove(location);
        }
        grid = gr;
        location = loc;
        if(grid.get(location) != null)
        {
            grid.get(location).removeSelfFromGrid();
        }
        grid.put(location, this);
    }
    
    /**
    * Moves the block to the given location in the same grid.
    * @param newLocation the given location
    * @postcondition the current block is removed from its original location,
    *                location is set to newLocation,
    *                the block originally at newLocation is removed,
    *                the current block is inserted at newLocation
    */
    public void moveTo(Location newLocation)
    {
        if(grid != null)
        {
            grid.remove(location);
            location = newLocation;
            if(grid.get(location) != null)
            {
                grid.get(location).removeSelfFromGrid();
            }
            grid.put(location, this);
        }
    }
    
    /**
    * Returns a String representation of the block.
    * @return a string with the location and color of the block
    */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}