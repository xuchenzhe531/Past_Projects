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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class models an iterable singly-linked list data structure which stores elements of type
 * MultipleChoiceQuestion.
 */
public class ListQuizzer extends Object implements Iterable<MultipleChoiceQuestion> {
  private LinkedNode<MultipleChoiceQuestion> head; //Head of this singly linked list
  private ListingMode listingMode;
  //The listing mode of this list quizzer which defines which questions
  //are going to be listed while iterating through this list
  private int size; //Total number of MultipleChoiceQuestions stored in this ListQuizzer
  private LinkedNode<MultipleChoiceQuestion> tail; //Tail of this singly linked list

  /**
   * this constructor creates a new empty instance of ListQuizzer which contains zero elements and
   * sets its listing mode to be ListingMode.ALL by default.
   */
  public ListQuizzer() {
    size = 0; //Assign size to 0
    listingMode = ListingMode.ALL; //sets its listing mode to be ListingMode.ALL by default
  }

  /**
   * Checks whether this list is empty
   *
   * @return true if this list is empty and false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0; //Check whether the ListQuizzer is zero
  }

  /**
   * Gets the size of this list
   *
   * @return the size of this list
   */
  public int size() {
    return size; //Return size
  }

  /**
   * Sets the listing mode of this list to the one provided as input
   *
   * @param listingMode - listing mode to set
   */
  public void switchMode(ListingMode listingMode) {
    this.listingMode = listingMode; //Assign the listingMode
  }

  /**
   * Adds a specific MultipleChoiceQuestion to a given position of this list
   *
   * @param index    - position index where to add the newQuestion to this list
   * @param question - some MultipleChoiceQuestion to add to this list
   * @throws NullPointerException      - with a descriptive error message if newQuestion is null
   * @throws IndexOutOfBoundsException - with a descriptive error message if index is OUT of the
   *                                   range 0 .. size() inclusive
   */
  public void add(int index, MultipleChoiceQuestion question)
      throws NullPointerException, IndexOutOfBoundsException {
    if (question == null) {
      //If question is null, throw exception
      throw new NullPointerException("Add: newQuestion is null!");
    }
    if (index > size) {
      //If index is out of bound, throw exception
      throw new IndexOutOfBoundsException("Add: Index is out of bound!");
    }
    if (size == 0) {
      addFirst(question); // if the list is empty add to the head
    } else if (index == size) {
      addLast(question); // if index is size add to tail
    } else if (index == 0) {
      addFirst(question);// if index is 0 add to head
    } else {
      LinkedNode<MultipleChoiceQuestion> runner = head;
      int tracker = 0;
      while (tracker < index - 1) {
        // find the node before the index need to be added
        runner = runner.getNext();
        tracker++;
      }
      LinkedNode<MultipleChoiceQuestion> newNode = new LinkedNode<>(question);
      newNode.setNext(runner.getNext());// set new node point to the pre-index node
      runner.setNext(newNode); // set the node before the index point to new node
      size++; // increment size
    }
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the head of this list
   *
   * @param question - some MultipleChoiceQuestion to add to the head of this list
   * @throws NullPointerException - with a descriptive error message if newQuestion is null
   */
  public void addFirst(MultipleChoiceQuestion question) throws NullPointerException {
    if (question == null) {
      //throw exception is the question is null
      throw new NullPointerException("AddFirst: newQuestion is null!");
    }
    if (size == 0) {
      // if the list is empty, set head and tail to the new node
      head = new LinkedNode<MultipleChoiceQuestion>(question);
      tail = head;
    } else {
      LinkedNode<MultipleChoiceQuestion> newNode = new LinkedNode<>(question);
      newNode.setNext(head); // set new node point to current head
      head = newNode; // set head to new node
    }
    size++; // increment size
  }

  /**
   * Adds a specific MutlipleChoiceQuestion to the tail of this list
   *
   * @param question - some MultipleChoiceQuestion to add to the tail of this list
   * @throws NullPointerException - with a descriptive error message if newQuestion is null
   */
  public void addLast(MultipleChoiceQuestion question) throws NullPointerException {
    if (question == null) {
      //throw exception is the question is null
      throw new NullPointerException("AddLast: newQuestion is null!");
    }
    if (size == 0) {
      // if list is empty, set head and tail to the new node
      tail = new LinkedNode<MultipleChoiceQuestion>(question);
      head = tail;
    } else {
      LinkedNode<MultipleChoiceQuestion> newNode = new LinkedNode<>(question);
      tail.setNext(newNode); // set current tail point to new node
      tail = newNode; // set tail to new node
    }
    size++; // increment size
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the head of this list
   *
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException - with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeFirst() throws NoSuchElementException {
    if (size == 0) {
      //throw exception is size is 0
      throw new NoSuchElementException("Remove First: The list is empty!");
    }
    MultipleChoiceQuestion remove = head.getData(); // set remove value
    if (size == 1) {
      // if there is only 1 node in list then clear the list
      clear();
    } else {
      // set head to the node that pre-head node point to
      head = head.getNext();
      size--; // decrement size
    }
    return remove;// return the data of removed node
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the tail of this list
   *
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException - with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion removeLast() throws NoSuchElementException {
    if (size == 0) {
      //throw exception is size is 0
      throw new NoSuchElementException("RemoveLast: The list is empty!");
    }
    MultipleChoiceQuestion remove = tail.getData();// set remove value
    if (size == 1) {
      // if there is only 1 node in list then clear the list
      clear();
    } else {
      LinkedNode<MultipleChoiceQuestion> runner = head;
      while (!runner.getNext().equals(tail)) {
        // find the node that point to tail
        runner = runner.getNext();
      }
      runner.setNext(null);// make the tail-pointer node point to null
      tail = runner; // make tail to the pre tail-pointer node
      size--; // decrement size
    }
    return remove;// return the data of removed node
  }

  /**
   * This method removes all the question from this list. The list should be empty after this
   * method is called.
   */
  public void clear() {
    size = 0; //Reset size
    head = null; //Reset head
    tail = null; //Reset tail
  }

  /**
   * Checks whether this list contains a match with someQuestion
   *
   * @param someQuestion - some question to find
   * @return true if this list contains a match with someQuestion and false otherwise
   */
  public boolean contains(MultipleChoiceQuestion someQuestion) {
    if (someQuestion == null) {
      //throw exception if the input is null
      throw new NullPointerException("contains: someQuestion is null!");
    }
    LinkedNode<MultipleChoiceQuestion> runner = head;
    while (runner != null) {
      // the runner will go thought every node in the list until the end or found the question
      if (runner.getData().equals(someQuestion)) {
        // check runner node's date is equal to the question need to be found
        return true;
      }
      runner = runner.getNext(); // make runner node to next if not find
    }
    return false;
  }

  /**
   * Gets the MultipleChoiceQuestion at the head of this list
   *
   * @return the MultipleChoiceQuestion at the head of this list
   * @throws NoSuchElementException - with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getFirst() throws NoSuchElementException {
    if (size == 0) {
      //throw exception is size is 0
      throw new NoSuchElementException("getFirst: The list is empty!");
    }
    return head.getData(); //Return the first node
  }

  /**
   * Gets the MultipleChoiceQuestion at the tail of this list
   *
   * @return the MultipleChoiceQuestion at the tail of this list
   * @throws NoSuchElementException - with a descriptive error message if this list is empty
   */
  public MultipleChoiceQuestion getLast() throws NoSuchElementException {
    if (size == 0) {
      //throw exception is size is 0
      throw new NoSuchElementException("getLast: The list is empty!");
    }
    return tail.getData(); //Return the last node
  }
  /**
   * Gets the MultipleChoiceQuestion at the index
   *
   * @return the MultipleChoiceQuestion at the index
   * @throws IndexOutOfBoundsException - with the index is out of bound
   */
  public MultipleChoiceQuestion get(int index) throws IndexOutOfBoundsException {
    if (index > (size - 1) || index < 0) {
      //throw exception is size is less than 0 or out of bound
      throw new IndexOutOfBoundsException("Get: Index out of Bound!");
    }
    if (index == 0) {
      return getFirst(); // if index is 0 return head
    } else if (index == (size - 1)) {
      return getLast(); // if index is the last return tail
    } else {
      LinkedNode<MultipleChoiceQuestion> runner = head; //Set the initial 
      int tracker = 0;
      while (tracker < index) {
        // find the runner node until the tracker is equal to index
        runner = runner.getNext();
        tracker++;
      }
      return runner.getData();//Return the node at index
    } 
  }

  /**
   * Removes and returns the MultipleChoiceQuestion at the given index
   *
   * @param index - of the MultipleChoiceQuestion to remove
   * @return the removed MultipleChoiceQuestion
   * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the
   *                                   range 0 .. size()-1 inclusive
   */
  public MultipleChoiceQuestion remove(int index) throws IndexOutOfBoundsException {
    if (index > size() - 1 || index < 0) {
      //throw exception is size is less than 0 or out of bound
      throw new IndexOutOfBoundsException("Remove: Index out of Bound!");
    }
    if (index == 0) {
      return removeFirst(); // if index is 0 then remove the head
    } else if (index == size - 1) {
      return removeLast(); // if index is the last the remove the tail
    } else {
      LinkedNode<MultipleChoiceQuestion> runner = head;
      int tracker = 0;
      while (tracker < index - 1) {
        // find the node before the node at index which need to be removed
        runner = runner.getNext();
        tracker++;
      }
      LinkedNode<MultipleChoiceQuestion> remove = runner.getNext();// the node need to be removed
      runner.setNext(remove.getNext());
      // set the node before remove node point to the node after remove node point to
      size--; // decrement size
      return remove.getData(); // return the data of removed node
    }
  }

  /**
   * Calculates the total Calculates the total possible points of this ListQuizzer
   *
   * @return the score of this ListQuizzer
   */
  public int calculateTotalPoints() {
    if (size == 0) {
      //throw exception is size is 0
      throw new NoSuchElementException("The list is empty!");
    }
    LinkedNode<MultipleChoiceQuestion> runner = head;
    int totalPoints = 0;
    while (runner != null) {
      // add every points possible to totalPoints
      totalPoints += runner.getData().getPointsPossible();
      runner = runner.getNext();
    }
    return totalPoints;
  }

  /**
   * Calculates the total points of the correctly answered questions of this ListQuizzer
   *
   * @return the score of this ListQuizzer
   */
  public int calculateScore() {
    if (size == 0) {
      //throw exception is size is 0
      throw new NoSuchElementException("The list is empty!");
    }
    LinkedNode<MultipleChoiceQuestion> runner = head;
    int score = 0;
    while (runner != null) {
      if (runner.getData().isCorrect()) {
        // if the answer is correct, add points to score
        score += runner.getData().getPointsPossible();
      }
      runner = runner.getNext();
    }
    return score;
  }

  /**
   * Returns a deep copy of this list. This method creates a copy of this list as a new ListQuizzer
   * and adds deep copies of each MultipleChoiceQuestion stored in this list to the deep copy.
   *
   * @return a deep copy of this list.
   */
  public ListQuizzer copy() {
    ListQuizzer newQuiz = new ListQuizzer();
    LinkedNode<MultipleChoiceQuestion> runner = head;
    while (runner != null) {
      // add every node to the new List in order
      newQuiz.addLast(runner.getData());
      runner = runner.getNext();
    }
    return newQuiz;
  }

  /**
   * Loads MultipleChoiceQuestions from a file
   *
   * @param file file to read
   * @return the number of added MultipleChoiceQuestions to this list
   * @throws FileNotFoundException if the file is not found
   * @author Jeff and Mouna
   */
  public int loadQuestions(File file) throws FileNotFoundException {
    int loadedCount = 0;
    // try to read the file
    Scanner reader = null;
    try {
      reader = new Scanner(file);
      // parse the file lines line by line
      while (reader.hasNextLine()) {
        String title = reader.nextLine(); //Get title
        String question = reader.nextLine(); //Get question
        int answerCount = reader.nextInt(); //Get answercount
        String[] answerList = new String[answerCount]; //Get answerList
        int index = 0;
        while (!reader.hasNextInt()) {
          //Assign the answer to the array
          String answer = reader.nextLine();
          answerList[index] = answer;
          index++;
        }

        String line = reader.nextLine(); //Get next line
        Scanner lineScanner = new Scanner(line);
        int indexCorrectAnswer = lineScanner.nextInt();
        lineScanner.close();

        line = reader.nextLine();//Get next line
        lineScanner = new Scanner(line);
        int points = lineScanner.nextInt(); //Get the next integer value
        lineScanner.close(); //Close the Scanner

        MultipleChoiceQuestion quizQuestion =
            new MultipleChoiceQuestion(title, question, answerList, indexCorrectAnswer, points);

        addLast(quizQuestion); //Add the question to the tail
        loadedCount += 1;
      }
    } finally {
      if (reader != null)
        reader.close();
    }
    return loadedCount; //Return value
  }

  /**
   * Allows a user to take this quiz. The quiz should be taken on a deep copy of this ListQuizzer.
   * This method should not make any changes to the contents of this ListQuizzer.
   *
   * @return the instance of ListQuizzer taken by the user. It should include the user's responses.
   * @author Jeff and Mouna
   */
  public ListQuizzer takeQuiz() {
    ListQuizzer copy = this.copy(); //deep copy the list
    copy.switchMode(ListingMode.ALL); //Set the ListingMode
    Scanner input = new Scanner(System.in);
    for (MultipleChoiceQuestion question : copy) {
      System.out.println(question); //Print question
      System.out.print("Enter your answer: ");
      int entry = input.nextInt();  //Ask the user to enter the answer
      question.setCorrectAnswerIndex(entry - 1);
      if (question.isCorrect()) { //Check the correctness
        System.out.println("Correct!");
      } else { //Check correctness
        System.out.println("Incorrect!");
      }
    }
    int correctPoints = copy.calculateScore(); //Calculate the correct score
    int totalPoints = copy.calculateTotalPoints(); //Calculate the total score
    System.out.println("Your Score: " + correctPoints); //Print score
    System.out.println("Percentage: " + correctPoints / totalPoints); //print percentage
    input.close(); //Close th3e Scanner
    return copy;
  }

  /**
   * Returns an iterator to iterate through this list with respect to the listingMode. If the
   * listingMode is ALL, the returned iterator is initialized to the head of this list. If the
   * listingMode is CORRECT, the returned iterator is initialized to the node carrying first
   * correctly answered question If the listingMode is INCORRECT, the returned iterator is
   * initialized to the node carrying first incorrectly answered question
   *
   * @return an iterator to iterate through this list with respect to the listingMode of this list.
   */
  @Override
  public Iterator<MultipleChoiceQuestion> iterator() {
    if (listingMode.equals(ListingMode.CORRECT)) {
      LinkedNode<MultipleChoiceQuestion> runner = head;
      while (runner != null && !runner.getData().isCorrect()) {
        // find the first node carrying correctly answered question
        runner = runner.getNext();
      }
      // return iterator initialized to the node carrying first correctly answered question
      return new CorrectQuestionsIterator(runner);
    }
    if (listingMode.equals(ListingMode.INCORRECT)) {
      LinkedNode<MultipleChoiceQuestion> runner = head;
      while (runner != null && runner.getData().isCorrect()) {
        // find the first node carrying incorrectly answered question
        runner = runner.getNext();
      }
      // return iterator initialized to the node carrying first incorrectly answered question
      return new IncorrectQuestionsIterator(runner);
    }
    //return iterator initialized to the head of this list.
    return new QuizQuestionsIterator(head);
  }

  /**
   * Returns true if o is a ListQuizzer which has the exact same contents as this ListQuizzer
   *
   * @param o an object to compare with
   * @return true if o is instanceof ListQuizzer with the exact same contents as this ListQuizzer
   * @author Mouna
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof ListQuizzer) { //Check whether it is ListQuizzer
      ListQuizzer other = (ListQuizzer) o; //Casting
      if (this.size() != other.size()) //Check the data field
        return false;
      this.switchMode(listingMode.ALL); //Assign the Mode
      other.switchMode(listingMode.ALL); //Assign the Mode
      Iterator<MultipleChoiceQuestion> iterator = this.iterator();
      Iterator<MultipleChoiceQuestion> otherIterator = other.iterator();
      while (iterator.hasNext()) { //To iterate
        if (!iterator.next().equals(otherIterator.next()))
          return false;
      }
      return true;
    }
    return false;
  }
}
