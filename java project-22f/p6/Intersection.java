//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P06 City Route Planner
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
 * resents a single intersection point where two streets laid out on a grid plan cross at specified
 * x and y coordinate positions.
 */
public class Intersection {
  private final int x; //Horizontal position of this Intersection
  private final int y; //Vertical position of this Intersection

  /**
   * This is the constructor Initializes this intersection with the given coordinates
   *
   * @param x Horizontal position of this Intersection
   * @param y Vertical position of this Intersection
   */
  public Intersection(int x, int y) {
    this.x = x; //Initializes x with the given coordinates
    this.y = y; //Initializes y with the given coordinates
  }

  /**
   * Getter for X of this Intersection
   *
   * @return int X of this Intersection
   */
  public int getX() {
    return x; //return x coordinate
  }

  /**
   * Getter for Y of this Intersection
   *
   * @return int Y of this Intersection
   */
  public int getY() {
    return y; //return y coordinate
  }

  /**
   * Returns a coordinate-pair representation of this Intersection in the form "(x,y)"
   *
   * @return a coordinate-pair representation of this Intersection
   */
  @Override
  public String toString() {
    //Returns a coordinate-pair representation of this Intersection in the form "(x,y)"
    return "(" + x + "," + y + ")";
  }

  /**
   * Returns true if the given Object is identical to this Intersection
   *
   * @param o object to compare for equality
   * @return true if the given Object is an Intersection object which has the same x and y
   * coordinates as this Intersection
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Intersection) { //true if the given Object is an Intersection object
      Intersection obj = (Intersection) o;
      //check if the object has the same x and y coordinates as this Intersection
      return obj.getX() == x && obj.getY() == y;
    }
    //False if it does not satisfy
    return false;
  }

  /**
   * Creates a new Intersection instance which is one step directly above this Intersection.
   *
   * @return a new Intersection instance which is one step directly above this Intersection
   */
  public Intersection goNorth() {
    //a new Intersection instance which is one step directly above this Intersection
    return new Intersection(x, y + 1);
  }

  /**
   * Creates a new Intersection instance which is one step directly below this Intersection.
   *
   * @return a new Intersection instance which is one step directly below this Intersection
   */
  public Intersection goSouth() {
    //a new Intersection instance which is one step directly below this Intersection
    return new Intersection(x, y - 1);
  }


  /**
   * Creates a new Intersection instance which is one step directly right of this Intersection.
   *
   * @return new Intersection instance which is one step directly right of this Intersection
   */
  public Intersection goEast() {
    //a new Intersection instance which is one step directly right of this Intersection
    return new Intersection(x + 1, y);
  }

  /**
   * Creates a new Intersection instance which is one step directly left of this Intersection.
   *
   * @return a new Intersection instance which is one step directly left of this Intersection
   */
  public Intersection goWest() {
    //a new Intersection instance which is one step directly left of this Intersection
    return new Intersection(x - 1, y);
  }
}
