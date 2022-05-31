package main.java.dto;

import main.java.topics.BinaryQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BinaryQuizDao extends QuizDao<BinaryQuiz>{

    @Override
    public void create(BinaryQuiz binaryQuiz) throws SQLException {
        String prepapredCreate =
                "INSERT INTO multiChoiceQuiz(" +
                        "question," +
                        "answerA, " +
                        "answerB, " +
                        "answerC, " +
                        "answerD)" +
                        "VALUES(?, ?, ?, ?, ?)";
        try(Connection conn = getConnection()){
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(prepapredCreate);


        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public BinaryQuiz retrieve(BinaryQuiz binaryQuiz) throws SQLException {
        return null;
    }

    @Override
    public BinaryQuiz mapFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
