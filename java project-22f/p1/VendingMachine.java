////////////////////////////////FILE HEADER////////////////////////////////////
//
//Title:    P01 Vending Machine
//Course:   CS 300 Fall 2022
//
//@author:  Chenzhe Xu
//@email:   cxu296@wisc.edu
//Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email:  None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
public class VendingMachine {

  /**
   * Adds/appends an item defined by its description and expirationDate to a
   * vending machine
   * represented by an oversize array of strings defined by the two-dimensional
   * array items and its
   * size itemsCount. The item will be added to the end of the array. If the
   * vending machine is
   * full, the new item won't be added and the method returns the items count
   * passed as input
   * without making any changes to the contents of the array items.
   * 
   * @param description    description of the item to be added to the vending
   *                       machine
   * @param expirationDate a string parsable to a positive integer which
   *                       represents the expiration
   *                       date of the item. The date "0" represents January 1st
   *                       2023.
   * @param items          a two-dimensional of strings storing items. items[i][0]
   *                       and items[i][1]
   *                       respectively represent the description and the
   *                       expiration date of the
   *                       item stored at index i
   * @param itemsCount     number of items in the vending machine
   * @return the size of the vending machine after trying to add the new item
   */
  public static int addItem(String description, String expirationDate, String[][] items,
      int itemsCount) {
    //add description and expirationdate to the 2D array
    if (items.length > itemsCount) {
      items[itemsCount] = new String[] { description, expirationDate };
      itemsCount++;
    }
    return itemsCount; //return the itemsCount
  }

  /**
   * Returns without removing a string representation of the item at the given
   * index within the
   * vending machine defined by the array items and its size itemsCount. This
   * method does not make
   * any changes to the contents of the vending machine.
   * 
   * @param items      two dimensional array storing items within a vending
   *                   machine where
   *                   items[i][0] represents the description of the item at index
   *                   i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @param index      index of an item within the provided vending machine
   * @return a string representation of the item stored at the given index within
   *         the vending
   *         machine defined by items and itemsCount. The returned string must
   *         have the following
   *         format: "description (expiration date)". If the provided index is out
   *         of the range of
   *         indexes 0..itemsCount-1, the method returns "ERROR INVALID INDEX"
   */
  public static String getItemAtIndex(int index, String[][] items, int itemsCount) {
    //return "ERROR INVALID INDEX" if the index input is out of bound
    if (index > itemsCount - 1 || index < 0) {
      return "ERROR INVALID INDEX";
    }
    return items[index][0] + " (" + items[index][1] + ")"; // return the item at given index
  }

  /**
   * Returns without removing the index of the item having the provided
   * description and the smallest
   * expiration date within the vending machine defined by the array items and its
   * size itemsCount.
   * 
   * @param description description of the item to get its index
   * @param items       two dimensional array storing items within a vending
   *                    machine where
   *                    items[i][0] represents the description of the item at
   *                    index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the index of the next item, meaning the item with the given
   *         description and the
   *         smallest expiration date. If no match found, return -1.
   */
  public static int getIndexNextItem(String description, String[][] items, int itemsCount) {
    int index = -1;
    int expiration_date = 0;
    //find the first item which is the same as the description and convert the date to integer
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0]) == true) {
        expiration_date = Integer.parseInt(items[i][1]);
        index = i;
        break;
      }
    }
    //if the description does not exist in the array
    if (index == -1) {
      return -1;
    }
    //Compare the expiration date and find the smallest one
    for (int j = index + 1; j < itemsCount; j++) {
      if (description.equals(items[j][0]) == true) {
        if (Integer.parseInt(items[j][1]) < expiration_date) {
          expiration_date = Integer.parseInt(items[j][1]);
          index = j;
        }
      }
    }
    return index; //return the index of the next item
  }

  /**
   * Removes the item having the provided description with the smallest expiration
   * date within the
   * vending machine defined by the array items and its size itemsCount. This
   * method should maintain
   * the order of precedence of items in the vending machine. This means that the
   * remove operation
   * involves a shift operation.
   * 
   * @param description description of the item to remove or dispense
   * @param items       array storing items within a vending machine
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return size of the vending machine after removing the item with the given
   *         description and the
   *         smallest expiration date. If no match found, this method must return
   *         the provided
   *         itemsCount without making any change to the contents of the vending
   *         machine.
   */
  public static int removeNextItem(String description, String[][] items, int itemsCount) {
    int index = -1;
    int smallest_expiration_date = -1;
    //Find the first item corresponds to the description and parse date to integer
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0]) == true) {
        index = i;
        smallest_expiration_date = Integer.parseInt(items[i][1]);
        break;
      }
    }
    //Compare the dates and find the smallest
    for (int j = index + 1; j < itemsCount; j++) {
      if (description.equals(items[j][0]) == true) {
        if (Integer.parseInt(items[j][1]) < smallest_expiration_date) {
          index = j;
          smallest_expiration_date = Integer.parseInt(items[j][1]);
        }
      }
    }
    //Shifting after removal
    if (index >= 0) {
      for (int k = index; k < itemsCount - 1; k++) {
        items[k][0] = items[k + 1][0];
        items[k][1] = items[k + 1][1];
      }
      items[itemsCount-1] = null;
      --itemsCount;
    }

    return itemsCount; //return the itemsCount after removal
  }

  /**
   * Returns the number of occurrences of an item with a given description within
   * the vending
   * machine defined by items and itemsCount
   * 
   * @param description description (name) of an item
   * @param items       two dimensional array storing items within a vending
   *                    machine where
   *                    items[i][0] represents the description of the item at
   *                    index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return the number of occurrences of an item with a given description within
   *         the vending
   *         machine
   */
  public static int getItemOccurrences(String description, String[][] items, int itemsCount) {
    int counter = 0;
    //Check the occurence through description
    for (int i = 0; i < itemsCount; i++) {
      if (items[i][0].equals(description) == true) {
        counter++;
      }
    }
    return counter; //return the occurrences
  }

  /**
   * Checks whether a vending machine defined by the array items and its size
   * itemsCount contains at
   * least an item with the provided description
   * 
   * @param description description (name) of an item to search
   * @param items       two dimensional array storing items within a vending
   *                    machine where
   *                    items[i][0] represents the description of the item at
   *                    index i and
   *                    items[i][1] stores its expiration date.
   * @param itemsCount  (size) number of items stored in the vending machine
   * @return true if there is a match with description in the vending machine,
   *         false otherwise
   */
  public static boolean containsItem(String description, String[][] items, int itemsCount) {
    //Check whether the item is in the array
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0]) == true) {
        return true;
      }
    }
    return false; //If the item is not found
  }

  /**
   * Returns the number of items in the vending machine with a specific
   * description and whose
   * expiration dates are greater or equal to a specific expiration date
   * 
   * @param description    description of the item to find its number of
   *                       occurrences
   * @param expirationDate specific (earliest) expiration date
   * @param items          two dimensional array storing items within a vending
   *                       machine where
   *                       items[i][0] represents the description of the item at
   *                       index i and
   *                       items[i][1] stores its expiration date.
   * @param itemsCount     (size) number of items stored in the vending machine
   * @return the number of items with a specific description and whose expiration
   *         date is greater or
   *         equal to the given one
   */
  public static int getItemsOccurrencesByExpirationDate(String description, String expirationDate,
      String[][] items, int itemsCount) {
    int counter = 0;
    int currentExpiration = Integer.parseInt(expirationDate);
    //Check the item through the description and find the number of dates are greater or equal to
    //the specific date
    for (int i = 0; i < itemsCount; i++) {
      if (description.equals(items[i][0]) == true) {
        int tempExpiration = Integer.parseInt(items[i][1]);
        if (tempExpiration >= currentExpiration) {
          counter++;
        }
      }
    }
    return counter; //return the occurrences
  }

  /**
   * Returns a summary of the contents of a vending machine in the following
   * format: Each line
   * contains the description or item name followed by one the number of
   * occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending
   * machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary
   * description will be as
   * follows. "water (5)\nchocolate (10)\nsnack (7)"
   * If the vending machine is empty, this method returns an empty string ""
   * 
   * @param items      two dimensional array storing items within a vending
   *                   machine where
   *                   items[i][0] represents the description of the item at index
   *                   i and items[i][1]
   *                   stores its expiration date.
   * @param itemsCount (size) number of items stored in the vending machine
   * @return a descriptive summary of the contents of a vending machine
   */
  public static String getItemsSummary(String[][] items, int itemsCount) {
    if (itemsCount <= 0) {
      return "";
    }
    String summary = "";
    int total = 0;;
    int index = 0;
    while (true) {
      String description = items[index][0];
      //To loop and check whether the description is already in the summary
      if(summary.contains(description) != true){
        int counter = 0;
        //To count the number of occurrence of each item
        for (int i = 0; i < itemsCount; i++) {
          if (description.equals(items[i][0]) == true) {
            counter++;
          }
        }
        total += counter;
        //Format the string
        if(total == itemsCount){
          summary = summary.concat(description + " (" + Integer.toString(counter) + ")");
          break;
        }else{
          summary = summary.concat(description + " (" + Integer.toString(counter) + ")\n");
        }  
      }
      index++;
      }
    return summary; // return the string
  }
}
