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
 * A FIFO linked queue of SongNodes, conforming to our QueueADT interface.
 */
public class Playlist implements QueueADT<Song> {
  private SongNode first;
  //The current first song in the queue; the next one up to play (front of the queue)
  private SongNode last;
  //The current last song in the queue; the most-recently added one (back of the queue)
  private int numSongs; //The number of songs currently in the queue

  /**
   * Constructs a new, empty playlist queue
   */
  public Playlist() {
    first = null; //Set to null
    last = null; //Set to null
    numSongs = 0; //number of songs to 0
  }

  /**
   * Adds a new song to the end of the queue
   *
   * @param element - the song to add to the Playlist
   */
  @Override
  public void enqueue(Song element) {
    SongNode node = new SongNode(element);
    if (numSongs == 0) {
      first = node; //set node to the first if number of songs is 0
    } else {
      //Adds a new song to the end of the queue
      last.setNext(node);
    }
    last = node;
    numSongs++; //Increase 1 to numSongs
  }

  /**
   * Removes the song from the beginning of the queue
   *
   * @return the song that was removed from the queue, or null if the queue is empty
   */
  @Override
  public Song dequeue() {
    if (numSongs == 0) {
      return null;
    }
    Song song = first.getSong();
    //Removes the song from the beginning of the queue
    first = first.getNext();
    if (numSongs == 1) {
      //If there is only one song in the list
      last = null;
      first = null;
    }
    numSongs--; //Decrease one
    return song;
  }

  /**
   * Returns the song at the front of the queue without removing it
   *
   * @return the song that is at the front of the queue, or null if the queue is empty
   */
  @Override
  public Song peek() {
    if (numSongs == 0) {
      return null; //Return null if there is no song in playlist
    } else {
      //Return the first song
      return first.getSong();
    }
  }

  /**
   * Returns true if and only if there are no songs in this queue
   *
   * @return true if this queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return numSongs == 0; //Check whether the playlist is empty
  }

  /**
   * Returns the number of songs in this queue
   *
   * @return the number of songs in this queue
   */
  @Override
  public int size() {
    return numSongs; //Return size of playlist
  }

  /**
   * Creates and returns a formatted string representation of this playlist,
   * with the string version of each song in the list on a separate line.
   *
   * @return the string representation of this playlist
   */
  @Override
  public String toString() {
    String playlist = "";
    SongNode current = first;
    while (current != null) {
      //Set the String to return
      playlist += current.getSong().toString() + "\n";
      current = current.getNext();
    }
    return playlist;
  }
}
