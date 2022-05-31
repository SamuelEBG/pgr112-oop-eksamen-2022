package expg2022.dto;

import expg2022.UserScore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserScoreDao extends QuizDao<UserScore> {

    @Override
    public void create(UserScore userScore){
        String preparedCreate = "INSERT INTO userScore(" +
                "user, " +
                "score, " +
                "topic)" +
                "VALUES(?, ?, ?)";
        try(Connection conn = getConnection()){
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(preparedCreate);

            stmt.setString(1, userScore.getUser().getUserName());
            stmt.setInt(2, userScore.getScore());
            stmt.setString(3, userScore.getTopic());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public ArrayList<UserScore> retrieveAll(String userName) throws SQLException {
        return null;
    }

    @Override
    public UserScore mapFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
