import java.util.*;

public class Knight extends Piece {

    public Knight(Color c) { 
        set_color(c);
    }

    public String toString() {
	    return color() == Color.WHITE ? "wn" : "bn";
    }

    public List<String> moves(Board b, String loc) {
        List<String> legalMoves = new ArrayList<>();

        int row = 8 - (loc.charAt(1) - '0'); 
        int col = loc.charAt(0) - 'a';

        int[][] potentialMoves = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
        };

        for (int[] move : potentialMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (newRow >= 0 && newRow <= 7 && newCol >= 0 && newCol <= 7) {
                Piece destinationPiece = b.getPiece(toLoc(newCol, newRow));
                if (destinationPiece == null || destinationPiece.color() != color()) {
                    legalMoves.add(toLoc(newCol, newRow));
                }
            }
        }
        return legalMoves;
    }
    private String toLoc(int col, int row) {
        return (char) ('a' + col) + Integer.toString(8 - row);
    }

}