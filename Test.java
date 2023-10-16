import java.util.*;

public class Test {

    // Run "java -ea Test" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

    public static void test1() {
        Board b = Board.theBoard();
        b.clear();
        Piece.registerPiece(new BishopFactory());
        Piece p = Piece.createPiece("bb");
        b.addPiece(p, "a3");
        assert b.getPiece("a3") == p;
    }
    public static void test2() {
        Board b = Board.theBoard();
        b.clear();
        Piece.registerPiece(new KingFactory());
        Piece p = Piece.createPiece("bk");
        b.addPiece(p, "e5");
        List<String> kmoves = p.moves(b, "e3");
        assert kmoves.size() == 8;
        assert b.getPiece("e5") == p;
    }
    public static void testPawn() {
        Board b = Board.theBoard();
        b.clear();
        Piece.registerPiece(new PawnFactory());
    
        // Test white pawn's basic forward movement
        Piece wp = Piece.createPiece("wp");
        b.addPiece(wp, "e2");
        List<String> pawnMoves = wp.moves(b, "e2");
        System.out.println(wp.moves(b, "e2"));

        assert pawnMoves.size() == 2;  // can move two squares from the starting position
        assert pawnMoves.contains("e3");
        assert pawnMoves.contains("e4");
        assert (wp.color() == Color.WHITE);
    
        // Test white pawn's capture
        Piece bp = Piece.createPiece("bp");
        b.addPiece(bp, "d3");
        pawnMoves = wp.moves(b, "e2");
        System.out.println(b.getPiece("d3"));
        System.out.println(wp.moves(b, "e2"));
        assert pawnMoves.contains("d3");  // can capture diagonally
    }
    public static void testKnight() {
        Board b = Board.theBoard();
        b.clear();
        Piece.registerPiece(new KnightFactory());
    
        // Test knight's movement from center
        Piece wk = Piece.createPiece("wk");
        b.addPiece(wk, "d4");
        List<String> knightMoves = wk.moves(b, "d4");
        assert knightMoves.size() == 8;
        assert wk.color() == Color.WHITE;
    }
    public static void testBishop() {
        Board b = Board.theBoard();
        b.clear();
        Piece.registerPiece(new BishopFactory());
    
        // Test bishop's diagonal movement
        Piece bb = Piece.createPiece("bb");
        b.addPiece(bb, "c3");
        List<String> bishopMoves = bb.moves(b, "c3");
        System.out.println(bishopMoves);
        assert bishopMoves.contains("a1");
        assert bishopMoves.contains("e5");
        assert !bishopMoves.contains("c1");  
        System.out.println(bb.color().toString());
        assert bb.color() == Color.BLACK;
    }
    public static void testRook() {
        Board b = Board.theBoard();
        b.clear();
        Piece.registerPiece(new RookFactory());
    
        // Test rook's vertical movement
        Piece wr = Piece.createPiece("br");
        b.addPiece(wr, "a4");
        List<String> rookMoves = wr.moves(b, "a4");
        assert rookMoves.contains("a1");
        assert rookMoves.contains("a8");
        assert rookMoves.contains("b4");  
        assert wr.color() == Color.BLACK;
    }
    
    
    
    
    public static void main(String[] args) {
	    test1();
        test2();
        testPawn();
        testKnight();
        testBishop();
        testRook();
    }

}