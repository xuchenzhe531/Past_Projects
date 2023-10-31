//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Chugidex
// Course: CS 300 Fall 2022
//
// Author: Jim Yi
// Email: yyi37@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Chenzhe Xu
// Partner Email: cxu296@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////


/**
 * This class models the Chugimon data type.
 */
public class Chugimon implements Comparable<Chugimon> {
  public static final int MAX_ID = 151; // The maximum ID number
  public static final int MIN_ID = 1; // The minimum ID number
  private final int FIRST_ID; // The first ID of the Chugimon
  private final double HEIGHT; // The height of the Chugimon in meters
  private final String NAME; // The name of the Chugimon
  private final ChugiType PRIMARY_TYPE; // The primary type of the Chugimon; cannot be null;
  // cannot be the same as the secondary type
  private final int SECOND_ID; // The second ID of the Chugimon
  private final ChugiType SECONDARY_TYPE; // The secondary type of the Chugimon; may be null;
  // cannot be the same as the primary type
  private final double WEIGHT; // The weight of the Chugimon in kilograms

  /**
   * Creates a new Chugimon with specific first and second IDs and initializes all its data fields
   * accordingly.
   *
   * @param firstID  the first ID of the Chugimon, between 1-151
   * @param secondID the second ID of the Chugimon, between 1-151
   * @throws IllegalArgumentException if the first and second ID are out of bounds or equal to each
   *                                  other.
   */

  public Chugimon(int firstID, int secondID) throws IllegalArgumentException {
    if (firstID == secondID) {
      throw new IllegalArgumentException("The first and second ID are equal to each other.");
    }
    if (firstID > 151 || firstID < 1) {
      throw new IllegalArgumentException("The first ID is out of bounds.");
    }
    if (secondID > 151 || secondID < 1) {
      throw new IllegalArgumentException("The second ID is out of bounds.");
    }
    // Assign the values to the data fields
    FIRST_ID = firstID;
    SECOND_ID = secondID;
    NAME = ChugidexUtility.getChugimonName(firstID, secondID);
    WEIGHT = ChugidexUtility.getChugimonWeight(firstID, secondID);
    HEIGHT = ChugidexUtility.getChugimonHeight(firstID, secondID);
    // To get the primary and secondary type
    ChugiType[] arr = ChugidexUtility.getChugimonTypes(firstID, secondID);
    PRIMARY_TYPE = arr[0];
    // To check whether the CHugimon has secondary type
    if (arr.length < 2 || arr[1] == null) {
      SECONDARY_TYPE = null;
    } else {
      SECONDARY_TYPE = arr[1];
    }
  }

  /**
   * Gets the name of this Chugimon
   *
   * @return the name of the Chugimon
   */
  public String getName() {
    return NAME;
  }

  /**
   * Gets the first id of this Chugimon
   *
   * @return the first id of the Chugimon
   */
  public int getFirstID() {
    return FIRST_ID;
  }

  /**
   * Gets the second id of this Chugimon
   *
   * @return the second id of the Chugimon
   */
  public int getSecondID() {
    return SECOND_ID;
  }

  /**
   * Gets the primary type of this Chugimon
   *
   * @return the primary type of the Chugimon
   */
  public ChugiType getPrimaryType() {
    return PRIMARY_TYPE;
  }

  /**
   * Gets the secondary type of this Chugimon
   *
   * @return the secondary type of the Chugimon
   */
  public ChugiType getSecondaryType() {
    return SECONDARY_TYPE;
  }

  /**
   * Gets the height of this Chugimon
   *
   * @return the height of the Chugimon
   */
  public double getHeight() {
    return HEIGHT;
  }

  /**
   * Gets the weight of this Chugimon
   *
   * @return the weight of the Chugimon
   */
  public double getWeight() {
    return WEIGHT;
  }

  /**
   * Determines the ordering of Chugimon. Chugimon are ordered by: 1) name (alphabetical) 2) the
   * first ID (if name is equal). The one with the smaller first ID is less than the other. 3) the
   * second ID (if name and first ID are equal). The one with the smaller second ID is less than
   * the other. A Chugimon with identical #1-3 are considered equal.
   *
   * @param otherChugi the other Chugimon to compare this Chugimon to.
   * @return a negative int if this Chugimon is less than other, a positive int if this Chugimon is
   * greater than other, or 0 if this and the other Chugimon are equal.
   */
  @Override
  public int compareTo(Chugimon otherChugi) {
    // Compare NAME first
    if (NAME.equals(otherChugi.getName())) {
      // Then compare FirstID if Name is the same
      if (FIRST_ID == otherChugi.getFirstID()) {
        // Finally, compare second ID
        return SECOND_ID - otherChugi.getSecondID();
      } else {
        return FIRST_ID - otherChugi.getFirstID();
      }
    } else {
      return NAME.compareTo(otherChugi.getName());
    }
  }

  /**
   * A Chugimon's String representation is its name followed by "#FIRST_ID.SECOND_ID" -- Example:
   * "Zapchu#145.25"
   *
   * @return the String Example: "Zapchu#145.25"
   */
  @Override
  public String toString() {
    return NAME + "#" + FIRST_ID + "." + SECOND_ID;
  }

  /**
   * Equals method for Chugimon. This Chugimon equals another object if other is a Chugimon with
   * the exact same name, and their both first and second IDs match.
   *
   * @param other Object to determine equality against this Chugimon
   * @return true if this Chugimon and other Object are equal, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Chugimon) {
      // cast other object and compare it
      Chugimon check = (Chugimon) other;
      if (compareTo(check) == 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
