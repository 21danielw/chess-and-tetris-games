import java.awt.*;
import java.util.*;
/**
 * The ChessGame class runs a game of chess.
 * @author Daniel Wang
 * @version 4/25/18
 */
public class ChessGame
{
    /**
     * Runs a game of chess.
     * @param args command line arguments
     * @postcondition the game is over
     */
    public static void main(String[] args)
    {
        Board board = new Board();
        for(int i = 0; i < 8; i++)
        {
            Piece blackPawn = new Pawn(Color.BLACK, 
                    "./chessfiles/black_pawn.gif");
            blackPawn.putSelfInGrid(board, new Location(1, i));
            Piece whitePawn = new Pawn(Color.WHITE, 
                    "./chessfiles/white_pawn.gif");
            whitePawn.putSelfInGrid(board, new Location(6, i));
        }
        
        Piece blackRookLeft = new Rook(Color.BLACK, 
                "./chessfiles/black_rook.gif");
        blackRookLeft.putSelfInGrid(board, new Location(0, 0));
        
        Piece blackKnightLeft = new Knight(Color.BLACK, 
                "./chessfiles/black_knight.gif");
        blackKnightLeft.putSelfInGrid(board, new Location(0, 1));
        
        Piece blackBishopLeft = new Bishop(Color.BLACK, 
                "./chessfiles/black_bishop.gif");
        blackBishopLeft.putSelfInGrid(board, new Location(0, 2));
        
        Piece blackQueen = new Queen(Color.BLACK, 
                "./chessfiles/black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));
        
        Piece blackKing = new King(Color.BLACK, 
                "./chessfiles/black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        
        Piece blackBishopRight = new Bishop(Color.BLACK, 
                "./chessfiles/black_bishop.gif");
        blackBishopRight.putSelfInGrid(board, new Location(0, 5));
        
        Piece blackKnightRight = new Knight(Color.BLACK, 
                "./chessfiles/black_knight.gif");
        blackKnightRight.putSelfInGrid(board, new Location(0, 6));
        
        Piece blackRookRight = new Rook(Color.BLACK, 
                "./chessfiles/black_rook.gif");
        blackRookRight.putSelfInGrid(board, new Location(0, 7));
        
        
        Piece whiteRookLeft = new Rook(Color.WHITE, 
                "./chessfiles/white_rook.gif");
        whiteRookLeft.putSelfInGrid(board, new Location(7, 0));
        
        Piece whiteKnightLeft = new Knight(Color.WHITE, 
                "./chessfiles/white_knight.gif");
        whiteKnightLeft.putSelfInGrid(board, new Location(7, 1));
        
        Piece whiteBishopLeft = new Bishop(Color.WHITE, 
                "./chessfiles/white_bishop.gif");
        whiteBishopLeft.putSelfInGrid(board, new Location(7, 2));
        
        Piece whiteQueen = new Queen(Color.WHITE, 
                "./chessfiles/white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));
        
        Piece whiteKing = new King(Color.WHITE, 
                "./chessfiles/white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));
        
        Piece whiteBishopRight = new Bishop(Color.WHITE, 
                "./chessfiles/white_bishop.gif");
        whiteBishopRight.putSelfInGrid(board, new Location(7, 5));
        
        Piece whiteKnightRight = new Knight(Color.WHITE, 
                "./chessfiles/white_knight.gif");
        whiteKnightRight.putSelfInGrid(board, new Location(7, 6));
        
        Piece whiteRookRight = new Rook(Color.WHITE, 
                "./chessfiles/white_rook.gif");
        whiteRookRight.putSelfInGrid(board, new Location(7, 7));
        
        BoardDisplay display = new BoardDisplay(board);
        
        /*
        Player p1 = new SmartPlayer(board, "Player1", Color.WHITE, 3);
        Player p2 = new SmartPlayer(board, "Player2", Color.BLACK, 3);
        Game.play(board, display, p1, p2);
        */
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Chess!");
        System.out.println("Which side do you want to play?");
        String side = sc.next();
        System.out.println("Which level do you want to play?");
        int level = sc.nextInt();
        System.out.println("The game is starting now.");
        if(side.toLowerCase().equals("white"))
        {
            HumanPlayer human = new HumanPlayer(board, "Person", Color.WHITE, display);
            SmartPlayer computer = new SmartPlayer(board, "Computer", Color.BLACK, level);
            Game.play(board, display, human, computer);
        }
        else
        {
            SmartPlayer computer = new SmartPlayer(board, "Computer", Color.WHITE, level);
            HumanPlayer human = new HumanPlayer(board, "Person", Color.BLACK, display);
            Game.play(board, display, computer, human);
        }
    }
}
