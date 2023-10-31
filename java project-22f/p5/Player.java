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


/**
 * This class models a player in Dragon Treasure Game
 */
public class Player extends Character implements Moveable {
  private boolean hasKey; //Used to indicate whether the player has the key

  /**
   * Constructor for player object. The label should be "PLAYER" and not have a key by default.
   *
   * @param currentRoom the room that the player should start in
   * @throws IllegalArgumentException if the currentRoom is not a StartRoom
   */
  public Player(Room currentRoom) throws IllegalArgumentException {
    super(currentRoom, "PLAYER"); //Call the super class to pass the values
    if (!(currentRoom instanceof StartRoom)) { //To make sure that current room is a start room
      // Throw exception if player's current room is not a start room
      throw new IllegalArgumentException("currentRoom is not a StartRoom!");
    }
    hasKey = false; //To set player does not have key at first
  }

  /**
   * Determines if the player has the key.
   *
   * @return true if the player has the key, false otherwise
   */
  public boolean hasKey() {
    return hasKey; //return whether the player has the key
  }

  /**
   * Gives player the key. Change hasKey to true.
   */
  public void obtainKey() {
    hasKey = true; //Set the key to true which means the player has key
    System.out.println("KEY OBTAINED"); //Print the status
  }

  /**
   * Moves the Player to the destination room.
   *
   * @param destination the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  @Override
  public boolean changeRoom(Room destination) {
    //To check whether the destination is a null
    if (destination == null) {
      throw new IllegalArgumentException("destination cannot be null!");
    }
    //Check whether the player can move to the room
    if (canMoveTo(destination)) {
      //Move the player
      setCurrentRoom(destination);
      return true; //Return true if change successfully
    }
    return false; //Return false if change unsuccessfully
  }

  /**
   * Checks if the player can move to the given destination. A valid move is the destination is a
   * room adjacent to the player.
   *
   * @param destination the room to check if the player can move towards
   * @return true if they can, false otherwise
   */
  @Override
  public boolean canMoveTo(Room destination) {
    //To check whether the destination is a null
    if (destination == null) {
      throw new IllegalArgumentException("destination cannot be null!");
    }
    // Check destination is an adjacent room to player
    return getAdjacentRooms().contains(destination);
  }

  /**
   * Checks if the player needs to teleport and move them if needed.
   *
   * @return true if a teleport occurred, false otherwise
   */
  public boolean teleport() {
    //Check whether the current room is in Portal room
    if (getCurrentRoom() instanceof PortalRoom) {
      //if yes, to set the current to and return true
      setCurrentRoom(((PortalRoom) getCurrentRoom()).getTeleportLocation());
      return true;
    }
    return false; //If not in the portal room, return false
  }

  /**
   * Determines whether or not the given dragon is nearby. A dragon is considered nearby if it is in
   * one of the adjacent rooms.
   *
   * @param d the dragon to check if nearby
   * @return true if the dragon is nearby, false otherwise
   */

  public boolean isDragonNearby(Dragon d) {
    // Check if dragon's currentRoom is in player's adjrooms list
    for (int i = 0; i < getAdjacentRooms().size(); i++) {
      //If dragon is nearby return true
      if (getAdjacentRooms().get(i).equals(d.getCurrentRoom())) {
        return true;
      }
    }
    return false;//if not, return false
  }

  /**
   * Determines whether or not a portal room is nearby. A portal room is considered nearby if it is
   * one of the adjacent rooms.
   *
   * @return true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby() {
    //Check if portal room is in adjrooms list
    for (int i = 0; i < getAdjacentRooms().size(); i++) {
      //if Portal is nearby, return true
      if (getAdjacentRooms().get(i) instanceof PortalRoom) {
        return true;
      }
    }
    return false; //if not return false
  }

  /**
   * Determines whether or not the treasure room is nearby. The treasure room is considered nearby
   * if it is one of the adjacent rooms.
   *
   * @return true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby() {
    //Check if treasure room is in adjrooms list
    for (int i = 0; i < getAdjacentRooms().size(); i++) {
      //Check whether the treasure is near the player
      if (getAdjacentRooms().get(i) instanceof TreasureRoom) {
        return true;//if yes, return true
      }
    }
    return false; //if not nearby, return false
  }
}

