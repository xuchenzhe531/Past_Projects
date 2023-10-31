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

import java.util.Random;

/**
 * This class models a portal room in Dragon Treasure Game
 */
public class PortalRoom extends Room {
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TELEPORT_MESSAGE =
      "The space distortion teleported you to another room!\n";
  // The massage tells that you successfully teleported
  private static PImage portalImage; //image of a portal to be shown in all portal rooms
  private Random randGen; //random number generator for location picking

  /**
   * Constructor for a PortalRoom object. Initializes all instance data fields.
   *
   * @param ID          the ID that this PortalRoom should have
   * @param description the verbal description this PortalRoom should have
   * @param image       the image that should be used as a background when drawing this PortalRoom
   */
  public PortalRoom(int ID, String description, processing.core.PImage image) {
    //Pass the values to the super class which is Room
    super(ID, description, image);
    //Initialize the Random function
    randGen = new Random();
  }

  /**
   * Getter for PORTAL_WARNING.
   *
   * @return the string for warning about a portal being nearby.
   */
  public static String getPortalWarning() {
    return PORTAL_WARNING; //Return Portal Warning
  }

  /**
   * Getter for TELEPORT_MESSAGE.
   *
   * @return the string for letting the player know they were teleported.
   */
  public static String getTeleportMessage() {
    return TELEPORT_MESSAGE; //return teleport warning
  }

  /**
   * Sets the portal image for the PortalRoom class.
   *
   * @param portalImage the image to represent the portal
   */
  public static void setPortalImage(processing.core.PImage portalImage) {
    PortalRoom.portalImage = portalImage; //Set the image of Portal Room
  }

  /**
   * Picks an adjacent room at random for the player to teleport into.
   *
   * @return The room that player should immediately be moved to
   */
  public Room getTeleportLocation() {
    //return The room that player should immediately be moved to
    return getAdjacentRooms().get(randGen.nextInt(getAdjacentRooms().size()));
  }

  /**
   * Draws this PortalRoom to the window by drawing the background image, a rectangle, some text,
   * and the portal image.
   */
  @Override
  public void draw() {
    //Call the super class which is Room to draw the background
    super.draw();
    //Use the PApplet’s image() instance method to draw the image at (325, 325)
    processing.image(portalImage, 325, 225);
    // Use the PApplet’s fill() instance method to
    // change the draw color to giving it a value of −7028.
    processing.fill(-7028);
    // Use the PApplet’s rect() instance method to draw a rectangle.
    processing.rect(0, 500, 800, 600);
    // Use the PApplet’s fill() instance method again to
    // change the draw color to giving it a value of 0.
    processing.fill(0);
    // Use the PApplet’s text() instance method to draw the Room’stoString()at(300,525).
    processing.text(this.toString(), 300, 525);
  }
}
