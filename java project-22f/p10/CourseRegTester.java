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
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Course, CourseIterator,
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {

  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() {
    try {
      // Test Course constructor
      try {
        Course c3 = new Course(null, 220, 4, 200);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // Test Course constructor
      try {
        Course c4 = new Course("", 220, 4, 200);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // Test Course constructor
      try {
        Course c5 = new Course("CS", 0, 4, 200);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // Test Course constructor
      try {
        Course c6 = new Course("CS", 220, 0, 200);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // Test Course constructor
      try {
        Course c7 = new Course("CS", 220, 8, 200);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // Test Course constructor
      try {
        Course c8 = new Course("CS", 220, 4, -1);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      Course c1 = new Course("CS", 220, 4, 200);
      Course c2 = new Course("CS", 220, 4, 200);
      c1.setProfessor("Mr Hello", 4);
      // Test Course Accessor
      if (c1.getNumCredits() != 4) {
        return false;
      }
      // Test Course Equal()
      if (c1.equals(c2) == true) {
        return false;
      }
      // Test Course Equal()
      if (c1.equals(c1) == false) {
        return false;
      }
      // Test toString()
      if (!c1.toString().equals("CS 220 (200 seats) with Mr Hello (4.0)")) {
        return false;
      }
      // Test toString()
      if (!c2.toString().equals("CS 220 (200 seats)")) {
        return false;
      }
      Course c3 = new Course("CS", 220, 4, 0);
      if (!c3.toString().equals("CS 220 (closed)")) {
        return false;
      }
      c3.setProfessor("Mike", 5);
      if (!c3.toString().equals("CS 220 (closed) with Mike (5.0)")) {
        return false;
      }
      // Test Course Setter
      c1.setSeatsAvailable(100);
      if (!c1.toString().equals("CS 220 (100 seats) with Mr Hello (4.0)")) {
        return false;
      }
      // Test Course Setter
      try {
        c1.setSeatsAvailable(-1);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // Test Course Setter
      try {
        c1.setProfessor(null, 1);
      } catch (IllegalArgumentException e) {
        return false;
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      Course c5 = new Course("CS", 220, 4, 200);
      try {
        c5.setProfessor(null, 2);
        if(!c5.toString().equals("CS 220 (200 seats)")){
          return false;
        }
      } catch (IllegalArgumentException e) {
        return false;
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      c5.setProfessor(null, -1);
      if(!c5.toString().equals("CS 220 (200 seats)")){
        return false;
      }
      // Test Course Setter
      try {
        c1.setProfessor("Hello", -1);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      // Test Course Setter
      try {
        c1.setProfessor("Hello", 6);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels of
   * the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() {
    try {
      Course c1 = new Course("CS", 220, 4, 200);
      Course c2 = new Course("CS", 220, 4, 200);
      Course c3 = new Course("CS", 220, 4, 200);
      //Test compare to method
      if (c1.compareTo(c2) != 0) {
        return false;
      }
      //Test compare to method
      c2.setSeatsAvailable(0);
      if (c1.compareTo(c2) != 1) {
        return false;
      }
      //Test compare to method
      c2.setSeatsAvailable(200);
      c1.setSeatsAvailable(0);
      if (c1.compareTo(c2) != -1) {
        return false;
      }
      //Test compare to method
      c1.setSeatsAvailable(200);
      c1.setProfessor("Hello", 2);
      if (c1.compareTo(c2) != 1) {
        return false;
      }
      if (c3.compareTo(c1) != -1) {
        return false;
      }
      Course c4 = new Course("CS", 220, 4, 200);
      Course c5 = new Course("CS", 220, 4, 200);
      c4.setSeatsAvailable(0);
      c5.setSeatsAvailable(0);
      //Test compare to method
      if (c4.compareTo(c5) != 0) {
        return false;
      }
      c4.setProfessor("Mike", 2);
      if (c4.compareTo(c5) != 1) {
        return false;
      }
      //Test compare to method
      if (c5.compareTo(c4) != -1) {
        return false;
      }
      //Test compare to method
      c5.setProfessor("Lucas", 4);
      if (c4.compareTo(c5) != -1) {
        return false;
      }
      //Test compare to method
      c4.setProfessor("Mike", 5);
      if (c4.compareTo(c5) != 1) {
        return false;
      }
      //Test compare to method with given condition
      c4.setProfessor("Mike", 4);
      if (c4.compareTo(c5) != 0) {
        return false;
      }
      Course c6 = new Course("CS", 220, 4, 200);
      Course c7 = new Course("CS", 220, 4, 200);
      //Test compare to method with given condition
      if (c6.compareTo(c7) != 0) {
        return false;
      }
      c6.setProfessor("mike", 4);
      if (c6.compareTo(c7) != 1) {
        return false;
      }
      if (c7.compareTo(c6) != -1) {
        return false;
      }
      c7.setProfessor("lucas", 3);
      if (c6.compareTo(c7) != 1) {
        return false;
      }
      if (c7.compareTo(c6) != -1) {
        return false;
      }
      c7.setProfessor("lucas", 4);
      if (c6.compareTo(c7) != 0) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() {
    try {
      try {
        CourseQueue q1 = new CourseQueue(0);
        return false;
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        System.out.println("Error Detected: Unexpected Error");
        return false;
      }
      CourseQueue q2 = new CourseQueue(5);
      Course c1 = new Course("CS", 220, 4, 200);
      Course c2 = new Course("CS", 300, 3, 200);
      Course c3 = new Course("CS", 252, 3, 200);
      c1.setProfessor("Lucas Xu", 5);
      c2.setProfessor("Jennie", 3);
      c3.setProfessor("Bella", 4);
      q2.enqueue(c1);
      q2.enqueue(c2);
      q2.enqueue(c3);
      //Test CourseQueue and its related details like size and empty
      if (q2.size() != 3) {
        return false;
      }
      if (q2.isEmpty() == true) {
        return false;
      }
      q2.dequeue();
      if (q2.isEmpty() || q2.size() != 2) {
        return false;
      }
      if (!q2.peek().equals(c3)) {
        return false;
      }
      q2.dequeue();
      q2.dequeue();
      //Test CourseQueue and its related details like size and empty
      if (q2.size() != 0 || !q2.isEmpty()) {
        return false;
      }
      try {
        q2.peek();
        return false;
      } catch (NoSuchElementException e) {

      } catch (Exception e) {
        System.out.println("Unexpected Exception: Peek()");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() {
    try {
      CourseQueue q2 = new CourseQueue(3);
      Course c1 = new Course("CS", 220, 4, 200);
      Course c2 = new Course("CS", 300, 3, 200);
      Course c3 = new Course("CS", 252, 3, 200);
      c1.setProfessor("Lucas Xu", 5);
      c2.setProfessor("Jennie", 3);
      c3.setProfessor("Bella", 4);
      //      CS220
      //    CS300  CS252
      //
      //Test Enqueue by testing its toString and size
      q2.enqueue(c1);
      if (q2.size() != 1 || !q2.toString().trim().equals("CS 220 (200 seats) with Lucas Xu (5.0)")) {
        return false;
      }
      q2.enqueue(c2);
       //Test Enqueue by testing its toString and size
      if (q2.size() != 2 || !q2.toString().trim()
          .equals("CS 220 (200 seats) with Lucas Xu (5.0)\nCS 300 (200 seats) with Jennie (3.0)")) {
        return false;
      }
      q2.enqueue(c3);
       //Test Enqueue by testing its toString and size
      if (q2.size() != 3 || !q2.toString().trim().equals(
          "CS 220 (200 seats) with Lucas Xu (5.0)\nCS 252 (200 seats) with Bella (4.0)\nCS 300 (200 seats) with Jennie (3.0)")) {
        return false;
      }
       //Test Enqueue by testing its special exception handling
      try {
        q2.enqueue(null);
        return false;
      } catch (NullPointerException e) {

      } catch (Exception e) {
        System.out.println("Unexpected Exception: Enqueue()");
        return false;
      }
      Course c4 = new Course("CS", 320, 3, 200);
      c4.setProfessor("Mike", 2);
      try {
        //Test Enqueue by testing its special exception handling
        q2.enqueue(c4);
        return false;
      } catch (IllegalStateException e) {

      } catch (Exception e) {
        System.out.println("Unexpected Exception: Enqueue()");
        return false;
      }
      CourseQueue q3 = new CourseQueue(4);
      try {
        //Test Dequeue by testing its special exception handling
        q3.dequeue();
        return false;
      } catch (NoSuchElementException e) {

      } catch (Exception e) {
        System.out.println("Unexpected Exception: Dequeue()");
        return false;
      }
      //      CS220
      //    CS300  CS252
      //CS320
      //Priority CS220>CS252>CS300>CS320
      q3.enqueue(c1);
      q3.enqueue(c2);
      q3.enqueue(c3);
      q3.enqueue(c4);
      Course returnValue = q3.dequeue();
      //Test Dequeue by testing its toString and size
      if (q3.size() != 3 || !q3.toString().trim().equals(
          "CS 252 (200 seats) with Bella (4.0)\nCS 300 (200 seats) with Jennie (3.0)\nCS 320 (200 seats) with Mike (2.0)")
          || !returnValue.equals(c1)) {
        return false;
      }
      Course returnValue2 = q3.dequeue();
      //Test Dequeue by testing its toString and size
      if (q3.size() != 2
          || !q3.toString().trim()
              .equals("CS 300 (200 seats) with Jennie (3.0)\nCS 320 (200 seats) with Mike (2.0)")
          || !returnValue2.equals(c3)) {
        return false;
      }
      Course returnValue3 = q3.dequeue();
      //Test Dequeue by testing its toString and size
      if (q3.size() != 1 || !q3.toString().trim().equals("CS 320 (200 seats) with Mike (2.0)")
          || !returnValue3.equals(c2)) {
        return false;
      }
      Course returnValue4 = q3.dequeue();
      //Test Dequeue by testing its toString and size
      if (q3.size() != 0 || !returnValue4.equals(c4)) {
        return false;
      }

    } catch (Exception e) {
      System.out.println("Error");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() {
    CourseQueue q2 = new CourseQueue(3);
    Course c1 = new Course("CS", 220, 4, 200);
    Course c2 = new Course("CS", 300, 3, 200);
    Course c3 = new Course("CS", 252, 3, 200);
    c1.setProfessor("Lucas Xu", 5);
    c2.setProfessor("Jennie", 3);
    c3.setProfessor("Bella", 4);
    CourseIterator iterator = new CourseIterator(q2);
    if(iterator.hasNext()){
      return false;
    }
    //Check each element of the queue by iterating through 
    q2.enqueue(c1);
    q2.enqueue(c2);
    q2.enqueue(c3);
    if(!iterator.hasNext()){
      return false;
    }
    //Check each element of the queue by iterating through 
    if(!iterator.next().equals(c1)){
      return false;
    }
    //Check each element of the queue by iterating through 
    if(!iterator.next().equals(c3)){
      return false;
    }
    //Check each element of the queue by iterating through 
    if(!iterator.next().equals(c2)){
      return false;
    }
    try {
      //Special exception handling
      iterator.next();
    } catch (NoSuchElementException e) {
      
    }catch (Exception e) {
      System.out.println("Unexpected Exception: Dequeue()");
      return false;
    }
    return true;
  }

  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() {
    try {
      CourseReg courses = new CourseReg(3, 10);
      try {
        //Check CourseReg with its exception
        CourseReg courses_1 = new CourseReg(-5, 18);
      } catch (IllegalArgumentException e) {
        
      }catch (Exception e) {
        System.out.println("Unexpected Exception: CourseReg()");
        return false;
      }
      try {
        //Check CourseReg with its exception
        CourseReg courses_2 = new CourseReg(5, -18);
      } catch (IllegalArgumentException e) {
        
      }catch (Exception e) {
        System.out.println("Unexpected Exception: CourseReg()");
        return false;
      }
      try {
        //Check CourseReg with its exception
        CourseReg courses_3 = new CourseReg(-5, -18);
      } catch (IllegalArgumentException e) {
        
      }catch (Exception e) {
        System.out.println("Unexpected Exception: CourseReg()");
        return false;
      }
      //This is to test the function of CourseReg class
      Course c1 = new Course("CS", 220, 4, 200);
      Course c2 = new Course("CS", 300, 5, 200);
      Course c3 = new Course("CS", 252, 3, 200);
      c1.setProfessor("Lucas Xu", 5);
      c2.setProfessor("Jennie", 4);
      c3.setProfessor("Bella", 3);
      boolean response1 = courses.add(c1);
      if(response1 == false){
        return false;
      }
      //This is to test the function of CourseReg class: Add()
      boolean response2 =courses.add(c2);
      if(response2 == false){
        return false;
      }
      boolean response3 =courses.add(c3);
      if(response3 == false){
        return false;
      }
      Course c4 = new Course("CS", 320, 3, 200);
      c4.setProfessor("Mike", 2);
      boolean response4 =courses.add(c4);
      if(response4 != false){
        return false;
      }
      try {
        courses.setCreditLoad(0);
      } catch (IllegalArgumentException e) {
        
      }catch (Exception e) {
        System.out.print("Unexpected Exception: SetCreditLoad()");
        return false;
      }
      if(!courses.getRecommendedCourses().trim().equals("CS 220 (200 seats) with Lucas Xu (5.0)\nCS 300 (200 seats) with Jennie (4.0)")){
        return false;
      }
      courses.setCreditLoad(12);
      if(!courses.getRecommendedCourses().trim().equals("CS 220 (200 seats) with Lucas Xu (5.0)\nCS 300 (200 seats) with Jennie (4.0)\nCS 252 (200 seats) with Bella (3.0)")){
        return false;
      }
      CourseReg courses1 = new CourseReg(7, 19);
      Course c11 = new Course("CS", 220, 5, 200);
      Course c22= new Course("CS", 300, 5, 200);
      Course c33 = new Course("CS", 252, 5, 200);
      c11.setProfessor("Lucas Xu", 5);
      c22.setProfessor("Jennie", 4.9);
      c33.setProfessor("Bella", 4.8);
      Course c5 = new Course("CS", 200, 5, 20);
      c5.setProfessor("Julia", 4);
      Course c6 = new Course("CS", 540, 2, 0);
      c6.setProfessor("Julia", 3.9);
      Course c7 = new Course("CS", 680, 1, 0);
      c6.setProfessor("Tony", 1.2);
      //Check the creditload with some low credit and low priority courses
      courses1.add(c11);
      courses1.add(c22);
      courses1.add(c33);
      courses1.add(c5);
      courses1.add(c6);
      courses1.add(c7);
      //System.out.println(courses1.getRecommendedCourses());
      if(!courses1.getRecommendedCourses().trim().equals("CS 220 (200 seats) with Lucas Xu (5.0)\nCS 300 (200 seats) with Jennie (4.9)\nCS 252 (200 seats) with Bella (4.8)")){
        return false;
      }
      //System.out.println(courses1.getRecommendedCourses());
    } catch (Exception e) {
      System.out.println("Unexpected Exception: testCourseReg");
      return false;
    }
    return true;
  }

  /**
   * This method calls all test methods defined by us; you may add additional methods to this if you
   * like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() {
    boolean testVal = true;

    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else {
      System.out.println("pass");
    }

    return testVal;
  }

  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
