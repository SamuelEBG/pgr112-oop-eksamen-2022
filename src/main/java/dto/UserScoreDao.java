package main.java.dto;

import main.java.UserScore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserScoreDao extends QuizDao<UserScore> {

    @Override
    public void create(UserScore userScore) throws SQLException {

    }

    @Override
    public UserScore retrieve(UserScore userScore) throws SQLException {
        return null;
    }

    @Override
    public UserScore mapFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
