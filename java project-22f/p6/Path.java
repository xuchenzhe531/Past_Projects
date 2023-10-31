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
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class represents a valid path through a grid of city intersections surrounded by streets.
 */
public class Path {
  //List of Intersections followed in this Path
  private ArrayList<Intersection> intersections;

  /**
   * Initializes this Path to start as empty
   */
  public Path() {
    //Initializes this Path to start as empty
    intersections = new ArrayList<>();
  }

  /**
   * Returns the number of Intersections in this Path
   *
   * @return the number of Intersections in this Path
   */
  public int length() {
    return intersections.size();
  }

  /**
   * Returns the first Intersection in this Path, if it is not empty. Otherwise, throws a
   * NoSuchElementException
   *
   * @return the first Intersection in this Path, if it is not empty
   * @throws NoSuchElementException - if this Path is empty
   */
  public Intersection getHead() {
    if (intersections.isEmpty()) {
      //Throw NoSuchElementException - if this Path is empty
      throw new NoSuchElementException("The Path is empty!");
    }
    //Returns the first Intersection in this Path
    return intersections.get(0);
  }

  /**
   * Returns the last Intersection in this Path, if it is not empty. Otherwise, throws a
   * NoSuchElementException
   *
   * @return the last Intersection in this Path, if it is not empty
   * @throws NoSuchElementException - if this Path is empty
   */
  public Intersection getTail() {
    if (intersections.isEmpty()) {
      //Throw NoSuchElementException - if this Path is empty
      throw new NoSuchElementException("The Path is empty!");
    }
    //Returns the last Intersection in this Path
    return intersections.get(intersections.size() - 1);
  }

  /**
   * Adds the given Intersection to the end of this Path if it is a valid addition. An Intersection
   * is a valid addition if the current Path is empty, or the Intersection to add is one step
   * directly east, or one step directly north of the current tail Intersection in this Path.
   * Should throw an IllegalArgumentException if the given Intersection is not a valid addition.
   *
   * @param toAdd - Intersection to add to the end of this Path
   * @throws IllegalArgumentException - if the Intersection to add is not valid
   */
  public void addTail(Intersection toAdd) {
    //An Intersection is a valid addition if the current Path is empty
    if (intersections.isEmpty()) {
      intersections.add(toAdd);
    } else {
      int x = toAdd.getX(); //Get x in toAdd
      int y = toAdd.getY(); //Get y in toAdd
      Intersection tail = getTail(); //Get the tail
      //Intersection to add is one step directly east
      if (x == tail.getX() + 1 && tail.getY() == y) {
        intersections.add(toAdd);
        //one step directly north of the current tail Intersection in this Path
      } else if (y == tail.getY() + 1 && tail.getX() == x) {
        intersections.add(toAdd);
      } else {
        //throw an IllegalArgumentException if the given Intersection is not a valid addition
        throw new IllegalArgumentException("Invalid intersection to add!");
      }
    }
  }

  /**
   * Adds the given Intersection to the front of this Path if it is a valid addition. An
   * Intersection is a valid addition if the current Path is empty, or the Intersection to add is
   * one step directly west, or one step directly south of the current head Intersection in this
   * Path. Should throw an IllegalArgumentException if the given Intersection is not a valid
   * addition.
   *
   * @param toAdd - is an Intersection object
   * @throws IllegalArgumentException - if the Intersection to add is not valid
   */
  public void addHead(Intersection toAdd) {
    //A Intersection is a valid addition if the current Path is empty,
    if (intersections.isEmpty()) {
      intersections.add(toAdd);
    } else {
      int x = toAdd.getX(); //Get x in toAdd
      int y = toAdd.getY(); //Get y in toAdd
      Intersection head = getHead(); //Get the tail
      //Intersection to add is one step directly west
      if (x == head.getX() - 1 && head.getY() == y) {
        intersections.add(0, toAdd);
        //one step directly south of the current tail Intersection in this Path
      } else if (y == head.getY() - 1 && head.getX() == x) {
        intersections.add(0, toAdd);
      } else {
        //throw an IllegalArgumentException if the given Intersection is not a valid addition
        throw new IllegalArgumentException("Invalid intersection to add!");
      }
    }
  }


  /**
   * Returns a String representing the coordinates taken in this Path.
   *
   * @return a String representing the coordinates followed by this Path
   */
  @Override
  public String toString() {
    // An empty Path should return the String "Empty"
    if (intersections.isEmpty()) {
      return "Empty";
    }
    //a non-empty Path should return the coordinates of the Intersections it
    //visits separated by a "->"
    StringBuilder totalString = new StringBuilder();
    for (int i = 0; i < intersections.size(); i++) {
      //if it is the last element, it does not need ->
      if (i == intersections.size() - 1) {
        totalString.append(intersections.get(i).toString());
      } else {
        //If it is not the last element, it does need ->
        totalString.append(intersections.get(i).toString()).append("->");
      }
    }
    return totalString.toString(); //return string
  }
}
