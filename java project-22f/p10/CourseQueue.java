//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 CourseReg
// Course: CS 300 Fall 2022
//
// Author: Chenzhe Xu
// Email: cxu296@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __ Write-up states that pair programming is allowed for this assignment.
// __ We have both read and understand the course Pair Programming Policy.
// __ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: The helper methods are from PriorityQueue.java used by Professor Mouna Kacem in the lecture
// I use the helper methods to help me to write percolateup and percolatedown function
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the max-heap
 * invariant, so that the Course at the root should have the highest score, and all children always
 * have a score lower than or equal to their parent's.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>, PriorityQueueADT<Course> {

  // data fields
  private Course[] queue; // array max-heap of courses representing this priority queue
  private int size; // number of courses currently in this priority queue

  /**
   * Creates a new, empty CourseQueue with the given capacity
   * 
   * @param capacity the capacity of this CourseQueue
   * @throws IllegalArgumentException if the capacity is not a positive integer
   */
  public CourseQueue(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("CourseQueue() Error Detected: capacity is non-positive");
    }
    size = 0;
    queue = new Course[capacity];
  }

  /**
   * Returns a deep copy of this CourseQueue containing all of its elements in the same order. This
   * method does not return the deepest copy, meaning that you do not need to duplicate courses.
   * Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
   */
  public CourseQueue deepCopy() {
    Course[] copy = new Course[size];
    CourseQueue c = new CourseQueue(queue.length);
    // Deep Copy the array
    System.arraycopy(queue, 0, copy, 0, size);
    // Loop through each element in the array and add to the new CourseQueue
    for (int i = 0; i < size; i++) {
      c.enqueue(copy[i]);
    }
    return c;
  }

  /**
   * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
   * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY of
   * this queue.
   * 
   * @see CourseIterator
   * @return an Iterator for this CourseQueue
   */
  @Override
  public Iterator<Course> iterator() {
    CourseQueue copied = deepCopy();
    return new CourseIterator(copied);
  }

  ///////////////////////////// TODO: PRIORITY QUEUE METHODS //////////////////////////////////
  // Add the @Override annotation to these methods once this class implements PriorityQueueADT!

  /**
   * Checks whether this CourseQueue is empty
   * 
   * @return {@code true} if this CourseQueue is empty
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the size of this CourseQueue
   * 
   * @return the size of this CourseQueue
   */
  public int size() {
    return size;
  }

  /**
   * Adds the given Course to this CourseQueue and use the percolateUp() method to maintain max-heap
   * invariant of CourseQueue. Courses should be compared using the Course.compareTo() method.
   * 
   * 
   * @param toAdd Course to add to this CourseQueue
   * @throws NullPointerException  if the given Course is null
   * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
   */
  public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
    if (toAdd == null) {
      throw new NullPointerException("Enqueue Error Detected: the item to add is null");
    }
    if (size == queue.length) {
      throw new IllegalStateException("Enqueue Error Detected: the CourseQueue is full");
    }
    if (size == 0) {
      queue[size++] = toAdd;
    } else {
      queue[size++] = toAdd;
      // Recursively percolateup the element
      percolateUp(size - 1);
    }
  }

  /**
   * Removes and returns the Course at the root of this CourseQueue, i.e. the Course with the
   * highest priority. Use the percolateDown() method to maintain max-heap invariant of CourseQueue.
   * Courses should be compared using the Course.compareTo() method.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException with a descriptive error message if this CourseQueue is empty
   */
  public Course dequeue() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException("Denqueue Error Detected: the queue is null");
    }
    if (size == 1) {
      // If there is only one item, just return it
      Course returnValue = queue[0];
      queue[0] = null;
      size--;
      return returnValue;
    } else {
      // If the items are greater than 1, need percolate
      Course returnValue = queue[0];
      queue[0] = queue[size - 1];
      queue[size - 1] = null;
      size--;
      // Iteratively percolate the element
      percolateDown(0);
      return returnValue;
    }
  }

  /**
   * Returns the Course at the root of this CourseQueue, i.e. the Course with the highest priority.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException if this CourseQueue is empty
   */
  public Course peek() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException("Peek Error Detected: the queue is null");
    }
    return queue[0];
  }

  ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////

  /**
   * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If
   * the element at the given index does not violate the max-heap invariant (it is higher priority
   * than its children), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int index) throws IndexOutOfBoundsException {
    if (index > size - 1 || index < 0) {
      throw new IndexOutOfBoundsException("PercolateDown Error Detected: index out of bound");
    }
    // Iteratively loop through each element
    while (hasLeftChild(index)) {
      int leftIndex = getLeftChildIndex(index);
      int largestIndex = leftIndex;
      if (hasRightChild(index)) {
        int rightIndex = getRightChildIndex(index);
        // To get the current largest
        if (queue[rightIndex].compareTo(queue[leftIndex]) > 0) {
          largestIndex = rightIndex;
        }
      }
      if (queue[index].compareTo(queue[largestIndex]) < 0) {
        // Swap if it does not satisfy max-heap
        swap(index, largestIndex);
        index = largestIndex;
      } else {
        break;
      }
    }

  }

  /**
   * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element
   * at the given index does not violate the max-heap invariant (it is lower priority than its
   * parent), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int index) throws IndexOutOfBoundsException {
    if (index > size - 1 || index < 0) {
      throw new IndexOutOfBoundsException("PercolateUp Error Detected: index out of bound");
    }
    if (index != 0) {
      // Check odd or even of the index
      if (index % 2 == 0) {
        // if the index is even
        if (queue[index].compareTo(queue[(index / 2) - 1]) < 0) {
        } else if (queue[index].compareTo(queue[(index / 2) - 1]) > 0) {
          // Swap and perfolate up
          swap(index, (index / 2) - 1);
          percolateUp((index / 2) - 1);
        } else {

        }
      } else {
        // if the index is odd
        if (queue[index].compareTo(queue[(index / 2)]) < 0) {
        } else if (queue[index].compareTo(queue[(index / 2)]) > 0) {
          // Swap and perfolate up
          swap(index, (index / 2));
          percolateUp((index / 2));
        } else {

        }
      }
    } else {

    }
  }

  /**
   * Helper Method : Swaps the elements at indices i and j Citation: This helper method is from
   * PriorityQueue.java used by Professor Mouna Kacem in the lecture
   * 
   * @param i index to swap with
   * @param j index to swap with
   * @throws IndexOutOfBoundsException if i or j is not in the range 0..size-1
   */
  private void swap(int i, int j) throws IndexOutOfBoundsException {
    Course temp = queue[i];
    queue[i] = queue[j];
    queue[j] = temp;
  }

  /**
   * helper method Returns the index of the left child of the node at index p in the heap Citation:
   * This helper method is from PriorityQueue.java used by Professor Mouna Kacem in the lecture
   * 
   * @param p index of a node (parent aka internal node)
   * @return index of the left child of p
   */
  private int getLeftChildIndex(int p) {
    return 2 * p + 1;
  }

  /**
   * helper method Returns the index of the right child of the node at index p in the heap Citation:
   * This helper method is from PriorityQueue.java used by Professor Mouna Kacem in the lecture
   * 
   * @param p index of a node
   * @return index of the right child of p
   */
  private int getRightChildIndex(int p) {
    return 2 * p + 2;
  }

  /**
   * helper method Checks whether the node of index p has a left child or not
   * 
   * @param p index of a node/element stored in the heap
   * @return true if the node of index p has a left child, false otherwise
   */
  private boolean hasLeftChild(int p) {
    return getLeftChildIndex(p) < size;
  }

  /**
   * Checks whether the node of index p has a right child Citation: This helper method is from
   * PriorityQueue.java used by Professor Mouna Kacem in the lecture
   * 
   * @param p index of a particular node stored in the heap
   * @return true if the node of index p has a left child, false otherwise
   */
  private boolean hasRightChild(int p) {
    return getRightChildIndex(p) < size;
  }
  ////////////////////////////// PROVIDED: TO STRING ////////////////////////////////////

  /**
   * Returns a String representing this CourseQueue, where each element (course) of the queue is
   * listed on a separate line, in order from the highest priority to the lowest priority.
   * 
   * @author yiwei
   * @see Course#toString()
   * @see CourseIterator
   * @return a String representing this CourseQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Course c : this) {
      val.append(c).append("\n");
    }

    return val.toString().trim();
  }

}
