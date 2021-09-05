package au.edu.usc.mathgame;

import java.util.Random;
import java.util.Scanner;

public class Question {
    public int value1;
    public int value2;
    public String operator;
    public int score;
    public boolean correctAnswer;
    private int calculatedAnswer;

    public int gameTime() {
        long startTime = System.currentTimeMillis(); //game time starts

        showQuestions();

        long endTime = System.currentTimeMillis(); //game time ends
        double playTime = (endTime - startTime) / 1000.0; // game time is converted to seconds
        System.out.printf("%sYou got %s out of 10 correct in %s seconds%n",
                score > 8 && playTime <= 30 ? "Great Work, You finished in an excellent time\n" : "",
                score, playTime); //print score and time if time is less than 30 sec and score is > 8 player gets special message


        return score; // pushes score to Main class
    }

    public void showQuestions() {

        Random rand = new Random();
        for (int i = 0; i <= 9; i++) { //loop to generate 10 questions
            if (Main.currentGame > Main.lastGame && !Main.repeatV1.isEmpty()) { //cycles through incorrect questions from last questions
                value1 = Main.repeatV1.get(0);
                value2 = Main.repeatV2.get(0);
                operator = Main.repeatOp.get(0);

                Main.repeatV1.remove(0);
                Main.repeatV2.remove(0);
                Main.repeatOp.remove(0);
            } else {  // if no incorrect questions left game generates random questions
                value1 = rand.nextInt(12) + 1;
                value2 = rand.nextInt(12) + 1;
                operator = operators(value1, value2);
                if (operator.equals("/")) { //if question if division v1 is multiplied by v2 so answer is not decimal
                    value1 = value1 * value2;
                }
            }
            askQuestion(value1, value2, operator); //generates question in form of a string
            totalScore(correctAnswer); //tallies score
            }
    }

    public void askQuestion(int v1, int v2, String op) {
        if (op.equals("joke")) { // if operator is joke special string is generated
            System.out.println("A farmer counted 297 cows in the field.");
            System.out.println("How many did he have once he rounded them up?");
        } else { //if operator is normal maths a generic string is generated
            System.out.printf("What is %s %s %s?%n", v1, op, v2);
        }
        userAnswer(v1, v2, op);
    }

    private String operators(int v1, int v2) {
        Random rand = new Random();
        String op;
        int num = rand.nextInt(5); //generates random number to choose the operator

        if (v1 > v2 && (num == 1)) {
            op = "-";
        } else if (num == 2 && v1 <= 10 && v2 <= 10) {
            op = "/";
        } else if (num == 3 && v1 <= 5 && v2 <= 5){
            op = "*";
        } else if (num == 4 && v1 <=3 && v2 >= 10) {
            op = "joke";
        } else {
            op = "+";
        }
        return op;
    }

    public void totalScore(boolean ca) {
        if (ca) {
            score++; // add 1 to total score per correct answer
        }
    }

    public void userAnswer(int v1, int v2, String op) {
        Scanner input = new Scanner(System.in);
        try { //try to ensure input errors dont break game
            int answer = input.nextInt(); //users input
            checkAnswer(answer, v1, v2, op);
        }catch (Exception e){ //if answer in not a integer game asked question again without adding to total number of questions asked
            System.out.println("The answer must be a number!!\n");
            askQuestion(v1, v2, op);
        }

    }


    public boolean checkAnswer(int answer, int v1, int v2, String op) { //checks users input matches equation answer
        calculateAnswer(v1, v2, op);
        if (calculatedAnswer == answer) {
            System.out.println("Correct");
            correctAnswer = true; //this determines if the score increases
        } else if (op.equals("joke") && answer == 300) {
            System.out.println("Correct");
            System.out.println("The farmer had 300 cattle once he rounded them up.");
            correctAnswer = true;
        } else if (op.equals("joke")) {
            System.out.println("The correct answer was 300 because:");
            System.out.println("The farmer had 300 cattle once he rounded them up.");
            correctAnswer = false; //nothing happens to score
        } else {

            Main.repeatV1.add(v1); // if answer is wrong the values are added to corresponding static arraylist to be asked again in the next game.
            Main.repeatV2.add(v2);
            Main.repeatOp.add(op);

            System.out.printf("The correct answer was %s\n", calculatedAnswer);
            correctAnswer = false;
        }
        return correctAnswer;
    }

    private void calculateAnswer (int v1, int v2, String op){
        switch (op) {
            case "+" -> calculatedAnswer = v1 + v2;
            case "-" -> calculatedAnswer = v1 - v2;
            case "/" -> calculatedAnswer = v1 / v2;
            case "*" -> calculatedAnswer = v1 * v2;
        }
    }

}
