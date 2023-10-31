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
 * This is an iterator that moves through MultipleChoiceQuestion(s) in a singly linked list defined
 * by its head
 */
public class QuizQuestionsIterator implements Iterator<MultipleChoiceQuestion> {
  private LinkedNode<MultipleChoiceQuestion> next;
  //refers to a node in the singly linked list of MultipleChoiceQuestion

  /**
   * Creates a new QuizQuestionsIterator to start iterating through a singly linked list storing
   * MultipleChoiceQuestion elements
   *
   * @param startNode pointer to the head of the singly linked list
   */
  public QuizQuestionsIterator(LinkedNode<MultipleChoiceQuestion> startNode) {
    next = startNode; //Set the next node
  }

  /**
   * Returns true if this iteration has more MultipleChoiceQuestion(s).
   *
   * @return true if there are more MultipleChoiceQuestion(s) in this iteration
   */
  @Override
  public boolean hasNext() {
    return next != null; //return next node
  }

  /**
   * Returns the next MultipleChoiceQuestion in this iteration
   *
   * @return the next MultipleChoiceQuestion in this iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more questions
   *                                in this iteration
   */
  @Override
  public MultipleChoiceQuestion next() throws NoSuchElementException {
    if (!hasNext()) {
      //throw exception if the next node is null
      throw new NoSuchElementException("No more questions!");
    }
    MultipleChoiceQuestion value = next.getData();
    next = next.getNext(); // get next node
    return value;
  }
}
