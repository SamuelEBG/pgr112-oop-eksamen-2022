package main.java;

import main.java.topics.AbstractQuiz;
import main.java.topics.BinaryQuiz;
import main.java.topics.MultiChoiceQuiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAndWriteFromTextFile {

    public static void readFromTextFile(ArrayList<AbstractQuiz> quizArrayList){
        File file = new File("src/main/resources/QuizQuestions.txt");
        try(Scanner scan = new Scanner(file)){
            while(scan.hasNextLine()){
                String topic = scan.nextLine();
                if(topic.equalsIgnoreCase("football")){
                    while(!scan.nextLine().equals("---")){
                        quizArrayList.add(createMultiQuizFromFile(scan));
                    }
                } else if(topic.equalsIgnoreCase("mma")){
                    while(!scan.nextLine().equals("---")){
                        quizArrayList.add(createBinaryFromTextFile(scan));
                    }
                }
            }
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
    }

    private static BinaryQuiz createBinaryFromTextFile(Scanner scan){

        String question = scan.nextLine();
        String correctAnswer = scan.nextLine();
        System.out.println(question + "added");
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
        System.out.println(question + "added");
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        return new MultiChoiceQuiz(question, correctAnswer, answers);
    }
}
