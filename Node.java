package chessAttacks;
import java.util.*;
import java.io.*;

//code obtained with help from https://github.com/vigilkj/LinkedList
class Node {
  ChessPiece value;
  Node next;
  
  public Node(ChessPiece val){
    value = val;
    next = null;
  }
  //gets piece
  public ChessPiece getVal() {
    return value;
  }
  //sets piece
  public void setVal(ChessPiece val) {
    value = val;
  }
  //gets next piece
  public Node getNext() {
    return next;
  }
  //sets next piece
  public void setNext(Node next) {
    this.next = next;
  }
}
