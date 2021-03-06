package expg2022;

import expg2022.dto.BinaryQuizDao;
import expg2022.dto.MultiChoiceQuizDao;
import expg2022.dto.UserScoreDao;
import expg2022.topics.AbstractQuiz;
import expg2022.topics.BinaryQuiz;
import expg2022.topics.MultiChoiceQuiz;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

import static expg2022.textFileToDb.quizzesFromTextToDb;

public class Program {

    private User currentPlayer;
    // When running program first time run quizzesFromTextToDb, if it isn't commented
    // out it will keep adding the same quizzes to the db again.
    public Program() {
        quizzesFromTextToDb();
        Scanner input = new Scanner(System.in);
        createUser(input);
        topicMenu(input);
    }

    public void topicMenu(Scanner input){
        boolean running = true;

        String topicChoices = """
                1 - American Football (Multi-choice Quiz)\s
                2 - MMA (Binary Quiz)
                3 - Quit the program""";

        String userChoice;
        while(running){

            System.out.println("Choose what quiz you want to play \n" + topicChoices);
            userChoice = input.nextLine();

            switch (userChoice) {
                case "1" -> {
                    multiChoiceQuiz(input);
                    gameMenu(input, userChoice);
                }
                case "2" -> {
                    binaryQuiz(input);
                    gameMenu(input, userChoice);
                }
                case "3" -> {
                    System.out.println("Shutting down the program, au revoir!");
                    running = false;
                }
                default -> System.out.println("Not a recognized topic, try again.");
            }
        }
    }

    public void gameMenu(Scanner input, String topic){
        boolean running = true;

        String menuChoicesBinary = """
            1 - List highscores for a specific user
            2 - List all scores above a specific number for the MMA-quiz
            3 - Play another round of the MMA-Quiz
            4 - Quit the game""";
        String menuChoicesMultiChoice = """
            1 - List highscores for a specific user
            2 - List all scores above a specific number for the American Football-quiz
            3 - Play another round of the American Football-Quiz
            4 - Quit the game""";

        String userChoice;
        do{
            if(topic.equals("1")){
                System.out.println(menuChoicesMultiChoice);
                userChoice = input.nextLine();

                switch(userChoice){
                    case "1" -> {
                        System.out.println("Enter a username to search by");
                        String userName = input.nextLine();
                        printHighScoreByName(userName);
                    }
                    case "2" -> {
                        System.out.println("Enter a number to filter by");
                        int number = Integer.parseInt(input.nextLine());
                        printHighScoreByScore(number, "American Football");
                    }
                    case "3" -> multiChoiceQuiz(input);
                    case "4" -> {
                        System.out.println("Quitting the game!");
                        running = false;
                    }
                    default -> {
                        System.out.println("Not a recongized menu choice\n");
                        continue;
                    }
                }
            }

            if(topic.equals("2")){
                System.out.println(menuChoicesBinary);
                userChoice = input.nextLine();

                switch(userChoice){
                    case "1" -> {
                        System.out.println("Enter a username to search by");
                        String userName = input.nextLine();
                        printHighScoreByName(userName);
                    }
                    case "2" -> {
                        System.out.println("List all scores above a chosen number");
                        int number = Integer.parseInt(input.nextLine());
                        printHighScoreByScore(number, "MMA");
                    }
                    case "3" -> binaryQuiz(input);
                    case "4" -> {
                        System.out.println("Quitting the game!");
                        running = false;
                    }
                }
            }
        } while (running);
    }

    public void binaryQuiz(Scanner input){
        BinaryQuizDao bqd = new BinaryQuizDao();
        ArrayList<BinaryQuiz> currentQuizList = bqd.retrieveAll();

        Collections.shuffle(currentQuizList);
        currentQuizList = new ArrayList<>(currentQuizList.subList(0, 5));

        System.out.println(currentQuizList.size());

        String userAnswer;
        int currentScore = 0;
        int counter = currentQuizList.size();

        System.out.println(
                """
                This is a quiz with questions about MMA with yes or no questions.
                You get 1 point for each correct answer, good luck!
                """);

        for(int i = 0; i < counter; i++){
            System.out.println("Question " + (i+1) + " : "
                    + currentQuizList.get(i).getQuestion());
            userAnswer = input.nextLine();
            if(!currentQuizList.get(i).isCorrectAnswer(userAnswer)){
                System.out.println("Incorrect answer");

                if(i == 4){
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

            } else {
                currentScore++;
                System.out.println("Congratulations, that was the right answer!");

                if(i == 4){
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
        System.out.println("Your final score is " + currentScore + " of " + counter + "\n" +
                "\n" +
                "Here is the highscore for this quiz, see if you made it in the top 5!");
        printHighScore("MMA");
    }

    public void multiChoiceQuiz(Scanner input){
        MultiChoiceQuizDao mcqd = new MultiChoiceQuizDao();
        ArrayList<MultiChoiceQuiz> currentQuizList = mcqd.retrieveAll();

        Collections.shuffle(currentQuizList);
        currentQuizList = new ArrayList<>(currentQuizList.subList(0, 5));

        String userAnswer;
        int currentScore = 0;
        int counter = currentQuizList.size();

        System.out.println(
                """
                This is a quiz with questions about American Football.
                The quiz is multi-choice, answer questions with 1-4.
                You get 1 point for each correct answer, good luck!
                """);

        /*
            This will run through each quiz-object and print its questions and answers.
            When user is done it will print the highscore, if user exits the quiz
            before it ends they will not be a part of the highscore, and go to the menu instead.
        */
        for(int i = 0; i < counter; i++){
            System.out.println("Question " + (i+1) + " : "
                    + currentQuizList.get(i).getQuestion());

            for(int j = 0; j < 4; j++){
                System.out.println("Answer " + (j+1) + " : "
                        + currentQuizList.get(i).getAnswers().get(j));
            }

            int inputNumber = Integer.parseInt(input.nextLine());

            if(!currentQuizList.get(i)
                    .isCorrectAnswer(currentQuizList.get(i)
                    .getAnswers().get((inputNumber-1)))){
                System.out.println("Incorrect answer");

                if(i == 4){
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

            } else {
                currentScore++;
                System.out.println("Congratulations, that was the right answer!");

                if(i == 4){
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

        addHighscore(new UserScore(currentScore, "American Football", currentPlayer));
        System.out.println("Your final score is " + currentScore + " of " + counter + "\n" +
                "Here is the highscore for this quiz, see if you made it in the top 5!\n");
        printHighScore("American Football");
    }

    public void printHighScoreByName(String userName){
        UserScoreDao usd = new UserScoreDao();
        ArrayList<UserScore> highscore = new ArrayList<>(
                usd.retrieveByUserName(userName));

        if(highscore.isEmpty()){
            System.out.println("That user has not registered any scores, tell them to play more!");
            return;
        }
        System.out.println("Here are the highscores by user " + userName);
        highscore.stream()
                .sorted(Comparator.comparing(UserScore::getScore).reversed())
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(System.out::println);
        System.out.println(" ");
    }

    public void printHighScoreByScore(int score, String filterTopic){
        UserScoreDao usd = new UserScoreDao();
        ArrayList<UserScore> highscore = new ArrayList<>(
                usd.retrieveByScoreHigherThan(score, filterTopic));

        if(highscore.isEmpty()){
            System.out.println("""
                    No one has registered a highscore that high..
                    So go ahead and play a game and try to get on the highscore list!
                    """);
            return;
        }

        System.out.println("Here is a list of scores above " + score);
        highscore.stream()
                .sorted(Comparator.comparing(UserScore::getScore).reversed())
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(System.out::println);
        System.out.println(" ");
    }

    public void printHighScore(String filterTopic){
        UserScoreDao usd = new UserScoreDao();
        ArrayList<UserScore> highscore = new ArrayList<>(usd.retrieveAll());

        highscore.stream()
                .filter(UserScore -> UserScore.getTopic().equalsIgnoreCase(filterTopic))
                .sorted(Comparator.comparing(UserScore::getScore).reversed())
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(System.out::println);
        System.out.println(" ");
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
