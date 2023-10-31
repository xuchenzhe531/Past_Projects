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
     * This class is to test Player, Room, and Gragon class in order to 
     * successfully run them together with DragonTresureGame.java
     * 
     */
public class DragonTreasureTester {
    private static Room r; //Room object to check methods
    private static Room check;//Room object to check methods
    private static Room roomCheckForNearby;//Room object to check methods
    private static Player p; //Playerobject to check methods
    private static Player check2; //Playerobject to check methods
    private static Player checkForisNearby; //Playerobject to check methods
  /** 
   * This is the main method
   * @param String[] args
   */
    public static void main(String[] args) {
        System.out.println("Room Instance Methods Tester: " + testRoomInstanceMethods());
        System.out.println("Room static Methods Tester: " + testRoomStaticMethods());
        System.out.println("Player CanMoveTo Tester: " + testPlayerCanMoveTo());
        System.out.println("Player ShouldTeleport Tester: " + testPlayerShouldTeleport());
        System.out.println("Player isTreasureNearby and isPortalNearby Tester: " + testPlayerDetectNearbyRooms());
        System.out.println("Dragon changeRoom Tester: " + testDragonChangeRooms());
    }
    /**
     * test method for Instance Methods in Room
     * 
     * @return true if all tests are passed, otherwide return false
     */
    public static boolean testRoomInstanceMethods() {
        int idToCheck = -1;
        String roomDescriptionToCheck = "";
        RoomType typeToCheck = null;
        try {
            r = new Room(3, "This is Room3");
            p = new Player(r);
            idToCheck = r.getID();
            roomDescriptionToCheck = r.getRoomDescription();
            r.setRoomType(RoomType.PORTAL);
            typeToCheck = r.getType();
            if (idToCheck != 3) {
                return false;   // return false if a test is failed
            }
            if (roomDescriptionToCheck.equals("This is Room3") != true) {
                return false;   // return false if a test is failed
            }
            if (typeToCheck != RoomType.PORTAL) {
                return false;   // return false if a test is failed
            }
            check = new Room(4, "This is Room4");
            r.addToAdjacentRooms(check);
            if (r.isAdjacent(check) != true) {
                return false;   // return false if a test is failed
            }
            ArrayList<Room> list = r.getAdjacentRooms();
            if (list.contains(check) != true) {
                return false;   // return false if a test is failed
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal Argument Exception");
            return false;   // return false if a test is failed
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        } catch (Exception e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        }
        return true;   // return true if all tests are passed
    }
    /**
     * test method for Static Methods in Room
     * 
     * @return true if all tests are passed, otherwide return false
     */
    public static boolean testRoomStaticMethods() {
        try {
            Room.assignTeleportLocation(2);
            if (Room.getTeleportationRoom() != 2) {
                return false;   // return false if a test is failed
            }
            if (Room.getPortalWarning() != "You feel a distortion in space nearby.\n") {
                return false;   // return false if a test is failed
            }
            if (Room.getPortalWarning().equals("You feel a distortion in space nearby.\n") != true) {
                return false;   // return false if a test is failed
            }
            if (Room.getTreasureWarning().equals("You sense that there is treasure nearby.\n") != true) {
                return false;   // return false if a test is failed
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        } catch (Exception e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        }
        return true;   // return true if all tests are passed
    }
    /**
     * test method for CanMoveTo() in Player
     * 
     * @return true if all tests are passed, otherwide return false
     */
    public static boolean testPlayerCanMoveTo() {
        try {
            p = new Player(r);
            Room temp = new Room(2, "This is Room2");
            if (p.canMoveTo(temp) != false) {
                return false;   // return false if a test is failed
            }
            p.getAdjacentRoomsToPlayer().add(temp);
            if (p.canMoveTo(temp) != true) {
                return false;   // return false if a test is failed
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        } catch (Exception e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        }
        return true;   // return true if all tests are passed
    }
    /**
     * test method for ShouldTeleport() in Player
     * 
     * @return true if all tests are passed, otherwide return false
     */
    public static boolean testPlayerShouldTeleport() {
        try {
            p = new Player(r);
            if (p.shouldTeleport() == false) {
                return false;   // return false if a test is failed
            }
            r.setRoomType(RoomType.NORMAL);
            check2 = new Player(r);
            if (p.shouldTeleport() == true) {
                return false;   // return false if a test is failed
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        } catch (Exception e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        }
        return true;   // return true if all tests are passed
    }
    /**
     * test method for NearbyRooms() in Player
     * 
     * @return true if all tests are passed, otherwide return false
     */
    public static boolean testPlayerDetectNearbyRooms() {
        try {
            roomCheckForNearby = new Room(4, "This is Room4");
            check.setRoomType(RoomType.TREASURE);
            checkForisNearby = new Player(roomCheckForNearby);
            if (checkForisNearby.isTreasureNearby() != false) {
                return false;   // return false if a test is failed
            }
            checkForisNearby.getCurrentRoom().addToAdjacentRooms(check);
            if (checkForisNearby.isTreasureNearby() != true) {
                return false;   // return false if a test is failed
            }
            if (checkForisNearby.isPortalNearby() != false) {
                return false;   // return false if a test is failed
            }
            check.setRoomType(RoomType.PORTAL);
            if (checkForisNearby.isPortalNearby() != true) {
                return false;   // return false if a test is failed
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        } catch (Exception e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        }
        return true;   // return true if all tests are passed
    }
    /**
     * test method for ChangeRooms() in dragon class
     * 
     * @return true if all tests are passed, otherwide return false
     */
    public static boolean testDragonChangeRooms() {
        try {
            Room room0 = new Room(1, "This is Room0");
            room0.setRoomType(RoomType.START);
            Dragon dragon = new Dragon(room0);
            Room room1 = new Room(8, "This is Room8");
            room1.setRoomType(RoomType.NORMAL);
            Room room2 = new Room(9, "This is Room9");
            room2.setRoomType(RoomType.PORTAL);
            Room room3 = new Room(10, "This is Room10");
            room3.setRoomType(RoomType.NORMAL);
            Room room4 = new Room(11, "This is Room11");
            room4.setRoomType(RoomType.TREASURE);
            dragon.getCurrentRoom().addToAdjacentRooms(room1);
            dragon.getCurrentRoom().addToAdjacentRooms(room2);
            room1.addToAdjacentRooms(room3);
            room1.addToAdjacentRooms(room4);
            dragon.changeRooms();
            if (dragon.getCurrentRoom().getType() != RoomType.NORMAL) {
                return false;   // return false if a test is failed
            }
            dragon.changeRooms();
            if (dragon.getCurrentRoom().getType() != RoomType.NORMAL) {

            } else if (dragon.getCurrentRoom().getType() != RoomType.TREASURE) {

            } else {
                return false;   // return false if a test is failed
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        } catch (Exception e) {
            e.printStackTrace();
            return false;   // return false if a test is failed
        }
        return true;   // return true if all tests are passed
    }

}