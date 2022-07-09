import java.awt.*;
import java.util.ArrayList;
public class SquareKnight extends Piece
{
    public SquareKnight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }
    public ArrayList<Location> destinations()
    {
        Location myLocation = getLocation();
        int row = myLocation.getRow();
        int col = myLocation.getCol();
        ArrayList<Location> destinationLocations = new ArrayList<Location>();
        for(int i = -2; i <= 2; i++)
        {
            for(int j = -2; j <= 2; j++)
            {
                if(i != 0 || j != 0)
                {
                    int newRow = row + i;
                    int newCol = col + j;
                    Location loc = new Location(newRow, newCol);
                    if(isValidDestination(loc))
                    {
                        destinationLocations.add(loc);
                    }
                }
            }
        }
        return destinationLocations;
    }
}
