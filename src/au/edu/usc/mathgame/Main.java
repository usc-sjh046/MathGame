package au.edu.usc.mathgame;


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple console-based maths quiz for primary school children.
 *
 * @author sjh046 Samuel Hayward
 *
 */
public class Main {
    public ArrayList<Integer> highScoreList = new ArrayList<>();
    public static ArrayList<Integer> repeatV1 = new ArrayList<>();
    public static ArrayList<Integer> repeatV2 = new ArrayList<>();
    public static ArrayList<String> repeatOp = new ArrayList<>();
    public static int lastGame;
    public static int currentGame;



    public static void main(String[] args) {
       Main m = new Main(); //used to start the code fresh
       m.games();
    }

    private void games(){
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        Question q = new Question();
        q.gameTime(); // starts game

        topScores(q.score); //pulls score from game and puts it into score table.

        while (!exit) { //code keeps running until player stops it.
            System.out.println("Do you want to play another game Yes/No?"); //asks for player for new game or end game
            String nextGame = input.nextLine();
            if (nextGame.equalsIgnoreCase("yes") | nextGame.equalsIgnoreCase("y")) { //yes can be in multiple forms and any case
                System.out.println("Starting New Game\n");
                lastGame = currentGame; //last game is increased to equal currentGame
                currentGame++; // currentGame is increased
                games(); //method from this class is called again
            } else if (nextGame.equalsIgnoreCase("no") | nextGame.equalsIgnoreCase("n")) { //no can be in multiple forms and any case.
                System.out.println("Game Over");
                exit = true; //conditions to end loop are met. game ends
                repeatOp.clear(); //clears arrays to ensure nothing is saved when player ends game
                repeatV1.clear();
                repeatV2.clear();
                currentGame = 0;
                lastGame = 0;
            }
        }
    }

    public void topScores(int score){ //shows top 3 scores in order
        highScoreList.add(score);//adds score to arraylist
        highScoreList.sort(Collections.reverseOrder()); //orders score from highest to lowest in array

        int size = highScoreList.size();
        if(size > 3){ //if more then 3 score are in list the lowest score is removed
            highScoreList.remove(3);
        }
        System.out.println("Your Top 3 high-scores are: ");
        int index = 1;
        for (int gs: highScoreList) //goes through score array and prints out score in a list format.
        {
            System.out.printf("%s: %s%n", index, gs);
            index++;
        }
    }
    }