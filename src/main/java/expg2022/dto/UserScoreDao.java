package expg2022.dto;

import expg2022.User;
import expg2022.UserScore;
import expg2022.topics.BinaryQuiz;

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

    public ArrayList<UserScore> retrieveByScoreHigherThan(int score, String topic){
        ArrayList<UserScore> result = new ArrayList<>();
        String preparedSelect = "SELECT * FROM userScore " +
                "WHERE topic LIKE ? " +
                "HAVING score > ? " +
                "ORDER BY score";

        try(Connection conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(preparedSelect);

            stmt.setString(1, topic);
            stmt.setInt(2, score);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet == null){
                return null;
            }

            while(resultSet.next()){
                result.add(mapFromResultSet(resultSet));
            }
            return result;

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserScore> retrieveByUserName(String userName){
        ArrayList<UserScore> result = new ArrayList<>();
        String preparedSelect = "SELECT * FROM userScore " +
                "WHERE user LIKE ?";

        try(Connection conn = getConnection()){
            PreparedStatement stmt = conn.prepareStatement(preparedSelect);

            stmt.setString(1, userName);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet == null){
                return null;
            }

            while(resultSet.next()){
                result.add(mapFromResultSet(resultSet));
            }
            return result;

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserScore> retrieveAll(){
        ArrayList<UserScore> result = new ArrayList<>();
        String preparedSelect = "SELECT * FROM userScore";

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
    public UserScore mapFromResultSet(ResultSet resultSet) throws SQLException {
        String userName = resultSet.getString("user");
        int score = resultSet.getInt("score");
        String topic = resultSet.getString("topic");

        return new UserScore(score, topic, new User(userName));
    }
}
