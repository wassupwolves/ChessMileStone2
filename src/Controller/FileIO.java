package Controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Board;
import Model.Piece;
import Model.Square;

public class FileIO {
	
	public InputStream inputStream;
	public File file;
	private ArrayList<String> actions;
	private Board board;
	private int pieceCounter = 0;
	
//	public static void main(String[] args) {
//		FileIO fileIO = new FileIO(args[0]);
//		try {
//			InputStream fileInput = new FileInputStream(fileIO.file);
//			fileIO.readFile(fileInput);
//		} catch (FileNotFoundException ex) {
//			System.out.println(ex);
//		}
//		ArrayList<String> actions = fileIO.getActions();
//		for(int i = 0; i < actions.size(); i++){
//			System.out.println(actions.get(i));
//		}		
//	}
	
	private void assignSquarePieces(char pieceChar, char color, String fileRank){
		Square[] squares = board.getSquares();
		Piece[] pieces = board.getPieces();
		Piece piece;
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getFileRank().equals(fileRank)){
				if(color == 'l'){
					piece = new Piece(Character.toUpperCase(pieceChar), "White");
					squares[i].setOccupied(true);
//					squares[i].setPieceChar();
					squares[i].setPiece(piece);
					pieces[pieceCounter] = piece;
					pieceCounter++;
				}
				else if(color == 'd'){
					piece = new Piece(Character.toLowerCase(pieceChar), "Black");
					squares[i].setOccupied(true);
//					squares[i].setPieceChar();
					squares[i].setPiece(piece);
					pieces[pieceCounter] = piece;
					pieceCounter++;
				}
				board.setPieces(pieces);
				break;
			}
		}
	}
	
	public Board getBoard(){
		return board;
	}
	
	public FileIO(String fileName){
		file = new File(fileName);
		try{
			inputStream = new FileInputStream(fileName);
		}
		catch(FileNotFoundException ex){
			System.out.println(ex);
		}
		board = new Board();
	}
	
	public ArrayList<String> getActions(){
		return actions;
	}
	
	public void readFile(){
		actions = new ArrayList<String>();
		String line = "";
		boolean regexMatch;
		String placementRegex = new String("([A-Z][a-z][a-z][0-9]).*");
		String moveRegex = new String("([a-z][0-9]\\s[a-z][0-9][\\*]*).*");
		String doubleMoveRegex = new String("([a-z][0-9]\\s[a-z][0-9]\\s[a-z][0-9]\\s[a-z][0-9]).*");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		try{			
			while((line = bufferedReader.readLine()) != null){
				regexMatch = false;
				line = line.trim();
				Pattern p = Pattern.compile(placementRegex);
				Matcher m = p.matcher(line);
				if(m.matches() && regexMatch == false){
					line = m.group(1);
					actions.add(placePiece(line));
					regexMatch = true;
				}
				
				p = Pattern.compile(doubleMoveRegex);
				m = p.matcher(line);
				if(m.matches() && regexMatch == false){
					line = m.group(1);
					actions.add(moveTwoPieces(line));
					regexMatch = true;
				}
				
				p = Pattern.compile(moveRegex);
				m = p.matcher(line);
				if(m.matches() && regexMatch == false){
					line = m.group(1);
					actions.add(movePiece(line));
					regexMatch = true;
				}				
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println(ex);
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	}
	
	private String placePiece(String line){
		String placement = "[" + line + "] ";
		String fileRank = fileRankConcat(line.charAt(2), line.charAt(3));
		switch(line.charAt(1)){
		case 'l':
			placement += "White ";
			break;
		case 'd':
			placement += "Black ";
			break;
		default:
			placement += "INVALID color ";
		}
		switch(line.charAt(0)){
		case 'Q':
			placement += "Queen was placed at " + fileRank;
			break;
		case 'K':
			placement += "King was placed at " + fileRank;
			break;
		case 'B':
			placement += "Bishop was placed at " + fileRank;
			break;
		case 'N':
			placement += "Knight was placed at " + fileRank;
			break;
		case 'R':
			placement += "Rook was placed at " + fileRank;
			break;
		case 'P':
			placement += "Pawn was placed at " + fileRank;
			break;
		default:
			placement += "INVALID piece was placed at " + fileRank;
		}
		assignSquarePieces(line.charAt(0), line.charAt(1), fileRank);
		return placement;
	}
	
	private String checkFilePlacement(char letter){
		String placement = "";
		switch(letter){
		case 'a':
			placement = "a";
			break;
		case 'b':
			placement = "b";
			break;
		case 'c':
			placement = "c";
			break;
		case 'd':
			placement = "d";
			break;
		case 'e':
			placement = "e";
			break;
		case 'f':
			placement = "f";
			break;
		case 'g':
			placement = "g";
			break;
		case 'h':
			placement = "h";
			break;
		default:
			placement = "INVALID FILE ";
		}
		return placement;
	}
	
	private String checkRankPlacement(char number){
		String placement = "";
		switch(number){
		case '1':
			placement = "1";
			break;
		case '2':
			placement = "2";
			break;
		case '3':
			placement = "3";
			break;
		case '4':
			placement = "4";
			break;
		case '5':
			placement = "5";
			break;
		case '6':
			placement = "6";
			break;
		case '7':
			placement = "7";
			break;
		case '8':
			placement = "8";
			break;
		default:
			placement = " INVALID RANK ";
		}
		return placement;
	}
	
	private String fileRankConcat(char file, char rank){
		return checkFilePlacement(file) + checkRankPlacement(rank);
	}
	
	private String movePiece(String line){
		String movement = "[" + line + "] ";
		String startSpace = fileRankConcat(line.charAt(0), line.charAt(1));
		String finalSpace = fileRankConcat(line.charAt(3), line.charAt(4));
		if(line.length() == 6){
			movement += "Piece from " + startSpace + " took piece at " + finalSpace;
		}
		else{
			movement += "Piece from " + startSpace + " moved to " + finalSpace;
		}
		return movement;
	}
	
	private String moveTwoPieces(String line){
		String kingStart = fileRankConcat(line.charAt(0), line.charAt(1));
		String kingFinal = fileRankConcat(line.charAt(3), line.charAt(4));
		String rookStart = fileRankConcat(line.charAt(6), line.charAt(7));
		String rookFinal = fileRankConcat(line.charAt(9), line.charAt(10));
		return "[" + line + "] " + "King moved from " + kingStart + " to " + kingFinal + 
				" Rook moved from " + rookStart + " to " + rookFinal;
	}
	
}