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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an iterator to iterate over incorrectly answered MultipleChoiceQuestion(s) stored in a
 * singly linked list defined by its head.
 */
public class IncorrectQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  private LinkedNode<MultipleChoiceQuestion> next;
  // refers to a node in the singly linked list of MultipleChoiceQuestion

  /**
   * Creates a new InCorrectQuestionsIterator to start iterating through a singly linked list
   * storing MultipleChoiceQuestion elements
   *
   * @param startNode pointer to the head of the singly linked list
   */
  public IncorrectQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    next = startNode;
    while(next != null && next.getData().isCorrect()){
      // find the first node with incorrect answer
      next = next.getNext();
    }
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s) answered incorrectly.
   *
   * @return true if there are more incorrect MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext() {
    return next != null; //Return true if the next node is not empty
  }

  /**
   * Returns the next incorrect MultipleChoiceQuestion in this iteration
   *
   * @return the next incorrect MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more correct
   *                                questions in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (!hasNext()) {
      //If the next node is empty, throw the exception
      throw new NoSuchElementException("No more questions with incorrect answer!");
    }
    MultipleChoiceQuestion value = next.getData();
    do {
      next = next.getNext();// get the next node with an incorrect answer
    } while (next != null && next.getData().isCorrect());
    return value;
  }
}
