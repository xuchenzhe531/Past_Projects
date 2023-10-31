//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Chugidex
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
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the Chugimon
 * and ChugiTree classes.
 *
 * @author Chenzhe Xu, Jim Yi
 */

public class ChugidexTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Chugimon class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonCompareToEquals() {
    try {
      Chugimon test1 = new Chugimon(29, 134);
      Chugimon test2 = new Chugimon(29, 134);
      Chugimon test3 = new Chugimon(30, 135);

      if (test1.compareTo(test2) != 0) {
        System.out.println("testChugimonCompareToEquals 1: unexpected output");
        return false;
      }

      if (test1.compareTo(test3) >= 0) {
        System.out.println("testChugimonCompareToEquals 2: unexpected output");
        return false;
      }

      if (test1.equals(test3)) {
        System.out.println("testChugimonCompareToEquals 3: unexpected output");
        return false;
      }

      if (!test1.equals(test2)) {
        System.out.println("testChugimonCompareToEquals 4: unexpected output");
        return false;
      }
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the implementation of Chugimon.toString() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testChugimonToString() {
    try {
      Chugimon test1 = new Chugimon(1, 2);
      Chugimon test2 = new Chugimon(1, 3);

      if (!test1.toString().equals("Bulbysaur#1.2")) {
        System.out.println("testChugimonToString 1: unexpected output");
        return false;
      }

      if (!test2.toString().equals("Bulbusaur#1.3")) {
        System.out.println("testChugimonToString 2: unexpected output");
        return false;
      }
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the implementation of ChugiTree.isValidBSTHelper() method. This
   * tester should consider at least three scenarios. (1) An empty tree whose root is null should
   * be a valid BST. (2) Consider a valid BST whose height is at least 3. Create the tree using the
   * constructors of the BSTNode and its setters methods, (3) Consider a NON-valid BST where the
   * search order property is violated at at least one internal node.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIsValidBSTHelper() {
    try {
      // (1) An empty tree whose root is null should be a valid BST.
      if (!ChugiTree.isValidBSTHelper(null)) {
        System.out.println("testIsValidBSTHelper 1: unexpected output");
        return false;
      }

      // (2) Consider a valid BST whose height is at least 3. Create the tree using
      // the
      // constructors of the BSTNode and its setters methods,
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      BSTNode<Chugimon> testNode1 = new BSTNode<>(test1);
      BSTNode<Chugimon> testNode2 = new BSTNode<>(test2);
      BSTNode<Chugimon> testNode3 = new BSTNode<>(test3);
      BSTNode<Chugimon> testNode4 = new BSTNode<>(test4);
      BSTNode<Chugimon> testNode5 = new BSTNode<>(test5);
      BSTNode<Chugimon> testNode6 = new BSTNode<>(test6);
      testNode1.setLeft(testNode2);
      testNode1.getLeft().setLeft(testNode4);
      testNode1.getLeft().setRight(testNode3);
      testNode1.setRight(testNode5);
      testNode1.getRight().setLeft(testNode6);
      if (!ChugiTree.isValidBSTHelper(testNode1)) {
        System.out.println("testIsValidBSTHelper 2: unexpected output");
        return false;
      }

      // (3) Consider a NON-valid BST where the
      // search order property is violated at  least one internal node.
      BSTNode<Chugimon> testNode7 = new BSTNode<>(test1);
      BSTNode<Chugimon> testNode8 = new BSTNode<>(test2);
      BSTNode<Chugimon> testNode9 = new BSTNode<>(test3);
      BSTNode<Chugimon> testNode10 = new BSTNode<>(test4);
      BSTNode<Chugimon> testNode11 = new BSTNode<>(test5);
      BSTNode<Chugimon> testNode12 = new BSTNode<>(test6);
      testNode7.setLeft(testNode11);
      testNode7.setRight(testNode8);
      testNode7.getRight().setLeft(testNode12);
      testNode11.setRight(testNode9);
      testNode9.setRight(testNode10);
      if (ChugiTree.isValidBSTHelper(testNode7)) {
        System.out.println("testIsValidBSTHelper 3: unexpected output");
        return false;
      }

      //（4）Test for two duplicated nodes
      BSTNode<Chugimon> testNode13 = new BSTNode<>(test1);
      BSTNode<Chugimon> testNode14 = new BSTNode<>(test2);
      BSTNode<Chugimon> testNode15 = new BSTNode<>(test1);
      testNode13.setLeft(testNode14);
      testNode13.setRight(testNode15);
      if (ChugiTree.isValidBSTHelper(testNode13)) {
        System.out.println("testIsValidBSTHelper 4: unexpected output");
        return false;
      }
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks the correctness of the implementation of both add() and toString() methods implemented
   * in the ChugiTree class. This unit test considers at least the following scenarios. (1) Create
   * a new empty ChugiTree, and check that its size is 0, it is empty, and that its string
   * representation is an empty string "". (2) try adding one Chugimon and then check that the
   * add() method call returns true, the tree is not empty, its size is 1, and the toString()
   * called on the tree returns the expected output. (3) Try adding another Chugimon which is
   * less than the Chugimon at the root, (4) Try adding a third Chugimon which is greater than
   * the one at the root, (5) Try adding at least two further Chugimons such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios,
   * and more, double check each time that size() method returns the expected value, the add
   * method call returns true, that the ChugiTree is a valid BST, and that the toString() method
   * returns the expected string representation of the contents of the binary search tree in an
   * increasing order from the smallest to the greatest Chugimon. (6) Try adding a Chugimon
   * already stored in the tree. Make sure that the add() method call returned false, and that
   * the size of the tree did not change.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddToStringSize() {
    try {
      // (1) Create a new empty ChugiTree, and check that its size is 0, it is empty,
      // and that its string
      // representation is an empty string "".
      try {
        ChugiTree tester0 = new ChugiTree();
        tester0.add(null);
        System.out.println("testIsValidBSTHelper 0: didn't throw an exception when add null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      ChugiTree tester1 = new ChugiTree();
      if (!tester1.toString().equals("") || tester1.size() != 0 || tester1.getRoot() != null) {
        System.out.println("testIsValidBSTHelper 1: didn't create the correct ChugiTree");
        return false;
      }

      // (2) try adding one Chugimon and then check that the add() method call returns
      // true, the tree is not empty,
      // its size is 1, and the toString() called on the tree returns the expected
      // output.
      Chugimon test1 = new Chugimon(11, 118);
      if (!tester1.add(test1) || !tester1.toString()
          .equals("Metadeen#11.118") || tester1.size() != 1) {
        System.out.println("testIsValidBSTHelper 2: didn't add the correct Chugimon");
        return false;
      }

      // (3) Try adding another Chugimon which is less than the Chugimon at the root
      Chugimon test2 = new Chugimon(10, 94);
      if (!tester1.add(test2) || !tester1.toString()
          .equals("Catergar#10.94,Metadeen#11.118") || tester1.size() != 2) {
        System.out.println("testIsValidBSTHelper 3: didn't add the correct Chugimon");
        return false;
      }

      // (4) Try adding a third Chugimon which is greater than the one at the root
      Chugimon test3 = new Chugimon(16, 96);
      if (!tester1.add(test3) || !tester1.toString()
          .equals("Catergar#10.94,Metadeen#11.118,Pidzee#16.96") || tester1.size() != 3) {
        System.out.println("testIsValidBSTHelper 4: didn't add the correct Chugimon");
        return false;
      }

      // (5) Try adding at least two further Chugimons such that one must be added at
      // the left
      // subtree, and the other at the right subtree. For all the above scenarios, and
      // more, double
      // check each time that size() method returns the expected value, the add method
      // call returns
      // true, that the ChugiTree is a valid BST, and that the toString() method
      // returns the expected
      // string representation of the contents of the binary search tree in an
      // increasing order from the
      // smallest to the greatest Chugimon.
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(43, 7);
      if (!tester1.add(test4) || !tester1.add(test5) || !tester1.toString().equals(
          "Blastgar#9.94,Catergar#10.94,Metadeen#11.118,Oddtle#43.7,Pidzee#16.96")
          || tester1.size() != 5) {
        System.out.println("testIsValidBSTHelper 5: didn't add the correct Chugimons");
        return false;
      }

      // (6) Try adding a Chugimon already stored in the tree. Make
      // sure that the add() method call returned false, and that the size of the tree
      // did not change.
      Chugimon test7 = new Chugimon(43, 7);
      if (tester1.add(test7) || tester1.size() != 5) {
        System.out.println("testIsValidBSTHelper 6: add the incorrect Chugimon");
        return false;
      }
      return true; // No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }

  /**
   * This method checks mainly for the correctness of the ChugiTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ChugiTree. Then, check that
   * calling the lookup() method on an empty ChugiTree returns false. (2) Consider a ChugiTree of
   * height 3 which contains at least 5 Chugimons. Then, try to call lookup() method to search for
   * a Chugimon having a match at the root of the tree. (3) Then, search for a Chugimon at the
   * right and left subtrees at different levels considering successful and unsuccessful search
   * operations. Make sure that the lookup() method returns the expected output for every method
   * call.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    try {
      // (1) Create a new ChugiTree. Then, check that
      // calling the lookup() method on an empty ChugiTree returns false.
      ChugiTree tester1 = new ChugiTree();
      if (tester1.lookup(3, 4) != null) {
        System.out.println("testLookup 1: unexpected output");
        return false;
      }

      // (2) Consider a ChugiTree of height 3 which contains at least 5 Chugimons.
      // Then, try to call lookup() method to search for a
      // Chugimon having a match at the root of the tree.
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      tester1.add(test1);
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      if (!tester1.lookup(11, 118).equals(test1)) {
        System.out.println("testLookup 2: unexpected output");
        return false;
      }

      // (3) Then, search for a Chugimon at the right
      // and left subtrees at different levels considering successful and unsuccessful
      // search
      // operations. Make sure that the lookup() method returns the expected output
      // for every method call.
      if (!tester1.lookup(9, 94).equals(test4)) {
        System.out.println("testLookup 3-1: unexpected output");
        return false;
      }
      if (!tester1.lookup(43, 7).equals(test6)) {
        System.out.println("testLookup 3-2: unexpected output");
        return false;
      }
      if (tester1.lookup(16, 94) != null) {
        System.out.println("testLookup 3-3: unexpected output");
        return false;
      }
      return true; // No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.countType() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCountType() {
    try {
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      ChugiTree tester1 = new ChugiTree();
      tester1.add(test1);
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      //checks for the correctness of ChugiTree.countType() method.
      ChugiType[] arr = ChugidexUtility.getChugimonTypes(14, 141);
      if (tester1.countType(arr[0]) != 3) {
        System.out.println("testCountType: unexpected output");
        return false;
      }
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Chugimon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height
   * of a ChugiTree with four levels for instance, is 4.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    try {
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      ChugiTree tester1 = new ChugiTree();

      // (1) ensures that the height of an empty Chugimon tree is zero.
      if (tester1.height() != 0) {
        System.out.println("testHeight 1: unexpected output");
        return false;
      }

      // (2) ensures that the height of a tree which consists of only one node is 1.
      tester1.add(test1);
      if (tester1.height() != 1) {
        System.out.println("testHeight 2: unexpected output");
        return false;
      }

      // (3) ensures that the height of a ChugiTree with four levels for instance, is
      // 4.
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      if (tester1.height() != 3) {
        System.out.println("testHeight 3: unexpected output");
        return false;
      }
      return true;// No bug detected
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.getFirst() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetFirst() {
    try {
      ChugiTree tester0 = new ChugiTree();
      if (tester0.getFirst() != null) {
        System.out.println("testGetFirst 1: unexpected output");
        return false;
      }
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      ChugiTree tester1 = new ChugiTree();
      tester1.add(test1);
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      //Checks for the correctness of ChugiTree.getFirst() method.
      if (!tester1.getFirst().equals(test4)) {
        System.out.println("testGetFirst 2: unexpected output");
        return false;
      }
      return true; // No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.getLast() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLast() {
    try {
      ChugiTree tester0 = new ChugiTree();
      if (tester0.getLast() != null) {
        System.out.println("testGetLast 1: unexpected output");
        return false;
      }
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      ChugiTree tester1 = new ChugiTree();
      tester1.add(test1);
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      //Checks for the correctness of ChugiTree.getLast() method.
      if (!tester1.getLast().equals(test5)) {
        System.out.println("testGetLast 2: unexpected output");
        return false;
      }
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.delete() method. This test must consider at least 3
   * test scenarios. (1) Remove a Chugimon that is at leaf node (2) Remove a Chugimon at non-leaf
   * node. For each of these scenarios, check that the size of the tree was decremented by one and
   * that the resulting ChugiTree is a valid BST, (3) ensures that the ChugiTree.delete() method
   * returns false when called on an Chugimon that is not present in the BST.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testDelete() {
    try {
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      Chugimon test7 = new Chugimon(43, 8);
      ChugiTree tester1 = new ChugiTree();
      //Test when size == 0
      if (tester1.delete(test7)) {
        System.out.println("testDelete 0: cannot delete null");
        return false;
      }

      tester1.add(test1);
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      // (1) Remove a Chugimon that is at leaf node
      if (!tester1.delete(test4) || tester1.size() != 5 || !tester1.isValidBST()) {
        System.out.println("testDelete 1: didn't deleted correctly");
        return false;
      }

      // (2) Remove a Chugimon at non-leaf
      // node. For each of these scenarios, check that the size of the tree was
      // decremented by one and
      // that the resulting ChugiTree is a valid BST
      if (!tester1.delete(test3) || tester1.size() != 4 || !tester1.isValidBST()) {
        System.out.println("testDelete 2: didn't deleted correctly");
        return false;
      }

      // (3) ensures that the ChugiTree.delete() method
      // returns false when called on an Chugimon that is not present in the BST.
      if (tester1.delete(test7) || tester1.size() != 4) {
        System.out.println("testDelete 3-1: didn't deleted correctly");
        return false;
      }

      //4 When the input is a null
      try {
        tester1.delete(null);
        System.out.println("testDelete 4: didn't throw exception when delete null");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }

      //5 remove root
      ChugiTree tester2 = new ChugiTree();
      tester2.add(test1);
      tester2.add(test2);
      tester2.add(test3);
      tester2.add(test4);
      tester2.add(test5);
      tester2.add(test6);
      if (!tester2.delete(test1) || tester2.size() != 5 || !tester2.isValidBST() || !tester2.getRoot().equals(test6)) {
        System.out.println("testDelete 5: didn't deleted correctly");
        return false;
      }
      return true; // No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }

  /**
   * Checks for the correctness of ChugiTree.next() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testNext() {
    try {
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      Chugimon test7 = new Chugimon(13, 7);
      ChugiTree tester1 = new ChugiTree();
      tester1.add(test1);
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      //Checks for the correctness of ChugiTree.next() method.
      if (!tester1.next(test4).equals(test2)) {
        System.out.println("testNext 1: unexpected output");
        return false;
      }
      //Checks for the correctness of ChugiTree.next() method.
      try {
        tester1.next(null);
        System.out.println("testNext 2: didn't throw exception");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }

      //Checks for the correctness of ChugiTree.next() method.
      try {
        tester1.next(test7);
        System.out.println("testNext 3: didn't throw exception");
        return false;
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      return true;// No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Checks for the correctness of ChugiTree.previous() method.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testPrevious() {
    try {
      Chugimon test1 = new Chugimon(11, 118);
      Chugimon test2 = new Chugimon(10, 94);
      Chugimon test3 = new Chugimon(14, 141);
      Chugimon test4 = new Chugimon(9, 94);
      Chugimon test5 = new Chugimon(16, 96);
      Chugimon test6 = new Chugimon(43, 7);
      Chugimon test7 = new Chugimon(13, 7);
      ChugiTree tester1 = new ChugiTree();
      tester1.add(test1);
      tester1.add(test2);
      tester1.add(test3);
      tester1.add(test4);
      tester1.add(test5);
      tester1.add(test6);
      //Checks for the correctness of ChugiTree.previous() method.
      if (!tester1.previous(test2).equals(test4)) {
        System.out.println("testPrevious 1: unexpected output");
        return false;
      }

      //Checks for the correctness of ChugiTree.previous() method.
      try {
        tester1.previous(null);
        System.out.println("testPrevious 2: didn't throw exception");
        return false;
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      //Checks for the correctness of ChugiTree.previous() method.
      try {
        tester1.previous(test7);
        System.out.println("testPrevious 3: didn't throw exception");
        return false;
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      return true; // No bug detected
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Calls the test methods
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testChugimonCompareToEquals: " + testChugimonCompareToEquals());
    System.out.println("testChugimonToString(): " + testChugimonToString());
    System.out.println("testIsValidBSTHelper(): " + testIsValidBSTHelper());
    System.out.println("testAddToStringSize(): " + testAddToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testCountType(): " + testCountType());
    System.out.println("testGetFirst(): " + testGetFirst());
    System.out.println("testGetLast(): " + testGetLast());
    System.out.println("testDelete(): " + testDelete());
    System.out.println("testNext(): " + testNext());
    System.out.println("testPrevious(): " + testPrevious());
  }

}
