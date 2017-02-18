package chessAttacks;
import java.util.*;
import java.io.*;

class ChessPiece {
  //need the type of piece, its column, and row
  char piece;
  int col;
  int row;
  boolean color;
  
  ChessPiece (char piece, int col, int row) { //constructor for the chess piece
    this.piece = piece;
    this.col = col;
    this.row = row;
    if (piece==Character.toUpperCase(piece)) {
      this.color = true;
    }
    else{
      this.color = false;
    }
  }
  
  boolean isBlocking (ChessPiece c, int col, int row) {
	  return false;
  }
  boolean isAttacking (ChessPiece c) {
	  return false;
  }
}

class King extends ChessPiece {
  King (char piece, int col, int row) {
    super(piece, col, row);
  }
  
  boolean isBlocking (ChessPiece piece, int col, int row) { 
	  return false;
  }
  
  boolean isAttacking (ChessPiece c) {
    //king can attack 1 square in any direction
    if(Math.abs(row-c.row)>1||Math.abs(col-c.col)>1) //if piece and c are more than one space apart, pieces aren't attacking
      return false;
    else
      return true; //if piece and c are a space or less apart, pieces are attacking
    
  }
}

class Queen extends ChessPiece {
  Queen (char piece, int col, int row) {
    super(piece, col, row);
  }
  
  boolean isBlocking (ChessPiece piece, int col, int row) { 
	  //check up,down,left, right
	  if ((piece.row == row) && ((this.col < piece.col << col) || (this.col > piece.col >> col))) {
		  return true;
	  }
	  if ((piece.col == col) && ((this.row < piece.row << row) || (this.row > piece.row >> row))) {
		  return true;
	  }
	  //check diagonals
	  if ( ((Math.abs(row-piece.row)) <= (Math.abs(piece.row-this.row)) << (Math.abs(row-this.row))) == ((Math.abs(col-piece.col)) <= (Math.abs(piece.col-this.col)) << (Math.abs(col-this.col)))) {
		  return true;
	  }
	  if ( ((Math.abs(piece.row-this.row)) <= (Math.abs(row-piece.row)) << (Math.abs(row-this.row))) == ((Math.abs(piece.col-this.col)) <= (Math.abs(col-piece.col)) << (Math.abs(col-this.col)))) {
		  return true;
	  }
	  return false;
  }
  
  boolean isAttacking (ChessPiece c) { //from HW1 solution
    //queen can attack up,down,left,right,and diagonal
    if (row ==c.row || col == c.col) // if piece has same row or column as c, piece is attacking c
      return true;
    // if piece is on same diagonal as c, this is attack. we use absolute values to determine diagonal
    else if (Math.abs(row-c.row) == Math.abs(col - c.col)) 
      return true;
    else
      return false; // piece is not attacking c
  }
  
}

class Rook extends ChessPiece {
  Rook (char piece, int col, int row) {
    super(piece, col, row);
  }
  
  boolean isBlocking (ChessPiece piece, int col, int row) { 
	  if ((piece.row == row) && ((this.col < piece.col << col) || (this.col > piece.col >> col))) {
		  return true;
	  }
	  if ((piece.col == col) && ((this.row < piece.row << row) || (this.row > piece.row >> row))) {
		  return true;
	  }
	  return false;
  }
  
  boolean isAttacking (ChessPiece c) {
    //rook can attack up,down,left, and right
    if (row==c.row||col==c.col) //if piece has same row or column as c, piece is attacking c
      return true;
    else
      return false; 
  }
}

class Bishop extends ChessPiece {
  Bishop (char piece, int col, int row) {
    super(piece, col, row);
  }
  
  boolean isBlocking (ChessPiece piece, int col, int row) { 
	  return false;
  }
  
  boolean isAttacking (ChessPiece c) {
    //bishop can attack diagonally
    if (Math.abs(row-c.row) == Math.abs(col - c.col)) //if piece is on same diagonal as c, it attacks c
      return true;
    else
      return false;
  }
}

class Knight extends ChessPiece {
  Knight (char piece, int col, int row) {
    super(piece, col, row);
  }
  
  boolean isBlocking (ChessPiece piece, int col, int row) { 
	  return false;
  }
  
  //knight can attack in L-shape
  boolean isAttacking (ChessPiece c) { 
    if (((Math.abs(col-c.col))==2) && (Math.abs(row-c.row))==1){ //algorithm with help from Ran Tao on Piazza
      return true;
    }
    else
      return false;
  }
}

  class Pawn extends ChessPiece{
    Pawn (char piece, int col, int row){
      super(piece, col, row);
    }
    
    boolean isBlocking (ChessPiece piece, int col, int row) { 
  	  return false;
    }
    
    //black pawns move down and white pawns move up. can attack diag or up/down
    boolean isAttacking (ChessPiece c){
      if(color == true){
        if(row-1==c.row&&col+1==c.col){
          return true;
        }
        if(row-1==c.row&&col-1==c.col){
          return true;
        }
      }
      return false;
    }
  }
