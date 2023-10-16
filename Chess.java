import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Chess {
    public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Chess layout moves");
			return; // Return here so we don't proceed further in case of invalid arguments.
		}
	
		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());
	
		// args[0] is the layout file name
		// args[1] is the moves file name
	
		try {
			// Read layout file and validate
			List<String> layoutLines = Files.readAllLines(Paths.get(args[0]));
	
			if (!isValidLayout(layoutLines)) {
				System.err.println("Invalid layout file.");
				return;
			}
	
			// Create pieces and set up the board
			for (String line : layoutLines) {
				if (line.charAt(0) == '#') continue; // ignore comments
				String location = line.substring(0, 2);
				String pieceCode = line.substring(3, 5);
				Piece piece = Piece.createPiece(pieceCode);
				Board.theBoard().addPiece(piece, location);
			}
	
			// Read moves file and play moves
			List<String> movesLines = Files.readAllLines(Paths.get(args[1]));
			for (String move : movesLines) {
				if (move.charAt(0) == '#') continue; // ignore comments
				String fromLocation = move.substring(0, 2);
				String toLocation = move.substring(3, 5);
				Board.theBoard().movePiece(fromLocation, toLocation);
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
	
		// Leave the following code at the end of the simulation:
		System.out.println("Final board:");
		Board.theBoard().iterate(new BoardPrinter());
	}
	



	public static boolean isValidLayout(List<String> layoutLines) {
		Set<String> occupiedPositions = new HashSet<>();
	
		for (String line : layoutLines) {
			// Ignore comments
			if (line.charAt(0) == '#') {
				continue;
			}
	
			// Check for correct line length and format (e.g., "a1=bp")
			if (line.length() != 5 || line.charAt(2) != '=') {
				return false;
			}
	
			String position = line.substring(0, 2);
			String piece = line.substring(3, 5);
	
			// Validate position
			char column = position.charAt(0);
			char row = position.charAt(1);
			if (column < 'a' || column > 'h' || row < '1' || row > '8') {
				return false;
			}
	
			// Check for duplicate positions
			if (occupiedPositions.contains(position)) {
				return false;
			}
			occupiedPositions.add(position);
	
			// Validate piece color and kind
			char color = piece.charAt(0);
			char kind = piece.charAt(1);
			if ((color != 'b' && color != 'w') || "kqnbrp".indexOf(kind) == -1) {
				return false;
			}
		}
	
		return true;
	}
	
}