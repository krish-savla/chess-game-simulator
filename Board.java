import java.util.*;

public class Board {
    private static final Board boardInstance = new Board();
    private Piece[][] pieces = new Piece[8][8]; // 8x8 chess board
    private List<BoardListener> listeners = new ArrayList<>();

    private Board() {
        // private constructor to prevent external instantiation
    }

    public static Board theBoard() {
        return boardInstance;
    }

    public Piece getPiece(String loc) {
        int[] coordinates = locationToCoordinates(loc);
        if (coordinates[0] < 0 || coordinates[0] > 7 || coordinates[1] < 0 || coordinates[1] > 7) {
            throw new IllegalArgumentException("Invalid location.");
        }
        return pieces[coordinates[0]][coordinates[1]];
    }

    public void addPiece(Piece p, String loc) {
        int[] coordinates = locationToCoordinates(loc);
        if (pieces[coordinates[0]][coordinates[1]] != null) {
            throw new IllegalArgumentException("Location already occupied.");
        }
        pieces[coordinates[0]][coordinates[1]] = p;
    }

    public void movePiece(String from, String to) {
        int[] fromCoordinates = locationToCoordinates(from);
        int[] toCoordinates = locationToCoordinates(to);

        Piece movingPiece = pieces[fromCoordinates[0]][fromCoordinates[1]];

        if (movingPiece == null) {
            throw new IllegalArgumentException("No piece at starting location.");
        }

        if (!movingPiece.moves(this, from).contains(to)) {
            throw new IllegalArgumentException("Invalid move for the piece.");
        }
        BoardListener bl = listeners.get(0);
        bl.onMove(from, to, movingPiece);

        if (getPiece(to) != null) {
            bl.onCapture(movingPiece, getPiece(to));
        }

        pieces[toCoordinates[0]][toCoordinates[1]] = movingPiece;
        pieces[fromCoordinates[0]][fromCoordinates[1]] = null;
    }

    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
    }

    private int[] locationToCoordinates(String loc) {
        int col = loc.charAt(0) - 'a';
        int row = loc.charAt(1) - '1';
        return new int[]{row, col};
    }


    public void registerListener(BoardListener bl) {
        listeners.add(bl);
    }

    public void removeListener(BoardListener bl) {
        listeners.remove(bl);
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    public void iterate(BoardInternalIterator bi) {
        if (bi == null) {
            throw new NoSuchElementException();
        }

        char[] rows = {'1', '2', '3', '4', '5', '6', '7', '8'};
        char[] cols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String curr = Character.toString(cols[j]) + Character.toString(rows[i]);
                bi.visit(curr, getPiece(curr)); 
            }
        }
    }
}
