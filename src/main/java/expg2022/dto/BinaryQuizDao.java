package expg2022.dto;

import expg2022.topics.BinaryQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BinaryQuizDao extends QuizDao<BinaryQuiz>{

    @Override
    public void create(BinaryQuiz binaryQuiz){
        String prepapredCreate =
                "INSERT INTO binaryQuiz(" +
                        "question, " +
                        "correctAnswer)" +
                        "VALUES(?, ?)";
        try(Connection conn = getConnection()){
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(prepapredCreate);

            stmt.setString(1, binaryQuiz.getQuestion());
            stmt.setString(2, binaryQuiz.getCorrectAnswer());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public ArrayList<BinaryQuiz> retrieveAll(String quizId){
        ArrayList<BinaryQuiz> result = new ArrayList<>();
        String preparedSelect = "SELECT * FROM binaryQuiz";
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
    public BinaryQuiz mapFromResultSet(ResultSet resultSet) throws SQLException {
        String question = resultSet.getString("question");
        String correctAnswer = resultSet.getString("correctAnswer");

        return new BinaryQuiz(question, correctAnswer);
    }
}
