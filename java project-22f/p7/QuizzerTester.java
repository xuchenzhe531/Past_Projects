//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Quizzer
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
 * This class checks the correctness of the implementation of cs300 Fall 2022 p07 Quizzer
 */
public class QuizzerTester {
  /**
   * Main method
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) throws Exception {
    System.out.println("runAllTests:" + runAllTests());
  }

  /**
   * Runs all the tester methods defined in this QuizzerTester
   *
   * @return true if all tests pass and false if any of the tests fails
   */
  public static boolean runAllTests() {
    return testMultipleChoiceQuestion() && testLinkedNode() && testCorrectQuestionsIterator() &&
        testInCorrectQuestionsIterator() && testQuizQuestionsIterator() && testAddLast() &&
        testRemoveLast() && testRemoveFirst() && testRemove() && testAdd() && testAddFirst();
  }

  /**
   * This method test and make use of the MultipleChoiceQuestion constructor, an accessor (getter)
   * method, overridden method toString() and equal() method defined in the MultipleChoiceQuestion
   * class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMultipleChoiceQuestion() {
    try {
      // pass an invalid correct answer
      try {
        //Set the first question
        String[] answers = {"Integer", "Array", "String"};
        MultipleChoiceQuestion first =
            new MultipleChoiceQuestion("CS300", "Which one is mutable?", answers, 5, 5);
        System.out.println(
            "testMultipleChoiceQuestion problem detected: Your constructor did " +
                "not throw IndexOutOfBoundsException when pass invalid correct answer");
        return false; //If the method does not throw the exception
      } catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
      }

      // pass an invalid possible points
      try {
        String[] answers = {"Integer", "Array", "String"};
        MultipleChoiceQuestion first =
            new MultipleChoiceQuestion("CS300", "Which one is mutable?", answers, 1, -1);
        System.out.println(
            "testMultipleChoiceQuestion problem detected: Your constructor did " +
                "not throw IllegalArgumentException when pass invalid possible points");
        return false; //If the method does not throw the exception
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }

      // pass valid value
      String[] answers = {"Integer", "Array"};
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answers, 1, 5);
      MultipleChoiceQuestion expectedQ =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answers, 1, 5);
      String expected =
          "QUESTION TITLE: " + "\"" + first.getTitle() + "\"" + "\n" + "Question:\n" +
              first.getQuestion() + "\n" + "Available Answers:\n" + first.getAnswers();
      //Check the contents
      if (!first.toString().equals(expected.trim()) || !first.equals(
          expectedQ) || first.getPointsPossible() != 5) {
        System.out.println(
            "testMultipleChoiceQuestion problem detected: Your constructor did " +
                "not create correct question when pass valid value");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * This method test and make use of the LinkedNode constructor, an accessor (getter) method, and
   * a mutator (setter) method defined in the LinkedNode class.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode() {
    try {
      // pass valid data
      LinkedNode<Integer> first = new LinkedNode<>(5);
      LinkedNode<Integer> second = new LinkedNode<>(8);
      first.setNext(second);
      //Check the contents
      if (!(first.getData() == 5) && !(first.getNext().getData() == 8)) {
        System.out.println(
            "testLinkedNode problem detected: Your constructor did not " +
                "create correct LinkedNode with expected data");
        return false;
      }

      // check setNext method
      first.setNext(second);
      //Check the contents
      if (!first.getNext().equals(second)) {
        System.out.println(
            "testLinkedNode problem detected: Your setNext did not set the correct next node");
        return false;//Return false if the values are not correct
      }

      // pass null data
      try {
        LinkedNode<Integer> third = new LinkedNode<>(null, null);
        System.out.println(
            "testLinkedNode problem detected: Your constructor did not " +
                "throw NullPointerException when pass null data");
        return false;
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; //Return false if the values are not correct
    }
    return true; // No bug detected
  }

  /**
   * This method checks for the correctness of CorrectQuestionsIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCorrectQuestionsIterator() {
    try {
      String[] answerOne = {"Integer", "Array", "String"};
      //Set the first question
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      first.setStudentAnswerIndex(1);
      ListQuizzer list1 = new ListQuizzer();
      list1.addLast(first);
      //Set the second question
      String[] answerTwo = {"List", "Array", "String"};
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      second.setStudentAnswerIndex(1);
      list1.addLast(second);
      String[] answerThree = {"1", "2", "3"};
      //Set the third question
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      three.setStudentAnswerIndex(2);
      list1.addLast(three);//Add it to the last
      list1.switchMode(ListingMode.CORRECT);
      CorrectQuestionsIterator iterator = (CorrectQuestionsIterator) list1.iterator();
      //Check the contents
      if (!iterator.hasNext() || !iterator.next().equals(first) || !iterator.next()
          .equals(three) || iterator.hasNext()) {
        System.out.println(
            "testCorrectQuestionsIterator problem detected " +
                "your CorrectQuestionsIterator did not return the expected value");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // no bug detected
  }

  /**
   * This method checks for the correctness of InCorrectQuestionsIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInCorrectQuestionsIterator() {
    try {
      String[] answerOne = {"Integer", "Array", "String"};
      //Set the first question
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      first.setStudentAnswerIndex(2);
      ListQuizzer list1 = new ListQuizzer();
      list1.addLast(first);
      String[] answerTwo = {"List", "Array", "String"};
      //Set the second question
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      second.setStudentAnswerIndex(2);
      list1.addLast(second);
      String[] answerThree = {"1", "2", "3"};
      //Set the third question
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      three.setStudentAnswerIndex(1);
      list1.addLast(three);//Add it to the last
      list1.switchMode(ListingMode.INCORRECT);//Set the Mode
      IncorrectQuestionsIterator iterator = (IncorrectQuestionsIterator) list1.iterator();
      //Check the contents
      if (!iterator.hasNext() || !iterator.next().equals(first) || !iterator.next()
          .equals(three) || iterator.hasNext()) {
        System.out.println(
            "testCorrectQuestionsIterator problem detected: your IncorrectQuestionsIterator " +
                "did not return the expected value");
        return false;//If the expected value is not returned, return false
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // no bug detected
  }

  /**
   * This method checks for the correctness of QuizQuestionsIterator class
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testQuizQuestionsIterator() {
    try {
      String[] answerOne = {"Integer", "Array", "String"};
      //Set the first question
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      ListQuizzer list1 = new ListQuizzer();
      list1.addLast(first);
      String[] answerTwo = {"List", "Array", "String"};
      //Set the second question
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      list1.addLast(second);
      String[] answerThree = {"1", "2", "3"};
      //Set the third question
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      list1.addLast(three);
      QuizQuestionsIterator iterator = (QuizQuestionsIterator) list1.iterator();
    //Check the content
      if (!iterator.hasNext() || !iterator.next().equals(first) || !iterator.next()
          .equals(second) || !iterator.next().equals(three) || iterator.hasNext()) {
        System.out.println(
            "testQuizQuestionsIterator problem detected: your QuizQuestionsIterator " +
                "did not return the expected value");
        return false;//If the expected value is not returned, return false
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // no bug detected
  }

  /**
   * Tester for ListQuizzer.addFirst() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddFirst() {
    try {
      // pass valid value
      String[] answerOne = {"Integer", "Array", "String"};
      //Set up the first qustion
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
          //Create a new listQuizzer
      ListQuizzer list1 = new ListQuizzer();
      list1.addFirst(first); //Add it to the first
      //Check the content
      if (list1.size() != 1 || list1.isEmpty() || !list1.get(0).equals(first)) {
        System.out.println(
            "testAddFirst problem detected: your addFirst did not add the correct " +
                "node to the list when pass a valid value");
        return false;//If the content is not correct return false
      }

      String[] answerTwo = {"List", "Array", "String"};
      //set the second question
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      list1.addFirst(second);
       //Check the content
      if (list1.size() != 2 || list1.isEmpty() || !list1.get(0).equals(second)) {
        System.out.println(
            "testAddFirst problem detected: your addFirst did not add the correct " +
                "node to the list when pass a valid value");
        return false;//If the content is not correct return false
      }

      // pass null
      try {
        list1.addFirst(null);
        System.out.println(
            "testAddFirst problem detected: your addFirst did not throw " +
                "NullPointerException when pass null");
        return false; //If the content is not correct return false
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; // No bug detected
  }

  /**
   * public static boolean testAddLast()
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddLast() {
    try {
      // pass valid value
      String[] answerOne = {"Integer", "Array", "String"};
      //Set up the first qustion
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      ListQuizzer list1 = new ListQuizzer(); //Create a new listQuizzer

      list1.addLast(first);
      //Check the content
      if (list1.size() != 1 || list1.isEmpty() || !list1.get(0).equals(first)) {
        System.out.println(
            "testAddLast problem detected: your addLast did not add the correct " +
                "node to the list when pass a valid value");
        return false;//If the content is not correct return false
      }

      String[] answerTwo = {"List", "Array", "String"};
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      list1.addLast(second);

      if (list1.size() != 2 || list1.isEmpty() || !list1.getLast()
          .equals(second) || !list1.getFirst().equals(first)) {
        System.out.println(
            "testAddLast problem detected: your addLast did not add the correct " +
                "node to the list when pass a valid value");
        return false;//If the content is not correct return false
      }

      String[] answerThree = {"1", "2", "3"};
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      list1.addLast(three);

      if (list1.size() != 3 || list1.isEmpty() || !list1.getLast()
          .equals(three) || !list1.getFirst().equals(first) || !list1.get(1).equals(second)) {
        System.out.println(
            "testAddLast problem detected: your addLast did not add the correct " +
                "node to the list when pass a valid value");
        return false; //If the content is not correct return false
      }

      // pass null
      try {
        list1.addLast(null);
        System.out.println(
            "testAddLast problem detected: your addLast did not throw " +
                "NullPointerException when pass null");
        return false;//If the content is not correct return false
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; //If the unexpected exception is thrown
    }
    return true; // No bug detected
  }

  /**
   * Tester for ListQuizzer.add() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAdd() {
    try {
      // pass valid value
      String[] answerOne = {"Integer", "Array", "String"};
       //Set the first question
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      ListQuizzer list1 = new ListQuizzer();
      list1.addLast(first);
      String[] answerTwo = {"List", "Array", "String"};
      //Set the second question
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      list1.addFirst(second);
      String[] answerThree = {"1", "2", "3"};
      //Set the second question
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      list1.add(1, three);
      //Check the condition
      if (!list1.get(1).equals(three) || !list1.get(0).equals(second) || !list1.get(2)
          .equals(first)) {
        System.out.println(
            "testAdd problem detected: your add did not add the correct " +
                "node to the list when pass a valid value");
        return false; //If the content is not correct return false
      }

      // pass invalid index
      try {
        list1.add(100, three);
        System.out.println(
            "testAdd problem detected: your add did not throw IndexOutOfBoundsException when " +
                "pass an invalid index");
        return false; //Return false if the expected exception is not thrown
      } catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
      }

      // pass null question
      try {
        list1.add(1, null);
        System.out.println(
            "testAdd problem detected: your add did not throw NullPointerException when pass " +
                "an invalid question");
        return false; //Return false if the expected exception is not thrown
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; //Return false if the expected exception is not thrown
    }
    return true; // no bug detected
  }

  /**
   * Tester for ListQuizzer.removeFirst() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveFirst() {
    try {
      // remove valid value
      String[] answerOne = {"Integer", "Array", "String"};
       //Set the first question
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      ListQuizzer list1 = new ListQuizzer();
      list1.addFirst(first);
      String[] answerTwo = {"List", "Array", "String"};
      //Set the second question
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      list1.addFirst(second);
      String[] answerThree = {"1", "2", "3"};
      //Set the third question
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      list1.add(0, three);
      //Check the content
      if (!list1.removeFirst().equals(three) || list1.size() != 2 || !list1.get(0)
          .equals(second) || !list1.get(1).equals(first)) {
        System.out.println(
            "testRemoveFirst problem detected: your removeFirst did not remove the correct node "
                + "as expected");
        return false;
      }
      //remove the rest
      list1.removeFirst();
      list1.removeFirst();

      // remove invalid value
      try {
        list1.removeFirst();
        System.out.println(
            "testRemoveFirst problem detected: your removeFirst did not throw " +
                "NoSuchElementException when remove invalid value");
        return false; //Return false if the expected exception is not thrown
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; //Return false if the unexpected exception is  thrown
    }
    return true; // No bug detected
  }

  /**
   * Tester for ListQuizzer.removeLast() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveLast() {
    try {
      // remove valid value
      String[] answerOne = {"Integer", "Array", "String"};
      //Set the first question
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      ListQuizzer list1 = new ListQuizzer();
      list1.addFirst(first);
      String[] answerTwo = {"List", "Array", "String"};
      //Set the second question
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      list1.addFirst(second);
      String[] answerThree = {"1", "2", "3"};
      //Set the third quesiton
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      list1.addFirst(three);
      //Check the content
      if (!list1.removeLast().equals(first) || !(list1.size() == 2) || !list1.get(0)
          .equals(three) || !list1.get(1).equals(second)) {
        System.out.println(
            "testRemoveLast problem detected: your removeLast did not remove the correct node "
                + "as expected");
        return false;
      }
      list1.removeFirst();
      list1.removeFirst();

      // remove invalid value
      try {
        list1.removeLast();
        System.out.println(
            "testRemoveLast problem detected: your removeLast did not throw " +
                "NoSuchElementException when remove invalid value");
        return false;//Return false if the expected exception is not thrown
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;//Return false if the unexpected exception is  thrown
    }
    return true; // No bug detected
  }

  /**
   * Tester for ListQuizzer.remove() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemove() {
    try {
      // remove valid value
      String[] answerOne = {"Integer", "Array", "String"};
      //Set the first question
      MultipleChoiceQuestion first =
          new MultipleChoiceQuestion("CS300", "Which one is mutable?", answerOne, 1, 5);
      ListQuizzer list1 = new ListQuizzer();
      list1.addFirst(first);
      String[] answerTwo = {"List", "Array", "String"};
       //Set the second question
      MultipleChoiceQuestion second =
          new MultipleChoiceQuestion("CS300", "Which one is immutable?", answerTwo, 2, 5);
      list1.addLast(second);
      String[] answerThree = {"1", "2", "3"};
      //Set the third question
      MultipleChoiceQuestion three =
          new MultipleChoiceQuestion("MATH", "Which one is the biggest?", answerThree, 2, 5);
      list1.addLast(three);
      //Check the contents
      if (!list1.remove(1).equals(second) || !(list1.size() == 2) || !list1.get(0)
          .equals(first) || !list1.get(1).equals(three)) {
        System.out.println(
            "testRemove problem detected: your remove did not remove the correct node " +
                "as expected");
        return false;
      }
      //Check the contents
      if (!list1.remove(0).equals(first) || !(list1.size() == 1) ||
          !list1.get(0).equals(three)) {
        System.out.println(
            "testRemove problem detected: your remove did not remove the correct node " +
                "as expected");
        return false;
      }

      // remove invalid index value
      try {
        list1.remove(100);
        System.out.println(
            "testRemove problem detected: your remove did not throw " +
                "IndexOutOfBoundsException when remove invalid index value");
        return false;//Return false if the expected exception is not thrown
      } catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;//Return false if the expected exception is not thrown
    }
    return true; // No bug detected
  }

}
