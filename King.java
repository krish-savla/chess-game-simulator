import java.util.*;

public class King extends Piece {

    public King(Color c) {
        set_color(c);
    }

    public String toString() {
        return color() == Color.WHITE ? "wk" : "bk";
    }

    public List<String> moves(Board b, String loc) {
        List<String> validMoves = new ArrayList<>();
        int[] rowOffsets = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] colOffsets = {-1, 0, 1, 1, 1, 0, -1, -1};

        int row = loc.charAt(1) - '1'; // Convert from 1-based to 0-based index
        int col = loc.charAt(0) - 'a'; // Convert from char to int index

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                String newLoc = "" + (char) ('a' + newCol) + (newRow + 1);
                Piece pieceAtDestination = b.getPiece(newLoc);
                if (pieceAtDestination == null || pieceAtDestination.color() != color()) {
                    validMoves.add(newLoc);
                }
            }
        }
        return validMoves;
    }
}
