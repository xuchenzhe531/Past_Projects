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


import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This class implements testers to check the correctness of the methods implemented in p04
 * Exceptional Vending Machine
 */
public class ExceptionalVendingMachineTester {
  /**
   * Checks the correctness of the constructor of the class Item when passed invalid inputs
   *
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorNotValidInput() {
    try {
      Item item = new Item(" ", -1);
      System.out.println(
          "testItemConstructorNotValidInput-scenario 1. Problem detected: Your " +
              "Constructor should throw IllegalArgumentException when the " +
              "inputs are invalid");
      return false;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(
          "testItemConstructorNotValidInput-scenario 1. Problem detected: Your " +
              "Constructor should throw IllegalArgumentException when the " +
              "inputs are invalid");
      return false;
    }
  }

  /**
   * Checks the correctness of the constructor of the class Item, Item.getDescription(),
   * Item.getExpirationDate(), Item.setDescription(), and Item.toString() when passed valid inputs
   *
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemConstructorGettersSetters() {
    try {
      Item item = new Item("Coke", 1);

      // 1. Check the correctness of getDescription method
      if (item.getDescription().equals("Coke") != true) {
        System.out.println(
            "testItemConstructorGettersSetters-scenario 1. Problem detected: Your " +
                "getDescription did not return the correct string as expected");
        return false;
      }

      // 2. Check the correctness of getExpirationDate method
      if (item.getExpirationDate() != 1) {
        System.out.println(
            "testItemConstructorGettersSetters-scenario 2. Problem detected: Your " +
                "getExpirationDate did not return the correct int as expected");
        return false;
      }

      // 3. Check the correctness of setDescription method
      item.setDescription("Cocacola");
      if (!item.getDescription().equals("Cocacola")) {
        System.out.println(
            "testItemConstructorGettersSetters-scenario 3. Problem detected: Your " +
                "setDescription did not change the item description to the correct " +
                "description as expected");
        return false;
      }

      // 4. Check the correctness of toString method
      if (!item.toString().equals("Cocacola: 1")) {
        System.out.println(
            "testItemConstructorGettersSetters-scenario 4. Problem detected: Your " +
                "toString did not return the correct string as expected");
        return false;
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;// No bug detected
  }


  /**
   * Checks the correctness of the Item.equals() method. You should consider at least the following
   * four scenarios. (1) Create an item with valid description and expiration date, comparing it to
   * itself should return true. (2) Two items having the same description but different expiration
   * dates should be equal. (3) Passing a null reference to the Item.equals() method should return
   * false. (4) An item MUST NOT be equal to an object NOT instance of the class Item, for instance
   * a string object.
   *
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testItemEquals() {
    try {
      // 1. item with valid description and expiration date, comparing it to itself should return
      // true.
      Item item = new Item("Coke", 1);
      if (item.equals(item) != true) {
        System.out.println(
            "testItemEquals-scenario 1. Problem detected: Your equals did not return true " +
                "when item with valid inputs compare to itself");
        return false;
      }

      // 2. Two items having the same description but different expiration dates should be equal.
      Item item1 = new Item("Coke", 2);
      if (item.equals(item1) != true) {
        System.out.println(
            "testItemEquals-scenario 2. Problem detected: Your equals did not return true " +
                "when two items have the same description but different expiration dates");
        return false;
      }

      // 3. Passing a null reference to the Item.equals() method should return false.
      if (item.equals(null) != false) {
        System.out.println(
            "testItemEquals-scenario 3. Problem detected: Your equals did not return false " +
                "when passing a null reference to the equals method");
        return false;
      }

      // 4. An item MUST NOT be equal to an object NOT instance of the class Item, for instance a
      //string object.
      if (item.equals("myName") != false) {
        System.out.println(
            "testItemEquals-scenario 4. Problem detected: Your equals did not return false " +
                "when item compare to an object NOT instance of the class Item");
        return false;
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;// No bug detected
  }


  /**
   * Checks the correctness of the constructor of the ExceptionalVendingMachine when passed invalid
   * input
   *
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineConstructor() {
    try {
      ExceptionalVendingMachine vand = new ExceptionalVendingMachine(-10);
      System.out.println(
          "testExceptionalVendingMachineConstructor-scenario 1. Problem detected: Your " +
              "Constructor should throw IllegalArgumentException when the inputs are invalid");
      return false;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(
          "testExceptionalVendingMachineConstructor-scenario 1. Problem detected: Your " +
              "Constructor should throw IllegalArgumentException when the inputs are invalid");
      return false;
    }
  }

  /**
   * Checks the correctness of the following methods defined in the ExceptionalVendingMachine class
   * when an exception is expected to be thrown:
   *
   * addItem(), containsItem(), getIndexNextItem(), getItemAtIndex(), getItemOccurrences(),
   * getItemOccurrencesByExpirationDate(), removeNextItem().
   *
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testExceptionalVendingMachineAddContainsRemoveGetters() {
    try {
      ExceptionalVendingMachine vend = new ExceptionalVendingMachine(1);
      // 1-1 check the correctness of addItem method
      // when the description is null
      try {
        vend.addItem(null, 0);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 1-1. Problem " +
                "detected: Your addItem did not throw IllegalArgumentException when " +
                "the description is null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 1-2 check the correctness of addItem method
      // when the description is blank
      try {
        vend.addItem("", 0);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 1-2. Problem " +
                "detected: Your addItem did not throw IllegalArgumentException when " +
                "the description is blank");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 1-3 check the correctness of addItem method
      // when the expirationDate is negative
      try {
        vend.addItem("Cake", -1);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 1-3. Problem " +
                "detected: Your addItem did not throw IllegalArgumentException when " +
                "the expirationDate is negative");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 1-4 check the correctness of addItem method
      // when the vending machine is full
      try {
        vend.addItem("Tea", 1); // Tea:1 is now in the vending machine
        vend.addItem("Bread", 1);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 1-4. Problem " +
                "detected: Your addItem did not throw IllegalStateException when " +
                "the vending machine is full");
        return false;
      } catch (IllegalStateException e) {
        e.printStackTrace();
      }

      // 2-1 check the correctness of containsItem method
      // when the description is null
      try {
        vend.containsItem(null);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 2-1. Problem " +
                "detected: Your containsItem did not throw IllegalArgumentException when " +
                "the description is null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 2-2 check the correctness of containsItem method
      // when the description is blank
      try {
        vend.containsItem("");
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 2-2. Problem " +
                "detected: Your containsItem did not throw IllegalArgumentException when " +
                "the description is blank");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 3-1 check the correctness of getIndexNextItem method
      // when the description is null
      try {
        vend.getIndexNextItem(null);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 3-1. Problem " +
                "detected: Your getIndexNextItem did not throw IllegalArgumentException when " +
                "the description is null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 3-2 check the correctness of getIndexNextItem method
      // when the description is blank
      try {
        vend.getIndexNextItem("");
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 3-2. Problem " +
                "detected: Your getIndexNextItem did not throw IllegalArgumentException when " +
                "the description is blank");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 3-3 check the correctness of getIndexNextItem method
      // when the item is not in the vending machine
      try {
        vend.getIndexNextItem("Water");
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 3-3. Problem " +
                "detected: Your getIndexNextItem did not throw NoSuchElementException when " +
                "the item is not in the vending machine");
        return false;
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      }

      // 4 check the correctness of getItemAtIndex method
      // when the index is out of bounds
      try {
        vend.getItemAtIndex(5);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 4. Problem " +
                "detected: Your getItemAtIndex did not throw IndexOutOfBoundsException when " +
                "the index is out of bounds");
        return false;
      } catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
      }

      // 5-1 check the correctness of getItemOccurences method
      // when description is null
      try {
        vend.getItemOccurrences(null);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 5-1. Problem " +
                "detected: Your getItemOccurrences did not throw IllegalArgumentException when " +
                "the description is null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 5-2 check the correctness of getItemOccurences method
      // when description is blank
      try {
        vend.getItemOccurrences("");
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 5-2. Problem " +
                "detected: Your getItemOccurrences did not throw IllegalArgumentException when " +
                "the description is blank");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 6-1 check the correctness of getItemOccurrencesByExpirationDate method
      // when description is null
      try {
        vend.getItemOccurrencesByExpirationDate(null, 0);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 6-1. Problem " +
                "detected: Your getItemOccurrencesByExpirationDate did not throw " +
                "IllegalArgumentException when the description is null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 6-2 check the correctness of getItemOccurrencesByExpirationDate method
      // when description is blank
      try {
        vend.getItemOccurrencesByExpirationDate("", 0);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 6-2. Problem " +
                "detected: Your getItemOccurrencesByExpirationDate did not throw " +
                "IllegalArgumentException when the description is blank");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 6-3 check the correctness of getItemOccurrencesByExpirationDate method
      // when expirationDate is negative
      try {
        vend.getItemOccurrencesByExpirationDate("Tea", -1);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 6-3. Problem " +
                "detected: Your getItemOccurrencesByExpirationDate did not throw " +
                "IllegalArgumentException when the expirationDate is negative");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 7-1 check the correctness of removeNextItem method
      // when description is null
      try {
        vend.removeNextItem(null);
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 7-1. Problem " +
                "detected: Your removeNextItem did not throw IllegalArgumentException " +
                "when the description is null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 7-2 check the correctness of removeNextItem method
      // when description is blank
      try {
        vend.removeNextItem("");
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 7-2. Problem " +
                "detected: Your removeNextItem did not throw IllegalArgumentException " +
                "when the description is blank");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // 7-3 check the correctness of removeNextItem method
      // when description item is not in the vending machine
      try {
        vend.removeNextItem("Water");
        System.out.println(
            "testExceptionalVendingMachineAddContainsRemoveGetters-scenario 7-3. Problem " +
                "detected: Your removeNextItem did not throw NoSuchElementException " +
                "when the description item is not in the vending machine");
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;// No bug detected
  }

  /**
   * Checks the correctness of isEmpty(), size(), and isFull() methods defined in the
   * ExceptionalVendingMachine class
   *
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testEmptySizeFullExceptionalVendingMachine() {
    try {
      ExceptionalVendingMachine vend = new ExceptionalVendingMachine(1);
      // 1. Check the correctness of isEmpty(), size(), and isFull() methods
      // when the Vending machine is empty
      {
        if (vend.isEmpty() != true) {
          System.out.println(
              "testEmptySizeFullExceptionalVendingMachine-scenario 1-1. Problem " +
                  "detected: Your isEmpty did not return true when the Vending machine is empty");
          return false;
        }

        if (vend.isFull() != false) {
          System.out.println(
              "testEmptySizeFullExceptionalVendingMachine-scenario 1-2. Problem " +
                  "detected: Your isFull did not return false when the Vending machine is empty");
          return false;
        }

        if (vend.size() != 0) {
          System.out.println(
              "testEmptySizeFullExceptionalVendingMachine-scenario 1-3. Problem " +
                  "detected: Your size did not return the correct size as expected" +
                  "when the Vending machine is empty");
          return false;
        }
      }

      vend.addItem("Coke", 0);
      // 2. Check the correctness of isEmpty(), size(), and isFull() methods
      // when the Vending machine is full
      {
        if (vend.isEmpty() == true) {
          System.out.println(
              "testEmptySizeFullExceptionalVendingMachine-scenario 2-1. Problem " +
                  "detected: Your isEmpty did not return false when the Vending machine is full");
          return false;
        }

        if (vend.isFull() == false) {
          System.out.println(
              "testEmptySizeFullExceptionalVendingMachine-scenario 2-2. Problem " +
                  "detected: Your isFull did not return true when the Vending machine is full");
          return false;
        }
        if (vend.size() != 1) {
          System.out.println(
              "testEmptySizeFullExceptionalVendingMachine-scenario 2-3. Problem " +
                  "detected: Your size did not return the correct size as expected" +
                  "when the Vending machine is full");
          return false;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;// No bug detected
  }

  /**
   * Checks the correctness of loadOneItem method with respect to its specification. Consider at
   * least the four following scenarios. (1) Successful scenario for loading one item with a valid
   * string representation to a non-full vending machine. (2) Unsuccessful scenario for passing null
   * or a blank string (for instance one space or empty string) to the loadOneItem() method call, an
   * IllegalArgumentEXception is expected to be thrown. (3) Unsuccessful scenario for passing a
   * badly formatted string to the loadOneItem method. A DataFormatException is expected to be
   * thrown. (4) Unsuccessful scenario for trying to load an item with a valid representation to a
   * full vending machine. An IllegalStateException is expected to be thrown.
   *
   * @return true if the test verifies a correct functionality and false if any bug is detected
   */
  public static boolean testLoadOneItem() {
    try {
      ExceptionalVendingMachine vend = new ExceptionalVendingMachine(2);
      //  1. Successful scenario for loading one item with a valid
      //  string representation to a non-full vending machine.
      {
        vend.loadOneItem("Cake:2");
        if (!vend.getItemAtIndex(0).equals("Cake: 2")) {
          System.out.println(
              "testLoadOneItem-scenario 1. Problem detected: Your loadOneItem did not load the " +
                  "correct item to the vending machine as expected");
          return false;
        }
      }

      // 2. Unsuccessful scenario for passing null
      //    or a blank string (for instance one space or empty string) to the loadOneItem()
      //    method call, an IllegalArgumentException is expected to be thrown.
      {
        try {
          vend.loadOneItem(null);
          System.out.println(
              "testLoadOneItem-scenario-scenario 2-1. Problem detected: " +
                  "Your loadOneItem did not throw IllegalArgumentException " +
                  "when the itemRepresentation is null");
          return false;
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        }

        try {
          vend.loadOneItem(" ");
          System.out.println(
              "testLoadOneItem-scenario-scenario 2-2. Problem detected: " +
                  "Your loadOneItem did not throw IllegalArgumentException " +
                  "when the itemRepresentation is blank");
          return false;
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        }
      }

      // 3. Unsuccessful scenario for passing a
      //    badly formatted string to the loadOneItem method.
      //    A DataFormatException is expected to be thrown.
      {
        try {
          vend.loadOneItem(":Cake 3");
          System.out.println(
              "testLoadOneItem-scenario-scenario 3. Problem detected: " +
                  "Your loadOneItem did not throw DataFormatException " +
                  "when passing a badly formatted string");
          return false;
        } catch (DataFormatException e) {
          e.printStackTrace();
        }
      }

      // 4. Unsuccessful scenario for trying to load an item with
      // a valid representation to a full vending machine.
      // An IllegalStateException is expected to be thrown.
      {
        try {
          vend.loadOneItem("Tea: 2");// The vending machine is full
          vend.loadOneItem("Water: 3");
          System.out.println(
              "testLoadOneItem-scenario-scenario 4. Problem detected: " +
                  "Your loadOneItem did not throw IllegalStateException " +
                  "when load an item with a valid representation to a full vending machine");
          return false;
        } catch (IllegalStateException e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true; // No bug detected
  }

  /**
   * Invokes all the public tester methods implemented in this class
   *
   * @return true if all testers pass with no errors, and false if any of the tester fails.
   */
  public static boolean runAllTests() {
    if (testItemConstructorNotValidInput() && testItemConstructorGettersSetters() &&
        testItemEquals() && testExceptionalVendingMachineConstructor() &&
        testExceptionalVendingMachineAddContainsRemoveGetters() &&
        testEmptySizeFullExceptionalVendingMachine() && testLoadOneItem()) {
      return true; // No bug detected
    }
    return false;
  }

  /**
   * Main method for the tester class
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("Tests " + runAllTests());
  }

}
