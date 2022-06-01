package expg2022;

import expg2022.topics.MultiChoiceQuiz;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class QuizTest {

    @Test
    void testUser(){
        User testUser = new User("TestUser");
        testUser.setUserName("New name");

        assertEquals("New name", testUser.getUserName());
    }

    @Test
    void testUserScore(){
        User testUser = new User("TestUser");
        int score = 5;
        String topic = "Random Sport";

        UserScore testUS = new UserScore(score, topic, testUser);

        testUS.setScore(-1);
        assertEquals(0, testUS.getScore());

        testUS.setTopic(null);

        assertEquals("Unknown", testUS.getTopic());
    }

    @Test
    void quizTest(){
        String question = "Is this a test";
        String correctAnswer = "Yes it is";
        ArrayList<String> answers = new ArrayList<>();
        String answer1 = "Yes it is";
        String answer2 = "No it's not";
        answers.add(answer1);
        answers.add(answer2);

        MultiChoiceQuiz mcqTest = new MultiChoiceQuiz(question, correctAnswer, answers);

        assertEquals(answer1, mcqTest.getCorrectAnswer());

        mcqTest.setCorrectAnswer(answer2);

        assertEquals("No it's not", mcqTest.getCorrectAnswer());

        String answer3 = "Mew Right Answer";
        String answer4 = "Wrong answer";

        ArrayList<String> newAnswers = new ArrayList<>();

        newAnswers.add(answer3);
        newAnswers.add(answer4);

        mcqTest.setAnswers(newAnswers);

        assertFalse(mcqTest.isCorrectAnswer(answer3));
        assertFalse(mcqTest.isCorrectAnswer(answer4));

        mcqTest.setCorrectAnswer(newAnswers.get(1));

        assertEquals("Wrong answer", mcqTest.getCorrectAnswer());
    }
}
