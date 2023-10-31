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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to control the whole game and set up for the Dragon Treasure Game
 */
public class DragonTreasureGame extends PApplet {
  private ArrayList<Room> roomList; //The arraylist for the 
  private ArrayList<Character> characters; //The Character Arraylist
  private File roomInfo; //to read roomInfo.txt
  private File mapInfo; //To read mapInfo.txt
  private boolean isDragonTurn; //this should be false so the player gets to move first.
  private int gameState; //It can be 0, 1, or 2

  /**
   * The main method is to run the game
   */
  public static void main(String[] args) {
    PApplet.main("DragonTreasureGame"); //Run the game
  }

  /**
   * This is an Override method to set the window to the width and height
   */
  @Override
  public void settings() {
    size(800, 600); //set the window to have a width of 800 and height of 600.
  }

  /**
   * This is an override method to set up the whole game
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
    //as the top-left corner
    this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
    //as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); //sets the font size for the text
    roomList = new ArrayList<Room>(); //Initialize the roomList
    Room.setProcessing(this); //To setprocessing
    //Set Treasure Background
    TreasureRoom.setTreasureBackground(this.loadImage("images" + File.separator + "treasure.jpg"));
    //Set Portal Background
    PortalRoom.setPortalImage(this.loadImage("images" + File.separator + "portal.png"));
    mapInfo = new File("map.txt"); //read mapinfo
    roomInfo = new File("roominfo.txt"); //read roominfo
    loadRoomInfo(); //Load roominfo
    loadMap(); //Load mapinfo
    characters = new ArrayList<>(); //Create character lists
    loadCharacters(); //Load Character arraylist
    isDragonTurn = false; //Set the initial Dragon turn to false
    gameState = 0; //Game state is set to 0 as the initial of the game

  }

  /**
   * This method is to load the Character arraylist, add player, dragon and the key
   */
  private void loadCharacters() {
    System.out.println("Adding characters...");
    try {
      //Add the keyholder
      characters.add(new Character(getRoomByID(5), "KEYHOLDER"));
      //Add the player
      characters.add(new Player(getRoomByID(1)));
      //Add the dragon
      characters.add(new Dragon(getRoomByID(9)));
    } catch (Exception e) { //handle exception
      e.printStackTrace();
    }
  }

  /**
   * This method is mainly drawing the images of the corresponding room
   */
  public void draw() {
    //Player player = null;// Create a local player
    //To find player in characters list
    Player player = null;
    for (int i = 0; i < characters.size(); i++) {
      if (characters.get(i).getLabel() == "PLAYER") {
        //Downcast the character to player class in order to use the player methods
        player = (Player) characters.get(i);
        //Draw the currentRoom of the player
        player.getCurrentRoom().draw();
      }
    }

    Dragon dragon = null;// Create a local dragon
    //To find dragon in characters list
    for (int i = 0; i < characters.size(); i++) {
      if (characters.get(i).getLabel() == "DRAGON") {
        //Downcast the character to dragon in order to use dragon methods
        dragon = (Dragon) characters.get(i);
      }
    }

    Character keyHolder = null; //Create a local character
    //To find keyholder in characters list
    for (int i = 0; i < characters.size(); i++) {
      if (characters.get(i).getLabel() == "KEYHOLDER") {
        //Set keyholder in arraylist to local in order to use it
        keyHolder = characters.get(i);
      }
    }

    //Check if the player can grab the key, if they can let them get it.
    if (!player.hasKey()) {
      //They can obtain the key if they are in the same room as the “KEYHOLDER” character.
      if (player.getCurrentRoom().equals(keyHolder.getCurrentRoom())) {
        player.obtainKey();
      }
    }

    //Check if the player needs to teleport because they are in a room with a portal.
    if (player.getCurrentRoom() instanceof PortalRoom) {
      //If they do teleport successfully, print the message to the console.
      if (player.teleport()) {
        System.out.println(PortalRoom.getTeleportMessage());
      }
    }

    //If it is the dragon’s turn to move
    if (isDragonTurn) {
      Room destination; //Room class object
      do {
        destination = dragon.pickRoom();// pick a random adjacent room
      } while (!dragon.changeRoom(destination));
      // If dragon did not change room, dragon will pick a random adjacent room again
      // If the change is successful, make it that is no longer the dragon’s turn.
      isDragonTurn = false;
    }

    //Check and update the gameState data field.
    if (player.getCurrentRoom() instanceof TreasureRoom) {
      TreasureRoom t = (TreasureRoom) player.getCurrentRoom();
      //If the player is in the treasure room and can open the treasure they win.
      if (t.playerCanGrabTreasure(player) && gameState == 0) {
        System.out.println("[YOU WIN] You found the unlocked treasure!");
        gameState = 1;
      }
    }
    //If the dragon and player are in the same room then the player loses. 
    if (player.getCurrentRoom().equals(dragon.getCurrentRoom()) && gameState == 0) {
      System.out.println(Dragon.getDragonEncounter());
      gameState = 2;
    }

    //Check for any warnings
    if (player.isPortalNearby() && gameState == 0) {
      System.out.println(PortalRoom.getPortalWarning());
    }
    //Check for any warnings
    if (player.isTreasureNearby() && gameState == 0) {
      System.out.println(TreasureRoom.getTreasureWarning());
    }
    //Check for any warnings
    if (player.isDragonNearby(dragon) && gameState == 0) {
      System.out.println(Dragon.getDragonWarning());
    }
  }

  /**
   * Use the key pressed value (provided automatically by PApplet as a variable called key) as the
   * ID of the room the player wants to move into and changeRoom()
   */
  @Override
  public void keyPressed() {
    try {
      Player player = null;// Create a local player
      for (int i = 0; i < characters.size(); i++) {
        if (characters.get(i).getLabel() == "PLAYER") {
          //Downcast the character to player in order to use player methods
          player = (Player) characters.get(i);
        }
      }
      //Use the key pressed value
      int value = Integer.parseInt(String.valueOf(key)); //Parse the keyboard input
      //Use ID of the room the player wants to move into
      Room intendRoom = getRoomByID(value); //Get the id of the intended room
      if (gameState == 1 || gameState == 2) {
        //If gameState 1 or 2 it does nothing
      } else {
        if (player.changeRoom(intendRoom)) {
          //If the change is successful, make it the dragon’s turn to move
          isDragonTurn = true; //Set dragon turn to start
        } else {
          //If it is not, then print out to the console for the user to pick a valid room.
          System.out.println("Please pick a valid room!");
        }
      }
    } catch (IndexOutOfBoundsException e) {// catch the exception if the key value is 0
      System.out.println("Please pick a valid room!");
    }
  }

  /**
   * Loads in room info using the file stored in roomInfo
   *
   * @author Michelle
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {

      //scanner to read from file
      fileReader = new Scanner(roomInfo);

      //read line by line until none left
      while (fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();

        //parse info and create new room 
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); //get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;

        if (parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);

        }

        if (parts.length == 4) {
          description = parts[3].trim(); //get the room description
        }

        switch (parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }

        if (newRoom != null) {
          roomList.add(newRoom);
        }
      }
    } catch (IOException e) { //handle checked exception
      e.printStackTrace();
    } finally {
      if (fileReader != null) {
        fileReader.close(); //close scanner regardless of what happened for security reasons :)
      }
    }
  }

  /**
   * Loads in room connections using the file stored in mapInfo
   *
   * @author Michelle
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
      //scanner to read from file
      fileReader = new Scanner(mapInfo);

      //read line by line until none left
      while (fileReader.hasNext()) {

        //parse info
        String nextLine = fileReader.nextLine();
        String parts[] = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);

        Room toEdit = getRoomByID(id); //get the room we need to update info for adjacent rooms

        //add all the rooms to the adj room list of toEdit
        for (int i = 1; i < parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    } catch (IOException e) { //handle checked exception
      e.printStackTrace();
    } finally {    //close scanner regardless of what happened for security reasons :)
      if (fileReader != null)
        fileReader.close();
    }
  }

  /**
   * Get the room objected associated with the given ID.
   *
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id) {
    int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));
    Room toEdit = roomList.get(indexToEdit);
    return toEdit;
  }
}
