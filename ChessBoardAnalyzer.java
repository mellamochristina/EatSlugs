package chessAttacks;
/*
 
CMPS 12B Winter 2017
 HW2
 Coded by Vanessa Tan and Christina Vo with help from instructors and classmates on Piazza. 
 Outside sources are commented in the code.
 */

import java.io.*;
import java.util.*;

class ChessBoardAnalyzer {
  /*
   * The input.txt must contain several chessboards and queries as given in
   * Examples.zip. Your program should read 2 lines at a time, construct a
   * chessboard, output the solution (for the current chessboard considered)
   * in analysis.txt. Then read the next chessboard and query from the same
   * input.txt file, append the output to analysis.txt. The process continues
   * until you have exhausted all the chessboards in input.txt.
   * 
   * Just to clarify, there will be no separate input text files for each
   * board.
   */
  public static void main(String[] args) throws IOException{
    //open files
    Scanner in = new Scanner(new File("input.txt"));
    //prints solution to analysis.txt
    PrintWriter out = new PrintWriter(new FileWriter("analysis.txt"));
    //read lines from input.txt
    while (in.hasNextLine()){
      String line1 = in.nextLine();
      String line2 = in.nextLine();
      ChessBoard board = createNewBoard(line1);
      String moveResults = analyzeMove(board, line2);
      String checkmateResults = analyzeCheckmate ();
      out.println(moveResults);
      out.println(checkmateResults);
    }
    in.close();
    out.close();
  }
  
  //Create new board and add pieces from input line to the board 
  static ChessBoard createNewBoard(String boardLine) {
    //removes spaces in the line and stores each element as a string in an array
    String [] line1Tokens = boardLine.split("\\s+");
    
    //converts first element from string array into an integer
    int boardSize = Integer.parseInt(line1Tokens[0]);
    ChessBoard board = new ChessBoard(boardSize);
    
    //after the first element, reads in every three elements as one chess piece
    for (int i = 1; i < line1Tokens.length; i = i + 3 ) {
      //converts from string to character for chess piece type
      char pieceType = line1Tokens[i].charAt(0);
      //converts from string to integers for col and row
      int col = Integer.parseInt(line1Tokens[i+1]);
      int row = Integer.parseInt(line1Tokens[i+2]);
      ChessPiece piece = createChessPiece(pieceType, col, row);
      board.addPiece(piece);
    }
    return board;
  }
  
//  //If chess board is valid, return string of chess piece type at query square and attacking pieces with their location 
//  //If chess board is invalid, return string "Invalid"
//  static String analyzeBoard (ChessBoard board, String queryLine) {
//    if (board.validatePieces() && board.validateKings()) {
//      String [] line2Tokens = queryLine.split("\\s+");
//      int col = Integer.parseInt(line2Tokens[0]);
//      int row = Integer.parseInt(line2Tokens[1]);
//      String valid = board.querySquare(col, row) + " " + board.determineAttackPieces();
//      return valid;
//    }
//    
//    return "Invalid";
//  }
  
  static String analyzeMove (ChessBoard board, String attackLine) {
	  String results = "";
	  String [] line2Tokens = attackLine.split("\\s+");
	  for (int i = 0; i < line2Tokens.length; i=i+4) {
		  int col = Integer.parseInt(line2Tokens[i]);
	      int row = Integer.parseInt(line2Tokens[i+1]);
	      ChessPiece piece = board.getPieceAt(col, row);
	      int x = Integer.parseInt(line2Tokens[i+2]);
	      int y = Integer.parseInt(line2Tokens[i+3]);
	      if (board.moveTo(piece, x, y)) {
	    	  results = "Valid " + results;
	      }
	      else {
	    	  results = "Invalid " + results;
	      }
	  }
	  return results;
	  
  }
  
  static String analyzeCheckmate() {
	  //Check all black pieces to make sure they are not attacking white king
	  //Check all white pieces to make sure they are not attacking black king
	  	//if a piece is attacking king, return "(color) in check"
	  //Check if possible destinations for white king are also attacked by black pieces
	  //Check if possible destinations for black king are also attacked by white pieces
	  	//if so, return "(color) checkmated"
	  //if both kings not under attack, return "All kings safe"
	  //If piece at (x’, y’) is blocking and is of the opposite color, this is a check and the piece is removed from list (delete method)
	  return "Check"; 	  //Return "Black/White in check" OR "Black/White checkmated" OR "All kings safe"
  }
  
  //ignores case to determine what type of chess piece to create 
  static ChessPiece createChessPiece(char type, int col, int row) {
    char lowerPiece = Character.toLowerCase(type);
    ChessPiece piece = null;
    
    if (lowerPiece == 'k') {
      piece = new King (type, col, row);
    }
    else if (lowerPiece == 'q') {
      piece = new Queen (type, col, row);
    }
    else if (lowerPiece == 'r') {
      piece = new Rook (type, col, row);
    }
    else if (lowerPiece == 'b') {
      piece = new Bishop (type, col, row);
    }
    else if (lowerPiece == 'n') {
      piece = new Knight (type, col, row);
    }
    
    return piece;
  }
  
}
