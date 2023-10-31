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
import java.util.Random;

/**
 * This class models a dragon in Dragon Treasure Game
 */
public class Dragon extends Character implements Moveable {
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
  private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the firebreathing dragon!\n";
  private Random randGen; //random num generator used for moving

  /**
   * Constructor for a Dragon object. Initializes all instance fields. The label should be "DRAGON"
   * by default.
   *
   * @param currentRoom the room that the Dragon starts in
   * @throws IllegalArgumentException with a descriptive message if currentRoom is not a
   *                                  TreasureRoom
   */
  public Dragon(Room currentRoom) throws IllegalArgumentException {
    super(currentRoom, "DRAGON"); //call the super class which is Character to pass value
    //Check whether the currenRoom is Treasure room
    if (!(currentRoom instanceof TreasureRoom)) {
      throw new IllegalArgumentException("currentRoom is not a TreasureRoom!");
    }
    randGen = new Random(); //Initialize random
  }

  /**
   * Getter for DRAGON_WARNING.
   *
   * @return the string for warning about a dragon being nearby.
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING; //Return dragon warning
  }

  /**
   * Getter for DRAGON_ENCOUNTER.
   *
   * @return the string for letting the player know they ran into the dragon.
   */
  public static String getDragonEncounter() {
    return DRAGON_ENCOUNTER; //return dragon encounter
  }

  /**
   * Moves the Dragon to the destination room.
   *
   * @param destination the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  @Override
  public boolean changeRoom(Room destination) {
    //To check whether the destination is null, if it is throw exception
    if (destination == null) {
      throw new IllegalArgumentException("destination cannot be null!");
    }
    //Check whether the dragon can move to the destination
    if (canMoveTo(destination)) {
      //if the destination is nearby
      if (getCurrentRoom().getAdjacentRooms().contains(destination)) {
        //Set the destination to the current room because dragon moves
        setCurrentRoom(destination);
        //return true
        return true;
      }
    }
    return false; //If the destination is not nearby, return false
  }

  /**
   * Checks if the dragon can move to the given destination. A valid move is the destination not a
   * PortalRoom.
   *
   * @param destination the room to check if the dragon can move towards
   * @return true if they can, false otherwise
   */
  @Override
  public boolean canMoveTo(Room destination) {
    //Check whether the destination is null, if it is throw exception
    if (destination == null) {
      throw new IllegalArgumentException("destination cannot be null!");
    }
    //Check whether the destination is PortalRoom class
    return !(destination instanceof PortalRoom);
  }

  /**
   * Picks randomly ONCE an adjacent room to move into.
   *
   * @return the room that this Dragon should try to move into
   */
  public Room pickRoom() {
    //To randomly pick a room to move nearby
    return getAdjacentRooms().get(randGen.nextInt(getAdjacentRooms().size()));
  }
}
