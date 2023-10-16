import java.util.*;

public class Bishop extends Piece {

    public Bishop(Color c) {
        set_color(c);
    }

    public String toString() {
        return color() == Color.WHITE ? "wb" : "bb";
    }

    public List<String> moves(Board b, String loc) {
        List<String> validMoves = new ArrayList<>();
        int[] directions = {-1, 1};

        int row = loc.charAt(1) - '1'; // 0-based row index
        int col = loc.charAt(0) - 'a'; // 0-based column index

        for (int dRow : directions) {
            for (int dCol : directions) {
                int r = row + dRow;
                int c = col + dCol;
                while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                    Piece pieceAtDestination = b.getPiece("" + (char)('a' + c) + (r + 1));
                    if (pieceAtDestination != null) {
                        if (pieceAtDestination.color() != color()) {
                            validMoves.add("" + (char)('a' + c) + (r + 1));
                        }
                        break;
                    } else {
                        validMoves.add("" + (char)('a' + c) + (r + 1));
                    }
                    r += dRow;
                    c += dCol;
                }
            }
        }

        return validMoves;
    }
}
