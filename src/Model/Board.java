package Model;

public class Board {

	private final int boardSize = 64;
	private Square[] squares = new Square[boardSize];
	private Piece [] pieces = new Piece[32];
	
	public Board(){		
		populateSquares();
//		populatePieces();
	}
	
//	public void populatePieces(){
//		for(int i = 0; i < pieces.length; i++){
//			pieces[i] = new Piece('-', "NoColor");
//		}
//	}
	
	public void populateSquares(){			
		char startRank = '1';
		int arrayIndex = 0;
		Piece initialPiece = new Piece('-', "NoColor");
		for(int i = 0; i < boardSize/8; i++){
			char startFile = 'a';	
			for(int j = 0; j < boardSize/8; j++){
				Square square = new Square("" + startFile + startRank, initialPiece);
				squares[arrayIndex] = square;
				startFile++;				
				arrayIndex++;
			}
			startRank++;
		}
	}
	
	public void drawBoard(){
		int arrayIndex = 0;
		for(int i = 0; i < boardSize/8; i++){		
			for(int j = 0; j < boardSize/8; j++){
//				Change this to pull out the pieces value
				System.out.print(squares[arrayIndex].getPiece().getCharacterPiece() + "\t");
//				System.out.print(squares[arrayIndex].getPieceChar() + "\t");
//				System.out.print(squares[arrayIndex].getFileRank() + "\t");	
				arrayIndex++;
			}			
			System.out.println("\n");
		}
	}
	
	public Square[] getSquares(){
		return squares;
	}
	
	public Piece[] getPieces(){
		return pieces;
	}
	
	public void setPieces(Piece[] pieces){
		this.pieces = pieces;
	}
	
//	public static void main(String[] args) {
//		Board board = new Board();
//		board.populateSquares();
//		board.drawBoard();
//		for(int i = 0; i < 64; i++){
//			System.out.println(board.squares[i].getFileRank());
//		}
//	}
}
