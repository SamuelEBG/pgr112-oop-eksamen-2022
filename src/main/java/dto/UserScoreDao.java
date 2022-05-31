package main.java.dto;

import main.java.UserScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserScoreDao extends QuizDao<UserScore> {

    @Override
    public void create(UserScore userScore) throws SQLException {

    }

    @Override
    public ArrayList<UserScore> retrieveAll(String userName) throws SQLException {
        return null;
    }

    @Override
    public UserScore mapFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
