package au.edu.usc.mathgame;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static java.util.Arrays.asList;



public class TestQuestions {

    Question q = new Question();
    Main m = new Main();

    @Test
    void testAddition() { //tests addition questions are calculated correctly.
        Assertions.assertTrue(q.checkAnswer(7, 3, 4, "+"));
        Assertions.assertTrue(q.checkAnswer(18, 12, 6, "+"));
        Assertions.assertFalse(q.checkAnswer(12, 3, 4, "+"));
        Assertions.assertFalse(q.checkAnswer(6, 12, 6, "+"));
    }

    @Test
    void testMinus() { //tests subtraction questions are calculated correctly.
        Assertions.assertTrue(q.checkAnswer(2, 4, 2, "-"));
        Assertions.assertTrue(q.checkAnswer(4, 10, 6, "-"));
        Assertions.assertFalse(q.checkAnswer(1, 3, 4, "-"));
        Assertions.assertFalse(q.checkAnswer(18, 12, 6, "-"));
    }

    @Test
    void testDivision() { //tests division questions are calculated correctly.
        Assertions.assertTrue(q.checkAnswer(5, 10, 2, "/"));
        Assertions.assertTrue(q.checkAnswer(2, 12, 6, "/"));
        Assertions.assertFalse(q.checkAnswer(14, 10, 4, "/"));
        Assertions.assertFalse(q.checkAnswer(6, 12, 6, "/"));
    }

    @Test
    void testMultiplication() { //tests multiplication questions are calculated correctly.
        Assertions.assertTrue(q.checkAnswer(8, 2, 4, "*"));
        Assertions.assertTrue(q.checkAnswer(15, 3, 5, "*"));
        Assertions.assertFalse(q.checkAnswer(3, 6, 2, "*"));
        Assertions.assertFalse(q.checkAnswer(7, 11, 4, "*"));
    }
    @Test
    void testJoke() { //tests joke question are calculated correctly.
        Assertions.assertTrue(q.checkAnswer(300, 2, 11, "joke"));
        Assertions.assertFalse(q.checkAnswer(301, 3, 10, "joke"));
        Assertions.assertFalse(q.checkAnswer(299, 1, 12, "joke"));
    }

    @Test
    void testScore() { //tests score is added up correctly
        for (int i = 0; i <= 9; i++) {
            if (i % 2 == 0) {
                q.totalScore(true);
            }
        }
        Assertions.assertNotEquals(10, q.score);
        Assertions.assertEquals(5, q.score);
    }
    @Test
    void testHighScore(){ //tests high score is displayed correctly
       int [] scores = {4,8,3,10,2,9};
       List<Integer> correctScores = asList(10,9,8);
       List<Integer> incorrectScores1 = asList(4,8,3);
       List<Integer> incorrectScores2 = asList(2,10,9);
       List<Integer> incorrectScores3 = asList(8,9,10);

        for (int s : scores) {
            m.topScores(s);
        }

        Assertions.assertEquals(correctScores,m.highScoreList);
        Assertions.assertNotEquals(incorrectScores1,m.highScoreList);
        Assertions.assertNotEquals(incorrectScores2,m.highScoreList);
        Assertions.assertNotEquals(incorrectScores3,m.highScoreList);
    }



//    @Test
//    void testRepeatQuestions() {
//        Main.repeatV1.add(5);
//        Main.repeatV2.add(3);
//        Main.repeatOp.add("+");
//
//        List<Integer> v1List = asList(5);
//        List<Integer> v2List = asList(3);
//        List<String> opList = asList("+");
//        Main.currentGame = 1;
//        Main.lastGame = 0;
//
//        Assertions.assertEquals(v1List,Main.repeatV1);
//        Assertions.assertEquals(v2List,Main.repeatV2);
//        Assertions.assertEquals(opList,Main.repeatOp);
//
//
//        Assertions.assertEquals(5, q.value1);
//        Assertions.assertEquals(3, q.value2);
//        Assertions.assertEquals("+", q.operator);



//    }
}
