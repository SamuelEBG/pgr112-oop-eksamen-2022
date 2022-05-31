package main.java;

import main.java.topics.AbstractQuiz;
import main.java.topics.MultiChoiceQuiz;

import java.util.ArrayList;
import java.util.Scanner;

import static main.java.ReadAndWriteFromTextFile.readFromTextFile;

public class Program {

    private ArrayList<AbstractQuiz> quizRegister;
    private boolean running = true;
    private User currentPlayer;
    private AbstractQuiz currentQuiz;

    public Program() {
        quizRegister = new ArrayList<>();
        readFromTextFile(quizRegister);
        quizMenu();
        quizRegister.stream()
                .filter(abstractQuiz -> abstractQuiz instanceof MultiChoiceQuiz)
                .map(abstractQuiz -> ((MultiChoiceQuiz) abstractQuiz)
                        .getAnswers())
                .forEach(System.out::println);
    }

    public void addQuizzesToDb(){

    }

    String menuChoicesA = """
            1 - 
            """;

    String menuChoicesB = """
            """;

    public void quizMenu(){
        Scanner input = new Scanner(System.in);
        createUser(input);
        topicMenu(input);
    }

    public void topicMenu(Scanner input){
        System.out.println("Choose what quiz you want to play");
        String topicChoices = """
                1 - American Football (Multi-choice Quiz)\s
                2 - MMA (Binary Quiz)
                """;
        System.out.println(topicChoices);
        String userChoice = input.nextLine();
        if(userChoice.equals("1")){

        }
    }

    public void createUser(Scanner input){
        System.out.println("Enter a username to begin the quiz");
        String userName = input.nextLine();
        currentPlayer = new User(userName);
    }
}
