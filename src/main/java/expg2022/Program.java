package expg2022;

import expg2022.dto.BinaryQuizDao;
import expg2022.dto.MultiChoiceQuizDao;
import expg2022.topics.AbstractQuiz;
import expg2022.topics.BinaryQuiz;
import expg2022.topics.MultiChoiceQuiz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static expg2022.ReadAndWriteFromTextFile.readFromTextFile;

public class Program {

    private ArrayList<AbstractQuiz> quizRegister;
    private boolean running = true;
    private User currentPlayer;
    private AbstractQuiz currentQuiz;

    public Program() {
        quizRegister = new ArrayList<>();
        readFromTextFile(quizRegister);
        addQuizzesToDb(quizRegister);
        //quizMenu();
       /*
        quizRegister.stream()
                .filter(abstractQuiz -> abstractQuiz instanceof MultiChoiceQuiz)
                .map(abstractQuiz -> ((MultiChoiceQuiz) abstractQuiz)
                        .getAnswers())
                .forEach(System.out::println);
        quizRegister.stream().filter(abstractQuiz -> abstractQuiz instanceof MultiChoiceQuiz)
                .map(abstractQuiz -> ((MultiChoiceQuiz) abstractQuiz)
                        .getCorrectAnswer())
                .forEach(System.out::println);

        */
    }

    public void addQuizzesToDb(ArrayList<AbstractQuiz> quizArrayList){

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
