import java.util.*;

public class Queen extends Piece {
    public Queen(Color c) {set_color(c); }

    public String toString() {
	    return color() == Color.WHITE ? "wq" : "bq";
    }

    public List<String> moves(Board b, String loc) {
        Rook r = new Rook(color());
        Bishop bish = new Bishop(color());

	    List<String> qmoves = r.moves(b, loc);
        qmoves.addAll(bish.moves(b, loc));


        return qmoves;
    }

}