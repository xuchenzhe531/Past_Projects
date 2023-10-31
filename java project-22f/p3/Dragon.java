
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
import java.util.Random;
    /**
     * This class is to be the Gragon Class for the game. The class fields contain
     * currentRoom from Room class, randGen From Random and DRAGON_WARNING. This class
     *  will be responsible for keeping track of the dragon’s current location and picking a
     *  room to move to and then moving to it.
     * 
     */
public class Dragon {
    private Room currentRoom; //current location of the dragon
    private Random randGen; //random num generator used for moving
    private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
    /**
    * This is the constructor for Dragon class to set currenRoom and randGen
    * 
    * @param cuurrenRoom is from Room class
    */
    public Dragon(Room currenRoom){
        this.currentRoom = currenRoom;
        randGen = new Random();
    }
    /**
    * This method is to change the room from the current one to the next one nearby if
    * if it not PORTAL room
    * 
    */
    public void changeRooms(){
        while(true){
            int randNum = randGen.nextInt(currentRoom.getAdjacentRooms().size());
            if(currentRoom.getAdjacentRooms().get(randNum).getType() != RoomType.PORTAL){
                //currentRoom = currentRoom.getAdjacentRooms().get(randNum);
                currentRoom = currentRoom.getAdjacentRooms().get(randNum);
                break;
            }
        }
    }
    /**
     * the getter method for the currentRoom
     * 
     * @return currentRoom from Room class
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    /**
     * the getter method for the DragonWarning
     * 
     * @return DRAGON_WARNING
     */
    public static String getDragonWarning(){
        return DRAGON_WARNING;
    }
}
