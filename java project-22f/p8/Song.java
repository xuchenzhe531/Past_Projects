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
import java.io.IOException;

/**
 * A representation of a single Song. Interfaces with the provided AudioUtility class, which uses
 * the javax.sound.sampled package to play audio to your computer's audio output device
 */
public class Song {
  private AudioUtility audioClip;//This song's AudioUtility interface to javax.sound.sampled
  private String artist;//The artist of this song
  private int duration;//The duration of this song in number of seconds
  private String title;//The title of this song

  /**
   * Initializes all instance data fields according to the provided values
   *
   * @param title    - the title of the song, set to empty string if null
   * @param artist   - the artist of this song, set to empty string if null
   * @param filepath - the full relative path to the song file, begins with the "audio" directory
   *                 for P08
   * @throws IllegalArgumentException - if the song file cannot be read
   */
  public Song(String title, String artist, String filepath) throws IllegalArgumentException {
    if (title == null) {
      //the title of the song, set to empty string if null
      this.title = "";
    } else {
      //Assign input to title
      this.title = title;
    }
    if (artist == null) {
      // the artist of this song, set to empty string if null
      this.artist = "";
    } else {
      //Assign input to artist
      this.artist = artist;
    }
    try {
      audioClip = new AudioUtility(filepath); //Use audioUtility to process file path
      duration = audioClip.getClipLength(); //Get duration
    } catch (IOException e) {
      //if the song file cannot be read
      throw new IllegalArgumentException("file path loads unsuccessfully");
    }
  }

  /**
   * Tests whether this song is currently playing using the AudioUtility
   *
   * @return true if the song is playing, false otherwise
   */
  public boolean isPlaying() {
    return audioClip.isRunning(); //Check whether the music is runninh
  }

  /**
   * Accessor method for the song's title
   *
   * @return the title of this song
   */
  public String getTitle() {
    return title; //Return title
  }

  /**
   * Accessor method for the song's artist
   *
   * @return the artist of this song
   */
  public String getArtist() {
    return artist; // Return artist
  }

  /**
   * Uses the AudioUtility to start playback of this song, reopening the clip for playback if
   * necessary
   */
  public void play() {
    //Uses the AudioUtility to start playback of this song
    if (!audioClip.isRunning()) {
      //reopening the clip for playback if necessary
      audioClip.reopenClip();
    }
    //Uses the AudioUtility to start playback of this song
    audioClip.startClip();
    System.out.println("Playing... " + toString());
  }

  /**
   * Uses the AudioUtility to stop playback of this song
   */
  public void stop() {
    audioClip.stopClip(); //Stop the music
  }

  /**
   * Creates and returns a string representation of this Song, for example: "Africa" (4:16) by Toto
   * The title should be in quotes, the duration should be split out into minutes and seconds
   * (recall it is stored as seconds only!), and the artist should be preceded by the word "by". It
   * is intended for this assignment to leave single-digit seconds represented as 0:6, for example,
   * but if you would like to represent them as 0:06, this is also allowed.
   *
   * @return a formatted string representation of this Song
   */
  @Override
  public String toString() {
    int minute = duration / 60; //get the minutes
    int second = duration % 60; //get the seconds
    return "\"" + title + "\" (" + minute + ":" + second + ") by " + artist;
  }
}
