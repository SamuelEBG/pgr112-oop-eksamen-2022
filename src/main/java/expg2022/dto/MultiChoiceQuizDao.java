package expg2022.dto;

import expg2022.topics.MultiChoiceQuiz;

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
                        "answer1, " +
                        "answer2, " +
                        "answer3, " +
                        "answer4, " +
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

    public ArrayList<MultiChoiceQuiz> retrieveAll(){

        ArrayList<MultiChoiceQuiz> result = new ArrayList<>();
        String preparedSelect = "SELECT * FROM MultiChoiceQuiz";

        try(Connection conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(preparedSelect);

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                result.add(mapFromResultSet(resultSet));
            }
            return result;

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public MultiChoiceQuiz mapFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<String> answers = new ArrayList<>();

        String question = resultSet.getString("question");
        String answer1 = resultSet.getString("answer1");
        String answer2 = resultSet.getString("answer2");
        String answer3 = resultSet.getString("answer3");
        String answer4 = resultSet.getString("answer4");
        String correctAnswer = resultSet.getString("correctAnswer");

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        return new MultiChoiceQuiz(question, correctAnswer, answers);
    }
}
