//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Dragon Treasure Adventure 2.0
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
import java.util.ArrayList;

/**
 * This class models a character in Dragon Treasure Game
 */
public class Character {
  private Room currentRoom; //current room the character is in
  private String label; //a label giving a basic description of the character

  /**
   * Constructor for a Character object. Initializes all instance fields.
   *
   * @param currentRoom the room that the Character is located in
   * @param label       the room that the Character is located in
   * @throws IllegalArgumentException with a descriptive message if currentRoom is null.
   */
  public Character(Room currentRoom, String label) throws IllegalArgumentException {
    if (currentRoom == null) { //To check the method input
      throw new IllegalArgumentException("CurrentRoom cannot be null!");
    }
    this.currentRoom = currentRoom; //Assign the value to the field
    this.label = label; //Assign the value to the field
  }

  /**
   * Getter for the current room of this Character.
   *
   * @return the room where the character is
   */
  public Room getCurrentRoom() {
    return currentRoom; //return currentRoom in Room class
  }

  /**
   * Sets the current room to the one given.
   *
   * @param newRoom the room that should become the current room
   */
  public void setCurrentRoom(Room newRoom) {
    //if the newRoom is null, then throw the exception
    if (newRoom == null) {
      throw new IllegalArgumentException("newRoom cannot be null!");
      // Throw exception
    }
    currentRoom = newRoom; //If the newroom is not null, assign it to the field
  }

  /**
   * Getter for the label of this Character.
   *
   * @return this Character's descriptive label
   */
  public String getLabel() {
    return label; //return the string
  }

  /**
   * Gets the list of rooms adjacent to this Character.
   *
   * @return an ArrayList of rooms adjacent to this character
   */
  public ArrayList<Room> getAdjacentRooms() {
    return currentRoom.getAdjacentRooms(); //return current room's adjacent room arraylist
  }
}
