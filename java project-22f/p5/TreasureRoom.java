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
import processing.core.PImage;

/**
 * This class models a treasure room in Dragon Treasure Game
 */
public class TreasureRoom extends Room {
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
      //This is warning
  private static PImage treasureBackground; //the image ALWAYS used for treasure rooms

  /**
   * Constructor for a TresureRoom object and have a description of "In the back of this room, you
   * spot a treasure chest. It is locked...". Initializes all instance data fields.
   *
   * @param ID the ID to give to this object
   */
  public TreasureRoom(int ID) {
    //Pass the ID to the super class which is Room class
    super(ID, "In the back of this room, you spot a treasure chest. It is locked...",
        treasureBackground);
  }

  /**
   * Getter for TREASURE_WARNING.
   *
   * @return the string for warning about treasure being nearby.
   */
  public static String getTreasureWarning() {
    return TREASURE_WARNING; //Return "You sense that there is treasure nearby.\n";
  }

  /**
   * Sets the background image for the TreasureRoom class.
   *
   * @param treasureBackground the image to be the background
   */
  public static void setTreasureBackground(processing.core.PImage treasureBackground) {
    TreasureRoom.treasureBackground = treasureBackground; //This is to set the treasureBackground
  }

  /**
   * Determines whether or not the player can open the treasure chest in the room.
   *
   * @param p the Player to check if they can open the chest
   * @return true if the player has the key and is in this TreasureRoom, false otherwise
   */

  public boolean playerCanGrabTreasure(Player p) {
    //To check whether the player is in the treasure room and has key
    return p.getCurrentRoom() instanceof TreasureRoom && p.hasKey();
  }

}
