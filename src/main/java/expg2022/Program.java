package expg2022;

import expg2022.dto.BinaryQuizDao;
import expg2022.dto.MultiChoiceQuizDao;
import expg2022.dto.UserScoreDao;
import expg2022.topics.AbstractQuiz;
import expg2022.topics.BinaryQuiz;
import expg2022.topics.MultiChoiceQuiz;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static expg2022.textFileToDb.quizzesFromTextToDb;

public class Program {

    private ArrayList<AbstractQuiz> currentQuiz;
    private User currentPlayer;

    public Program() {
        // quizzesFromTextToDb();
        Scanner input = new Scanner(System.in);
        createUser(input);
        topicMenu(input);
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

    public void topicMenu(Scanner input){
        String topicChoices = """
                1 - American Football (Multi-choice Quiz)\s
                2 - MMA (Binary Quiz)""";
        System.out.println("Choose what quiz you want to play \n" + topicChoices);
        String userChoice;
        boolean choice = true;
        while(choice){
            userChoice = input.nextLine();
            if(userChoice.equals("1")){
                binaryQuiz(input);
                gameMenu(input, userChoice);
                choice = false;
            } else if(userChoice.equals("2")){
                binaryQuiz(input);
                choice = false;
            } else System.out.println("Not a recognized topic, try again.");
        }
    }

    public void gameMenu(Scanner input, String topic){
        boolean running = true;
        String menuChoicesA = """
            1 - List highscores for a specific user
            2 - List all scores above a specific number for the MMA-quiz
            3 - Play another round of the MMA-Quiz
            4 - Quit the game
            """;
        String menuChoicesB = """
            1 -
            """;
        String choice;
        do{
            if(topic.equals("1")){

            }
            choice = input.nextLine();

        } while (running);
    }

    public void binaryQuiz(Scanner input){
        BinaryQuizDao bqd = new BinaryQuizDao();
        ArrayList<BinaryQuiz> currentQuizList = bqd.retrieveAll();

        String userAnswer;
        int currentScore = 0;
        int counter = currentQuizList.size();

        System.out.println("This is a quiz with questions about MMA with yes or no questions.");
        System.out.println("You get 1 point for each correct answer, good luck!");

        for(int i = 0; i < counter; i++){
            System.out.println("Question " + (i+1) + " : "  + currentQuizList.get(i).getQuestion());
            userAnswer = input.nextLine();

            if(!userAnswer.equals(currentQuizList.get(i).getCorrectAnswer())){
                System.out.println("Incorrect answer");
            } else {
                currentScore++;
                System.out.println("Congratulations, that was the right answer!");
                if(i == (counter-1)){
                    break;
                }
                System.out.println("Press 1 to continue or 2 to exit to the menu.");
                userAnswer = input.nextLine();

                if(userAnswer.equals("2")){
                    System.out.println("Game over.");
                    currentScore = -1;
                    break;
                } else if (userAnswer.equals("1")){
                    System.out.println("Continuing with next question.");
                } else{
                    System.out.println("Not a recognized option, game is continuing.");
                }
            }
        }

        if(currentScore == -1){
            return;
        }

        addHighscore(new UserScore(currentScore, "MMA", currentPlayer));
        System.out.println("Your final score is " + currentScore + " of " + counter);
        System.out.println("Here is the top 5 scores, see if you made it on the list!");

    }

    public void multiChoiceQuiz(Scanner input){
        MultiChoiceQuizDao mcqd = new MultiChoiceQuizDao();
        ArrayList<MultiChoiceQuiz> currentQuizList = mcqd.retrieveAll();
        System.out.println(currentQuizList.get(2).getQuestion());
        System.out.println(currentQuizList.get(2).getAnswers());
        System.out.println(currentQuizList.get(2).getCorrectAnswer());
    }

    public void addHighscore(UserScore userScore){
        UserScoreDao usd = new UserScoreDao();
        usd.create(userScore);
    }

    public void createUser(Scanner input){
        System.out.println("Enter a username to begin the quiz");
        String userName = input.nextLine();
        currentPlayer = new User(userName);
    }
}
