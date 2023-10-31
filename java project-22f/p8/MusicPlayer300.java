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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A linked-queue based music player which plays Actual Music Files based on keyboard input in an
 * interactive console method. This music player can load playlists of music or add individual song
 * files to the queue.
 */
public class MusicPlayer300 {
  private String filterArtist; //The artist to play if filterPlay is true; should be null otherwise
  private boolean filterPlay;
  //Whether the current playback mode should be filtered by artist; false by default
  private Playlist playlist; //The current playlist of Songs

  /**
   * Creates a new MusicPlayer300 with an empty playlist
   */
  public MusicPlayer300() {
    playlist = new Playlist(); //Creates a new MusicPlayer300 with an empty playlist
    filterPlay = false;
    filterArtist = null;
  }

  /**
   * Stops any song that is playing and clears out the playlist
   */
  public void clear() {
    //Assign the default value to each data field
    playlist = null;
    filterPlay = false;
    filterArtist = null;
  }

  /**
   * Loads a playlist from a provided file, skipping any individual songs which cannot be loaded.
   * Note that filenames in the provided files do NOT include the audio directory, and will need
   * that added before they are loaded. Print "Loading" and the song's title in quotes before you
   * begin loading a song, and an "X" if the load was unsuccessful for any reason.
   *
   * @param file - the File object to load
   * @throws FileNotFoundException - if the playlist file cannot be loaded
   */
  public void loadPlaylist(File file) throws FileNotFoundException {
    Scanner reader = null;
    try {
      //Read the file object
      reader = new Scanner(file);
      while (reader.hasNextLine()) {
        //Parse the line
        String nextLine = reader.nextLine();
        String[] parts = nextLine.split(",");
        if (parts.length != 3) {
          // if is in the wrong format then skip
          continue;
        }
        //Print the first element in parts
        System.out.println("Loading \"" + parts[0] + "\"");
        // Note that filenames in the provided files do NOT include the audio directory
        //, and will need that added before they are loaded.
        String completePath = "audio/" + parts[2];
        try {
          loadOneSong(parts[0], parts[1], completePath);
        } catch (IllegalArgumentException e) {
          // if one of the song cannot be load skip that song
          System.out.println("X");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("X");
      //Throw the exception if the file cannot be loaded
      throw new FileNotFoundException("loadPlaylist: file cannot be loaded");
    } finally {
      if (reader != null) {
        reader.close(); //Close the Scanner
      }
    }
    if (playlist.isEmpty()) {
      // if nothing is load to the file then file cannot be loaded
      System.out.println("X");
      throw new FileNotFoundException("loadPlaylist: file cannot be loaded");
    }
  }

  /**
   * Loads a single song to the end of the playlist given the title, artist, and filepath.
   * Filepaths for P08 must refer to files in the audio directory.
   *
   * @param title    - the title of the song
   * @param artist   - the artist of this song
   * @param filepath - the full relative path to the song file, begins with the "audio" directory
   *                 for P08
   * @throws IllegalArgumentException - if the song file cannot be read
   */
  public void loadOneSong(String title, String artist, String filepath) {
    playlist.enqueue(new Song(title, artist, filepath)); //Add the song to the end of the playlist
  }

  /**
   * Provides a string representation of all songs in the current playlist
   *
   * @return a string representation of all songs in the current playlist
   */
  public String printPlaylist() {
    return playlist.toString(); //Return the string
  }

  /**
   * Creates and returns the menu of options for the interactive console program.
   *
   * @return the formatted menu String
   */
  public String getMenu() {
    //Return the menu
    return "Enter one of the following options:\n" +
        "[A <filename>] to enqueue a new song file to the end of this playlist\n" +
        "[F <filename>] to load a new playlist from the given file\n" +
        "[L] to list all songs in the current playlist\n" +
        "[P] to start playing ALL songs in the playlist from the beginning\n" +
        "[P -t <Title>] to play all songs in the playlist starting from <Title>\n" +
        "[P -a <Artist>] to start playing only the songs in the playlist by Artist\n" +
        "[N] to play the next song\n" + "[Q] to stop playing music and quit the program\n";
  }

  /**
   * Stops playback of the current song (if one is playing) and advances to the next song in the
   * playlist.
   *
   * @throws IllegalStateException - if the playlist is null or empty, or becomes empty at any time
   *                               during this method
   */
  public void playNextSong() throws IllegalStateException {
    // if the playlist is null or empty, or becomes empty at any time during this method
    if (playlist == null || playlist.isEmpty()) {
      throw new IllegalStateException("playNextSong: playlist is null or empty");
    }

    //Stops playback and remove the current song (if one is playing)
    if (playlist.peek().isPlaying()) {
      // remove and stop the playing song if any song is playing
      playlist.dequeue().stop();
    } else {
      // remove the first song
      playlist.dequeue();
    }

    // check after the current song is removed
    if (playlist == null || playlist.isEmpty()) {
      throw new IllegalStateException("playNextSong: playlist is null or empty");
    }

    // check if is filterPlay
    if (filterPlay) {
      while (playlist.size() > 0 && playlist.peek() != null) {
        // find if the first song is match the filter Artist
        if (playlist.peek().getArtist().equals(filterArtist)) {
          // if match then play the song
          playlist.peek().play();
          break;
        } else {
          // if is not match then remove the song
          playlist.dequeue();
        }
      }
    } else {
      // play the next song
      playlist.peek().play();
    }
  }

  /**
   * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user.
   */
  public void runMusicPlayer300(Scanner in) {
    in = new Scanner(System.in); //Create Scanner
    while (true) {
      // Print menu every time runs the loop
      System.out.println(getMenu());
      System.out.print("> ");
      String line = in.nextLine();
      //parse the input
      String[] parts = line.split(" ");
      //If there is only one element
      if (parts.length == 1) {
        if (parts[0].equals("L")) {
          //to list all songs in the current playlist
          if (playlist.size() == 0) {
            System.out.println("No songs left :(");
          } else {
            System.out.println(printPlaylist());
          }
        } else if (parts[0].equals("P")) {
          // Not filterPlay
          filterPlay = false;
          filterArtist = null;
          // stop the song if any song is playing
          if (playlist.peek() != null && playlist.peek().isPlaying()) {
            playlist.peek().stop();
          }
          //to start playing ALL songs in the playlist from the beginning
          if (playlist.size() == 0) {
            System.out.println("No songs left :(");
          } else {
            // play the first song in the list
            playlist.peek().play();
          }
        } else if (parts[0].equals("N")) {
          try {
            //to play the next song
            playNextSong();
          } catch (IllegalStateException e) {
            // if there is no song left
            System.out.println("No songs left :(");
          }
        } else if (parts[0].equals("Q")) {
          // stop the song if any song is playing
          if(playlist.peek() != null && playlist.peek().isPlaying()){
            playlist.peek().stop();
          }
          clear();// clear the playlist
          System.out.println("Goodbye!");
          break;// out the loop
        } else {
          // the condition is not in the manu
          System.out.println("I don't know how to do that.");
        }
        //If the input has two parts
      } else if (parts.length == 2) {
        if (parts[0].equals("A")) {
          //Ask the user to enter title and artist
          System.out.println("Title: ");
          String title = in.nextLine();
          System.out.println("Artist: ");
          String artist = in.nextLine();
          try {
            loadOneSong(title, artist, parts[1]);
          } catch (IllegalArgumentException e) {
            // if the song cannot be load
            System.out.println("Unable to load that song");
          }
        } else if (parts[0].equals("F")) {
          //Load file
          File file = new File(parts[1]);
          try {
            //Load the playlist
            loadPlaylist(file);
          } catch (FileNotFoundException e) {
            // if the file cannot be load
            e.printStackTrace();
          }
        } else {
          // the condition is not in the manu
          System.out.println("I don't know how to do that.");
        }
        //If the input has three parts divided by space
      } else if (parts.length == 3) {
        if (parts[0].equals("P")) {
          if ((parts[1].equals("-t"))) {
            // Not Artist filterPlay mode
            filterPlay = false;
            filterArtist = null;
            // stop the song if any song is playing
            if (playlist.peek() != null && playlist.peek().isPlaying()) {
              playlist.peek().stop();
            }
            //To find the title and play it
            while (playlist.size() > 0 && playlist.peek() != null) {
              if (playlist.peek().getTitle().equals(parts[2])) {
                // find the first match song and play it
                playlist.peek().play();
                break;
              } else {
                playlist.dequeue(); //Delete not match song
              }
            }
          } else if ((parts[1].equals("-a"))) {
            //Set the filter and to find the artist
            filterPlay = true;
            filterArtist = parts[2];
            // stop the song if any song is playing
            if (playlist.peek() != null && playlist.peek().isPlaying()) {
              playlist.peek().stop();
            }
            // find the first artist match song
            while (playlist.size() > 0 && playlist.peek() != null) {
              if (playlist.peek().getArtist().equals(filterArtist)) {
                // find the first match song and play it
                playlist.peek().play();
                break;
              } else {
                playlist.dequeue();//Delete not match song
              }
            }
          } else {
            // When the modifier is not in the menu
            System.out.println("I don't know how to do that.");
          }
        } else {
          //When the condition is not in the menu
          System.out.println("I don't know how to do that.");
        }
      } else {
        // When there is more than 3 parts
        System.out.println("I don't know how to do that.");
      }
    }
    in.close(); //Close Scanner
  }

}
