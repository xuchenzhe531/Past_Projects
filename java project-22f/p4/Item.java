//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Vending Machine
// Course:   CS 300 Fall 2022
//
// Author:  Chenzhe Xu
// Email:    cxu296@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Jim Yi
// Partner Email:   yyi37@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////


/**
 * This class is to store description and expiration date of the item.
 */
public class Item {
  private String description;
  private int expirationDate;

  /**
   * This is the constructor for Item class and it sets description date and expiration date
   *
   * @param description    description of item
   * @param expirationDate Expiration Date of item
   * @throws IllegalArgumentException throw the exception if the argument is wrong
   */
  public Item(String description, int expirationDate) throws IllegalArgumentException {
    if (expirationDate < 0 || (description == null || description.isBlank())) {
      throw new IllegalArgumentException("Wrong Argument Input");
    }
    this.description = description.trim();
    this.expirationDate = expirationDate;
  }

  /**
   * This method is to return description of this item
   *
   * @return description of this item
   */
  public String getDescription() {
    return description;
  }

  /**
   * This method is to set description of this item
   *
   * @param description new description of the item
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * This method is to return ExpirationDate of this item
   *
   * @return Expiration Date of this item
   */
  public int getExpirationDate() {
    return expirationDate;
  }

  /**
   * This method is to return the formatted string as "description: expirationDate"
   *
   * @return a String representation of this item
   */
  @Override
  public String toString() {
    return description + ": " + Integer.toString(expirationDate);
  }

  /**
   * This method is to check whether the input equals to this item
   *
   * @param other is the object
   * @return true if the object equals to the item object, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Item) {
      int index = -1;
      for (int i = 0; i < other.toString().length(); i++) {
        if (other.toString().charAt(i) == ':') {
          index = i;
          break;
        }
      }
      if (description.equals(other.toString().substring(0, index))) {
        return true;
      }
    }
    return false;
  }

}
