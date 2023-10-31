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
import processing.core.PImage; //Import for image loading

/**
 * This class models a start room in Dragon Treasure Game
 */
public class StartRoom extends Room {
  /**
   * Constructor for a StartRoom object and have a description of "You find yourself in the entrance
   * to a cave holding treasure." Initializes all instance data fields.
   *
   * @param ID    the ID to give to this object
   * @param image the image that should be used as a background when drawing this Room
   */
  public StartRoom(int ID, PImage image) {
    //Pass the input to the super class which is Room class
    super(ID, "You find yourself in the entrance to a cave holding treasure.", image);
  }
}
