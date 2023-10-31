
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 Walking Sim
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
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import java.io.File;
import processing.core.PImage;
/*
 * This class is to develope a graphical implementation of a walking animation
 */
public class WalkingSim {
    private static int bgColor;
    private static Random randGen;
    private static PImage frames[];
    private static Walker walkers[];

    public static void main(String[] args) throws Exception {
        Utility.runApplication();

    }
    /*
     * This method is to setup the walkers array with a certain number of elements which is determined by
     * the random value, and load images to the walkers array.
     */
    public static void setup() {
        randGen = new Random();
        bgColor = randGen.nextInt();
        frames = new PImage[Walker.NUM_FRAMES];
        //Load images
        for (int i = 0; i < frames.length; i++) {
            frames[i] = Utility.loadImage("images" + File.separator + "walk-" + Integer.toString(i) + ".png");
        }
        walkers = new Walker[8];
        int randNum = randGen.nextInt(walkers.length) + 1;
        //Assign Walker to the walkers array
        for (int i = 0; i < randNum; i++) {
            walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
        }

    }
    /*
     * This void method is use to draw the whole image and prevent the moving animation from moving out of the
     * bound. 
     */
    public static void draw() {
        Utility.background(bgColor);
        for (int i = 0; i < walkers.length; i++) {
            if (walkers[i] != null) {
                if (walkers[i].isWalking() == true) {
                    //Avoid moving out of the bound
                    walkers[i].setPositionX((walkers[i].getPositionX() + 3) % 800);
                }
                //Draw the image
                Utility.image(frames[walkers[i].getCurrentFrame()], walkers[i].getPositionX(),
                        walkers[i].getPositionY());
            }
        }

        for (int i = 0; i < walkers.length; i++) {
            if (walkers[i] != null) {
                if (walkers[i].isWalking() == true) {
                    walkers[i].update();
                }
            }
        }

    }
    /**
     * This method is to check whether the mouse is within the walk image
     * 
     * @param w is the Walker that is going to be checked for whether it is within the image
     * @return the boolean value whether the mouse is within the image
     */
    public static boolean isMouseOver(Walker w) {
        double positionX = w.getPositionX();
        double positionY = w.getPositionY();
        int width = frames[0].width;
        int height = frames[0].height;
        //Check the location of the mouse
        if ((Utility.mouseX() > (positionX - width / 2.0) && Utility.mouseX() < (positionX + width / 2.0)) &&
                ((Utility.mouseY() > (positionY - height / 2.0)) && (Utility.mouseY() < (positionY + height / 2.0)))) {
            return true;
        }
        return false;
    }

    public static void mousePressed() {
        for (int i = 0; i < walkers.length; i++) {
            if (walkers[i] != null) {
                if (isMouseOver(walkers[i]) == true) {
                    walkers[i].setWalking(true);
                    break;
                }
            }
        }

    }
    /**
     * This method is to check the input from the keyboard and do the corresponding
     * action according to the input. If the user enters 'A' or "a", the method will
     * find the null element in the array and add one more Walker. If the input is 's'
     * or 'S', the animation will be stopped.
     * 
     * @param input is the alphabet that the user enters through keyboard
     */
    public static void keyPressed(char input) {
        if (input == 'a' || input == 'A') {
            for (int i = 0; i < walkers.length; i++) {
                if (walkers[i] == null) {
                    walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
                    break;
                }
            }
        }
        if (input == 's' || input == 'S') {
            for (int i = 0; i < walkers.length; i++) {
                if (walkers[i] != null) {
                    walkers[i].setWalking(false);
                }
            }
        }
    }

}
