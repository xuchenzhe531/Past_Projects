//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P10 CourseReg
// Course:   CS 300 Fall 2022
//
// Author:   Chenzhe Xu
// Email:    cxu296@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __ Write-up states that pair programming is allowed for this assignment.
//   __ We have both read and understand the course Pair Programming Policy.
//   __ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
/**
 * A application handler for course registration using a priority queue.
 */
public class CourseReg {

  // data fields
  private CourseQueue courses;  // the priority queue of all courses
  private int creditLoad;       // the maximum number of credits you want to take
  
  /**
   * Creates a new course registration object
   * @param capacity the maximum number of courses to store in the priority queue
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if either capacity or creditLoad are not a positive integer
   */
  public CourseReg(int capacity, int creditLoad) throws IllegalArgumentException {
    if(capacity <= 0){
      throw new IllegalArgumentException("CourseReg Error Detected: capacity non-positive");
    }
    if(creditLoad <= 0){
      throw new IllegalArgumentException("CourseReg Error Detected: creditload non-positive");
    }
    this.creditLoad = creditLoad;
    courses = new CourseQueue(capacity);
  }
  
  /**
   * Returns a string representation of the highest-priority courses with a total number of credits
   * less than or equal to the creditLoad of this CourseReg object. Use the Iterable property of the
   * CourseQueue to help you out!
   * 
   * Note that this is NOT a "knapsack" problem - you're trying to maximize priority, not number of
   * credits. Just add courses to your result String until adding the next would take you over this
   * CourseReg object's creditLoad limit.
   * 
   * @return a string representation with one course on each line, where the total number of credits
   *   represented is less than or equal to the current creditLoad value
   */
  public String getRecommendedCourses() {
    int totalCredits = 0;
    CourseQueue copy = courses.deepCopy();
    String output = "";
    //Use the wile loop to loop through each items and combine to a string 
    while(!copy.isEmpty()){
      Course courseExtract = copy.dequeue();
      if((totalCredits + courseExtract.getNumCredits()) > creditLoad){
        break;
      }
      output += courseExtract.toString();
      output += '\n';
      totalCredits += courseExtract.getNumCredits();
    }
    //String credits = Integer.toString(totalCredits);
    //output += credits;
    return output;
  }
  
  /**
   * Tries to add the given course to the priority queue; return false if the queue is full
   * 
   * @param toAdd the course to add
   * @return true if the course was successfully added to the queue
   */
  public boolean add(Course toAdd) {
    try {
      //This is to check whether the queue is full or toAdd is null
      courses.enqueue(toAdd);
    } catch (IllegalStateException e) {
      return false;
    }catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true; 
  }
  
  /**
   * Updates the creditLoad data field to the provided value
   * @param creditLoad the maximum number of credits to take next semester
   * @throws IllegalArgumentException if creditLoad is not a positive integer
   */
  public void setCreditLoad(int creditLoad) throws IllegalArgumentException {
    if(creditLoad <= 0){
      throw new IllegalArgumentException("CourseReg Error Detected: creditload non-positive");
    }
    this.creditLoad = creditLoad;
  }
}
