import java.util.*;

abstract public class Piece {
    private static Map<String, PieceFactory> pieceFactories = new HashMap<String, PieceFactory>();

    public static void registerPiece(PieceFactory pf) {
        pieceFactories.put(Character.toString(pf.symbol()), pf);
    }
    private Color color;

    public static Piece createPiece(String name) {
        PieceFactory pf = (PieceFactory)pieceFactories.get(Character.toString(name.charAt(1))); // assuming name is like "bp", "wp", etc.
        if (pf == null) {
            throw new IllegalArgumentException("No registered factory for symbol: " + name);
        }
        if (name.charAt(0) == 'w') {
            return pf.create(Color.WHITE);
        }
        else if (name.charAt(0) == 'b') {
            return pf.create(Color.BLACK);
        }
        else {
            throw new NoSuchElementException();
        }
        
    }

   

    public Color color() {
        return this.color;
    }

    public void set_color(Color color) {
        this.color = color;
    }

    abstract public String toString();
    abstract public List<String> moves(Board b, String loc);
}
