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
 * This method tests all the method in PathUtils class
 */
public class PathUtilsTester {
  /**
   * Tests the case of countPaths() when there are no valid Paths.
   *
   * @return true if all test cases are passed
   */
  public static boolean testCountPathsNoPath() {
    try {
      Intersection start = new Intersection(2, 2);
      Intersection end = new Intersection(0, 0);
      // Check the correctness of countPaths method when there are no valid Paths.
      if (PathUtils.countPaths(start, end) != 0) {
        System.out.println(
            "testCountPathsNoPath-scenario 1 problem detected: Your countPaths() " +
                "method did not return 0 when there are no valid Paths");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * Tests the case of countPaths() when there is a single valid Path.
   *
   * @return true if all test cases are passed
   */
  public static boolean testCountPathsOnePath() {
    try {
      Intersection start = new Intersection(0, 0);
      Intersection end = new Intersection(0, 10);
      // Check the correctness of countPaths method when there is a single valid Path
      if (PathUtils.countPaths(start, end) != 1) {
        System.out.println(
            "testCountPathsOnePath-scenario 1 problem detected: Your countPaths() " +
                "method did not return 1 when there is a single valid Path");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * Tests the case of countPaths() when there are multiple possible paths.
   *
   * @return true if all test cases are passed
   */
  public static boolean testCountPathsRecursive() {
    try {
      Intersection start = new Intersection(0, 0);
      Intersection end = new Intersection(1, 2);
      // Check the correctness of countPaths method when there are multiple possible paths
      if (PathUtils.countPaths(start, end) != 3) {
        System.out.println(
            "testCountPathsRecursive-scenario 1 problem detected: Your countPaths() method did " +
                "not return the correct count of paths when there are multiple possible paths");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * Tests the case of findAllPaths() when there are no valid Paths.
   *
   * @return true if all test cases are passed
   */
  public static boolean testFindAllPathsNoPath() {
    try {
      Intersection start = new Intersection(2, 2);
      Intersection end = new Intersection(0, 0);
      // Check the correctness of findAllPaths method when there are no valid paths
      if (!PathUtils.findAllPaths(start, end).isEmpty()) {
        System.out.println(
            "testFindAllPathsNoPath-scenario 1 problem detected: Your findAllPaths() method " +
                "did not return an empty Arraylist when there are no valid Paths");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * Tests the case of findAllPaths() when there is a single valid Path
   *
   * @return true if all test cases are passed
   */
  public static boolean testFindAllPathsOnePath() {
    try {
      Intersection start = new Intersection(0, 0);
      Intersection end = new Intersection(0, 2);
      ArrayList<Path> paths = PathUtils.findAllPaths(start, end);
      // Create an expected path
      Path expectedPath = new Path();
      expectedPath.addHead(start);
      expectedPath.addTail(start.goNorth());
      expectedPath.addTail(end);
      // Check if there is only one path and the path is expected
      if (paths.size() != 1 || !paths.get(0).toString().equals(expectedPath.toString())) {
        System.out.println(
            "testFindAllPathsOnePath-scenario 1 problem detected: Your findAllPaths() method " +
                "did not return an Arraylist with one expected path when there is a single " +
                "valid Path");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * Tests the case of findAllPaths() when there are multiple possible paths.
   *
   * @return true if all test cases are passed
   */
  public static boolean testFindAllPathsRecursive() {
    try {
      Intersection start = new Intersection(0, 0);
      Intersection end = new Intersection(1, 2);
      ArrayList<Path> paths = PathUtils.findAllPaths(start, end);
      // Create expected path 1
      boolean path1Found = false;
      Path expectedPath1 = new Path();
      expectedPath1.addHead(start);
      expectedPath1.addTail(start.goNorth());
      expectedPath1.addTail(end.goWest());
      expectedPath1.addTail(end);
      for (Path path : paths) {
        // Check each path in the paths if they match the expected path 1
        if (path.toString().equals(expectedPath1.toString())) {
          path1Found = true; // true if match found
          break;
        }
      }
      // Create expected path 2
      boolean path2Found = false;
      Path expectedPath2 = new Path();
      expectedPath2.addHead(start);
      expectedPath2.addTail(start.goNorth());
      expectedPath2.addTail(end.goSouth());
      expectedPath2.addTail(end);
      for (Path path : paths) {
        // Check each path in the paths if they match the expected path 2
        if (path.toString().equals(expectedPath2.toString())) {
          path2Found = true; // true if match found
          break;
        }
      }
      // Create expected path 3
      boolean path3Found = false;
      Path expectedPath3 = new Path();
      expectedPath3.addHead(start);
      expectedPath3.addTail(start.goEast());
      expectedPath3.addTail(end.goSouth());
      expectedPath3.addTail(end);
      for (Path path : paths) {
        // Check each path in the paths if they match the expected path 3
        if (path.toString().equals(expectedPath3.toString())) {
          path3Found = true; // true if match found
          break;
        }
      }
      // Check if there are correct number of paths and those paths are expected
      if (paths.size() != 3 || !path1Found || !path2Found || !path3Found) {
        System.out.println(
            "testFindAllPathsRecursive-scenario 1 problem detected: Your findAllPaths() method " +
                "did not return an Arraylist with all expected path when there are multiple " +
                "possible paths");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * Run All Tests
   */
  public static void main(String[] args) {
    System.out.println("testCountPathsNoPath:" + testCountPathsNoPath());
    System.out.println("testCountPathsOnePath:" + testCountPathsOnePath());
    System.out.println("testCountPathsRecursive:" + testCountPathsRecursive());
    System.out.println("testFindAllPathsNoPath:" + testFindAllPathsNoPath());
    System.out.println("testFindAllPathsOnePath:" + testFindAllPathsOnePath());
    System.out.println("testFindAllPathsRecursive:" + testFindAllPathsRecursive());
  }
}
