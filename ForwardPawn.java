import java.awt.*;
import java.util.*;
public class ForwardPawn extends Piece
{
    public ForwardPawn(Color col, String filename)
    {
        super(col, filename, 1);
    }
    
    public ArrayList<Location> destinations()
    {
        Location myLocation = getLocation();
        int row = myLocation.getRow();
        int col = myLocation.getCol();
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        for(int i = -1; i <= 1; i++)
        {
            if(i != 0)
            {
                int newRow = row+i;
                int newCol = col;
                Location loc = new Location(newRow, newCol);
                if(isValidDestination(loc))
                {
                    destinationLocations.add(loc);
                }
            }
        }
        return destinationLocations;
    }
}
