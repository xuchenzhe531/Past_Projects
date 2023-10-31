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
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * This class models a room in Dragon Treasure Game
 */
public class Room {
  protected static PApplet processing; //PApplet object which the rooms will use to
  private final int ID; // a "unique" identifier for each room
  private String description; //verbal description of the room
  private ArrayList<Room> adjRooms; //list of all rooms directly connect
  //draw stuff to the GUI
  private PImage image; //stores the image that corresponds to the background of a room

  /**
   * Constructor for a Room object. Initializes all instance data fields.
   *
   * @param ID          the ID that this Room should have
   * @param description the verbal description this Room should have
   * @param image       the image that should be used as a background when drawing this Room
   */
  public Room(int ID, String description, processing.core.PImage image) {
    adjRooms = new ArrayList<>(); //Initialize the arraylist
    this.ID = ID; //the ID that this Room should have to private field
    this.description = description; //the verbal description this Room should have to private field
    this.image =
        image; //assign the image that should be used as a background when drawing this Room to private field
  }

  /**
   * Sets the processing for the class.
   *
   * @param processing the PApplet that this room will use to draw to the window
   */
  public static void setProcessing(processing.core.PApplet processing) {
    //set the PApplet that this room will use to draw to the window
    Room.processing = processing;
  }

  /**
   * Getter for ID
   *
   * @return the ID of this Room
   */
  public int getID() {
    //Getter for ID
    return ID; //return ID
  }

  /**
   * Getter for description
   *
   * @return the verbal description of this Room
   */
  public String getDescription() {
    //Getter for description
    return description; //return Description
  }

  /**
   * Getter for the list of adjacentRooms.
   *
   * @return the list of adjacent rooms
   */
  public ArrayList<Room> getAdjacentRooms() {
    //Getter for adjacent room
    return adjRooms; //return ArrayList
  }

  /**
   * Adds the given room to the list of rooms adjacent to this room.
   *
   * @param toAdd the room to be added
   */
  public void addToAdjacentRooms(Room toAdd) {
    //add the room to adjacent rooms
    adjRooms.add(toAdd);
  }

  /**
   * Checks whether or not the given room is adjacent to this room.
   *
   * @param r the room to check for adjacency
   * @return true if it is adjacent, false otherwise
   */
  public boolean isAdjacent(Room r) {
    //Check whether not the given room is adjacent to this room.
    return adjRooms.contains(r); // return true if it contains the room
    //Otherwide, return false
  }

  /**
   * Overrides Object.equals(). Determines if two objects are equal.
   *
   * @param other the object to check against this Room
   * @return true if other is of type Room and has the same ID, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    //Check whether the object is Room
    if (other instanceof Room) {
      Room otherRoom = (Room) other;
      return this.ID == otherRoom.ID;
    } //If it is, return true
    return false; //Otherwide, it returns false
  }

  /**
   * Overrides Object.toString(). Returns a string representation of a Room object.
   *
   * @return Returns a string in the form of "<ID>: <description>\n Adjacent Rooms: <r1's ID> <r2's
   * ID>" list of adjacent room IDs continues for all rooms adjacent to this Room.
   */
  @Override
  public String toString() {
    String s = this.ID + ": " + this.description + "\n Adjacent Rooms:";
    for (int i = 0; i < adjRooms.size(); i++) {
      s += " " + adjRooms.get(i).getID();//Add ID of Rooms in the adjRooms list to the String
    }
    return s; //Return the string
  }

  /**
   * Draws this Room to the window by drawing the background image, a rectangle, and some text.
   */
  public void draw() {
    //Use the PApplet’s image() instance method to draw the image at (0, 0)
    processing.image(image, 0, 0);
    //Use the PApplet’s fill() instance method to change
    // the draw color to giving it a value of −7028.
    processing.fill(-7028);
    //Use the PApplet’s rect() instance method to draw a rectangle.
    processing.rect(0, 500, 800, 600);
    //Use the PApplet’s fill() instance method again to
    // change the draw color to giving it a value of 0.
    processing.fill(0);
    //Use the PApplet’s text() instance method to draw the Room’s toString() at (300,525).
    processing.text(this.toString(), 300, 525);
  }
}
