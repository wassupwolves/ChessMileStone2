package Model;

public class Piece {

	private char characterPiece;
	private String color;
	
	public Piece(char pieceSymbol, String pieceColor){
		characterPiece = pieceSymbol;
		color = pieceColor;
	}
	
	public String move(String startingSpot){
		String endingSpot = null;
		return endingSpot;
	}

	public char getCharacterPiece() {
		return characterPiece;
	}

	public void setCharacterPiece(char characterPiece) {
		this.characterPiece = characterPiece;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
