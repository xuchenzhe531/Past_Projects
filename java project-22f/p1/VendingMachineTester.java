
////////////////////////////////FILE HEADER////////////////////////////////////
//
//Title:    P01 Vending Machine Tester
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
import java.util.Arrays;

public class VendingMachineTester {
  /**
   * This is a test for VendingMachine.java. Specifically, it contains all the
   * tests for each
   * method in VendingMachine.java. Each test method contains several scenerios to
   * test the method
   * conpletely. If one of the tests fail, the test method will return false.
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
   * @return true if the test is passed or false if the test is not passed
   */

  /**
   * Checks the correctness of getIndexNextItem defined in the VendingMachine
   * class. This method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testGetIndexNextItem() {
    // Test scenarios normal and edge cases
    // Recall that the VendingMachine.getNextItem method gets the next item to be
    // dispensed given
    // its description without removing it.

    // 1. Next item to be dispensed is NOT found: the expected output is -1
    {
      // define the vending machine
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, null, null,
          null };
      int itemsCount = 3;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("candy", items, itemsCount) != -1) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did not return "
                + "-1 when no match found.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items
      // passed as
      // input
      String[][] originalItems = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, null,
          null, null };
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 1. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 2. Next item to be dispensed is at index 0
    {
      String[][] items = new String[][] { { "Water", "1" }, { "Chocolate", "10" }, { "Juice", "20" },
          { "Water", "5" }, { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Water", items, itemsCount) != 0) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did not return "
                + "the expected output when the vending machines contains multiple matches with the "
                + "provided item description and the matching item with the soonest expiration date "
                + "is at index 0.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items
      // passed as
      // input
      String[][] originalItems = new String[][] { { "Water", "1" }, { "Chocolate", "10" }, { "Juice", "20" },
          { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 2. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 3. Next item to be dispensed is at the end of the array
    {
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "20" }, { "Juice", "20" },
          { "Water", "5" }, { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Chocolate", items, itemsCount) != 6) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did not return "
                + "the expected output when the vending machines contains multiple matches with the "
                + "provided item description and the matching item with the soonest expiration date "
                + "is at the end of the array");
        return false;
      }
      // Check that the method did not make change to the contents of the array items
      // passed as
      // input
      String[][] originalItems = new String[][] { { "Water", "15" }, { "Chocolate", "20" }, { "Juice", "20" },
          { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 3. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    // 4. Next item to be dispensed is at the middle of the array
    {
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" },
          { "Water", "5" }, { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;

      // check the correctness of the output
      if (VendingMachine.getIndexNextItem("Water", items, itemsCount) != 3) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did not return "
                + "the expected output when the vending machines contains matches with the provided "
                + "item description with different expiration dates.");
        return false;
      }
      // Check that the method did not make change to the contents of the array items
      // passed as
      // input
      String[][] originalItems = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" },
          { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      if (!Arrays.deepEquals(items, originalItems)) {
        System.out.println(
            "testGetIndexNextItem-scenario 4. Problem detected: Your getIndexNextItem did make "
                + "changes to the content of the array passed as input.");
        return false;
      }
    }

    return true; // No bug detected
  }

  /**
   * Checks the correctness of containsItem defined in the VendingMachine class.
   * This method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testContainsItem() {

    // 1.The item is in the vending machine
    {
      // Check the method can return true about the item in the vending machine
      // input
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.containsItem("Water", items, itemsCount) != true) {
        System.out.println("testContainsItem-scenario 1. Problem detected: Your ContainsItem" +
            "did not return the item that is in the vending machine.");
        return false;
      }
    }

    // 2.The item is not in the vending machine
    {
      // Check the method can return false about the item is not in the vending
      // machine
      // input
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.containsItem("Banana", items, itemsCount) == true) {
        System.out.println("testContainsItem-scenario 2. Problem detected: Your ContainsItem" +
            "returned the item that is not in the vending machine.");
        return false;
      }
    }
    return true; // No bug detected
  }

  /**
   * Checks the correctness of getItemAtIndexdefined in the VendingMachine class.
   * This method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testGetItemAtIndex() {
    // 1. The index is out of bound
    {
      // Check the method will return "ERROR INVALID INDEX" when the input index is
      // out of bound
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getItemAtIndex(8, items, itemsCount).equals("ERROR INVALID INDEX") != true) {
        System.out.println("testGetItemAtIndex-scenario 1. Problem detected: Your GetItemAtIndex" +
            " does not return ERROR INVALID INDEX when the index is out of the bound");
        return false;
      }
    }
    // 2. The index is in bounds
    {
      // Check the method will return the description when the input index is in bound
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getItemAtIndex(0, items, itemsCount).equals("Water (15)") != true) {
        System.out.println("testGetItemAtIndex-scenario 2. Problem detected: Your GetItemAtIndex" +
            "does not return the corresponding description when the index is in the bound");
        return false;
      }
    }
    return true; // No bug detected
  }

  /**
   * Checks the correctness of getItemOccurrences in the VendingMachine class.
   * This method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testGetItemsOccurrences() {
    // 1. no match found so that the method returns zero
    {
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getItemOccurrences("Banana", items, itemsCount) != 0) {
        System.out.println("testGetItemAtIndex-scenario 1. Problem detected: Your getItemOccurrences" +
            "does not return zero when the item is not found");
        return false;
      }
    }

    // 2. the items array contains multiple occurrences of the provided item
    // description.
    {
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getItemOccurrences("Water", items, itemsCount) != 3) {
        System.out.println("testGetItemAtIndex-scenario 2. Problem detected: Your getItemOccurrences" +
            "does not return the correct number of occurence of the item");
        return false;
      }
    }
    return true; // No bug detected
  }

  /**
   * Checks the correctness of addItem in the VendingMachine class. This method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testAddItem() {
    // 1.adding a new item to an empty vending machine whose size is zero (provided
    // itemsCount == 0)
    {
      String[][] items = new String[10][2];
      int itemsCount = 0;
      // check the correctness of the output
      if (VendingMachine.addItem("Water", "15", items, itemsCount) != 1) {
        System.out.println("testAddItem-scenario 1. Problem detected: Your addItem" +
            "does not return one when the first item is added to the empty array");
        return false;
      }
    }

    // 2.adding a new item to a non-empty non-full vending machine
    {
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" }, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.addItem("Water", "17", items, itemsCount) != 8) {
        System.out.println("testAddItem-scenario 2. Problem detected: Your addItem" +
            "does not return the value of itemCount+1  when the first item is added to the non-empty" +
            " and non full vending machine");
        return false;
      }
    }

    // 3.adding a new item to a full vending machine where the provided itemsCount
    // equals the
    // length of the provided items array.
    {
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "15" }, { "Chocolate", "10" } };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.addItem("Water", "17", items, itemsCount) != 7) {
        System.out.println("testAddItem-scenario 3. Problem detected: Your addItem" +
            "does not return the value of itemCount  when the array is full");
        return false;
      }
    }

    // For each tester scenario, check for the expected returned size of the vending
    // machine and
    // the expected content of the items array after the method call returns.
    return true; // no bug detected
  }

  /**
   * Checks the correctness of removeNextItem in the VendingMachine class. This
   * method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testRemoveNextItem() {
    // 1. Remove a non-existing item from a vending machine
    {
      String[][] items = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" }, null, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.removeNextItem("Banana", items, itemsCount) != 7) {
        System.out.println("testAddItem-scenario 1. Problem detected: Your removeNextItem" +
            "does not return the value of itemCount  when the item is not existing");
        return false;
      }
      // Check the content of the array after nothing is removed
      String[][] itemsCheck = new String[][] { { "Water", "15" }, { "Chocolate", "10" }, { "Juice", "20" },
          { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" }, null, null, null };
      for (int i = 0; i < 7; i++) {
        if (itemsCheck[i][0].equals(items[i][0]) == true && itemsCheck[i][1].equals(items[i][1]) == true) {
          continue;
        } else {
          System.out.println("testAddItem-scenario 1. Problem detected: Your array after removeNextItem " +
              "does not fit the correct array");
          return false;
        }
      }
      if (itemsCheck[7] != null) {
        return false;
      }
    }

    // 2. the next item to be removed matching the provided description is at index
    // 0 of the array
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" }, null, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.removeNextItem("Juice", items, itemsCount) != 6) {
        System.out.println("testAddItem-scenario 2. Problem detected: Your removeNextItem" +
            "does not return the reduced itemCount  when the item is at index 0 of the array");
        return false;
      }
      // Check the content of the array after the first one is removed
      String[][] itemsCheck2 = new String[][] { { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" }, null, null, null };
      for (int i = 0; i < 6; i++) {
        if (itemsCheck2[i][0].equals(items[i][0]) == true && itemsCheck2[i][1].equals(items[i][1]) == true) {
          continue;
        } else {
          System.out.println("testAddItem-scenario 2. Problem detected: Your array after removeNextItem " +
              "at the index 0 of array does not fit the correct array");
          return false;
        }
      }
      if (itemsCheck2[6] != null) {
        return false;
      }
    }

    // 3. the next item to be removed is at index itemsCount of the array (at the
    // end of the array)
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "20" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "15" }, { "Chocolate", "10" } };
      int itemsCount = 8;
      // check the correctness of the output
      if (VendingMachine.removeNextItem("Chocolate", items, itemsCount) != 7) {
        System.out.println("testAddItem-scenario 3. Problem detected: Your removeNextItem" +
            "does not return the reduced itemCount  when the item is at end of the array");
        return false;
      }
      // Check the content of the array after the last one is removed
      String[][] itemsCheck3 = new String[][] { { "Juice", "15" }, { "Chocolate", "20" }, { "Juice", "20" },
          { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "15" }, null };
      for (int i = 0; i < 7; i++) {
        if (itemsCheck3[i][0].equals(items[i][0]) == true && itemsCheck3[i][1].equals(items[i][1]) == true) {
          continue;
        } else {
          System.out.println("testAddItem-scenario 3. Problem detected: Your array after removeNextItem " +
              "at the end of array does not fit the correct array");
          return false;
        }
      }
      if (itemsCheck3[7] != null) {
        return false;
      }
    }

    // 4.the next item to be removed is at a middle index of the provided items
    // array.
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" } };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.removeNextItem("Water", items, itemsCount) != 6) {
        System.out.println("testAddItem-scenario 4. Problem detected: Your removeNextItem" +
            "does not return the reduced itemCount  when the item is at middle of the array");
        return false;
      }
      // Check the content of the array after the middle one is removed
      String[][] itemsCheck3 = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Juice", "20" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" } };
      for (int i = 0; i < 6; i++) {
        if (itemsCheck3[i][0].equals(items[i][0]) == true && itemsCheck3[i][1].equals(items[i][1]) == true) {
          continue;
        } else {
          System.out.println("testAddItem-scenario 4. Problem detected: Your array after removeNextItem" +
              "at the middle of the array does not fit the correct array");
          return false;
        }
      }
    }
    return true; // no bug detected
  }

  /**
   * Checks the correctness of getItemsSummary in the VendingMachine class. This
   * method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testGetItemsSummary() {
    // 1.the vending machine is empty
    {
      String[][] items = new String[10][2];
      int itemsCount = 0;
      // check the correctness of the output
      if (VendingMachine.getItemsSummary(items, itemsCount).equals("") != true) {
        System.out.println("testAddItem-scenario 1. Problem detected: Your getItemsSummary" +
            "does not return the corresponding string when the vending machine is empty");
        return false;
      }
    }

    // 2.the vending machine contains non duplicate items (no multiple items with
    // the same description)
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Bread", "20" } };
      int itemsCount = 3;
      // check the correctness of the output
      if (VendingMachine.getItemsSummary(items, itemsCount).equals("Juice (1)\nChocolate (1)\nBread (1)") != true) {
        System.out.println("testAddItem-scenario 2. Problem detected: Your getItemsSummary" +
            "does not return the corresponding string when the vending machine contains non duplicate items");
        return false;
      }
    }

    // 3.the vending machine contains multiple items with the same description at
    // various index locations.
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Chocolate", "15" },
          { "Bread", "20" }, { "Juice", "10" } };
      int itemsCount = 5;
      // check the correctness of the output
      if (VendingMachine.getItemsSummary(items, itemsCount).equals("Juice (2)\nChocolate (2)\nBread (1)") != true) {
        System.out.println("testAddItem-scenario 3. Problem detected: Your getItemsSummary" +
            "does not return the corresponding string when the vending machine contains duplicate items in various places");
        return false;
      }
    }

    return true; // no bug detected
  }

  /**
   * Checks the correctness of getItemsOccurrencesByExpirationDate in the
   * VendingMachine class. This method
   * returns true if the test verifies a correct functionality and false if any
   * bug is detected
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean testGetItemsOccurrencesByExpirationDate() {

    // 1. the vending machine does not contain the given item when the description
    // does not exist but expirationData exists
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" }, null, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getItemsOccurrencesByExpirationDate("Bread", "10", items, itemsCount) != 0) {
        System.out.println(
            "testAGetItemsOccurrencesByExpirationDate-scenario 1. Problem detected: Your getItemsOccurrencesByExpirationDate"
                +
                "does not return the 0 when item description is not existing");
        return false;
      }
    }

    // 2. the vending machine does contain the given item when the description does
    // exist but expirationData does not exist
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" }, null, null, null };
      int itemsCount = 7;
      // check the correctness of the output
      if (VendingMachine.getItemsOccurrencesByExpirationDate("Water", "11", items, itemsCount) != 1) {
        System.out.println(
            "testAGetItemsOccurrencesByExpirationDate-scenario 2. Problem detected: Your getItemsOccurrencesByExpirationDate"
                +
                "does not return the 1 when item description is existing but the expirationdate is not the same aa the ones in the array");
        return false;
      }
    }

    // 3. the vending machine does contain the given item when the description does
    // exist and expirationData does exist
    {
      String[][] items = new String[][] { { "Juice", "15" }, { "Chocolate", "10" }, { "Juice", "20" }, { "Water", "5" },
          { "Candy", "30" }, { "Water", "17" }, { "Chocolate", "10" }, { "Water", "25" }, null, null };
      int itemsCount = 8;
      // check the correctness of the output
      if (VendingMachine.getItemsOccurrencesByExpirationDate("Water", "5", items, itemsCount) != 3) {
        System.out.println(
            "testAGetItemsOccurrencesByExpirationDate-scenario 3. Problem detected: Your getItemsOccurrencesByExpirationDate"
                +
                "does not return the 3 when item description is existing");
        return false;
      }
    }
    return true; // no bug detected
  }

  /**
   * This method returns false if any of the tester methods defined in this class
   * fails, and true
   * if no bug detected.
   * 
   * @return true if all the scenerios are passed
   */
  public static boolean runAllTests() {
    if (testGetIndexNextItem() == true && testContainsItem() == true && testGetItemAtIndex() == true
        && testGetItemsOccurrences() == true &&
        testAddItem() == true && testRemoveNextItem() == true && testGetItemsSummary() == true
        && testGetItemsOccurrencesByExpirationDate() == true) {
      return true; // no bug detected
    }
    return false;
  }

  // main method to call the tester methods defined in this class
  public static void main(String[] args) {
    System.out.println("testGetIndexNextItem(): " + testGetIndexNextItem());
    System.out.println("testContainsItem(): " + testContainsItem());
    System.out.println("testGetItemAtIndex(): " + testGetItemAtIndex());
    System.out.println("testGetItemOccurrences: " + testGetItemsOccurrences());
    System.out.println("testAddItem(): " + testAddItem());
    System.out.println("testRemoveNextItem(): " + testRemoveNextItem());
    System.out.println("testGetItemsSummary(): " + testGetItemsSummary());
    System.out.println("testGetItemsOccurrencesByExpirationDate(): " + testGetItemsOccurrencesByExpirationDate());
    System.out.println("runAllTests(): " + runAllTests());
  }

}
