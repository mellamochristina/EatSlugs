package chessAttacks;

import java.util.*;
import java.io.*;

class ChessPiece {
	// need the type of piece, its column, and row
	char piece;
	int col;
	int row;
	boolean color;

	// constructor for the chess piece
	ChessPiece(char piece, int col, int row) { 
		this.piece = piece;
		this.col = col;
		this.row = row;
		if (piece == Character.toUpperCase(piece)) {
			this.color = true;
		} else {
			this.color = false;
		}
	}

	boolean isBlocking(ChessPiece c, int col, int row) {
		return false;
	}

	boolean canMove(int x, int y) {
		return false;
	}

	boolean isAttacking(ChessPiece c) {
		return false;
	}
}

class King extends ChessPiece {
	King(char piece, int col, int row) {
		super(piece, col, row);
	}

	boolean isBlocking(ChessPiece piece, int col, int row) {
		return false;
	}

	boolean canMove(int x, int y) {
		if (Math.abs(row - y) > 1 || Math.abs(col - x) > 1) 
			return false;
		else
			return true;
	}

	// king can attack 1 square in any direction 
	boolean isAttacking(ChessPiece c) {
		// if piece and c are more than one space apart, pieces aren't attacking
		if (Math.abs(row - c.row) > 1 || Math.abs(col - c.col) > 1) 
			return false;
		else
			 // if piece and c are a space or less apart, pieces are attacking
			return true;

	}
}

class Queen extends ChessPiece {
	Queen(char piece, int col, int row) {
		super(piece, col, row);
	}
	
	boolean isBlocking(ChessPiece p, int col, int row) {		
		// check up,down,left, right
		if ((p.row == row)
				&& (((this.col < p.col) && (p.col < col)) || ((this.col > p.col) && (p.col > col)))) {
			return true;
		}
		if ((p.col == col)
				&& (((this.row < p.row) && (p.row < row)) || ((this.row > p.row) && (p.row > row)))) {
			return true;
		}
		// Check diagonal
		float moveX = this.col - col;
		float moveY = this.row - row;
		float moveRatio = moveX/moveY;
		
		float pX = this.col - p.col;
		float pY = this.row - p.row;
		float pRatio = pX/pY;

		if (moveRatio == pRatio)
		{
			// Piece is in the same diagonal direction
			// Check if piece is between
			return Math.abs(pX) < Math.abs(moveX) && Math.abs(pY) < Math.abs(moveY);
		}
		else return false;
	}

	boolean canMove(int x, int y) {
		// queen can attack up,down,left,right,and diagonal
		if (row == y || col == x) 
			return true;
		else if (Math.abs(row - y) == Math.abs(col - x))
			return true;
		else
			return false; 
	}

	// queen can attack up,down,left,right,and diagonal
	boolean isAttacking(ChessPiece c) { // from HW1 solution
		// if piece has same row or column as c, piece is attacking c
		if (row == c.row || col == c.col) 
			return true;
		// if piece is on same diagonal as c, this is attack. we use absolute values to determine diagonal
		else if (Math.abs(row - c.row) == Math.abs(col - c.col))
			return true;
		else
			 // piece is not attacking c
			return false;
	}

}

class Rook extends ChessPiece {
	Rook(char piece, int col, int row) {
		super(piece, col, row);
	}
	
	boolean isBlocking(ChessPiece piece, int col, int row) {
		if ((piece.row == row) && (((this.col < piece.col)&&(piece.col < col)) || ((this.col > piece.col) && (piece.col > col)))) {
			return true;
		}
		if ((piece.col == col) && (((this.row < piece.row) && ( piece.row < row)) || ((this.row > piece.row) && (piece.row > row)))) {
			return true;
		}
		return false;
	}

	boolean canMove(int x, int y) {
		// rook can attack up,down,left, and right
		if (row == y || col == x) 
			return true;
		else
			return false;
	}

	// rook can attack up,down,left, and right
	boolean isAttacking(ChessPiece c) {
		// if piece has same row or column as c, piece is attacking c
		if (row == c.row || col == c.col) 
			return true;
		else
			return false;
	}
}

class Bishop extends ChessPiece {
	Bishop(char piece, int col, int row) {
		super(piece, col, row);
	}

	boolean isBlocking(ChessPiece p, int col, int row) {
		// Check diagonal
				float moveX = this.col - col;
				float moveY = this.row - row;
				float moveRatio = moveX/moveY;
				
				float pX = this.col - p.col;
				float pY = this.row - p.row;
				float pRatio = pX/pY;

				if (moveRatio == pRatio)
				{
					// Piece is in the same diagonal direction
					// Check if piece is between
					return Math.abs(pX) < Math.abs(moveX) && Math.abs(pY) < Math.abs(moveY);
				}
				else return false;
	}

	boolean canMove(int x, int y) {
		// bishop can attack diagonally
		if (Math.abs(row - y) == Math.abs(col - x)) 
			return true;
		else
			return false;
	}

	// bishop can attack diagonally
	boolean isAttacking(ChessPiece c) {
		// if piece is on same diagonal as c, it attacks c
		if (Math.abs(row - c.row) == Math.abs(col - c.col)) 
			return true;
		else
			return false;
	}
}

class Knight extends ChessPiece {
	Knight(char piece, int col, int row) {
		super(piece, col, row);
	}

	boolean isBlocking(ChessPiece piece, int col, int row) {
		return false;
	}

	boolean canMove(int x, int y) {
		if (((Math.abs(col - x)) == 2) && (Math.abs(row - y)) == 1) { 
			return true;
		} 
		if (((Math.abs(col - x)) == 1) && (Math.abs(row - y)) == 2) { 
			return true;
		}
		else
			return false;
	}

	// knight can attack in L-shape
	boolean isAttacking(ChessPiece c) {
		// algorithm with help from Ran Tao on Piazza
		if (((Math.abs(col - c.col)) == 2) && (Math.abs(row - c.row)) == 1) { 
			return true;
		} 
		if (((Math.abs(col - c.col)) == 1) && (Math.abs(row - c.row)) == 2) { 
			return true;
		}
		else
			return false;
		}
	}

class Pawn extends ChessPiece {
	Pawn(char piece, int col, int row) {
		super(piece, col, row);
	}

	boolean isBlocking(ChessPiece piece, int col, int row) {
		return false;
	}

	boolean canMove(int x, int y) {
		if (color == true) {
			if (row - 1 == y && col + 1 == x) {
				return true;
			}
			if (row - 1 == y && col - 1 == x) {
				return true;
			}
		}
		return false;
	}

	// black pawns move down and white pawns move up. can attack diag or up/down
	boolean isAttacking(ChessPiece c) {
		if (color == true) {
			if (row - 1 == c.row && col + 1 == c.col) {
				return true;
			}
			if (row - 1 == c.row && col - 1 == c.col) {
				return true;
			}
		}
		return false;
	}
}
