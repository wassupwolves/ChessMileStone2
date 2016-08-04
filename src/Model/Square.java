package Model;

public class Square {
	
	private boolean isOccupied;
//	private char pieceChar;
	private String fileRank;
	private Piece piece;
	
	public Square(String fileRank, Piece piece){
		isOccupied = false;
		this.fileRank = fileRank;
//		pieceChar = piece.getCharacterPiece();
		this.setPiece(piece);
	}
	
	public String getFileRank() {
		return fileRank;
	}
	
//	public void setFileRank(String fileRank) {
//		this.fileRank = fileRank;
//	}
	
//	public char getPieceChar() {
//		return pieceChar;
//	}
//	
//	public void setPieceChar() {
//		this.pieceChar = piece.getCharacterPiece();
//	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
	
	public void setOccupied(boolean occupied) {
		this.isOccupied = occupied;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
