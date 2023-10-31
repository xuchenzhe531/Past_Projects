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
// Online Sources:  
// [1]https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
// The code from this website helps me to have a clear undertanding about how to check
// the validity of a binary search tree recursively
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * This class implements a ChugidexStorage as a Binary Search Tree. Notes: 1) You may NOT use any
 * arrays or Collections objects (ArrayLists, etc) in this class. 2) You may NOT use any loops
 * (for, while, etc) in this class. Recursive strategies only.
 */
public class ChugiTree implements ChugidexStorage {

  // The root of this ChugiTree. Set to null when tree is empty.
  private BSTNode<Chugimon> root;

  //The size of this ChugiTree (total number of Chugimon stored in this BST)
  private int size;

  /**
   * Constructor for Chugitree. Creates an empty ChugiTree (sets size to 0 and root to null).
   */
  public ChugiTree() {
    root = null;
    size = 0;
  }

  /**
   * A helper method for determining whether a BST rooted at node is a valid BST. In order to be a
   * valid BST, the following must be true: For every internal (non-leaf) node of a binary tree,
   * all the values in a node's left subtree are less than the values in a node's right subtree.
   *
   * @param node The root of the BST.
   * @return true if the binary tree rooted at node is a BST, false otherwise
   */
  public static boolean isValidBSTHelper(BSTNode<Chugimon> node) {
    // [1]https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
    // The code from this website helps me to have a clear understanding about how to check
    // the validity of a binary search tree recursively
    if (node == null) {
      return true;
    }
    //If the left subtree is not null, then recursively, validate the BT
    if (node.getLeft() != null && node.getData()
        .compareTo(findMostRightNode(node.getLeft()).getData()) <= 0) {
      return false;
    }
    //If the right subtree is not null, then recursively, validate the BT
    if (node.getRight() != null && node.getData()
        .compareTo(findMostLeftNode(node.getRight()).getData()) >= 0) {
      return false;
    }
    //If one of the side is not a valid BST, then return false
    return isValidBSTHelper(node.getLeft()) && isValidBSTHelper(node.getRight());
  }

  /**
   * A private helper method to find the most right node of the root
   *
   * @param node the root of BST
   * @return the node have no more right node
   */
  private static BSTNode<Chugimon> findMostRightNode(BSTNode<Chugimon> node) {
    if (node.getRight() != null) {
      //Call findMostRightNode(node.getRight()) to get the most left node
      return findMostRightNode(node.getRight());
    } else {
      //Return original value if it is null
      return node;
    }
  }

  /**
   * A private helper method to find the most left node of the root
   *
   * @param node the root of BST
   * @return the node have no more left node
   */
  private static BSTNode<Chugimon> findMostLeftNode(BSTNode<Chugimon> node) {
    if (node.getLeft() != null) {
      //Call findMostLeftNode(node.getLeft) to get the most left node
      return findMostLeftNode(node.getLeft());
    } else {
      //Return original value if it is null
      return node;
    }
  }

  /**
   * Recursive helper method which returns a String representation of the ChugiTree rooted at node.
   * An example of the String representation of the contents of a ChugiTree storing three Chugimons
   * is provided in the description of the above toString() method.
   *
   * @param node references the root of a subtree
   * @return a String representation of all the Chugimons stored in the sub-tree rooted at node in
   * increasing order. Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Chugimon> node) {
    if (node == null) {
      //Return "" the node is null
      return "";
    }
    //If the node is not null, recursively call StringHelper to get the string in-order traverse
    return toStringHelper(node.getLeft()) + node.getData() + "," + toStringHelper(node.getRight());
  }

  /**
   * Recursive helper method to insert a new Chugimon to a Pokedex rooted at node.
   *
   * @param node        The "root" of the subtree we are inserting the new Chugimon into.
   * @param newChugimon The Chugimon to be added to a BST rooted at node. We assume that
   *                    newChugimon is NOT null.
   * @return true if the newChugimon was successfully added to the ChugiTree, false if a match with
   * newChugimon is already present in the subtree rooted at node.
   */
  protected static boolean addHelper(Chugimon newChugimon, BSTNode<Chugimon> node) {
    int compare = newChugimon.compareTo(node.getData());
    //Compare to the current node
    if (compare > 0) {
      if (node.getRight() == null) {
        //If it is greater than the node and the next right one is null
        node.setRight(new BSTNode<>(newChugimon));
        return true;
      } else {
        return addHelper(newChugimon, node.getRight());
      }
    } else if (compare < 0) {
      if (node.getLeft() == null) {
        //If it is less than the node and the next left one is null
        node.setLeft(new BSTNode<>(newChugimon));
        return true;
      } else {
        return addHelper(newChugimon, node.getLeft());
      }
    } else {
      // if newChugimon is equal to the node then it cannot be added
      return false;
    }
  }

  /**
   * Recursive helper method to search and return a match with a given Chugimon in the subtree
   * rooted at node, if present.
   *
   * @param toFind a Chugimon to be searched in the BST rooted at node. We assume that toFind is
   *               NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match to target.
   * @return a reference to the matching Chugimon if found, null otherwise.
   */
  protected static Chugimon lookupHelper(Chugimon toFind, BSTNode<Chugimon> node) {
    if (node == null) {
      // if target not found return null
      return null;
    }
    int compare = toFind.compareTo(node.getData());
    if (compare > 0) {
      // if target is bigger than current node then search the right node
      return lookupHelper(toFind, node.getRight());
    } else if (compare < 0) {
      // if target is smaller than current node then search the left node
      return lookupHelper(toFind, node.getLeft());
    } else {
      // if target is found then return the data
      return node.getData();
    }
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at node counting the
   * number of nodes and NOT the number of edges from node to the deepest leaf
   *
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  protected static int heightHelper(BSTNode<Chugimon> node) {
    if (node == null) {
      return 0;
    } else {
      //Call itself recursively to get the height of BST
      int left = heightHelper(node.getLeft());
      int right = heightHelper(node.getRight());
      if (left > right) {
        return left + 1;
      } else {
        return right + 1;
      }
    }
  }

  /**
   * Recursive helper method for getFirst().
   *
   * @param root the node from which to find the  minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getFirstHelper(BSTNode<Chugimon> root) {
    if (root.getLeft() != null) {
      //Call itself resursively to get the smallest value in BST
      return getFirstHelper(root.getLeft());
    } else {
      return root.getData();
    }
  }

  /**
   * Recursive helper method for getLast().
   *
   * @param root the node from which to find the the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getLastHelper(BSTNode<Chugimon> root) {
    if (root.getRight() != null) {
      //Call itself recursively to get the greatest value in BST
      return getLastHelper(root.getRight());
    } else {
      return root.getData();
    }
  }

  /**
   * Recursive helper method to find and return the next Chugimon in the tree rooted at node.
   *
   * @param chugi a Chugimon to search its in-order successor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param next  a BSTNode which stores a potential candidate for next node
   * @return the next Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order successor in the subtree rooted at node.
   */
  protected static Chugimon nextHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> next) {
    if (node == null) {
      //with a descriptive error message if the Chugimon provided as
      //input has no in-order successor in the subtree rooted at node.
      throw new NoSuchElementException("nextHelper: chugi not found");
    }
    int compare = chugi.compareTo(node.getData());
    if (compare > 0) {
      return nextHelper(chugi, node.getRight(), next);
    } else if (compare < 0) {
      return nextHelper(chugi, node.getLeft(), node);
    } else {
      if (node.getRight() != null) {
        return getFirstHelper(node.getRight());
      } else if (node.getRight() == null && next != null) {
        return next.getData();
      } else {
        //with a descriptive error message if the Chugimon provided as
        //input has no in-order successor in the subtree rooted at node.
        throw new NoSuchElementException("nextHelper: there is no in-order successor");
      }
    }
  }

  /**
   * Recursive helper method to find and return the previous Chugimon in the tree rooted at node.
   *
   * @param chugi a Chugimon to search its in-order predecessor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param prev  a BSTNode which stores a potentional candidate for previous node
   * @return the previous Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order predecessor in the subtree rooted at
   *                                node.
   */
  protected static Chugimon previousHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> prev) {
    if (node == null) {
      //with a descriptive error message if the Chugimon provided as
      //input has no in-order successor in the subtree rooted at node.
      throw new NoSuchElementException("previousHelper: chugi not found");
    }
    int compare = chugi.compareTo(node.getData());
    if (compare > 0) {
      return previousHelper(chugi, node.getRight(), node);
    } else if (compare < 0) {
      return previousHelper(chugi, node.getLeft(), prev);
    } else {
      if (node.getLeft() != null) {
        return getLastHelper(node.getLeft());
      } else if (node.getLeft() == null && prev != null) {
        return prev.getData();
      } else {
        //with a descriptive error message if the Chugimon provided as
        //input has no in-order successor in the subtree rooted at node.
        throw new NoSuchElementException("previousHelper: there is no in-order predecessor");
      }
    }
  }

  /**
   * Recursive helper method to search and delete a specific Chugimon from the BST rooted at node
   *
   * @param target a reference to a Chugimon to delete from the BST rooted at node. We assume that
   *               target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match with the
   *               target Chugimon.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Chugimon
   *                                matching target in the BST rooted at <b>node</b>
   */
  protected static BSTNode<Chugimon> deleteChugimonHelper(Chugimon target,
      BSTNode<Chugimon> node) {
    if (node == null) {
      //with a descriptive error message if there is no Chugimon
      //matching target in the BST rooted at <b>node</b>
      throw new NoSuchElementException("deleteChugimonHelper: match not found");
    }
    int compare = target.compareTo(node.getData());
    if (compare > 0) {
      //If it is greater than the node and the next right one is null
      node.setRight(deleteChugimonHelper(target, node.getRight()));
    } else if (compare < 0) {
      //If it is less than the node and the next left one is null
      node.setLeft(deleteChugimonHelper(target, node.getLeft()));
    } else {
      //Recursively to search and delete a specific Chugimon from the BST rooted at node
      if (node.getLeft() == null && node.getRight() == null) {
        node = null;
      } else if (node.getLeft() != null && node.getRight() == null) {
        node = node.getLeft();
      } else if (node.getLeft() == null && node.getRight() != null) {
        node = node.getRight();
      } else {
        // replace the node data with successor's data then deleted successor
        BSTNode<Chugimon> successor = findMostLeftNode(node.getRight());
        node = new BSTNode<>(successor.getData(), node.getLeft(), node.getRight());
        node.setRight(deleteChugimonHelper(successor.getData(), node.getRight()));
      }
    }
    return node;
  }

  /**
   * Recursive helper method for countType()
   *
   * @param chugiType the type need to count
   * @param root the root from which to find the type
   * @return the number of match type node
   */
  private static int countTypeHelper(ChugiType chugiType, BSTNode<Chugimon> root) {
    if (root == null) {
      return 0;
    }
    //Get primary type and Secondary type
    ChugiType primaryType = root.getData().getPrimaryType();
    ChugiType secondaryType = root.getData().getSecondaryType();
    //Recursively counting the number of given type
    if (primaryType.equals(chugiType) || secondaryType.equals(chugiType)) {
      return countTypeHelper(chugiType, root.getLeft()) + countTypeHelper(chugiType,
          root.getRight()) + 1;
    } else {
      return countTypeHelper(chugiType, root.getLeft()) + countTypeHelper(chugiType,
          root.getRight());
    }
  }

  /**
   * Returns a String representation of all the Chugimons stored within this ChugiTree in the
   * increasing order with respect to the result of Chugimon.compareTo() method. The string should
   * be a comma-separated list of all the Chugimon toString() values. No spaces are expected to be
   * in the resulting string. No comma should be at the end of the resulting string. For instance,
   * "nameOne#12.25,nameTwo#12.56,nameTwo#89.27"
   *
   * @return a string containing all of the Chugimon, in the increasing order. Returns an empty
   * string "" if this BST is empty.
   */
  @Override
  public String toString() {
    if (size == 0) {
      return "";
    }
    //Call toStringHelper to get the string
    String returnValue = toStringHelper(root);
    //Cut the last comma
    return returnValue.substring(0, returnValue.length() - 1);
  }

  /**
   * Adds a new Chugimon to this ChugiTree. Duplicate Chugimons are NOT allowed.
   *
   * @param newChugimon Chugimon to add to this ChugiTree
   * @return true if the newChugimon was successfully added to the ChugiTree, false if a match with
   * newChugimon is already present in the tree.
   * @throws IllegalArgumentException with a descriptive error message if newChugimon is null.
   */
  @Override
  public boolean add(Chugimon newChugimon) {
    if (newChugimon == null) {
      //with a descriptive error message if newChugimon is null.
      throw new IllegalArgumentException("add: newChugimon is null");
    }
    if (root == null) {
      //Check whether the root is null
      root = new BSTNode<>(newChugimon);
      size++;
      return true;
    }
    //Call the helper to get the result
    if (addHelper(newChugimon, root)) {
      size++;
      return true;
    }
    return false;
  }

  /**
   * Deletes a specific Chugimon from this ChugiTree.
   *
   * @param chugi the Chugimon to delete
   * @return true if the specific Chugimon is successfully deleted, false if no match found with
   * any Chugimon in this tree.
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   */
  @Override
  public boolean delete(Chugimon chugi) {
    if (chugi == null) {
      //with a descriptive error message if <b>chugi</b> is null
      throw new IllegalArgumentException("delete: chugi is null");
    }
    try {
      //Deletes a specific Chugimon from this ChugiTree.
      root = deleteChugimonHelper(chugi, root);
      size--;
      return true;
    } catch (NoSuchElementException e) {
      // if no match found or root is null
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Recursive method to get the number of Chugimon with a primary or secondary type of the
   * specified type, stored in this ChugiTree.
   *
   * @param chugiType the type of Chugimons to count. We assume that chugiType is NOT null.
   * @return the number of all the Chugimon objects with a primary or secondary type of the
   * specified type stored in this ChugiTree.
   */
  public int countType(ChugiType chugiType) {
    return countTypeHelper(chugiType, root);
  }

  /**
   * Getter method for the Chugimon at the root of this BST.
   *
   * @return the root of the BST.
   */
  public Chugimon getRoot() {
    if (root == null) {
      return null;
    }
    return root.getData();
  }

  /**
   * A helper method for determining whether this ChugiTree is a valid BST. In order to be a valid
   * BST, the following must be true: For every internal (non-leaf) node X of a binary tree, all
   * the values in the node's left subtree are less than the value in X, and all the values in the
   * node's right subtree are greater than the value in X.
   *
   * @return true if this ChugiTree is a valid BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(root);
  }

  /**
   * Checks whether this ChugiTree is empty or not
   *
   * @return true if this tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Gets the size of this ChugiTree
   *
   * @return the total number of Chugimons stored in this tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Searches a Chugimon given its first and second identifiers.
   *
   * @param firstId  First identifier of the Chugimon to find
   * @param secondId Second identifier of the Chugimon to find
   * @return the matching Chugimon if match found, null otherwise.
   */
  @Override
  public Chugimon lookup(int firstId, int secondId) {
    Chugimon toFind = new Chugimon(firstId, secondId);
    return lookupHelper(toFind, root);
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   *
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive method to find and return the first Chugimon, in the increasing order, within this
   * ChugiTree (meaning the smallest element stored in the tree).
   *
   * @return the first element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getFirst() {
    if (size == 0) {
      return null;
    }
    return getFirstHelper(root);
  }

  /**
   * Recursive method to find and return the last Chugimon, in the increasing order, within this
   * ChugiTree (meaning the greatest element stored in the tree).
   *
   * @return the last element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getLast() {
    if (size == 0) {
      return null;
    }
    return getLastHelper(root);
  }


  /**
   * Finds and returns the in-order successor of a specified Chugimon in this ChugiTree
   *
   * @param chugi the Chugimon to find its successor
   * @return the in-order successor of a specified Chugimon in this ChugiTree
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   with a descriptive error message if the Chugimon provided as
   *                                  input has no in-order successor in this ChugiTree.
   */
  @Override
  public Chugimon next(Chugimon chugi) {
    if (chugi == null) {
      //with a descriptive error message if the Chugimon provided as
      //input has no in-order successor in this ChugiTree.
      throw new IllegalArgumentException("next: chugi is null");
    }
    return nextHelper(chugi, root, null);
  }

  /**
   * Finds and returns the in-order predecessor of a specified Chugimon in this ChugiTree
   *
   * @param chugi the Chugimon to find its predecessor
   * @return the in-order predecessor of a specified Chugimon in this ChugiTree.
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   if there is no Chugimon directly before the provided Chugimon
   */
  @Override
  public Chugimon previous(Chugimon chugi) {
    if (chugi == null) {
      // if there is no Chugimon directly before the provided Chugimon
      throw new IllegalArgumentException("previous: chugi is null");
    }
    return previousHelper(chugi, root, null);
  }

}
