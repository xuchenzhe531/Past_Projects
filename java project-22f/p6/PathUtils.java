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

/**
 * Utility methods for planning a trip through a grid of city intersections
 */
public class PathUtils {
  /**
   * Constructor for PathUtils()
   */
  public PathUtils() {
  }

  /**
   * Finds the number of valid Paths between the given start and end Intersections. If it is not
   * possible to get from the start to the end intersection by moving up or right, then 0 should be
   * returned.
   *
   * @param start Intersection to start at
   * @param end   Intersection to end at
   * @return the number of valid Paths which start and end at the given Intersections
   */
  public static int countPaths(Intersection start, Intersection end) {
    if (start.getX() > end.getX() || start.getY() > end.getY()) {
      // if the start and end are invalid return 0
      return 0;
    }
    if (start.getX() < end.getX() && start.getY() < end.getY()) {
      // If the path can move in 2 direction it will separate into two path
      return countPaths(start.goEast(), end) + countPaths(start.goNorth(), end);
    } else if (start.getX() == end.getX() && start.getY() < end.getY()) {
      // If the path can only move to North it will move north
      return countPaths(start.goNorth(), end);
    } else if (start.getY() == end.getY() && start.getX() < end.getX()) {
      // If the path can only move to East it will move east
      return countPaths(start.goEast(), end);
    } else {
      // This is the base case
      // If the start and end is the same which mean finish one path it will return 1
      return 1;
    }
  }

  /**
   * Finds all valid Paths between the given start and end Intersections. If it is not possible to
   * get from the start to the end intersection by moving up or right, then an empty ArrayList
   * should be returned.
   *
   * @param start Intersection to start at
   * @param end   Intersection to end at
   * @return an ArrayList containing all valid Paths which start and end at the given Intersections
   */
  public static ArrayList<Path> findAllPaths(Intersection start, Intersection end) {
    ArrayList<Path> paths = new ArrayList<>();
    if (start.getX() > end.getX() || start.getY() > end.getY()) {
      // If the start and end are invalid it will return an empty ArrayList
      return paths;
    }
    if (start.getX() < end.getX() && start.getY() < end.getY()) {
      // if the path can both go east and go north,
      // it will separate into two path
      paths.addAll(findAllPaths(start.goEast(), end));
      paths.addAll(findAllPaths(start.goNorth(), end));
    } else if (start.getX() == end.getX() && start.getY() < end.getY()) {
      // if the path can only go north then make it go north
      paths.addAll(findAllPaths(start.goNorth(), end));
    } else if (start.getY() == end.getY() && start.getX() < end.getX()) {
      // if the path can only go east then make it go east
      paths.addAll(findAllPaths(start.goEast(), end));
    } else {
      // This is the base case.
      // Create a path with end intersection and add to the Arraylist
      // when the start and end matched which means finished one path
      Path path = new Path();
      path.addTail(end);
      paths.add(path);
      return paths; // return the arraylist
    }
    for (int i = 0; i < paths.size(); i++) {
      // Add the start intersection to the head of each path where the path start
      paths.get(i).addHead(start);
    }
    return paths; // return the arraylist
  }
}
