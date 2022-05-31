package expg2022;

import expg2022.dto.BinaryQuizDao;
import expg2022.dto.MultiChoiceQuizDao;
import expg2022.topics.AbstractQuiz;
import expg2022.topics.BinaryQuiz;
import expg2022.topics.MultiChoiceQuiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class textFileToDb {

    private static final ArrayList<AbstractQuiz> quizRegister = new ArrayList<>();

    public static void quizzesFromTextToDb(){
        File file = new File("src/main/resources/QuizQuestions.txt");

        try(Scanner scan = new Scanner(file)){

            while(scan.hasNextLine()){
                String topic = scan.nextLine();

                if(topic.equalsIgnoreCase("football")){
                    while(!scan.nextLine().equals("---")){
                        quizRegister.add(createMultiQuizFromFile(scan));
                    }
                } else if(topic.equalsIgnoreCase("mma")){
                    while(!scan.nextLine().equals("---")){
                        quizRegister.add(createBinaryFromTextFile(scan));
                    }
                }
            }

            addQuizzesToDb(quizRegister);
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
    }

    public static void addQuizzesToDb(ArrayList<AbstractQuiz> quizArrayList){

        BinaryQuizDao bqd = new BinaryQuizDao();
        MultiChoiceQuizDao mcqd = new MultiChoiceQuizDao();

        try{
            for (AbstractQuiz abstractQuiz : quizArrayList) {
                if (abstractQuiz instanceof MultiChoiceQuiz) {
                    mcqd.create((MultiChoiceQuiz) abstractQuiz);
                } else if (abstractQuiz instanceof BinaryQuiz) {
                    bqd.create((BinaryQuiz) abstractQuiz);
                }
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    private static BinaryQuiz createBinaryFromTextFile(Scanner scan){

        String question = scan.nextLine();
        String correctAnswer = scan.nextLine();
        return new BinaryQuiz(question, correctAnswer);
    }

    private static MultiChoiceQuiz createMultiQuizFromFile(Scanner scan){
        ArrayList<String> answers = new ArrayList<>();

        String question = scan.nextLine();
        String answer1 = scan.nextLine();
        String answer2 = scan.nextLine();
        String answer3 = scan.nextLine();
        String answer4 = scan.nextLine();
        String correctAnswer = scan.nextLine();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        return new MultiChoiceQuiz(question, correctAnswer, answers);
    }
}
