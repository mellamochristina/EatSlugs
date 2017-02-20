#------------------------------------------------------------------------------
# Another Makefile with macros
#------------------------------------------------------------------------------

JAVASRC    = ChessBoardAnalyzer.java ChessBoard.java ChessPiece.java Node.java LinkedList.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = ChessBoardAnalyzer
CLASSES    = ChessBoardAnalyzer.class ChessBoard.class ChessPiece.class Node.class LinkedList.class Bishop.class King.class Knight.class Queen.class Rook.class Pawn.class
JARFILE    = Chessmoves.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
