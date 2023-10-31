//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 MusicPlayer300
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


/**
 * This class is the tester for MusicPlayer300
 */
public class MusicPlayerTester {
  /**
   * This is the main method for MusicPlayerTester
   */
  public static void main(String[] args) {
    System.out.println("testSongConstructor(): " + testSongConstructor());
    System.out.println("testSongPlayback(): " + testSongPlayback());
    System.out.println("testSongNode(): " + testSongNode());
    System.out.println("testEnqueue(): " + testEnqueue());
    System.out.println("testDequeue(): " + testDequeue());
  }

  /**
   * This is the test method for Song constructor
   */
  public static boolean testSongConstructor() {
    try {
      //Song constructor with correct parameters
      Song song = new Song("Helloworld", "giegie", "audio/waterloo.mid");
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return false; //return false if the exception is thrown
    } catch (Exception e) {
      System.out.println("testSongConstructor() Error detected: Unexpected Exception Error");
      return false; //return false if the exception is thrown
    }
    try {
      //Song constructor with if the exception is expected to be thrown
      Song song = new Song("Helloworld", "giegie", "audio/random.mid");
      return false;//return false if the exception is not thrown
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("testSongConstructor() Error detected: Unexpected Exception Error");
      return false; //return false if the exception is thrown
    }
    return true;
  }

  /**
   * This is the test method for SongPlayback
   */
  public static boolean testSongPlayback() {
    Song song = new Song("Helloworld", "giegie", "audio/all-i-want-for-xmas.mid");
    song.play(); //play the music
    try {
      //To have 1 second for the computer to play the music
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (song.isPlaying() != true) {
      return false; //return false if the exception is thrown
    }
    return true;
  }

  /**
   * This is the method to test SongNode
   */
  public static boolean testSongNode() {
    Song song1 = new Song("Helloworld", "giegie", "audio/all-i-want-for-xmas.mid");
    Song song2 = new Song("My Friend", "GEGE", "audio/1.mid");
    try {
      //Set the parameter to null
      SongNode node1 = new SongNode(null);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
      return false; //Return false if the expected exception is not thrown
    }
    try {
      SongNode node2 = new SongNode(song1);
      SongNode node3 = new SongNode(null, node2); //Set parameter to numm
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
      return false;//Return false if the expected exception is not thrown
    }

    try {
      SongNode node4 = new SongNode(song1);
      SongNode node5 = new SongNode(song2);
      node4.setNext(node5);
      //Check the correctness
      if (!song1.equals(node4.getSong()) || !song2.equals(node4.getNext().getSong())) {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false; //Return false if the exception is thrown
    }
    try {
      SongNode node6 = new SongNode(song1);
      SongNode node7 = new SongNode(song2, node6);
      //Check the correctness
      if (!song2.equals(node7.getSong()) || !node6.equals(node7.getNext())) {
        return false;//Return false if the content is wrong
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;//Return false if the unexpected exception is thrown
    }

    return true;
  }

  /**
   * This is the test method for Enqueue
   */
  public static boolean testEnqueue() {
    Playlist playlist = new Playlist();
    if (!playlist.isEmpty() || playlist.size() != 0) {
      return false; //Return false if the content is wrong
    }
    Song song1 = new Song("Helloworld", "giegie", "audio/all-i-want-for-xmas.mid");
    Song song2 = new Song("My Friend", "GEGE", "audio/1.mid");
    playlist.enqueue(song1);
    if (!playlist.peek().equals(song1) || playlist.size() != 1 || playlist.isEmpty()) {
      return false;//Return false if the content is wrong
    }
    playlist.enqueue(song2);
    if (!playlist.peek().equals(song1) || playlist.size() != 2 || playlist.isEmpty()) {
      return false;//Return false if the content is wrong
    }
    return true;
  }

  /**
   * This is the test for Dequeue
   */
  public static boolean testDequeue() {
    try {
      Playlist playlist = new Playlist();
      Song song1 = new Song("Helloworld", "giegie", "audio/all-i-want-for-xmas.mid");
      Song song2 = new Song("My Friend", "GEGE", "audio/1.mid");
      playlist.enqueue(song1);
      playlist.enqueue(song2);
      if (!playlist.dequeue().equals(song1) || !playlist.peek()
          .equals(song2) || playlist.size() != 1 || playlist.isEmpty()) {
        return false;//Return false if the content is wrong
      }
      if (!playlist.dequeue().equals(
          song2) || playlist.peek() != null || playlist.size() != 0 || !playlist.isEmpty()) {
        return false;//Return false if the content is wrong
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;//Return false if the unexpected exception is thrown
    }
  }
}
