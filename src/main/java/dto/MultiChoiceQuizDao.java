package main.java.dto;

import main.java.topics.BinaryQuiz;
import main.java.topics.MultiChoiceQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MultiChoiceQuizDao extends QuizDao<MultiChoiceQuiz>{

    @Override
    public void create(MultiChoiceQuiz multiChoiceQuiz) throws SQLException {
        String prepapredCreate =
                "INSERT INTO multiChoiceQuiz(" +
                        "question," +
                        "answerA, " +
                        "answerB, " +
                        "answerC, " +
                        "answerD, " +
                        "correctAnswer)" +
                        "VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement stmt = conn.prepareStatement(prepapredCreate);

            stmt.setString(1, multiChoiceQuiz.getQuestion());
            stmt.setString(2, multiChoiceQuiz.getAnswers().get(0));
            stmt.setString(3, multiChoiceQuiz.getAnswers().get(1));
            stmt.setString(4, multiChoiceQuiz.getAnswers().get(2));
            stmt.setString(5, multiChoiceQuiz.getAnswers().get(3));
            stmt.setString(6, multiChoiceQuiz.getCorrectAnswer());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public ArrayList<MultiChoiceQuiz> retrieveAll(String quizId){

        ArrayList<MultiChoiceQuiz> result = new ArrayList<>();
        String preparedSelect = "SELECT * FROM MultiChoiceQuiz";

        try(Connection conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(preparedSelect);

            // stmt.setInt(1, Integer.parseInt(quizId));

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                result.add(mapFromResultSet(resultSet));
            }
            return result;
            /*
            if(resultSet.next()){
                return mapFromResultSet(resultSet);
            }
            else return null;

             */
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public MultiChoiceQuiz mapFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<String> answers = new ArrayList<>();

        String question = resultSet.getString("question");
        String answerA = resultSet.getString("answerA");
        String answerB = resultSet.getString("answerB");
        String answerC = resultSet.getString("answerC");
        String answerD = resultSet.getString("answerD");
        String correctAnswer = resultSet.getString("correctAnswer");

        answers.add(answerA);
        answers.add(answerB);
        answers.add(answerC);
        answers.add(answerD);

        return new MultiChoiceQuiz(question, correctAnswer, answers);
    }
}
