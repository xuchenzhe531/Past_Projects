
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Dragon Treasure
// Course:   CS 300 Fall 2022
//
// Author:   Chenzhe Xu
// Email:    cxu296@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
    /**
     * This class is to be the Player Class for the game. The class 
     * fields contain currentRoom from Room class. This class will be 
     * responsible for keeping track of the current player location, moving 
     * the player between rooms, and determining if the player is near a room
     * that has a special property (e.g. contains a dragon).
     */
public class Player {
    private Room currentRoom; // current location of the player
    /**
    * This is the constructor for Player class
    * 
    * @param cuurrenRoom is from Room class
    */
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    /**
    * This method is to change the currentRoom to the next available room
    * 
    * @param newRoom is a Room object
    */
    public void changeRoom(Room newRoom) {
        currentRoom = newRoom;
    }
    /**
     * the getter method for the adjacent arraylist
     * 
     * @return the varraylist
     */
    public ArrayList<Room> getAdjacentRoomsToPlayer() {
        return currentRoom.getAdjacentRooms();
    }
    /**
    * This method is to check whether the current room can move to the
    * given destination
    * 
    * @param destination is a Room object
    * @return true or false
    */
    public boolean canMoveTo(Room destination) {
        return currentRoom.isAdjacent(destination);
    }
    /**
     * the getter method for CurrentRoom
     * 
     * @return the Room object currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    /**
    * This method is to acheck whether Portal Room is nearby
    * 
    * @return true if the Portal nearby, otherwise return false
    */
    public boolean isPortalNearby() {
        ArrayList<Room> check = currentRoom.getAdjacentRooms();
        for (int i = 0; i < check.size(); i++) {
            if (check.get(i).getType() == RoomType.PORTAL) {
                return true;
            }
        }
        return false;
    }
    /**
    * This method is to acheck whether Treasure Room is nearby
    * 
    * @return true if the Treasure nearby, otherwise return false
    */
    public boolean isTreasureNearby() {
        ArrayList<Room> check = currentRoom.getAdjacentRooms();
        for (int i = 0; i < check.size(); i++) {
            if (check.get(i).getType() == RoomType.TREASURE) {
                return true;
            }
        }
        return false;
    }
    /**
    * This method is to acheck whether should teleport
    * 
    * @return true if it does equal to PORTAL, otherwise return false
    */
    public boolean shouldTeleport() {
        if (currentRoom.getType() != RoomType.PORTAL) {
            return false;
        }
        return true;
    }
    /**
    * This method is to acheck the given dragon class object is nearby
    * 
    * @return true if the object nearby, otherwise return false
    */
    public boolean isDragonNearby(Dragon d) {
        if (currentRoom.isAdjacent(d.getCurrentRoom())) {
            return true;
        }
        return false;
    }
}
