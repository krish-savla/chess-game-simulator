import java.util.*;

public class Rook extends Piece {

    public Rook(Color c) {
        set_color(c);
    }

    public String toString() {
        return color() == Color.WHITE ? "wr" : "br";
    }

    public List<String> moves(Board b, String loc) {
        List<String> validMoves = new ArrayList<>();
        int[] directions = {-1, 1};
    
        int row = loc.charAt(1) - '1'; // 0-based row index
        int col = loc.charAt(0) - 'a'; // 0-based column index
    
        // Check horizontal moves
        for (int dCol : directions) {
            int c = col + dCol;
            while (c >= 0 && c < 8) {
                Piece pieceAtDestination = b.getPiece("" + (char)('a' + c) + (row + 1));
                if (pieceAtDestination != null) {
                    if (pieceAtDestination.color() != color()) {
                        validMoves.add("" + (char)('a' + c) + (row + 1));
                    }
                    break;  // Stop when another piece is encountered
                } else {
                    validMoves.add("" + (char)('a' + c) + (row + 1));
                }
                c += dCol;
            }
        }
    
        // Check vertical moves
        for (int dRow : directions) {
            int r = row + dRow;
            while (r >= 0 && r < 8) {
                Piece pieceAtDestination = b.getPiece("" + (char)('a' + col) + (r + 1));
                if (pieceAtDestination != null) {
                    if (pieceAtDestination.color() != color()) {
                        validMoves.add("" + (char)('a' + col) + (r + 1));
                    }
                    break;  // Stop when another piece is encountered
                } else {
                    validMoves.add("" + (char)('a' + col) + (r + 1));
                }
                r += dRow;
            }
        }
    
        return validMoves;
    }
    
}
