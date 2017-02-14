package chessAttacks;
import java.util.*;
import java.io.*;

//coded with help from https://github.com/vigilkj/LinkedList
class LinkedList{
  Node head;
  int size; //used for the obtaining size of the linked list
  
  public LinkedList(){
    //empty constructor prevents java error
  }
  
  //method for retrieving a piece at an instance
  public ChessPiece get(int i){
    Node current = head; //set the head of the linked list to current
    int n = 0;
    while(current.next !=null && n<i){ //makes sure it doesn't return a NullPointerException
      current = current.getNext(); //sets the next value in linked list to the current
      n++; 
    }
    return current.getVal(); //returns the piece at that instance
  }
  //method for adding a piece into linked list
  public void add(ChessPiece value){
    if (head==null){ //if list is empty, the piece becomes the head of the list
      head = new Node(value);
    }
    else {
      Node temp = new Node(value); //stores the piece into a temporary variable
      Node current = head; //the current piece becomes the head of list
      if(current != null){
        while (current.getNext()!=null){ //makes sure it's not grabbing past the linked list
          current = current.getNext(); //cuurent becomes the next instance
        }
        current.setNext(temp); //next current value is now empty, so temp can now be next current
      }
    }
    size++; //size increments with each addition of a piece
  }
  //method to obtain size of list
  public int size(){
    return size;
  }
  
}
