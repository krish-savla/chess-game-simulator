import java.util.*;

public class Pawn extends Piece {

    public Pawn(Color c) {
        set_color(c);
    }

    public String toString() {
        return color() == Color.WHITE ? "wp" : "bp";
    }

    public List<String> moves(Board b, String loc) {
        List<String> legalMoves = new ArrayList<>();
    
        int row = loc.charAt(1) - '0';  // Convert loc like 'a2' to row index
        int col = loc.charAt(0) - 'a';  // Convert loc like 'a2' to column index
        // For white pawn
        if (color() == Color.WHITE) {
            // Move forward one square
            if (row + 1 <= 8 && b.getPiece((char)(col + 'a') + String.valueOf(row + 1)) == null) {
                legalMoves.add((char)(col + 'a') + String.valueOf(row + 1));
            }
            // Move forward two squares from the starting row
            if (row == 2 && b.getPiece((char)(col + 'a') + String.valueOf(row + 1)) == null 
                && b.getPiece((char)(col + 'a') + String.valueOf(row + 2)) == null) {
                legalMoves.add((char)(col + 'a') + String.valueOf(row + 2));
            }
            // Capture diagonally left
            if (col - 1 >= 0 && row + 1 <= 8 && b.getPiece((char)(col - 1 + 'a') + String.valueOf(row + 1)) != null 
                && b.getPiece((char)(col - 1 + 'a') + String.valueOf(row + 1)).color() == Color.BLACK) {
                legalMoves.add((char)(col - 1 + 'a') + String.valueOf(row + 1));
            }
            // Capture diagonally right
            if (col + 1 <= 7 && row + 1 <= 8 && b.getPiece((char)(col + 1 + 'a') + String.valueOf(row + 1)) != null 
                && b.getPiece((char)(col + 1 + 'a') + String.valueOf(row + 1)).color() == Color.BLACK) {
                legalMoves.add((char)(col + 1 + 'a') + String.valueOf(row + 1));
            }
        } 
        // For black pawn
        else {
            // Move forward one square
            if (row - 1 >= 1 && b.getPiece((char)(col + 'a') + String.valueOf(row - 1)) == null) {
                legalMoves.add((char)(col + 'a') + String.valueOf(row - 1));
            }
            // Move forward two squares from the starting row
            if (row == 7 && b.getPiece((char)(col + 'a') + String.valueOf(row - 1)) == null 
                && b.getPiece((char)(col + 'a') + String.valueOf(row - 2)) == null) {
                legalMoves.add((char)(col + 'a') + String.valueOf(row - 2));
            }
            // Capture diagonally left
            if (col - 1 >= 0 && row - 1 >= 1 && b.getPiece((char)(col - 1 + 'a') + String.valueOf(row - 1)) != null 
                && b.getPiece((char)(col - 1 + 'a') + String.valueOf(row - 1)).color() == Color.WHITE) {
                legalMoves.add((char)(col - 1 + 'a') + String.valueOf(row - 1));
            }
            // Capture diagonally right
            if (col + 1 <= 7 && row - 1 >= 1 && b.getPiece((char)(col + 1 + 'a') + String.valueOf(row - 1)) != null 
                && b.getPiece((char)(col + 1 + 'a') + String.valueOf(row - 1)).color() == Color.WHITE) {
                legalMoves.add((char)(col + 1 + 'a') + String.valueOf(row - 1));
            }
        }
    
        return legalMoves;
    }
    


}