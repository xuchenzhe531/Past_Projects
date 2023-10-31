
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
     * This class is to be the Room Class for the game. The class fields contain
     * the Room type, the arraylist of the adjacent rooms, the ID, the 
     * teleporLocationID, and two warnings.
     */
public class Room {
    private RoomType type; // one of the four types a room could be
    private String roomDescription; // a brief description of the room
    private ArrayList<Room> adjRooms; // arraylist that holds the rooms adjacent
    private final int ID; // unique ID for each room to identify it
    private static int teleportLocationID; // place where all portal rooms will go to
    private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
    private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
    /**
    * This is the constructor of Room class and it sets the ID, room Description, adjRooms
    * and type.
    * 
    * @param id is an integer
    * @param roomDescription is a String
    */
    public Room(int id, String roomDescription) {
        this.ID = id;
        this.roomDescription = roomDescription;
        adjRooms = new ArrayList<Room>();
        type = RoomType.NORMAL;

    }
    /**
    * This method is to add a room to the adjacent room
    * 
    * @param toAdd is a Room object
    */
    public void addToAdjacentRooms(Room toAdd) {
        this.adjRooms.add(toAdd);
    }
    /**
     * the getter method for ID
     * 
     * @return the variable id
     */
    public int getID() {
        return ID;
    }
    /**
     * This method is to set the class field newType
     * 
     * @param newType is a RoomType object
     */
    public void setRoomType(RoomType newType) {
        this.type = newType;
    }
    /**
     * the getter method for Type
     * 
     * @return the variable Type
     */
    public RoomType getType() {
        return type;
    }
    /**
     * the getter method for PortalWarning
     * 
     * @return the variable PortalWarning
     */
    public static String getPortalWarning() {
        return PORTAL_WARNING;
    }
    /**
     * the getter method for RoomDescription
     * 
     * @return the variable RoomDescription
     */
    public String getRoomDescription() {
        return roomDescription;
    }
    /**
     * This method is to set the class field teleportLocationID
     * 
     * @param nteleportID is an integer
     */
    public static void assignTeleportLocation(int teleportID) {
        teleportLocationID = teleportID;
    }
    /**
     * the getter method for TeleportationID
     * 
     * @return the variable TeleportationID
     */
    public static int getTeleportationRoom() {
        return teleportLocationID;
    }
    /**
     * the getter method for TreasureWarning
     * 
     * @return the variable TreasureWarning
     */
    public static String getTreasureWarning() {
        return TREASURE_WARNING;
    }
    /**
     * the getter method for AdjacentRooms
     * 
     * @return the variable adjRooms
     */
    public ArrayList<Room> getAdjacentRooms() {
        return adjRooms;
    }
    /**
     * This method is to check whether the given room is adjacent
     * 
     * @param r is a Room object
     */
    public boolean isAdjacent(Room r) {
        for (int i = 0; i < adjRooms.size(); i++) {
            if (adjRooms.get(i) == r) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given object is equal to this room.
     * They are equal if other is a Room and their IDs are the same.
     * 
     * @param other, another object to check if it is equal to this
     * @return true if the two rooms are equal, false otherwise
     * @author Michelle
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Room) {
            Room otherRoom = (Room) other;
            return this.ID == otherRoom.ID;
        }
        return false;
    }

    /**
     * Returns a String representation of this room.
     * 
     * @return the string representation of this room and
     *         it’s object data field values
     * @author Michelle
     */
    @Override
    public String toString() {
        String s = this.ID + ": " + this.roomDescription + " (" + type + ")\n Adjacent Rooms: ";
        for (int i = 0; i < adjRooms.size(); i++) {
            s += adjRooms.get(i).ID + " ";
        }
        return s;
    }
}
