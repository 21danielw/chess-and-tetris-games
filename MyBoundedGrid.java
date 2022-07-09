import java.util.*;
/**
 * The MyBoundedGrid class represents a two dimensional bounded grid.
 * @author Daniel Wang
 * @version 3/7/18
 * @param <E> the type of the objects placed in the MyBoundedGrid object
 */
public class MyBoundedGrid<E>
{
    /**
     * Instance variables
     */
    private E[][] grid;
    
    /**
     * Creates a new MyBoundedGrid object with the given numbers of rows and columns.
     * @param numRows the given number of rows
     * @param numCols the given number of columns
     * @postcondition grid is initialized to be a new two dimensional array of type E
     *                with the given numbers of rows and columns
     */
    public MyBoundedGrid(int numRows, int numCols)
    {
        grid = (E[][]) new Object[numRows][numCols];
    }
    
    /**
     * Gets the number of rows in the grid.
     * @return length of grid
     */
    public int getNumRows()
    {
        return grid.length;
    }
    
    /**
     * Gets the number of columns in the grid.
     * @return length of the first row of grid
     */
    public int getNumCols()
    {
        return grid[0].length;
    }
    
    /**
     * Checks whether the given location is within the boundary of the grid.
     * @param loc the given location
     * @return true if loc is within the grid, false otherwise
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0 && 
                loc.getRow() < grid.length && loc.getCol() < grid[0].length;
    }
    
    /**
     * Puts the given object into the grid at the given location.
     * @param loc the given location
     * @param obj the given object
     * @return the object previously at loc in the grid if loc was previously occupied, 
     *         null otherwise
     */
    public E put(Location loc, E obj)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        E old = grid[row][col];
        grid[row][col] = obj;
        return old;
    }
    
    /**
     * Removes the object at the given location from the grid.
     * @param loc the given location
     * @return the object previously at loc
     */
    public E remove(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        E old = grid[row][col];
        grid[row][col] = null;
        return old;
    }
    
    /**
     * Gets the object at the given location in the grid.
     * @param loc the given location
     * @return the object at loc
     */
    public E get(Location loc)
    {
        return grid[loc.getRow()][loc.getCol()];
    }
    
    /**
     * Gets all the locations in the grid that have objects in them.
     * @return an ArrayList of type Location containing the occupied locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[0].length; col++)
            {
                if(grid[row][col] != null)
                {
                    a.add(new Location(row, col));
                }
            }
        }
        return a;
    }
}
