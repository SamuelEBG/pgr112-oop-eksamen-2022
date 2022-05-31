package main.java.dto;

import main.java.topics.MultiChoiceQuiz;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MultiChoiceQuizDao extends QuizDao<MultiChoiceQuiz>{
    @Override
    public void create(MultiChoiceQuiz multiChoiceQuiz) throws SQLException {

    }

    @Override
    public MultiChoiceQuiz retrieve(MultiChoiceQuiz multiChoiceQuiz) throws SQLException {
        return null;
    }

    @Override
    public MultiChoiceQuiz mapFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
