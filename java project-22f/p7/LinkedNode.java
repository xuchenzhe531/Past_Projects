//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Quizzer
// Course:   CS 300 Fall 2022
//
// Author:   Jim Yi
// Email:    yyi37@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Chenzhe Xu
// Partner Email:   cxu296@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////


/**
 * An instance of this class represents a single node within a singly linked list.
 */
public class LinkedNode<T> {
  private T data; // data carried by this linked node
  private LinkedNode<T> next; // reference to the next linked node

  /**
   * Constructs a new node with the provided information.
   *
   * @param data to be stored within this node
   * @throws NullPointerException with a descriptive error message if data is null
   */
  public LinkedNode(T data) throws NullPointerException {
    if (data == null) {
      //If the data is null, throw exception
      throw new NullPointerException("Data is null!");
    }
    this.data = data; //Assign the data to field
  }

  /**
   * Constructs a new node with the provided information.
   *
   * @param data to be stored within this node
   * @param next node, which comes after this node in a singly linked list
   * @throws NullPointerException with a descriptive error message if data is null
   */
  public LinkedNode(T data, LinkedNode<T> next) throws NullPointerException {
    if (data == null) {
      //If data is null, throw exception
      throw new NullPointerException("Data is null!");
    }
    this.data = data; //Assign data to the field
    this.next = next; //Assign next to the field
  }

  /**
   * Returns the data carried by this node
   *
   * @return the data to be assigned to this node
   */
  public T getData() {
    return data; //Return data
  }

  /**
   * Returns the reference to the next node
   *
   * @return the next the reference to the next node
   */
  public LinkedNode<T> getNext() {
    return next; //Return next node
  }

  /**
   * Sets the reference to the next node
   *
   * @param next the next to set
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next; //Assign the value to next node
  }

  /**
   * Returns a string representation of this linked node formatted as follows: data.toString() if
   * this node does NOT have a next node in the list data.toString() + "->" if this node has a next
   * node in the list
   *
   * @return a String representation of this node in the list
   */
  @Override
  public String toString() {
    if (next == null) {
      return data.toString(); //Print node if it is the last one
    } else {
      return data.toString() + "->"; //print the node with an arraw if it is not the last one
    }
  }
}
