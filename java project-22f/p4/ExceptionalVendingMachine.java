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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * This class models a vending machine. When requested, the item with the soonest expiration date
 * will be dispensed first.
 */
public class ExceptionalVendingMachine {
  private Item[] items; // array storing the items available within this vending machine
  private int size; // number of items available in this vending machine

  /**
   * Creates a new vending machine with a given capacity
   *
   * @param capacity maximum number of items that can be held by this vending machine
   * @throws IllegalArgumentException with a descriptive error message if capacity is zero or
   *                                  negative
   */
  public ExceptionalVendingMachine(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity can't be <= 0");
    }
    items = new Item[capacity];
    size = 0; // optional since 0 is the default value for primitive type int
  }

  /**
   * Checks whether this vending machine is empty
   *
   * @return true if this vending machine is empty, false otherwise
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Checks whether this vending machine is full
   *
   * @return true if this vending machine is full, false otherwise
   */
  public boolean isFull() {
    if (size == items.length) {
      return true;
    }
    return false;
  }

  /**
   * Returns the total number of items available in this vending machine
   *
   * @return the size of this vending machine
   */
  public int size() {
    return size;
  }

  /**
   * Appends an item defined by its description and expirationDate to this vending machine.
   * The item will be added to the end of the vending machine.
   *
   * @param description    description of the item to be added to the vending machine
   * @param expirationDate a positive integer which represents the expiration date of the item.
   * @throws IllegalStateException    with a descriptive error message if the vending machine is
   *                                  full
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank or if expirationDate is negative ( &lt; 0)
   */
  public void addItem(String description, int expirationDate)
      throws IllegalStateException, IllegalArgumentException {
    // create a new item and add it to the end of this vending machine
    if (isFull() == true) {
      throw new IllegalStateException("Vending Machine is Full");
    }

    if (expirationDate < 0) {
      throw new IllegalArgumentException("ExpirationDate is less than 0");
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Invalid Description");
    }

    items[size] = new Item(description, expirationDate);
    size++;
  }


  /**
   * Returns without removing the string representation of the item at the given index within the
   * vending machine
   *
   * @param index index of an item within the vending machine
   * @return the string representation of the item stored at the given index within the vending
   * machine defined by items and itemsCount. The returned string must have the following format:
   * "description: expirationDate".
   * @throws IndexOutOfBoundsException with a descriptive error message if index &lt; 0 or index
   *                                   &gt;= size of the vending machine
   */
  public String getItemAtIndex(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bound");
    }
    return items[index].toString();
  }

  /**
   * Returns the number of occurrences of items with a given description within this vending
   * machine
   *
   * @param description description (name) of an item
   * @return the number of occurrences of items with a given description within the vending machine
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   */
  public int getItemOccurrences(String description) throws IllegalArgumentException {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Invalid Description");
    }

    int Occurrences = 0;
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())) {
        Occurrences++;
      }
    }
    return Occurrences;
  }

  /**
   * Checks whether the vending machine contains at least one item with the provided description
   *
   * @param description description (name) of an item to search
   * @return true if there is a match with description in the vending machine, false otherwise
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   */
  public boolean containsItem(String description) throws IllegalArgumentException {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Invalid Description");
    }
    return getItemOccurrences(description) != 0;
  }

  /**
   * Returns the number of items in the vending machine with a specific description and whose
   * expiration dates are greater or equal to a specific expiration date
   *
   * @param description    description of the item to find its number of occurrences
   * @param expirationDate specific (earliest) expiration date
   * @return the number of items with a specific description and whose expiration date is greater
   * or equal to the given one
   * @throws IllegalArgumentException with a descriptive error message if expirationDate is
   * negative(less than zero) or description is null or blank
   */
  public int getItemOccurrencesByExpirationDate(String description, int expirationDate)
      throws IllegalArgumentException {
    int Occurrences = 0; // number of occurrences of the matching items
    // traverse the vending machine looking for matching items
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Invalid Description");
    }
    if (expirationDate < 0) {
      throw new IllegalArgumentException("ExpirationDate is less than 0");
    }
    for (int i = 0; i < size; i++) {
      if (description.equals(
          items[i].getDescription()) && items[i].getExpirationDate() >= expirationDate) {
        // match found
        Occurrences++;
      }
    }
    // return the number of occurrences of the matching items
    return Occurrences;
  }

  /**
   * Returns without removing the index of the item having the provided description and the
   * smallest expiration date within the vending machine.
   *
   * @param description description of an item
   * @return the index of the next item, meaning the item with the given description and the
   * smallest expiration date.
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match found
   */
  public int getIndexNextItem(String description)
      throws IllegalArgumentException, NoSuchElementException {
    int index = -1; // index of the search item
    int minExpirationDate = -1; // smallest expiration date of matching items
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Invalid Description");
    }
    // traverse the vending machine looking for the matching item with the smallest expiration date
    for (int i = 0; i < size; i++) {
      if (description.equals(items[i].getDescription())) {
        int itemExpirationDate = items[i].getExpirationDate();
        if (index == -1) { // first match found
          minExpirationDate = items[i].getExpirationDate();
          index = i;
        } else { // another match found
          if (itemExpirationDate < minExpirationDate) {
            // match with smaller (sooner) expiration date found
            minExpirationDate = itemExpirationDate; // update minimum expiration date
            index = i; // update the index of the next item
          }
        }
      }
    }
    if (index == -1) {
      throw new NoSuchElementException("No such element");
    }

    return index; // return the index of the item with the given description and the smallest
    // expiration date if found
  }


  /**
   * Removes and returns the item having the provided description with the smallest expiration date
   * within the vending machine. This method should maintain the order of precedence of items in
   * the vending machine. This means that the remove operation involves a shift operation.
   *
   * @param description description of the item to remove or dispense
   * @return The removed or dispensed item if it is available
   * @throws IllegalArgumentException with a descriptive error message if description is null or
   *                                  blank
   * @throws NoSuchElementException   with a descriptive error message if no match found
   */
  public Item removeNextItem(String description)
      throws IllegalArgumentException, NoSuchElementException {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Invalid Description");
    }
    // get the index of the next item to dispense by this vending machine
    int index = getIndexNextItem(description); // exceptions throws by this method call should
    // propagate
    // save a copy of the item to dispense
    if (index == -1) {
      throw new NoSuchElementException("No such element");
    }
    Item itemToDispense = items[index];

    // remove the item at index using a shift operation
    for (int i = index + 1; i < size; i++) {
      items[i - 1] = items[i];
    }
    items[size - 1] = null;
    size--;

    return itemToDispense; // return the removed item
  }

  /**
   * Returns a summary of the contents of this vending machine in the following format: Each line
   * contains the description or item name followed by one the number of occurrences of the item
   * name in the vending machine between parentheses. For instance, if the vending machine contains
   * five bottles of water, ten chocolates, and seven snacks, the summary description will be as
   * follows. water (5)\n chocolate (10)\n snack (7) If the vending machine is empty, this method
   * returns an empty string ""
   *
   * @return a descriptive summary of the contents of the vending machine
   */
  public String getItemsSummary() {
    String summary = ""; // empty string

    // traverse the vending machine and build its items summary description
    for (int i = 0; i < size; i++) {
      // add the item's description and count if not yet processed
      if (!summary.contains(items[i].getDescription())) {
        summary = summary + items[i].getDescription() + " (" + getItemOccurrences(
            items[i].getDescription()) + ")\n";
      }
    }
    return summary.trim(); // return the items' summary
  }

  /**
   * Parse an item's string representation and add the corresponding item to this vending machine
   *
   * @param itemRepresentation a String representation of an item formatted as "description:
   *                           expirationDate". Extra spaces at the beginning and end of the item
   *                           description and expirationDate can be disregarded.
   * @throws IllegalArgumentException with a descriptive error message if itemRepresentation is
   *                                  null or blank
   * @throws DataFormatException      with a descriptive error message if the provided string is
   *                                  not correctly formatted. A correct format of the
   *                                  itemRepresentation is "description: expirationDate". The
   *                                  description must be a NOT blank string. The expirationDate
   *                                  must be a non-empty string parsable to a positive integer.
   *                                  The item's description and its expiration date must be
   *                                  separated by one colon ":". Extra whitespace at the
   *                                  beginning and end of description or expirationDate should
   *                                  be disregarded.
   * @throws IllegalStateException    with a descriptive error message if the vending machine is
   *                                  full
   */
  public void loadOneItem(String itemRepresentation)
      throws IllegalArgumentException, DataFormatException, IllegalStateException {
    if (isFull()) {
      throw new IllegalStateException("The vending Machine is Full");
    }
    if (itemRepresentation == null || itemRepresentation.isBlank()) {
      throw new IllegalArgumentException("item is null or is blank");
    }
    String splitted[] = itemRepresentation.split(":");
    if (splitted.length != 2) {
      throw new DataFormatException("There should be one description, colon, and expirationDate ");
    }
    String description = splitted[0].trim();
    if (description.isBlank()) {
      throw new DataFormatException("Description is Blank ");
    }
    String expirationDate = splitted[1].trim();
    int expDate = -1;
    try {
      expDate = Integer.parseInt(expirationDate);
    } catch (Exception e) {
      throw new DataFormatException("Expiration Date is not a Integer");
    }
    if (expDate < 0) {
      throw new DataFormatException("Expiration Date is negative ");
    }
    addItem(description, expDate);
  }


  /**
   * Reads and parses the file passed as input line by line and loads the corresponding items to
   * the vending machine. Each line in the file represents an item description formatted as
   * "description: expirationDate". Blank and badly formatted lines must be skipped.
   *
   * Displays "Vending machine FULL. No more items can be loaded." when trying to load a new item
   * to the vending machine if it is or becomes full.
   *
   * @param file file to load items from
   * @return the total number of new items loaded to this vending machine
   * @throws FileNotFoundException if the file object does not correspond to an actual file within
   *                               the file system.
   */
  public int loadItems(File file) throws FileNotFoundException {
    if (!file.exists()) {
      throw new FileNotFoundException("File does not exist");
    }
    int countNewItems = 0;
    Scanner scnr = new Scanner(file);
    try {
      if (isFull() == true) {
        System.out.println("Vending machine FULL. No more items can be loaded.");
      } else {
        while (scnr.hasNextLine()) {
          if (isFull() == true) {
            System.out.println("Vending machine FULL. No more items can be loaded.");
            break;
          }
          String item = scnr.nextLine();
          String splitted[] = item.split(":");
          if (splitted.length != 2) {
            continue;
          }
          String description = splitted[0].trim();
          if (description.isBlank()) {
            continue;
          }
          String expirationDate = splitted[1].trim();
          int expDate = -1;
          try {
            expDate = Integer.parseInt(expirationDate);
          } catch (Exception e) {
            continue;
          }
          if (expDate < 0) {
            continue;
          }
          try {
            loadOneItem(item);
            countNewItems++;
          } catch (IllegalArgumentException e) {
            e.printStackTrace();
          } catch (IllegalStateException e) {
            e.printStackTrace();
          } catch (DataFormatException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      scnr.close();
    }

    return countNewItems;
  }

  /**
   * Saves the summary of this vending machine to the file object passed as input
   *
   * @param file file object where the vending machine summary will be saved
   */
  public void saveVendingMachineSummary(File file) {
    try {
      FileWriter writer = new FileWriter(file);
      String toBeWrite = getItemsSummary();
      writer.write(toBeWrite);
      try {
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        writer.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
